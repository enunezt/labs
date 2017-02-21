package com.intermacs.commons.enums;

/**
 * Interfaz que define el nombre del parametro
 * @author enunezt
 *
 */
public interface IParametro {	
	/**
	 * Nombre del parametro dbe ser único
	 * @return String con el nombre del parametro
	 */
	public String getName();
	 @Override public boolean equals(Object o);
}
