package com.intermacs.core.services.users;

import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.model.UsuarioModel;
import com.intermacs.exceptions.ServicioExcepcion;


@Servicio(clase="UsuarioServicio",descripcion="Contrato para la Logica de negocio para Usuarios")
public interface UsuarioServicio extends UsuarioModel<ServicioExcepcion>{


	/**
	 * /**
	 * Carga foto a la entidad Usuario 
	 * @param rquest Usuario en RequestDTO.Object
	 * @return Usuario en RequestDTO.Object
	 * @throws E
	 */
	public ResponseDTO cargarFotoUsuario(RequestDTO request) throws ServicioExcepcion;
		/**
	  * Consultar Usuario Activo por numero y tipo de documento
	  * @param request con Entidad Usuario en RequestDTO.Object.
	  * @return Entidad Usuario en RequestDTO.Object
	  * @throws E
	  */
	public ResponseDTO consultarUsuarioPorNumDocumento(RequestDTO request)throws ServicioExcepcion;
}
