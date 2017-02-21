package com.intermacs.core.services.users.bo;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import com.intermacs.commons.annotations.CoreUnitPersistence;
import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.enums.EParametrosAdmin;
import com.intermacs.core.model.entidades.Menu;
import com.intermacs.core.model.entidades.Rol;
import com.intermacs.core.model.entidades.RolUsuario;
import com.intermacs.core.model.entidades.Usuario;
import com.intermacs.core.notificaciones.model.dto.NotificacionesDTO;
import com.intermacs.core.notificaciones.services.NotificacionesService;
import com.intermacs.core.services.GenericoServices;
import com.intermacs.core.services.users.UsuarioServicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.images.model.entidades.Imagen;
import com.intermacs.images.service.ImagenServicio;
import com.intermacs.security.ContextoPrincipal;
import com.intermacs.utils.DateUtils;
import com.intermacs.utils.StringUtils;
import com.intermacs.utils.UtilCrypt;




@Stateless
@Local(UsuarioServicio.class)
@Servicio(clase="UsuarioServicioImpl",descripcion="Implementacion Logica de negocio de Usuarios")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UsuarioServicioBO extends GenericoServices implements UsuarioServicio/*<Imagen>*/{ 
	private static long ID_PLANTILLA_CORREO_RECUPERACION=1;
	
	@Inject
	private ImagenServicio imagenServicio;
	
	@Inject
    private Validator validator;
	
	@Inject
	@CoreUnitPersistence
	private EntityManager em;
	
	@Inject
	private Logger log;
	
	@EJB 
	private NotificacionesService notificacionesService;
	
	
	@Resource 
	SessionContext ctx;
	
	

	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO cargarFotoUsuario(RequestDTO request) throws ServicioExcepcion {
		try {
			Usuario usuario=(Usuario) request.getObject();
			if(usuario.getFotoPerfil()!=null){
			  //  usuario.getFotoPerfil().getIdImagen()  idImagen Long en EParametro.Imagen
			    
			    RequestDTO req=new RequestDTO();
			    req.setParam(EParametro.Imagen,usuario.getFotoPerfil().getIdImagen());
			    
			Imagen img=imagenServicio.consultarImagen(req);			
			usuario.setFotoPerfil(img);
			
			}

			  ResponseDTO response=new ResponseDTO(usuario);
			  request.setObject(usuario);   
			         return response;
			
		} catch (ServicioExcepcion e) {
			throw new ServicioExcepcion(e);
		} catch (Exception e) {
				throw new ServicioExcepcion(e);
			}
	}

	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO consultarRolesDisponibles(RequestDTO usuario)
			throws ServicioExcepcion {
	    Usuario user=(Usuario) usuario.getObject();
	   // EParametrosAdmin.RolesDisponiblesUser;
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		   CriteriaQuery<Rol> criteria = cb.createQuery(Rol.class);
		 Root<Rol> rol = criteria.from(Rol.class);
		 criteria.select(rol);;
      
		List<Rol> lista = em.createQuery(criteria).getResultList();
		
		Collection<RolUsuario> _usrRoles=user.getRolUsuarioCollection();
		Iterator<RolUsuario> _itUsrRol=_usrRoles.iterator();
		while (_itUsrRol.hasNext()) {
			RolUsuario rolUsuario = (RolUsuario) _itUsrRol.next();
			
			lista.remove(rolUsuario.getRol());
		}
		
		/*Set<Rol> listaOP = user.getRolUsuarioCollection();      
      if(lista!=null && !lista.isEmpty() &&  listaOP!=null && !listaOP.isEmpty()){
     	 for (Rol opPO : listaOP) {
     		 lista.remove(opPO);
			}
      }   */    
      ResponseDTO response=new ResponseDTO();
      response.setParam(EParametrosAdmin.RolesDisponiblesUser,lista);
      return response;
	}

	@Override
	public ResponseDTO construirMenuUsuario(RequestDTO usuario)
			throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ResponseDTO consultarUsuario(RequestDTO request)
			throws ServicioExcepcion {
		Usuario user=(Usuario) request.getObject();		
		
		String passEncr;
		try {
			passEncr = UtilCrypt.encriptar(user.getClaveLegible());
		} catch (Exception e) {
		throw new ServicioExcepcion(e);
		}
		
		user.setClave(passEncr);		
		
		Query query = em.createNamedQuery(Usuario.NQ_findByPassAndUser);			
		query.setParameter("usuario", user.getUsuario()); 
		query.setParameter("passEncrypt", passEncr); 
      
      try{
         user = (Usuario) query.getSingleResult();  
		 //TODO: AQUI VA EL FILTRO QUE SE DEBE HACER POR SUBSISTEMA
         Collection<RolUsuario> lstRoles= user.getRolUsuarioCollection();
         
         for (Iterator iterator = lstRoles.iterator(); iterator.hasNext();) {
			RolUsuario rolUsuario = (RolUsuario) iterator.next();
			System.out.println(rolUsuario.getRol().getNombre());
			
		}
         
         
         
      }catch(NoResultException e){
    	  throw  new ServicioExcepcion("Usuario o clave incorrecta");
      }catch(NonUniqueResultException ex){
    	  throw  new ServicioExcepcion(ex);
      }  
         ResponseDTO response=new ResponseDTO();
         response.setObject(user);
         return response;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ResponseDTO consultarMenu(RequestDTO request)
			throws ServicioExcepcion {		
		Map<Long, Menu> retornoMap=null;		
        Query q = em.createQuery("from Menu ");     
         
         @SuppressWarnings("unchecked")
		List<Menu> lista = q.getResultList();
         if(lista!=null && !lista.isEmpty()){
             retornoMap=new HashMap<Long, Menu>();
        	 
        	 for (Menu menu : lista) {
        		 
        	     retornoMap.put(menu.getIdMenu(), menu);
			}
        	 
         }
         ResponseDTO response=new ResponseDTO();
         response.setParam(EParametro.Map, retornoMap);
         return response;
		
		
	}

	@Override
	public ResponseDTO crearUsuario(RequestDTO request)
			throws ServicioExcepcion {
		ResponseDTO response=null;
		Usuario user=(Usuario) request.getObject();
		user.setFecCambio(DateUtils.getFechaSistema());
		user.setFecRegistro(DateUtils.getFechaSistema());		
		validateUsuario(user);	
		
		String passEncr;
		try {
			passEncr = UtilCrypt.encriptar(user.getClaveLegible());
		} catch (Exception e) {
		throw new ServicioExcepcion(e);
		}
		user.setClave(passEncr);
		
		try{
			response=	crear(request);
		log.info("IP:"+getContextoPrincipal().getIpUsuario()+" Usuario "+getUserLogin().getUsuario() +" registra usuario: "+
				user.getUsuario()+ " ");
		}catch(Exception e){
			log.log(Level.SEVERE, "Error en servicio crearUsuario", e);
			throw new ServicioExcepcion(e);
		}
		return  response;
	}

	@Override
	public ResponseDTO actualizarUsuario(RequestDTO request)
			throws ServicioExcepcion {
		ResponseDTO response=null;
		Usuario user=(Usuario) request.getObject();
		user.setFecCambio(DateUtils.getFechaSistema());		
		validateUsuario(user);
		
		String passEncr=user.getClave();
		try {
			if(!StringUtils.isNullOrEmpty(user.getClaveLegible()))
			passEncr = UtilCrypt.encriptar(user.getClaveLegible());
		} catch (Exception e) {
		throw new ServicioExcepcion(e);
		}
		
		user.setClave(passEncr);
		try {
			response =actualizar(request);
			log.info("IP:"+getContextoPrincipal().getIpUsuario()+" Usuario "+getUserLogin().getUsuario()  +" actualiza usuario: "+
					user.getUsuario()+ " ");
		} catch (Exception e) {
		throw new ServicioExcepcion(e);
		}
		
		return response;
		
	}

	@Override
	public void eliminarUsuario(RequestDTO request) throws ServicioExcepcion {
		eliminar(request);
		
	}

	
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ResponseDTO buscarUsuarios(RequestDTO request)
			throws ServicioExcepcion {
		
		Usuario user=(Usuario) request.getObject();
		ResponseDTO resp=new ResponseDTO();
		
		if(user!=null && !StringUtils.isNullOrEmpty(user.getNumDocumento())){
			
			Map<String,Object> parameters=new HashMap<String, Object>();
			parameters.put("numDocumento", user.getNumDocumento());
			
			request.setParam(EParametro.NamedQuery, Usuario.NQ_findByNumDocumento);
			request.setParam(EParametro.ParemtrosQuery,parameters);
			
			resp=findWithNamedQuery(request); 
			
		}else{
		
		resp=buscarTodos(request);
		/*	  List<Usuario> list=(List<Usuario>) resp.getResultList();
	if(list!=null){
		
		for (Usuario usuario : list) {
			usuario.getRoles();
		}
	}*/}
		  
		return resp; 
	}

	@Override
	public ResponseDTO recuperarDatoLoguinUsuario(RequestDTO request)
			throws ServicioExcepcion {
		ResponseDTO response=new ResponseDTO();
		Usuario usuario=(Usuario) request.getObject();
		
		if (usuario.getNumDocumento() == null) {
			throw new ValidationException(
					" Número de Documento es obligatorio");
		} else if (usuario.getTipoDocumento() == null) {
			throw new ValidationException(
					" Tipo de Documento es obligatorio");
			} else if (StringUtils.isNullOrEmpty(usuario.getEmail())) {
				throw new ValidationException(
						" Email es obligatorio");
				} 
		String mailConsulta=usuario.getEmail();
		
		ResponseDTO userResp=consultarUsuarioPorNumDocumento(request); 
		//String mailPersona=null;		
		Usuario usResp=null;
		String numDocUsuario=null;	
		
		//verifico si exite el usuario
		if(userResp.isExisteResultado()){			
		usResp=(Usuario) userResp.getObject();
		numDocUsuario=usResp.getNumDocumento().toString();
			
		}else{
			/*RequestDTO reqPers=new RequestDTO();
			Persona persona=new Persona();
			
			persona.setTipoDocumento(usuario.getTipoDocumento());
			persona.setNumDocumento(usuario.getNumDocumento());			
			reqPers.setObject(persona);
			
			ResponseDTO perResp=personaServicio.consultarPersonaPorNumDocumento(reqPers);
			if(perResp.isExisteResultado()){
				Persona respPersona=(Persona) perResp.getObject();
				mailPersona=respPersona.getEmail();
				usResp=	respPersona.getUsuario();
				numDocUsuario=usResp.getNumDocumento().toString();
			}else{*/
				throw new ServicioExcepcion("Datos no regitrados en el sistema, consulte con el administrador");
			/*}*/
			
		}
		
		//Aqui ya existe usuario por doc y num de documento
		
		
		String mailUsuario=usResp.getEmail();
		
		if(/*(mailPersona!=null && mailPersona.equalsIgnoreCase(mailConsulta))|| */
				(mailUsuario!=null && mailUsuario.equalsIgnoreCase(mailConsulta)) ){
		
			//actualizo los datos del usuario			
			RequestDTO reqUser=new RequestDTO();
			
			usResp.setClaveLegible(numDocUsuario); 
			reqUser.setObject(usResp); 
			actualizarUsuario(reqUser);
			
			//envío la notificación			
			String asunto="Recuperación de datos de Ingreso";
			
			StringBuffer contenido=new StringBuffer("");
			contenido.append("USUARIO: "+usResp.getUsuario()+"<br/>")
			.append("CONTRASEÑA: "+numDocUsuario+"<br/>");
			//TODO construir notificacion ID_PLANTILLA_CORREO_RECUPERACION
			
			NotificacionesDTO notificar= new NotificacionesDTO(asunto, contenido.toString(),
					ID_PLANTILLA_CORREO_RECUPERACION, mailUsuario, "admin@apps.com","text/html");
			//notificar.addCorreoDestino(mailPersona);
			
			RequestDTO reqMail=new RequestDTO();
			
			reqMail.setObject(notificar); 
			
			response=notificacionesService.enviarEmail(reqMail);
			
			log.info("IP:"+getContextoPrincipal().getIpUsuario()+" Usuario "+usResp.getNombres() +" "+usResp.getApellido1()+" "+usResp.getApellido2()+" CC: "+numDocUsuario+ " solicita recuperación datos loguin. ");
			
		}else{
			
			throw new ServicioExcepcion("El email no corresponde con el regitrado en el sistema, consulte con el administrador");
			
		}
		
	return response;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ResponseDTO consultarUsuarioPorNumDocumento(RequestDTO request)
			throws ServicioExcepcion {
		ResponseDTO response=new ResponseDTO();
		try {
		Usuario usuario=(Usuario) request.getObject();
		
		if (usuario.getNumDocumento() == null) {
			throw new ValidationException(
					" Número de Documento es obligatorio");
		} else if (usuario.getTipoDocumento() == null) {
			throw new ValidationException(
					" Tipo de Documento es obligatorio");
			} 
		
		Query query = getEntityManager().createNamedQuery(Usuario.NQ_findTipoDocNumDoc);
		//idCatalogoDetalle , numDocumento

		query.setParameter(Usuario.PAR_IDE_CATALOGO_DET, usuario.getTipoDocumento().getIdCatalogoDet());
		query.setParameter(Usuario.PAR_NUM_DOCUMENTO, usuario.getNumDocumento());
		
		Usuario user=(Usuario) query.getSingleResult();
		
		response.setObject(user);
		response.setExisteResultado(user!=null); 
		
		
		}
		catch (NoResultException ex) {
			log.log(Level.SEVERE, "Error en consultarUsuarioPorNumDocumento :", ex.getMessage());			
		}catch (Exception e) {
			log.log(Level.SEVERE, "Error en consultarUsuarioPorNumDocumento", e);
			throw new ServicioExcepcion(e);
		}
		
		//NoResultException
		return response;
	    
	}
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Usuario consultarUsuario(Long idUsuario)
			throws ServicioExcepcion {
		Usuario _user=null;
		try {
		
		if (idUsuario == null) {
			throw new ValidationException(
					" Id usuario es obligatorio");
		} 
		
				
		_user=getEntityManager().find(Usuario.class, idUsuario);
		
		}
		catch (NoResultException ex) {
			log.log(Level.SEVERE, "Error en consultarUsuarioPorNumDocumento :", ex.getMessage());			
		}catch (Exception e) {
			log.log(Level.SEVERE, "Error en consultarUsuarioPorNumDocumento", e);
			throw new ServicioExcepcion(e);
		}
		
		//NoResultException
		return _user;
	    
	}
	
	 /**
	     * se le debe pasar el paraemtro EParametro.EntLocal en el argumento entidad
	     */
	/*    @SuppressWarnings("unchecked")
	    @Override
	    public ResponseDTO buscarTodos(RequestDTO clazz) throws ServicioExcepcion{
		//List<Object>		
		Usuario user=(Usuario) clazz.getobject();
		ResponseDTO response=new ResponseDTO();
		Class<?> clzz=user.getClass();
	        String namedQuery="FROM "+clzz.getSimpleName()+" ";  
	        Query query = getEntityManager().createQuery(namedQuery);
	        List<Object> list=verificarPaginacion(query,clazz);
		response.setParam(EParametro.ResultList,list);	
	        return response;
	      
	    }*/
	
	
	/**
     * <p>
     * Valida la variable usuario dado y lanza excepciones de validación en función del tipo de error. Si 
     * el error es de  errores de validación del bean estándar entonces lanzará una ConstraintValidationException 
     * con el conjunto de las restricciones violadas.
     * </p>
     * <p>
     * 
     * @param user Usuario to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     * @throws ValidationException para los camposnull que son requeridos
     */
    private void validateUsuario(Usuario user) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<Usuario>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException((Set<ConstraintViolation<?>>) new HashSet<ConstraintViolation<?>>(violations));
        }
        
        if(user.getFecCambio()==null){
        	 throw new ValidationException("Fecha de modificación es obligatoria");
        }else if(user.getFecRegistro()==null){
       	 throw new ValidationException("Fecha de registro es obligatoria");
       }else if(user.getUsuario()==null){
          	 throw new ValidationException("Usuario es obligatorio");
          }else if(StringUtils.isNullOrEmpty(user.getClave()) && StringUtils.isNullOrEmpty(user.getClaveLegible())){
        	 throw new ValidationException("Contraseña es obligatoria");
        }
        
      
        
    }

	@Override
	public EntityManager getEntityManager() {
			return em;
	}

	@Override
	protected ContextoPrincipal getContextoPrincipal() {
		// TODO Auto-generated method stub
		return (ContextoPrincipal) ctx.getCallerPrincipal();
	}


}
