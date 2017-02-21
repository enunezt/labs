package com.intermacs.core.facade.bo;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.facade.RolFacade;
import com.intermacs.core.services.rol.RolServicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.exceptions.ServicioFacadeExcepcion;



/**
 * Session Bean implementation class RolFacade
 */
@Stateless
@Local(RolFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@ServicioFacade(clase="RolFacadeImpl",descripcion="Centraliza la logica de negocio para la entidad Rol")
public class RolFacadeBO implements RolFacade{

@Inject
	private RolServicio serviceRol;	
	
  	/**
     * Default constructor. 
     */
    public RolFacadeBO() {
        // TODO Auto-generated constructor stub
    }

	
	@Override	
	 /**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO consultarOpcionesDisponiblesRol(RequestDTO request)
				throws ServicioFacadeExcepcion {
			try {
				return serviceRol.consultarOpcionesDisponiblesRol(request);
			} catch (ServicioExcepcion e) {
				throw new ServicioFacadeExcepcion(e);
			} catch (Exception e) {
				throw new ServicioFacadeExcepcion(e);
			}
		}
	@Override
	 /**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO consultarOpcionesRol(RequestDTO request) throws ServicioFacadeExcepcion {
			try {
				return serviceRol.consultarOpcionesRol(request);
			} catch (ServicioExcepcion e) {
				throw new ServicioFacadeExcepcion(e);
			} catch (Exception e) {
				throw new ServicioFacadeExcepcion(e);
			}
		}


	@Override
	public ResponseDTO guardarRol(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceRol.guardarRol(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}


	@Override
	public void eliminarRol(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			serviceRol.eliminarRol(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
		
	}


	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO buscarRoles(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceRol.buscarRoles(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}


	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO consultarRol(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceRol.consultarRol(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

	
}
