package com.intermacs.core.parametros.model;


import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.parametros.model.entidades.ParametroDet;



public interface ParametrosModel<E extends Exception> { 	
	
	
	/**
     * Permite guardar un regisgtro en la respectiva tabla
     * @param request con Entidad Parametro en RequestDTO.Object
     * @return  RequestDTO.Object con entidad Parametro gestionada
     * @throws Exepcion
     */
	 public ResponseDTO guardarParametro(RequestDTO request) throws E;
	 
	 /**
	     * Permite guardar un regisgtro en la respectiva tabla
	     * @param request con Entidad ParametroDet en RequestDTO.Object
	     * @return  RequestDTO.Object con entidad ParametroDet gestionada
	     * @throws Exepcion
	     */
		 public ResponseDTO guardarParametroDetalle(RequestDTO request) throws E;
	 
	 
	
	/**
	 * Consulta el listado definido para un parametro activo
	 * @param request con idParametro en EParametro.ID_OBJECT de tipo Long <br/>
	 * 		o request con entidad ParametroDet en EParametro.OBJECT : en este caso realiza la consulta 
	 * de acuerdo a los datos no nulos de la entidad pasada como parametro. 
	 * @return ResponseDTO.ResultList con listado de entidades ParametroDet<br/>
	 *         ResponseDTO.isResultBoolean para saber si exiten o no parametros
	 * @throws E
	 */
	public ResponseDTO consultarLstParametrosDet(RequestDTO request) throws E;
	
	/**
	 * Consulta un ParametroDet Activos según sus atributos no nulos
	 * @param request con idParametro en EParametro.ID_OBJECT de tipo Long <br/>
	 * 		o request con entidad ParametroDet en EParametro.OBJECT
	 * @return ParametroDet 
	 * @throws E
	 */
	public ParametroDet consultarParametroDet(RequestDTO request) throws E;
	
	
	
	


}