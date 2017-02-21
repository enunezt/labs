package com.intermacs.core.model;

import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;


public interface RolModel<E  extends Exception> {
	
	/**
     * Permite guardar un regisgtro en la respectiva tabla
     * @param request con Entidad Rol en RequestDTO.Object
     * @return  RequestDTO.Object con entidad Rol gestionada
     * @throws Exepcion
     */
	 public ResponseDTO guardarRol(RequestDTO request) throws E;
	 
   /**
    *  Permite eliminar un regisgtro en la respectiva tabla
    * @param request con Entidad en RequestDTO.Object
    * @throws Exepcion
    */
    public void eliminarRol(RequestDTO request) throws E;
    
    /**
     * Realiza la busqueda todos los registros
     * @param request con Entidad en RequestDTO.Object
     * @return List<T> en el parametro EParametro.ResultList
     * @throws Exepcion
     */
   
    public ResponseDTO buscarRoles(RequestDTO request) throws E;	
	
	
	/**
	 * Consulta Rol segun su id
	 * @param Rol Rol en RequestDTO.Object
	 * @return Rol en parametro RequestDTO.Object
	 * @throws Exepcion
	 */
	public ResponseDTO consultarRol(RequestDTO request) throws E;
	
	
	
	/**
	 * Consulta las opciones de Diponibles del Rol
	 * @param request Rol en RequestDTO.Object
	 * @return List<Opcion> en parametro EParametro.ResultList
	 * @throws Exepcion
	 */
	public ResponseDTO consultarOpcionesDisponiblesRol(RequestDTO request) throws E;
	
	/**
	 * Consulta las opciones asignadas al Rol
	 * @param Rol Rol en RequestDTO.Object
	 * @return List<Opcion> en parametro EParametro.ResultList
	 * @throws Exepcion
	 */
	public ResponseDTO consultarOpcionesRol(RequestDTO request) throws E;

	

	
}
