 
package com.intermacs.core.test.admin.lugares;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.lugares.facade.LugaresFacade;
import com.intermacs.core.lugares.model.entidades.Pais;
import com.intermacs.core.test.common.BaseTest;

@RunWith(Arquillian.class)
public class LugaresTest extends BaseTest{	
	
	public LugaresTest() {
		super();
		
			
		
	}
  static{
   /*   lst.add(com.intermacs.core.lugares.model.LugaresModel.class);
	  lst.add(com.intermacs.core.lugares.facade.impl.LugaresFacadeImpl.class);
	   lst.add(com.intermacs.core.lugares.facade.LugaresFacade.class);	   
	   lst.add(com.intermacs.core.lugares.services.LugaresServicio.class);
	   lst.add(com.intermacs.core.lugares.services.impl.LugaresServicioImpl.class);	   
	   lst.add(com.intermacs.core.lugares.model.entidades.Pais.class);
	   lst.add(com.intermacs.core.lugares.model.entidades.Departamento.class);
	   lst.add(com.intermacs.core.lugares.model.entidades.Ciudad.class);
	 */
	   //lst.add(com.intermacs.core.lugares.PaisTest.class);
	 
  }
  @Deployment
  public static Archive<?> createTestArchive() {
	  
	
	
	      return ShrinkWrap.create(WebArchive.class, "test-lugares.war")
	            .addClasses(getListClazzes())
	             //.addAsLibraries(resolver.resolve("commons-codec:commons-codec:1.9").withoutTransitivity().as(JavaArchive.class))
	             .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
           .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
           // Deploy our test datasource
           .addAsWebInfResource("test-ds.xml", "test-ds.xml");
  }

   @EJB
   LugaresFacade lugaresSrv; 
   
   @Inject
   Logger log;

   @Test
   public void testCunsultarPaises() throws Exception {
	   log.info("-------------------------TEST INI testCunsultarPaises-----------------------");
   Pais entPaisTest=new Pais();
	   //TODO: Completar entidad
	   RequestDTO req=new RequestDTO();
	   ResponseDTO resp= lugaresSrv.consultarPaises(req);  
	 List<Pais> lst=(List<Pais>) resp.getParam(EParametro.ResultList);
	/*for (Pais pais : lst) {
	    Set<Departamento> lstDtos=pais.getDepartamentos();
	    
	    for (Departamento departamento : lstDtos) {
		
		Set<Ciudad> lstCiudades=departamento.getCiudades();
		for (Ciudad ciudad : lstCiudades) {
		    System.out.println(pais.getNombre()+":"+departamento.getNombre()+":"+ciudad.getNombre());
		}
		
	    }
	    
	    
	}*/
	
		//assertNotNull(entPaisTest.getIdPais());
	 log.info("-------------------------TEST FIN testCunsultarPaises-----------------------");
   }
   
   @Test
   public void testCunsultarDepartamento() throws Exception {
	   log.info("-------------------------TEST INI testCunsultarDepartamento-----------------------");
	//   Pais entPaisTest=new Pais();
	   //TODO: Completar entidad
	   RequestDTO req=new RequestDTO();
	   req.setParam(EParametro.IdEntidad, 52); 
	   ResponseDTO resp= lugaresSrv.consultarDepartamentos(req); 
	// List<Departamento> lstDtos=(List<Departamento>) resp.getParam(EParametro.ResultList);
	
	    
	 /*   for (Departamento departamento : lstDtos) {
		
		Set<Ciudad> lstCiudades=departamento.getCiudades();
		for (Ciudad ciudad : lstCiudades) {
		    System.out.println(departamento.getNombre()+":"+ciudad.getNombre());
		}
		
	    }*/
	    
	
		//assertNotNull(entPaisTest.getIdPais());
	 log.info("-------------------------TEST FIN testCunsultarDepartamento-----------------------");
   }
   
   
   
}