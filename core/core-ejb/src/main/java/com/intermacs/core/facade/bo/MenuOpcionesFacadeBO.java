package com.intermacs.core.facade.bo;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.facade.MenuOpcionesFacade;
import com.intermacs.core.services.menu.MenuOpcionesServicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.exceptions.ServicioFacadeExcepcion;

/**
 * Session Bean implementation class PerfilFacade
 */
@Stateless
@Local(MenuOpcionesFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@ServicioFacade(clase="MenuOpcionesFacadeImpl",descripcion="Centraliza la logica de negocio para la entidad Menu y Opcion")
public class MenuOpcionesFacadeBO implements MenuOpcionesFacade{

@Inject
	private MenuOpcionesServicio serviceMeuOpst;	
	
  	/**
     * Default constructor. 
     */
    public MenuOpcionesFacadeBO() {
        // TODO Auto-generated constructor stub
    }

	public ResponseDTO guardarMenu(RequestDTO request) throws ServicioFacadeExcepcion {
		try {//serviceMeuOpst
			return serviceMeuOpst.guardarMenu(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

	public ResponseDTO guardarOpcion(RequestDTO request)
			throws ServicioFacadeExcepcion {
	try {//serviceMeuOpst
		return serviceMeuOpst.guardarOpcion(request);
	} catch (ServicioExcepcion e) {
		throw new ServicioFacadeExcepcion(e);
	} catch (Exception e) {
		throw new ServicioFacadeExcepcion(e);
	}
	}

	public ResponseDTO consultarMenu(RequestDTO request)
			throws ServicioFacadeExcepcion {
	try {
		return serviceMeuOpst.consultarMenu(request);
	} catch (ServicioExcepcion e) {
		throw new ServicioFacadeExcepcion(e);
	} catch (Exception e) {
		throw new ServicioFacadeExcepcion(e);
	}
	}

	public ResponseDTO consultarOpciones(RequestDTO request)
			throws ServicioFacadeExcepcion {
	try {
		return serviceMeuOpst.consultarOpciones(request);
	} catch (ServicioExcepcion e) {
		throw new ServicioFacadeExcepcion(e);
	} catch (Exception e) {
		throw new ServicioFacadeExcepcion(e);
	}
	}

	// /**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	
}
