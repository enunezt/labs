package com.intermacs.images.service.bo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
//import javax.validation.Validator;

import com.intermacs.commons.annotations.CoreUnitPersistence;
import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.services.GenericoServices;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.images.model.entidades.EImagen;
import com.intermacs.images.model.entidades.Imagen;
import com.intermacs.images.model.enums.ETipoEntidad;
import com.intermacs.images.service.ImagenServicio;
import com.intermacs.security.ContextoPrincipal;
@Stateless
@Local(ImagenServicio.class)
@Servicio(clase="ImagenServicioImpl",descripcion="Implementacion Logica de negocio para la gestion de imagenes")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ImagenServicioBO extends GenericoServices  implements ImagenServicio/*<Imagen>*/{ 

	@Inject
	@CoreUnitPersistence
	private EntityManager em;
	
	@Resource 
  	SessionContext ctx;
	/*@Inject
    private Validator validator;
	
	@Inject
	private Logger log;*/

	
	@Override
	public ResponseDTO consultarIdFotoUsuario(RequestDTO request) throws ServicioExcepcion {
	    Long idUsuario=(Long) request.getParam(EParametro.User);
		try {
			Map<String,Object> parameters=new HashMap<String, Object>();
			String idUserImagen=""+ETipoEntidad.USER.getId()+idUsuario.longValue();
			parameters.put("idEntidad", Long.valueOf(idUserImagen)); 
			parameters.put("tipo", EImagen.FOTO_PERFIL);

			
			request.setParam(EParametro.NamedQuery,"Imagen.findByEnte");
			request.setParam(EParametro.ParemtrosQuery,parameters);
			
			ResponseDTO resp=findWithNamedQuery(request);
			
			//Imagen.findById
			@SuppressWarnings("unchecked")
			List<Imagen> lst=(List<Imagen>) resp.getParam(EParametro.ResultList);
			Long idImagen=null;
			if(lst!=null && !lst.isEmpty()){
			    idImagen=lst.iterator().next().getIdImagen();
			}
			
			ResponseDTO retorno=new ResponseDTO();
			retorno.setParam(EParametro.Imagen, idImagen);
			//retorno.setParam(EParametro.Set,(Set<CatalogoDetalle>) resp.getParam(EParametro.ResultList)); 
			return retorno;
			
		} catch (Exception e) {
				throw new ServicioExcepcion(e);
			}
	}

	@Override
	public Imagen consultarImagen(RequestDTO request) throws ServicioExcepcion {
		try {
			//Imagen.findById
		 
			
			RequestDTO requestTmp=new RequestDTO(null);
			
			requestTmp.setParam(EParametro.ID_OBJECT ,(Long) request.getParam(EParametro.Imagen));
			requestTmp.setParam(EParametro.NamedQueryLocal ,"Imagen.findById");		
			
			Imagen img=(Imagen) buscarPorIdNamedQeryId(requestTmp);
			img.getContent();
			
		return img;
			
		}catch (Exception e) {
				throw new ServicioExcepcion(e);
			}
	}

	@Override
	public ResponseDTO consultarLstImagenes(RequestDTO request)
			throws ServicioExcepcion {
		try {
		    
		   // idEntidad Long , tipo EImagen EParametro.ImagenTipo

		    Map<String,Object> parameters=new HashMap<String, Object>();
			parameters.put("idEntidad", (Long)request.getParam(EParametro.IdEntidad));
			parameters.put("tipo", (EParametro)request.getParam(EParametro.ImagenTipo));

			
			request.setParam(EParametro.NamedQuery,"Imagen.findByEnte");
			request.setParam(EParametro.ParemtrosQuery,parameters);
			
			ResponseDTO resp=findWithNamedQuery(request);
			
			
			//Imagen.findById
			Set<Imagen> ret=null;
			@SuppressWarnings("unchecked")
			List<Imagen> lst=(List<Imagen>) resp.getParam(EParametro.ResultList);
			
			if(lst!=null && !lst.isEmpty()){				
				ret=new HashSet<Imagen>(lst);
				for (Imagen imagen : ret) {
					imagen.getContent();
				}
			}			
			ResponseDTO retorno=new ResponseDTO();
			retorno.setParam(EParametro.Set, ret);
			//retorno.setParam(EParametro.Set,(Set<CatalogoDetalle>) resp.getParam(EParametro.ResultList)); 
			return retorno;
			
		} catch (Exception e) {
				throw new ServicioExcepcion(e);
			}
	}

	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO consultarLstIdImagenes(RequestDTO request)
			throws ServicioExcepcion {
		try {
				
			
			  Map<String,Object> parameters=new HashMap<String, Object>();
				parameters.put("idEntidad", (Long)request.getParam(EParametro.IdEntidad));
				parameters.put("tipo", (EParametro)request.getParam(EParametro.ImagenTipo));

				
				request.setParam(EParametro.NamedQuery,"Imagen.findIdByEnte");
				request.setParam(EParametro.ParemtrosQuery,parameters);
				
				ResponseDTO resp=findWithNamedQuery(request);
				
				
				Set<Long> ret=null;
				@SuppressWarnings("unchecked")
				List<Long> lst=(List<Long>) resp.getParam(EParametro.ResultList);
				
				if(lst!=null && !lst.isEmpty()){				
					ret=new HashSet<Long>(lst);
					}
				ResponseDTO retorno=new ResponseDTO();
				retorno.setParam(EParametro.Set, ret);
				return retorno;
			
		} catch (Exception e) {
				throw new ServicioExcepcion(e);
			}
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	@Override
	public ResponseDTO guardarImagen(RequestDTO request)
			throws ServicioExcepcion {
		Imagen img=(Imagen) request.getObject();	
		//validateMenu(menu);
		if(img.getIdImagen()==null){
			return crear(request);
		}else{
			
	    	return actualizar(request);
		}
	}

	@Override
	protected ContextoPrincipal getContextoPrincipal() {
		
		return  (ContextoPrincipal) ctx.getCallerPrincipal();
	}

}
