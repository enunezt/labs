package com.intermacs.core.facade;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.model.UsuarioModel;
import com.intermacs.exceptions.ServicioFacadeExcepcion;


@ServicioFacade(clase="UsuarioFacade",descripcion="Contrato de la logica de negocio para la gestion de usuarios")
public interface UsuarioFacade extends  UsuarioModel<ServicioFacadeExcepcion>{ 	
	/**
	 * Carga foto a la entidad Usuario 
	 * @param rquest Usuario en RequestDTO.Object
	 * @return Usuario en RequestDTO.Object
	 * @throws E
	 */
	public ResponseDTO cargarFotoUsuario(RequestDTO request) throws ServicioFacadeExcepcion;
}
