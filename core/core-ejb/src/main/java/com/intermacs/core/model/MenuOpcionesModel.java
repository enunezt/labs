package com.intermacs.core.model;

import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;


public interface MenuOpcionesModel<E extends Exception> {
	
	/**
	 * Permite insertar un regisgtro en la respectiva tabla
	 * 
	 * @param request con Entidad en RequestDTO.Object
	 * @return RequestDTO.Object con entidad gestionada
	 * @throws E
	 */
	public ResponseDTO guardarMenu(RequestDTO request) throws E;

	/**
	 * Permite insertar un regisgtro en la respectiva tabla
	 * 
	 * @param requestcon Entidad en RequestDTO.Object
	 * @return RequestDTO.Object con entidad gestionada
	 * @throws E
	 */
	public ResponseDTO guardarOpcion(RequestDTO request) throws E; 
	 
	
	/**
     * Realiza la consulta todos los registros
     * @param request 
     * @return Map<Long, Menu> en el parametro EParametro.Map
     * @throws E
     */
   
    public ResponseDTO consultarMenu(RequestDTO request) throws E;	
	
	
	/**
	 * Realiza la consulta todos los registros
	 * @param request 
	 * @return List<T> en el parametro EParametro.ResultList
	 * @throws E
	 */
	public ResponseDTO consultarOpciones(RequestDTO request) throws E;
	
	
	
	
	
}
