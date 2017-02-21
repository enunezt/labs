package com.intermacs.images.facade.bo;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.exceptions.ServicioFacadeExcepcion;
import com.intermacs.images.facade.ImagenFacade;
import com.intermacs.images.model.entidades.Imagen;
import com.intermacs.images.service.ImagenServicio;

/**
 * Session Bean implementation class OpcionFacade
 */
@Stateless
@Local(ImagenFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@ServicioFacade(clase="ImagenFacadeImpl",descripcion="Centraliza la logica de negocio para las Imagenes")
public class ImagenFacadeBO implements ImagenFacade{


	@Inject
	private ImagenServicio serviceImagen;

	/**
     * Default constructor. 
     */
    public ImagenFacadeBO() {
        // TODO Auto-generated constructor stub
    }

	

	@Override
	public ResponseDTO consultarIdFotoUsuario(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceImagen.consultarIdFotoUsuario(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

	@Override
	public Imagen consultarImagen(RequestDTO request) throws ServicioFacadeExcepcion {
		try {
			return serviceImagen.consultarImagen(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

	@Override
	public ResponseDTO consultarLstImagenes(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceImagen.consultarLstImagenes(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

	@Override
	public ResponseDTO consultarLstIdImagenes(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceImagen.consultarLstIdImagenes(request);	
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}



	@Override
	public ResponseDTO guardarImagen(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceImagen.guardarImagen(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		} catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
		}
	}

}
