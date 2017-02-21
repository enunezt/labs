package com.intermacs.core.services.menu.bo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import com.intermacs.commons.annotations.CoreUnitPersistence;
import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.model.entidades.Menu;
import com.intermacs.core.model.entidades.Opcion;
import com.intermacs.core.services.GenericoServices;
import com.intermacs.core.services.menu.MenuOpcionesServicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.security.ContextoPrincipal;

@Stateless
@Local(MenuOpcionesServicio.class)
@Servicio(clase="MenuOpcionesImpl",descripcion="Implementacion Logica de negocio de Menu y Opciones")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
//@PermitAll
public class MenuOpcionesServicioBO extends GenericoServices implements MenuOpcionesServicio/*<Imagen>*/{ 
	
	@Inject
    private Validator validator;
	@Inject
	@CoreUnitPersistence
	private EntityManager em;
	
	@Resource 
	SessionContext ctx;
	
	

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
	public EntityManager getEntityManager() {
			return em;
	}




	@Override
	public ResponseDTO guardarMenu(RequestDTO request) throws ServicioExcepcion {
		Menu menu=(Menu) request.getObject();	
		
		if(menu.getIdMenu()==null){
			addDatosAuditoriaCrear(request);
			validateMenu(menu);
			return crear(request);
		}else{
			addDatosAuditoriaModificar(request);
			validateMenu(menu);
	    	return actualizar(request);
		}
	}




	@Override
	public ResponseDTO guardarOpcion(RequestDTO request)
			throws ServicioExcepcion {
		Opcion opcion=(Opcion) request.getObject();	
		validateOpcion(opcion);
		if(opcion.getIdOpcion()==null){
			return crear(request);
		}else{
			
	    	return actualizar(request);
		}
	}




	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ResponseDTO consultarOpciones(RequestDTO request)
			throws ServicioExcepcion {
		  Query q = em.createQuery("from Opcion ");     
	         
	         @SuppressWarnings("unchecked")
			List<Opcion> lista = q.getResultList();
	      	         
		return new ResponseDTO(lista);
	}

	
	
	
	

	
		
	
	/**
     * <p>
     * Valida la variable usuario dado y lanza excepciones de validación en función del tipo de error. Si 
     * el error es de  errores de validación del bean estándar entonces lanzará una ConstraintValidationException 
     * con el conjunto de las restricciones violadas.
     * </p>
     * <p>
     * 
     * @param menu to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     * @throws ValidationException para los camposnull que son requeridos
     */
    private void validateMenu(Menu menu) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<Menu>> violations = validator.validate(menu);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException((Set<ConstraintViolation<?>>) new HashSet<ConstraintViolation<?>>(violations));
        }
        
    /*    if(menu.getFecCambio()==null){
        	 throw new ValidationException("Fecha de modificación es obligatoria");
        }else if(menu.getFecRegistro()==null){
       	 throw new ValidationException("Fecha de registro es obligatoria");
       }else if(menu.getUsuarioCambio()==null){
          	 throw new ValidationException("Usuario Cambio es obligatorio");
          }
        
      */
        
    }
    
    
    /**
     * <p>
     * Valida la variable usuario dado y lanza excepciones de validación en función del tipo de error. Si 
     * el error es de  errores de validación del bean estándar entonces lanzará una ConstraintValidationException 
     * con el conjunto de las restricciones violadas.
     * </p>
     * <p>
     * 
     * @param menu to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     * @throws ValidationException para los camposnull que son requeridos
     */
    private void validateOpcion(Opcion opcion) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<Opcion>> violations = validator.validate(opcion);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException((Set<ConstraintViolation<?>>) new HashSet<ConstraintViolation<?>>(violations));
        }
        
    /*    if(menu.getFecCambio()==null){
        	 throw new ValidationException("Fecha de modificación es obligatoria");
        }else if(menu.getFecRegistro()==null){
       	 throw new ValidationException("Fecha de registro es obligatoria");
       }else if(menu.getUsuarioCambio()==null){
          	 throw new ValidationException("Usuario Cambio es obligatorio");
          }
        
      */
        
    }


	@Override
	protected ContextoPrincipal getContextoPrincipal() {
		// TODO Auto-generated method stub
		return (ContextoPrincipal) ctx.getCallerPrincipal();
	}

}
