package com.intermacs.core.facade.bo;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.facade.UsuarioFacade;
import com.intermacs.core.model.entidades.Usuario;
import com.intermacs.core.services.users.UsuarioServicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.exceptions.ServicioFacadeExcepcion;

/**
 * Session Bean implementation class RolFacade
 */
@Stateless
@Local(UsuarioFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@ServicioFacade(clase="UsuarioFacadeImpl",descripcion="Centraliza la logica de negocio para la entidad Usuario")
public class UsuarioFacadeBO implements UsuarioFacade{
/*
@Inject
	private UsuarioDAO usuarioDAO;	*/

@Inject
private UsuarioServicio usuarioServicio;	
	
  	/**
     * Default constructor. 
     */
    public UsuarioFacadeBO() {
        // TODO Auto-generated constructor stub
    }

	

	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO consultarRolesDisponibles(RequestDTO request)
			throws ServicioFacadeExcepcion {
		
		try {
			return usuarioServicio.consultarRolesDisponibles(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

	@Override
	public ResponseDTO construirMenuUsuario(RequestDTO request)
			throws ServicioFacadeExcepcion {
		
		try {
			return usuarioServicio.construirMenuUsuario(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

	@Override
	public ResponseDTO cargarFotoUsuario(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return usuarioServicio.cargarFotoUsuario(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO consultarMenu(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return usuarioServicio.consultarMenu(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

	@Override
	public ResponseDTO consultarUsuario(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return usuarioServicio.consultarUsuario(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}



	@Override
	public ResponseDTO crearUsuario(RequestDTO request)
			throws ServicioFacadeExcepcion {
	try {
		return usuarioServicio.crearUsuario(request);
	} catch (ServicioExcepcion e) {
		throw new ServicioFacadeExcepcion(e);
	} catch (Exception e) {
		throw new ServicioFacadeExcepcion(e);
	}
	}



	@Override
	public ResponseDTO actualizarUsuario(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return usuarioServicio.actualizarUsuario(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}



	@Override
	public void eliminarUsuario(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			usuarioServicio.eliminarUsuario(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
		
	}



	@Override
	public ResponseDTO buscarUsuarios(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return usuarioServicio.buscarUsuarios(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}



	@Override
	public ResponseDTO recuperarDatoLoguinUsuario(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return usuarioServicio.recuperarDatoLoguinUsuario(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}



	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public Usuario consultarUsuario(Long idUsuario)
			throws ServicioFacadeExcepcion {
		try {
			return usuarioServicio.consultarUsuario(idUsuario);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}


}
