package com.intermacs.images.facade;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.exceptions.ServicioFacadeExcepcion;
import com.intermacs.images.model.ImagenModel;


@ServicioFacade(clase="ImagenFacade",descripcion="Contrato de la logica de negocio para las imagenes")
public interface ImagenFacade extends ImagenModel<ServicioFacadeExcepcion> { 	

}
