/**
 * 
 */
package com.intermacs.core.lugares.model;

import java.util.List;

import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.lugares.model.entidades.Ciudad;

/**
 * @author enunezt
 *
 */
public interface LugaresModel<E  extends Exception> {
    
    /**
	 * Consulta el listado de paises con sus respectivos deparatamentos
	 * @param request 
	 * @return List<Pais> en parametro EParametro.ResultList
	 * @throws E
	 */
	public ResponseDTO consultarPaises(RequestDTO request) throws E;
	
	/**
	 * Consulta el listado de Deparatamentos cons sus repsectivas ciudades
	 * @param request idPais en EParametro.IdEntidad (Pais) ó  Pais en EParametro.Entidad
	 * @return List<Departamento> en parametro EParametro.ResultList
	 * @throws E
	 */	
	public ResponseDTO consultarDepartamentos(RequestDTO request) throws E;
	
	
	/**
	 * Consulta el listado de Ciudades
	 * @param request idDepartamento en EParametro.IdEntidad (Departamento) ó  Departamento en EParametro.Entidad
	 * @return List<Ciudad> en parametro EParametro.ResultList
	 * @throws E
	 */
	public ResponseDTO consultarCiudades(RequestDTO request) throws E;
	/**
	 * Consulta el listado de comunas de una ciudad
	 * @param request idCiudad en EParametro.IdEntidad (Integer)
	 * @return List<?> en parametro EParametro.ResultList
	 * @throws E
	 */
	public ResponseDTO consultarComunas(RequestDTO request) throws E;
	
	/**
	 *  Consulta el listado de barrios de una ciudad
	 * @param request idCiudad en EParametro.IdEntidad (Integer)
	 * @return  List<?> en parametro EParametro.ResultList
	 * @throws E
	 */
	public ResponseDTO consultarBarrios(RequestDTO request) throws E;
	

	/**
	 * Consulta el un Deparatamento 
	 * @param request idDepartamento en EParametro.IdEntidad (Departamento) ó  Departamento en EParametro.Entidad
	 * @return Deparatamento en RequestDTO.Object
	 * @throws E
	 */	
	public ResponseDTO consultarDepartamento(RequestDTO request) throws E;
	/**
	 * Consulta municipios por nombre de ciudad y departamento
	 * @param aTextoDigitado
	 * @param aNumero
	 * @return
	 * @throws E
	 */
	public List<Ciudad> getCiudades(String aTextoDigitado) throws E;

}
