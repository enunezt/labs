 
	
package com.intermacs.core.facade;


import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.model.entidades.Catalogo;
import com.intermacs.core.model.entidades.CatalogoDet;
import com.intermacs.exceptions.ServicioFacadeExcepcion;





@ServicioFacade(clase="CatalogoFacade",descripcion="Contrato de la logica de negocio para la gestion de Catalogos")
//@SecurityDomain("intermacssecurity")
public interface CatalogoFacade { 	
    /**
	 * Consulta Catalogo y sus detalle
	 * @param name EParametro.Nombre
	 * @return Catalgo en RequestDTO.Object 
	 * @throws ServicioFacadeExcepcion
	 */
	public ResponseDTO consultarCatalogo(RequestDTO request)throws ServicioFacadeExcepcion;
	/**
	 * Consulta Catalogo y sus detalle
	 * @param request RequestDTO.Object
	 * @return Catalgo
	 * @throws ServicioFacadeExcepcion
	 */
	public Catalogo consultarCatalogoId(RequestDTO request)throws ServicioFacadeExcepcion;
	/**
	 * Retorna detalle catalogo segun su id
	 * @param request con idCatalogoDetalle  EParametro.CatalogoDet
	 * @return  CatalogoDetalle 
	 * @throws ServicioFacadeExcepcion
	 */
	public CatalogoDet consultarCatalogDetalle(RequestDTO request)throws ServicioFacadeExcepcion;
	
	/**
	 * Retorna listado catalogo segun id
	 * @param request con idCatalogo EParametro.Catalogo tipo Integer
	 * @return Set<CatalogoDetalle> en EParametro.Set
	 * @throws ServicioFacadeExcepcion
	 */
	public ResponseDTO consultarLstCatalogDetalle(RequestDTO request)throws ServicioFacadeExcepcion;
	
	

}
