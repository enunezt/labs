 
	
package com.intermacs.core.services.catalogo.bo;

import java.util.HashMap;
import java.util.Map;
/*import java.util.logging.Logger;*/

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.model.entidades.Catalogo;
import com.intermacs.core.model.entidades.CatalogoDet;
import com.intermacs.core.services.GenericoServices;
import com.intermacs.core.services.catalogo.CatalogoServicio;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.security.ContextoPrincipal;



@Stateless
@Local(CatalogoServicio.class)
@Servicio(clase="CatalogoServicioImpl",descripcion="Implementacion Logica de negocio de Catalogos")
//@PermitAll
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CatalogoServicioBO extends GenericoServices implements CatalogoServicio{
	@Inject
	//@CoreUnitPersistence
	private EntityManager em;
	
	/*@Inject
	private Logger log;*/
	
	@Resource 
	SessionContext ctx;


	
	@Override
	public ResponseDTO consultarCatalogo(RequestDTO request) throws ServicioExcepcion {
		try {
		    String name=(String) request.getParam(EParametro.Nombre);
			Map<String,Object> parameters=new HashMap<String, Object>();
			parameters.put("name", name);
			
			request.setParam(EParametro.NamedQuery, "Catalogo.findIdByName");
			request.setParam(EParametro.ParemtrosQuery,parameters);
			
			return findWithNamedQuery(request);
			
			
		}catch (Exception e) {
				throw new ServicioExcepcion(e);
			}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	//@RolesAllowed({"ADMINS"})
	public Catalogo consultarCatalogoId(RequestDTO request)
			throws ServicioExcepcion {
		try {
			 Long idCatDet= (Long) request.getParam(EParametro.CATALOGO);
			 request.setParam(EParametro.ClassEntidad,Catalogo.class);  
			 request.setParam(EParametro.ID_OBJECT,idCatDet);
			 Catalogo _catResponse= (Catalogo) buscarPorId(request);
			 //_catResponse.getCatalogoDetCollection();
		    return _catResponse;
			
			
			
		}  catch (Exception e) {
				throw new ServicioExcepcion(e);
			}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public CatalogoDet consultarCatalogDetalle(RequestDTO request)
			throws ServicioExcepcion {
		try {
			
		    Long idCatDet= (Long) request.getParam(EParametro.CATALOGO_DET);
		    /*CatalogoDetalle catDet=new CatalogoDetalle();
		    catDet.setIdCatalogoDetalle(idCatDet);		    
		    request.setObject(catDet);	*/
		    
		    request.setParam(EParametro.ClassEntidad,CatalogoDet.class);  
		    request.setParam(EParametro.ID_OBJECT,idCatDet);
			return (CatalogoDet) buscarPorId(request);
			
		}  catch (Exception e) {
				throw new ServicioExcepcion(e);
			}
	}

	
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ResponseDTO consultarLstCatalogDetalle(RequestDTO request)
			throws ServicioExcepcion {
		try {
		    Map<String,Object> parameters=new HashMap<String, Object>();
		    Catalogo catalogo=new Catalogo();
		    catalogo.setIdCatalogo((Long)request.getParam(EParametro.CATALOGO));
			parameters.put("idCatalogo",catalogo);
			parameters.put("estado",new String("A"));
			
			request.setParam(EParametro.NamedQuery, "CatalogoDetalle.findByCatalogo");
			request.setParam(EParametro.ParemtrosQuery,parameters);
			
			//Set<CatalogoDetalle> retorno= (Set<CatalogoDetalle>) 
			return findWithNamedQuery(request);
			
			
		} catch (Exception e) {
				throw new ServicioExcepcion(e);
			}
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected ContextoPrincipal getContextoPrincipal() {
		
		try {
			Subject userSubject=(Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
			return (ContextoPrincipal) userSubject.getPrincipals().iterator().next();
		} catch (PolicyContextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return (ContextoPrincipal) ctx.getCallerPrincipal();
	} 
	
	

	
	
}
