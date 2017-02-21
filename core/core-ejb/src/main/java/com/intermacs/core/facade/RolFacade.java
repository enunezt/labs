package com.intermacs.core.facade;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.core.model.RolModel;
import com.intermacs.exceptions.ServicioFacadeExcepcion;


@ServicioFacade(clase="PerfilFacade",descripcion="Contrato de la logica de negocio para la entidad Perfil")
public interface RolFacade extends  RolModel<ServicioFacadeExcepcion> { 
    

}
