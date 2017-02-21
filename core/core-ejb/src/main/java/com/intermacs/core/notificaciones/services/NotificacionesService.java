/**
 * 
 */
package com.intermacs.core.notificaciones.services;


import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.notificaciones.model.NotificacionesModel;
import com.intermacs.exceptions.ServicioExcepcion;

/**
 * @author intermacs-2
 *
 */
@Servicio(clase="NotificacionesService",descripcion="Contrato para la Logica de negocio para Correos")
public interface NotificacionesService extends NotificacionesModel<ServicioExcepcion>{

	/**
	 * Permite consultar una plantilla
	 * @param request con idPlantilla (Long) en EParametro.ID_OBJECT
	 * @return ResponseDTO con entidad Plantilla en ResponseDTO.Object y
	 *  true/false en ResponseDTO.ResultBoolean si existe plantilla
	 * 
	 * @throws ServicioExcepcion
	 */
	public ResponseDTO  consultarPlantilla(RequestDTO request) throws ServicioExcepcion;
}
