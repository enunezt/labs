 
	
package com.intermacs.core.lugares.facade;


import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.core.lugares.model.LugaresModel;
import com.intermacs.exceptions.ServicioFacadeExcepcion;


@ServicioFacade(clase="PaisFacade",descripcion="Contrato de la logica de negocio para la gestion de Lugares")
public interface LugaresFacade extends  LugaresModel<ServicioFacadeExcepcion>{ 
    
	
}
