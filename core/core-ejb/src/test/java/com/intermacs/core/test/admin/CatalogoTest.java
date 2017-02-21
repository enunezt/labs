 
package com.intermacs.core.test.admin;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
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
import com.intermacs.core.facade.CatalogoFacade;
import com.intermacs.core.model.entidades.Catalogo;
import com.intermacs.core.model.entidades.CatalogoDet;
import com.intermacs.core.test.common.BaseTest;
import com.intermacs.exceptions.ServicioFacadeExcepcion;

@RunWith(Arquillian.class)
//@RunWith(EjbWithMockitoRunner.class)
//@ServerSetup({EjbSecurityDomainSetup.class})
//@Category(CommonCriteria.class)
public class CatalogoTest extends BaseTest{	
	
	 static{
		 
		  }
	 
	   @EJB
	   CatalogoFacade CatalogoSrv; 
	   
	   @Inject
	   Logger log;
	   
	 
	 
	 public CatalogoTest() {
			super();
			
			
			
		}
  @Deployment
  public static Archive<?> createTestArchive() {
	  
	 /* MavenResolverSystem resolver = Maven.resolver();  
      resolver.loadPomFromFile("pom.xml"); */ 
	      return ShrinkWrap.create(WebArchive.class, "test-catalogo.war")
	            .addClasses(getListClazzes())
	         //.addAsLibraries(resolver.resolve("commons-codec:commons-codec:1.9").withoutTransitivity().as(JavaArchive.class))
           .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
           .addAsResource("META-INF/jboss-ejb3.xml", "META-INF/jboss-ejb3.xml")
           
           .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
           // Deploy our test datasource
           .addAsWebInfResource("test-ds.xml", "test-ds.xml")
           .addAsWebInfResource("jboss-web.xml", "jboss-web.xml");
  }
  
  public void init(){
	  
  }



   @Test
   public void testCrearCatalogo() throws Exception {
	   log.info("-------------------------TEST INI testCrearCatalogo-----------------------");
	/*   Catalogo entCatalogoTest=new Catalogo();
	   //TODO: Completar entidad
	   RequestDTO req=new RequestDTO();
	   req.setobject(entCatalogoTest);
	   CatalogoSrv.crear(req);
	   log.info(Catalogo.class.getSimpleName()+" creado exitosamente");
		assertNotNull(entCatalogoTest.getIdCatalogo());*/
	 log.info("-------------------------TEST FIN testCrearCatalogo-----------------------");
   }
   
   @Test
   public void testConsultarCatalogo() throws Exception {  
	
	   log.info("-------------------------TEST INI testConsultarCatalogo-----------------------");
		RequestDTO req=new RequestDTO();	 
		  req.setParam(EParametro.CATALOGO, Long.valueOf(1l)); 
		  CatalogoFacade ejb =lookupService(CatalogoFacade.class);	
		 Catalogo _cat=ejb.consultarCatalogoId(req);
		 assertTrue(_cat!=null);
		 try {
				ResponseDTO resp = ejb
					.consultarLstCatalogDetalle(req);
				List<CatalogoDet> tipoDocumentoItems = (List<CatalogoDet>) resp
					.getParam(EParametro.ResultList);
				
				 assertTrue(tipoDocumentoItems!=null);
					
				 log.info("Catalogo name:"+_cat.getNombre());
				 Iterator<CatalogoDet> _itDet=tipoDocumentoItems.iterator();
				while (_itDet.hasNext()) {
					CatalogoDet catalogoDet = (CatalogoDet) _itDet.next(); 
					log.info("\t CatalogoDet name:"+catalogoDet.getLabel());
					
				}
			    } catch (ServicioFacadeExcepcion e) {
			    	log.info("\t Error:"+e.getMessage());
			    	assertTrue(false);
			    }
		 
		log.info("-------------------------TEST FIN testConsultarCatalogo-----------------------");
   }
  /* System.out.println("ctx.getCallerPrincipal()--->>>>>"+ctx.getCallerPrincipal().getName());
			 ctx.isCallerInRole("ADMINS");
			
			 try {
					Subject userSubject=(Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
					if(userSubject!=null){
					System.out.println("userSubject.getPrincipals()--->>>>>"+Arrays.deepToString(userSubject.getPrincipals().toArray()));
					}
				} catch (PolicyContextException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

}