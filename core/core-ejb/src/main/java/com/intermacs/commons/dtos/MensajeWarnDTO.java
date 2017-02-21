/**
 * 
 */
package com.intermacs.commons.dtos;

import com.intermacs.commons.enums.ETipoMensaje;

/**
 * @author eanunezt
 *
 */
public class MensajeWarnDTO extends IMensajesDTO {
	
	/**
	 * @param codMensaje
	 * @param mensaje
	 */
	public MensajeWarnDTO(String mensaje) {
		super(mensaje);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codMensaje
	 * @param mensaje
	 */
	public MensajeWarnDTO(String codMensaje, String mensaje) {
		super(codMensaje, mensaje);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codMensaje
	 * @param mensaje
	 */
	public MensajeWarnDTO(Integer codMensaje, String mensaje) {
		super(codMensaje, mensaje);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see co.edu.unitropico.common.dtos.IMensajesDTO#getTipoMensaje()
	 */
	@Override
	public ETipoMensaje getTipoMensaje() {
		// TODO Auto-generated method stub
		return ETipoMensaje.WARN;
	}

}
