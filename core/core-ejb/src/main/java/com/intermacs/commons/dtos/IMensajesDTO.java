/**
 * 
 */
package com.intermacs.commons.dtos;

import com.intermacs.commons.enums.ETipoMensaje;

/**
 * @author eanunezt
 *
 */
public abstract class IMensajesDTO {
	
	protected String codMensaje;
	
	protected String mensaje;
	
	/**
	 * @param codMensaje
	 * @param mensaje
	 */
	public IMensajesDTO(String mensaje) {
		super();
		//this.codMensaje = codMensaje;
		this.mensaje = mensaje;
	}
	
	
	/**
	 * @param codMensaje
	 * @param mensaje
	 */
	public IMensajesDTO(String codMensaje, String mensaje) {
		super();
		this.codMensaje = codMensaje;
		this.mensaje = mensaje;
	}
	
	/**
	 * 
	 * @param codMensaje
	 * @param mensaje
	 */
	public IMensajesDTO(Integer codMensaje, String mensaje) {
		super();
		this.codMensaje =codMensaje!=null? codMensaje.toString():null;
		this.mensaje = mensaje;
	}
	
	
	/**
	 * Código del mensaje
	 * @return
	 */
	public  String getCodMensaje(){
		return codMensaje;
	}
	/**
	 * Valor del mensaje
	 * @return
	 */
	public  String getMensaje(){
		return mensaje;
	}
	/**
	 * Tipo de mensaje
	 * @return
	 */
	public abstract ETipoMensaje getTipoMensaje();
	
	/**
	 * Retorna mensaje con formato:
	 * codMensaje: mensaje
	 * 
	 * @return
	 */
	public String getMensajeFormat(){
		
		return getCodMensaje()+": "+getMensaje();
	}
	
	/**
	 * Verifica si el mensaje es de tipo Error
	 * @return
	 */
	public boolean isTipoError(){
		return ETipoMensaje.ERROR.equals(getTipoMensaje());
	}
	
	/**
	 * Verifica si el mensaje es de tipo INFO
	 * @return
	 */
	public boolean isTipoInfo(){
		return ETipoMensaje.INFO.equals(getTipoMensaje());
	}
	
	/**
	 * Verifica si el mensaje es de tipo WARN
	 * @return
	 */
	public boolean isTipoWarn(){
		return ETipoMensaje.WARN.equals(getTipoMensaje());
	}

}
