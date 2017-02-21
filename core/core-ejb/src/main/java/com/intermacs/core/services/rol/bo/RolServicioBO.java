 
	
package com.intermacs.core.services.rol.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*import java.util.logging.Logger;*/

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import com.intermacs.commons.annotations.CoreUnitPersistence;
import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.model.entidades.Opcion;
import com.intermacs.core.model.entidades.Rol;
import com.intermacs.core.model.entidades.RolOpcion;
import com.intermacs.core.services.GenericoServices;
import com.intermacs.core.services.rol.RolServicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.security.ContextoPrincipal;
import com.intermacs.utils.DateUtils;


@Stateless
@Local(RolServicio.class)
@Servicio(clase="RolServicioImpl",descripcion="Implementacion Logica de negocio de Roles")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class RolServicioBO extends GenericoServices implements RolServicio{ 
	
	@Inject
    private Validator validator;
	
	@Inject
	@CoreUnitPersistence
	private EntityManager em;
	
	/*@Inject
	private UsuarioServicio userServicio;*/
	
	/*@Inject
	private Logger log;*/
	
	@Resource 
	SessionContext ctx;
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	
	@Override
	public ResponseDTO guardarRol(RequestDTO request)
			throws ServicioExcepcion {//TODO: VERIFICAR CAMBIOS AL UTILIZAR UN UNICO SISTEMA DE LOGUIN
	Rol rol=(Rol) request.getObject();
	rol.setFecCambio(DateUtils.getFechaSistema());
	validateRol(rol);
	if(rol.getIdRol()==null){
		return crear(request);
	}else{
		
    	em.createQuery("delete RolOpcion where id_rol="+rol.getIdRol()).executeUpdate();    	
        ResponseDTO response=new ResponseDTO(em.merge(rol));
        return response;
	}
	}

	@Override
	public void eliminarRol(RequestDTO request) throws ServicioExcepcion {
		eliminar(request);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ResponseDTO buscarRoles(RequestDTO request)
			throws ServicioExcepcion {	
		//CriteriaBuilder builder = this.em.getCriteriaBuilder();
	      List<Predicate> predicatesList = new ArrayList<Predicate>();

	     /* String street1 = example.getCustomer().getHomeAddress().getStreet1();
	      if (street1 != null && !"".equals(street1))
	      {
	         predicatesList.add(builder.like(builder.lower(root.<String>get("street1")), '%' + street1.toLowerCase() + '%'));
	      }
	      String street2 = example.getCustomer().getHomeAddress().getStreet2();
	      if (street2 != null && !"".equals(street2))
	      {
	         predicatesList.add(builder.like(builder.lower(root.<String>get("street2")), '%' + street2.toLowerCase() + '%'));
	      }
	      String city = example.getCustomer().getHomeAddress().getCity();
	      if (city != null && !"".equals(city))
	      {
	         predicatesList.add(builder.like(builder.lower(root.<String>get("city")), '%' + city.toLowerCase() + '%'));
	      }
	      String state = example.getCustomer().getHomeAddress().getState();
	      if (state != null && !"".equals(state))
	      {
	         predicatesList.add(builder.like(builder.lower(root.<String>get("state")), '%' + state.toLowerCase() + '%'));
	      }
	      String zipcode = example.getCustomer().getHomeAddress().getZipcode();
	      if (zipcode != null && !"".equals(zipcode))
	      {
	         predicatesList.add(builder.like(builder.lower(root.<String>get("zipcode")), '%' + zipcode.toLowerCase() + '%'));
	      }*/
		
		return findWithPredicate(Rol.class,predicatesList.toArray(new Predicate[predicatesList.size()]));//buscarPorFiltroEntidad(request);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ResponseDTO consultarRol(RequestDTO request)
			throws ServicioExcepcion {		
	
		Rol rol=(Rol) request.getObject();		
		request.setParam(EParametro.ClassEntidad, Rol.class);
		request.setParam(EParametro.ID_OBJECT, rol.getIdRol());		
		
		ResponseDTO response=new ResponseDTO(buscarPorId(request));
		return response;
	}

	
    
	
	 @Override
	    @SuppressWarnings("unchecked")
		@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	    public ResponseDTO consultarOpcionesDisponiblesRol(RequestDTO request) {
			/*CriteriaBuilder cb = em.getCriteriaBuilder();
			   CriteriaQuery<Opcion> criteria = cb.createQuery(Opcion.class);*/
		//Rol rol=(Rol) request.getObject();
	    	String criteria="FROM Opcion";
			List<Opcion> lista = em.createQuery(criteria).getResultList();
	        
			
			ResponseDTO resp= consultarOpcionesRol(request);
			List<Opcion> listaOP =(List<Opcion>) resp.getParam(EParametro.ResultList);
			
	         
	         if(lista!=null && !lista.isEmpty() &&  listaOP!=null && !listaOP.isEmpty()){
	        	 for (Opcion opPO : listaOP) {
	        		 lista.remove(opPO);
	        		/* if(!listaOP.contains(opPO)){
	        			 listaTmp.add(opPO) ;
	        		 }*/
				}
	         } /*else{
	        	 if(listaOP==null || listaOP.isEmpty() && lista!=null){
	        		 listaTmp.addAll(lista);
	        		 
	        	 }
	        	 
	         }*/
	         ResponseDTO response=new ResponseDTO();
	         response.setParam(EParametro.ResultList, lista);
	         return response;
		
		}

		
	    @Override
	    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
	    public ResponseDTO consultarOpcionesRol(RequestDTO request) {
			
			 List<Opcion> listReturn=new ArrayList<Opcion>();
			 Rol rol=(Rol) request.getObject();
	        @SuppressWarnings("unchecked")
			List<RolOpcion> lista = em.createQuery("from RolOpcion where id_rol="+rol.getIdRol()).getResultList();;
	        
	        if(lista!=null && !lista.isEmpty()){
	       	 for (RolOpcion rolOpcion : lista) {
	       			 listReturn.add(rolOpcion.getOpcion());
				}
	       	 
	        } 
	        ResponseDTO response=new ResponseDTO();
	        response.setParam(EParametro.ResultList, listReturn);
	        return response;
		}
	 

		@SuppressWarnings("unchecked")
		@Override
		@TransactionAttribute(TransactionAttributeType.SUPPORTS)
		public List<Rol> consultarRolesPorID(Long... ids)
				throws ServicioExcepcion {
			Query query = em.createNamedQuery(Rol.NQ_FindByIdIn);			
		//	String params="";
			List<Long> idsLst = new ArrayList<Long>();
			for (int i = 0; i < ids.length; i++) {
				idsLst.add(ids[i]);
				
			}
			//new ArrayList<Long>(ids);
			query.setParameter("idRol", idsLst); 
			
			return query.getResultList();
		}


	 
    
	/**
     * <p>
     * Validates the given Rol variable and throws validation exceptions based on the type of error. If the error is standard
     * bean validation errors then it will throw a ConstraintValidationException with the set of the constraints violated.
     * </p>
     * <p>
     *  
     * @param rol Rol to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     * @throws ValidationException  para los camposnull que son requeridos
     */
    private void validateRol(Rol rol) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<Rol>> violations = validator.validate(rol);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
        
        if(rol.getDescripcion()==null){
        	 throw new ValidationException("Descripción  es obligatoria");
        }
        
		if (rol.getFecCambio() == null) {
			throw new ValidationException(
					" Fecha de modificación en Datos personales es obligatoria");
		} else if (rol.getNombre() == null) {
			throw new ValidationException(
					" Nombre es obligatorio");
		}
        
     
    }


    @Override
	protected ContextoPrincipal getContextoPrincipal() {
		// TODO Auto-generated method stub
		return (ContextoPrincipal) ctx.getCallerPrincipal();
	}
	
}
