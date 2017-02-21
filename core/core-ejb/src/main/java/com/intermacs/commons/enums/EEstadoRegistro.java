/**
 * 
 */
package com.intermacs.commons.enums;



/**
 * @author enunezt
 *
 */
public enum EEstadoRegistro implements IParametro {
	
	/**
	 * vALORES DEBEN IR EN ORDEN
	 */
	
	SIN_ESTADO ("SIN ESTADO",0),
	ACTIVO ("ACTIVO",1),
	INACTIVO("INACTIVO",2),
	CANCELADO("CANCELADO",3),
	DEFINITIVO("DEFINITIVO",4),
	ELIMINADO("ELIMINADO",5);
	//OTRO("OTRO",3);
	
    private final String name; // in meters
    private final int id; 
    EEstadoRegistro(String name,int id) {
   
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
