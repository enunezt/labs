package com.intermacs.core.parametros.facade;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.core.parametros.model.ParametrosModel;
import com.intermacs.exceptions.ServicioFacadeExcepcion;

@ServicioFacade(clase="ParametroFacade",descripcion="Contrato de la logica de negocio para las Parametroes")
public interface ParametroFacade extends ParametrosModel<ServicioFacadeExcepcion> { 	

}