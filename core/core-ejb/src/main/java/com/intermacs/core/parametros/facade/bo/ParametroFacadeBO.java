package com.intermacs.core.parametros.facade.bo;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.parametros.facade.ParametroFacade;
import com.intermacs.core.parametros.model.entidades.ParametroDet;
import com.intermacs.core.parametros.services.ParametroServicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.exceptions.ServicioFacadeExcepcion;


/**
 * Session Bean implementation class OpcionFacade
 */
@Stateless
@Local(ParametroFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@ServicioFacade(clase="ParametroFacadeImpl",descripcion="Centraliza la logica de negocio para las Parametroes")
public class ParametroFacadeBO implements ParametroFacade{


	@Inject
	private ParametroServicio serviceParametro;

	/**
     * Default constructor. 
     */
    public ParametroFacadeBO() {
    }

	



	@Override
	public ResponseDTO consultarLstParametrosDet(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceParametro.consultarLstParametrosDet(request);	
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}



	@Override
	public ResponseDTO guardarParametro(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceParametro.guardarParametro(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}
	
	@Override
	public ResponseDTO guardarParametroDetalle(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceParametro.guardarParametroDetalle(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}



	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	
	public ParametroDet consultarParametroDet(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceParametro.consultarParametroDet(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

}
