package com.intermacs.core.services.menu;

import com.intermacs.commons.annotations.Servicio;
import com.intermacs.core.model.MenuOpcionesModel;
import com.intermacs.exceptions.ServicioExcepcion;


@Servicio(clase="MenuOpcionesServicio",descripcion="Contrato para la Logica de negocio para Menu y Opciones")
public interface MenuOpcionesServicio extends MenuOpcionesModel<ServicioExcepcion>{


}
