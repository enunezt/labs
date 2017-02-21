package com.intermacs.core.lugares.services;

import com.intermacs.commons.annotations.Servicio;
import com.intermacs.core.lugares.model.LugaresModel;
import com.intermacs.exceptions.ServicioExcepcion;


@Servicio(clase="LugaresServicio",descripcion="Contrato para la Logica de negocio para lugares")
public interface LugaresServicio extends LugaresModel<ServicioExcepcion>{ 

}