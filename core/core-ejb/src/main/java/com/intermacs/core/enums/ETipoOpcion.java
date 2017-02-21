/**
 * 
 */
package com.intermacs.core.enums;

import com.intermacs.commons.enums.IParametro;


/**
 * @author enunezt
 *
 */
public enum ETipoOpcion implements IParametro {
	
	/**
	 * vALORES DEBEN IR EN ORDEN
	 */
	
	VISTA ("VISTA",0),
	TRANSACCION("TRANSACCION",1),
	ACCION("ACCION",2),
	OTRO("OTRO",3);
	
    private final String name; // in meters
    private final int id; 
    ETipoOpcion(String name,int id) {
   
        this.name = name;
        this.id = id;
    }

	@Override
	public String getName() {
		return this.name;
	}
	public int getId() {
		return this.id;
	}

}
