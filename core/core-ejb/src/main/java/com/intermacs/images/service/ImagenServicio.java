package com.intermacs.images.service;

import com.intermacs.commons.annotations.Servicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.images.model.ImagenModel;


@Servicio(clase="ImagenServicio",descripcion="Contrato para la Logica de negocio para la gestion de imagenes")
public interface ImagenServicio/*<T>*/ extends ImagenModel<ServicioExcepcion>/*, GenericoInterface<T,ServicioExcepcion>*/{

}
