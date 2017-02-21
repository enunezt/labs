package com.intermacs.core.facade;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.core.model.MenuOpcionesModel;
import com.intermacs.exceptions.ServicioFacadeExcepcion;


@ServicioFacade(clase="MenuOpcionesFacade",descripcion="Contrato de la logica de negocio para la entidad Menu y Opciones")
//@SecurityDomain("intermacssecurity")
public interface MenuOpcionesFacade extends  MenuOpcionesModel<ServicioFacadeExcepcion> { 
    

}
