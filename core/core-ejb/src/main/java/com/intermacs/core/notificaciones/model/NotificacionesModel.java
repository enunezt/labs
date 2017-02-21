/**
 * 
 */
package com.intermacs.core.notificaciones.model;

import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;

/**
 * @author enunezt
 *
 */
public interface NotificacionesModel<E  extends Exception> {
    
    /**
	 * Permite utilizar el servicio de mensaejería de correos electrónicos
	 * @param request con objeto NotificacionesDTO
	 * @return true/false en parametro ResponseDTO.boleanResult true si es enviado y false si no
	 * @throws E
	 */
	public ResponseDTO enviarEmail(RequestDTO request) throws E;
	
	

}
