/**
 * 
 */
package com.intermacs.commons.enums;



/**
 * @author enunezt
 *
 */
public enum ETipoMensaje {
	
	/**
	 * Error
	 */
	ERROR ("ERROR"),
	/**
	 * Advertencia
	 */
	WARN("WARN"),
	/**
	 * Informativo
	 */
	INFO("INFO");//, OTRO(),....;

    private final String value; // in meters
    ETipoMensaje(String value) {
   
        this.value = value;
    }

	
	public String getValue() {
		return this.value;
	}


}
