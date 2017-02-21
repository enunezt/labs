/**
 * 
 */
package com.intermacs.commons.dtos;

import java.io.Serializable;


/**
 * @author ENUNEZT
 * 
 */
@SuppressWarnings("serial")
public class PaginationDTO implements Serializable {
    
    private int incio;
    private int fin;
    private int rango;
    private int total;//toal registros
    private int actual;//corresponde a los registros consultados
	
	public PaginationDTO() {
		super();
		//param = new HashMap<IParametro, Object>();
	}
	/**
	 * @param incio
	 * @param fin
	 * @param rango
	 */
	public PaginationDTO(int incio, int fin, int rango) {
	    super();
	    this.incio = incio;
	    this.fin = fin;
	    this.rango = rango;
	}

	/**
	 * Inicio de la paginación
	 * @return the incio
	 */
	public int getIncio() {
	    return incio;
	}

	/**
	 * Inicio de la paginación
	 * @param incio the incio to set
	 */
	public void setIncio(int incio) {
	    this.incio = incio;
	}

	/**
	 * Fin de la paginación
	 * @return the fin
	 */
	public int getFin() {
	    return fin;
	}

	/**
	 * Fin de la paginación
	 * @param fin the fin to set
	 */
	public void setFin(int fin) {
	    this.fin = fin;
	}

	/**
	 * Rango de valores consultados
	 * @return the rango
	 */
	public int getRango() {
	    return rango;
	}

	/**
	 * Rango de valores a consultar
	 * @param rango the rango to set
	 */
	public void setRango(int rango) {
	    this.rango = rango;
	}

	

	/**
	 * Total de registros para la consulta
	 * @return the total
	 */
	public int getTotal() {
	    return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
	    this.total = total;
	}

	/**
	 * Cantidad de registros consultados
	 * @return the actual
	 */
	public int getActual() {
	    return actual;
	}

	/**
	 * Cantidad de registros consultados
	 * @param actual the actual to set
	 */
	public void setActual(int actual) {
	    this.actual = actual;
	}

	
}
