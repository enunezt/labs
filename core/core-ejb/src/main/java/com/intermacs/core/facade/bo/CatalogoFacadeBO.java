 
	
package com.intermacs.core.facade.bo;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.intermacs.commons.annotations.ServicioFacade;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.facade.CatalogoFacade;
import com.intermacs.core.model.entidades.Catalogo;
import com.intermacs.core.model.entidades.CatalogoDet;
import com.intermacs.core.services.catalogo.CatalogoServicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.exceptions.ServicioFacadeExcepcion;

/**
 * Session Bean implementation class CatalogoFacade
 */
@Stateless
@Local(CatalogoFacade.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@ServicioFacade(clase="CatalogoFacadeImpl",descripcion="Centraliza la logica de negocio para la entidad Catalogo")
public class CatalogoFacadeBO  implements CatalogoFacade{

	
	@Inject
	private CatalogoServicio serviceCatalogo;
	/**
     * Default constructor. 
     */
    public CatalogoFacadeBO() {
        // TODO Auto-generated constructor stub
    }


	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO consultarCatalogo(RequestDTO request) throws ServicioFacadeExcepcion {
		try {
			return serviceCatalogo.consultarCatalogo(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
			}
	}
	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public Catalogo consultarCatalogoId(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceCatalogo.consultarCatalogoId(request);
		}  catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
			}
	}
	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public CatalogoDet consultarCatalogDetalle(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceCatalogo.consultarCatalogDetalle(request);
		}  catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
			}
	}

	@Override
	/**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
	public ResponseDTO consultarLstCatalogDetalle(RequestDTO request)
			throws ServicioFacadeExcepcion {
		try {
			return serviceCatalogo.consultarLstCatalogDetalle(request);
		} catch (ServicioExcepcion e) {
			throw new ServicioFacadeExcepcion(e);
		}catch (Exception e) {
			throw new ServicioFacadeExcepcion(e);
			}
		}

	
	
}