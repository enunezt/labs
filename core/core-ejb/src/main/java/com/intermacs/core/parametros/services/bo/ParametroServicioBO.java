 
	
package com.intermacs.core.parametros.services.bo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import com.intermacs.commons.annotations.CoreUnitPersistence;
import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.parametros.model.entidades.Parametro;
import com.intermacs.core.parametros.model.entidades.ParametroDet;
import com.intermacs.core.parametros.services.ParametroServicio;
import com.intermacs.core.services.GenericoServices;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.security.ContextoPrincipal;

/**\verbatim**/
@Stateless
@Local(ParametroServicio.class)
@Servicio(clase="ParametroServicioImpl",descripcion="Implementacion Logica de negocio de Parametros")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
/**\endverbatim**/
public class ParametroServicioBO extends GenericoServices implements ParametroServicio{ 

@Inject
    private Validator validator;
	
//@PersistenceContext(unitName="primary_core")
	@Inject 
	@CoreUnitPersistence
	private EntityManager em;
	@Resource 
	SessionContext ctx;
	
	@Inject
	private Logger log;

	@Override
	public ResponseDTO guardarParametro(RequestDTO request)
			throws ServicioExcepcion {
		
		Parametro _Parametro=(Parametro) request.getObject();
		
		if(_Parametro.getIdParametro()==null){
			return crearParametro(request);
		}else{			
			return actualizarParametro(request);
		}
	
	}
	
	//@Override
	public ResponseDTO guardarParametroDetalle(RequestDTO request)
			throws ServicioExcepcion {
		
		ParametroDet _Parametro=(ParametroDet) request.getObject();
		
		if(_Parametro.getIdParametroDet()==null){

			ResponseDTO response=null;
			//validateParametro(_Parametro);
			try{
				response=	crear(request);
			log.info("IP:"+getContextoPrincipal().getIpUsuario()+" Usuario "+getUserLogin().getUsuario() +" crea ParametroDetalle: "+
					_Parametro.getIdParametroDet());
			}catch(Exception e){
				log.log(Level.SEVERE, "Error en servicio crearParametro", e);
				throw new ServicioExcepcion(e);
			}
			return  response;
			
			
			
			
		}else{	
			ResponseDTO response=null;
			try{
				response=	actualizar(request);
			log.info("IP:"+getContextoPrincipal().getIpUsuario()+" Usuario "+getUserLogin().getUsuario() +" actualiza ParametroDetalle: "+
					_Parametro.getIdParametroDet());
			}catch(Exception e){
				log.log(Level.SEVERE, "Error en servicio actualizarParametro", e);
				throw new ServicioExcepcion(e);
			}
			return  response;
		}
	
	}
	
	@Override
	public ResponseDTO crearParametro(RequestDTO request)
			throws ServicioExcepcion {
		ResponseDTO response=null;
		Parametro _Parametro=(Parametro) request.getObject();
		validateParametro(_Parametro);
		try{
			response=	crear(request);
		log.info("IP:"+getContextoPrincipal().getIpUsuario()+" Usuario "+getUserLogin().getUsuario() +" crea Parametro: "+
				_Parametro.getIdParametro());
		}catch(Exception e){
			log.log(Level.SEVERE, "Error en servicio crearParametro", e);
			throw new ServicioExcepcion(e);
		}
		return  response;
	}
	
	@Override
	public ResponseDTO actualizarParametro(RequestDTO request)
			throws ServicioExcepcion {
		ResponseDTO response=null;
		Parametro _Parametro=(Parametro) request.getObject();
		validateParametro(_Parametro);	
		try{
			response=	actualizar(request);
		log.info("IP:"+getContextoPrincipal().getIpUsuario()+" Usuario "+getUserLogin().getUsuario() +" actualiza Parametro: "+
				_Parametro.getIdParametro());
		}catch(Exception e){
			log.log(Level.SEVERE, "Error en servicio actualizarParametro", e);
			throw new ServicioExcepcion(e);
		}
		return  response;
	}
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ResponseDTO consultarLstParametrosDet(RequestDTO request)
			throws ServicioExcepcion {
		// TODO Auto-generated method stub
	Long _IdParametro=	(Long) request.getParam(EParametro.ID_OBJECT);
	ResponseDTO response=new ResponseDTO();
	if(_IdParametro!=null){
		request.setParam(EParametro.ClassEntidad, Parametro.class);
		Parametro _param=	(Parametro) buscarPorId(request);
	
	Set<ParametroDet>  _setParamsDet=(Set<ParametroDet>) _param.getParametroDetCollection();
	if(_setParamsDet!=null){
		List<ParametroDet> _lst=new ArrayList<ParametroDet>(_setParamsDet);
		 Collections.sort(_lst);//orden ascendente por id
		response.setParam(EParametro.ResultList,_lst);
	}
	}else{
		/*ParametroDet _ParametroDet=(ParametroDet) request.getObject();
		findAllEntitiesOrderedBy(ParametroDet.class, "idParametrosDet", true);*/
	}
	
		 
		return response;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ParametroDet consultarParametroDet(RequestDTO request)
			throws ServicioExcepcion {
		Long _IdParametro=	(Long) request.getParam(EParametro.ID_OBJECT);
		ResponseDTO response=new ResponseDTO();
		if(_IdParametro!=null){
			request.setParam(EParametro.ClassEntidad, ParametroDet.class);			
			response.setObject(buscarPorId(request));
		}else{
			/*ParametroDet _ParametroDet=(ParametroDet) request.getObject();
			findAllEntitiesOrderedBy(ParametroDet.class, "idParametrosDet", true);*/
		}
		return null;
	}

	

/**
     * <p>
     * Valida la variable _Parametro dada y lanza excepciones de validación en función del tipo de error. Si 
     * el error es de  errores de validación del bean estándar entonces lanzará una ConstraintValidationException 
     * con el conjunto de las restricciones violadas.
     * </p>
     * <p>
     * 
     * @param _Parametro Parametro to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     * @throws ValidationException para los campos null que son requeridos
     */
    private void validateParametro(Parametro _Parametro) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<Parametro>> violations = validator.validate(_Parametro);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException((Set<ConstraintViolation<?>>) new HashSet<ConstraintViolation<?>>(violations));
        }
        //TODO: Implementar velidaciones. ejemplo:
      /*  if(_Parametro.getFecCambio()==null){
        	 throw new ValidationException("Fecha de modificación es obligatoria");
        }*/
      
        
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
