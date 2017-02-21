 
	
package com.intermacs.core.lugares.facade.bo;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.lugares.facade.LugaresFacade;
import com.intermacs.core.lugares.model.entidades.Ciudad;
import com.intermacs.core.lugares.services.LugaresServicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.exceptions.ServicioFacadeExcepcion;
/**
 * Session Bean implementation class PaisFacade
 */
@Stateless
@Local(LugaresFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@ServicioFacade(clase="PaisFacadeImpl",descripcion="Centraliza la logica de negocio para la entidad Pais")
public class LugaresFacadeBO implements LugaresFacade{

	@Inject
	private LugaresServicio serviceLugar;	
	
  	/**
     * Default constructor. 
     */
    public LugaresFacadeBO() {
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarPaises(co.edu.unitropico.common.dtos.RequestDTO)
	 */
	@Override
	public ResponseDTO consultarPaises(RequestDTO request)
		throws ServicioFacadeExcepcion {
	    try {
		  return serviceLugar.consultarPaises(request);
	    } catch (ServicioExcepcion e) {
		throw new ServicioFacadeExcepcion(e);
	    }
	}

	/* (non-Javadoc)
	 * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarDepartamentos(co.edu.unitropico.common.dtos.RequestDTO)
	 */
	@Override
	public ResponseDTO consultarDepartamentos(RequestDTO request)
		throws ServicioFacadeExcepcion {
	    try {
		  return serviceLugar.consultarDepartamentos(request);
	    } catch (ServicioExcepcion e) {
		throw new ServicioFacadeExcepcion(e);
	    }
	}

	/* (non-Javadoc)
	 * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarCiudades(co.edu.unitropico.common.dtos.RequestDTO)
	 */
	@Override
	public ResponseDTO consultarCiudades(RequestDTO request)
		throws ServicioFacadeExcepcion {
	    try {
		  return  serviceLugar.consultarCiudades(request);
	    } catch (ServicioExcepcion e) {
		throw new ServicioFacadeExcepcion(e);
	    }
	}

	/* (non-Javadoc)
	 * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarComunas(co.edu.unitropico.common.dtos.RequestDTO)
	 */
	@Override
	public ResponseDTO consultarComunas(RequestDTO request)
		throws ServicioFacadeExcepcion {
	    try {
		  return serviceLugar.consultarComunas(request);
	    } catch (ServicioExcepcion e) {
		throw new ServicioFacadeExcepcion(e);
	    }
	}

	/* (non-Javadoc)
	 * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarBarrios(co.edu.unitropico.common.dtos.RequestDTO)
	 */
	@Override
	public ResponseDTO consultarBarrios(RequestDTO request)
		throws ServicioFacadeExcepcion {
	    try {
		  return serviceLugar.consultarBarrios(request);
	    } catch (ServicioExcepcion e) {
		throw new ServicioFacadeExcepcion(e);
	    }
	  
	}

	/* (non-Javadoc)
	 * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarDepartamento(co.edu.unitropico.common.dtos.RequestDTO)
	 */
	@Override
	public ResponseDTO consultarDepartamento(RequestDTO request)
			throws ServicioFacadeExcepcion {
		    try {
			  return serviceLugar.consultarDepartamento(request);
		    } catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		    }
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Ciudad> getCiudades(String aTextoDigitado)
			throws ServicioFacadeExcepcion {
		    try {
			  return serviceLugar.getCiudades(aTextoDigitado);
		    } catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		    }
	}

	
	
}