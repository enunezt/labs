/**
 * 
 */
package com.intermacs.core.enums;

import com.intermacs.commons.enums.IParametro;


/**
 * @author enunezt
 *
 */
public enum ETipoUsuario implements IParametro {
	
	/**
	 * vALORES DEBEN IR EN ORDEN
	 */
	ADMIN ("ADMINISTRADOR",0),
	GESTION("GESTION",1),
	AFILIADO("AFILIADO",2),
	OTRO("OTRO",3);
	
    private final String name; // in meters
    private final int id; 
    ETipoUsuario(String name,int id) {
   
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
