 
	
package com.intermacs.core.services.catalogo;

import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.model.entidades.Catalogo;
import com.intermacs.core.model.entidades.CatalogoDet;
import com.intermacs.exceptions.ServicioExcepcion;



@Servicio(clase="CatalogoServicio",descripcion="Contrato para la Logica de negocio para Catalogo")
public interface CatalogoServicio {
//TODO :agregar metodos de contrato
	
	/**
	 * Consulta Catalogo y sus detalle
	 * @param request con name en parametro EParametro.Nombre
	 * @return Catalgo en RequestDTO.Object 
	 * @throws ServicioExcepcion
	 */
	public ResponseDTO consultarCatalogo(RequestDTO request)throws ServicioExcepcion;
	/**
	 * Consulta Catalogo y sus detalle
	 * @param request RequestDTO.Object
	 * @return Catalgo
	 * @throws ServicioExcepcion
	 */
	public Catalogo consultarCatalogoId(RequestDTO request)throws ServicioExcepcion;
	/**
	 * Retorna detalle catalogo segun su id
	 * @param request con idCatalogoDetalle  EParametro.CatalogoDet
	 * @return  CatalogoDetalle 
	 * @throws ServicioExcepcion
	 */
	public CatalogoDet consultarCatalogDetalle(RequestDTO request)throws ServicioExcepcion;
	
	/**
	 * Retorna listado catalogo segun id
	 * @param request con idCatalogo EParametro.Catalogo
	 * @return List<CatalogoDet> en EParametro.ResultList
	 * @throws ServicioExcepcion
	 */
	public ResponseDTO consultarLstCatalogDetalle(RequestDTO request)throws ServicioExcepcion;
	

}