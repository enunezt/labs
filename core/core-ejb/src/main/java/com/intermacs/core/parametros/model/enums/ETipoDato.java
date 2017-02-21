package com.intermacs.core.parametros.model.enums;

import com.intermacs.commons.enums.IParametro;

public enum ETipoDato implements IParametro {
	
	/**
	 * Entidad base para la parte génrica
	 */
	EN ("ENTERO"),
	CA ("CADENA"),
	DO ("DOUBLE"),
	DA ("DATE")
	;//, OTRO(),....;

    private final String name; // in meters
    ETipoDato(String name) {
   
        this.name = name;
    }

	@Override
	public String getName() {
		return this.name;
	}


}