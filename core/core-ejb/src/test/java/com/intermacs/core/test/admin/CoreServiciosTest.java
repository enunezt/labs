package com.intermacs.core.test.admin;

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
import com.intermacs.core.facade.UsuarioFacade;
import com.intermacs.core.model.entidades.RolUsuario;
import com.intermacs.core.model.entidades.Usuario;
import com.intermacs.core.test.common.BaseTest;

@RunWith(Arquillian.class)
public class CoreServiciosTest extends BaseTest{
	static{
		   
		   lst.add(com.intermacs.core.model.entidades.Catalogo.class);
		   lst.add( com.intermacs.core.model.entidades.CatalogoDet.class);	
		   
		/*   lst.add(com.intermacs.core.facade.bo.MenuOpcionesFacadeBO.class);
			 lst.add(com.intermacs.core.facade.MenuOpcionesFacade.class);
			 lst.add(com.intermacs.core.model.MenuOpcionesModel.class);
			 lst.add(com.intermacs.core.services.menu.MenuOpcionesServicio.class);
			 lst.add(com.intermacs.core.services.menu.bo.MenuOpcionesServicioBO.class);
		   */
	}
	
	 @Deployment
	   public static Archive<?> createTestArchive() {
		 
	
	      return ShrinkWrap.create(WebArchive.class, "test-usuario.war")
	            .addClasses(getListClazzes())
	            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
	            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	            // Deploy our test datasource
	            .addAsWebInfResource("test-ds.xml", "test-ds.xml")
		           .addAsWebInfResource("jboss-web.xml", "jboss-web.xml");
	   }

   @EJB
   UsuarioFacade userSrv;

   @Inject
   Logger log;


   @Test
   public void testLogin() throws Exception {
	   System.out.println("-------------------------TEST INI -----------------------");
	 
	   RequestDTO req=new RequestDTO();
	   Usuario userConsulta=new Usuario();
	    userConsulta.setUsuario("Admin");
	    userConsulta.setClaveLegible("1234");
	   req.setObject(userConsulta);
	   ResponseDTO resp=userSrv.consultarUsuario(req);
	   userConsulta=(Usuario) resp.getObject();
	   
	   RequestDTO requestDTO = new RequestDTO();
       requestDTO.setObject(userConsulta);
       resp = userSrv.consultarMenu(requestDTO);
       
	   for (RolUsuario rolUsuario :  userConsulta.getRolUsuarioCollection()) {
		System.out.println(rolUsuario.getRol().getNombre());
	}
	   
	 System.out.println("-------------------------TEST FIN-----------------------");
     // assertNotNull(newMember.getId());
    //  log.info(newMember.getNombre1() + " consultado exitosamente  numero " + newMember.getId());
	   //System.out.println("-------------------------TEST FIN-----------------------");
   }
   
   /*//@Test
   public void testCrearMenu() throws Exception {
	   System.out.println("-------------------------TEST INI -----------------------");
	   Menu menu=new Menu();
	   //menu.setIdMenu(0L);
	   menu.setDescripcion("Test-Crear-Menu");
	   menu.setNombre("Test-Nombre-Menu");
	   RequestDTO req=new RequestDTO();
	   req.setObject(menu);
	   
	   MenuOpcionesFacade  menuSrv =lookupService(MenuOpcionesFacade.class);
	   menuSrv.guardarMenu(req);
	   //ResponseDTO resp= opcionSrv.crear(req);
	   log.info(menu.getNombre() + " creado exitosamente  numero " + menu.getIdMenu());
	   assertNotNull(menu.getIdMenu());
	
	 System.out.println("-------------------------TEST FIN-----------------------");
     // assertNotNull(newMember.getId());
  
	   //System.out.println("-------------------------TEST FIN-----------------------");
   }
   
   //@Test
   public void testCrearOpcion() throws Exception {
	   System.out.println("-------------------------TEST INI -----------------------");
	   Opcion opcion=new Opcion();
	  // INSERT INTO `opcion` VALUES (1,'/pgadmin/gestionarUsuario.jsf',2,1,NULL),(2,'/pgadmin/gestionarPerfil.jsf',4,1,NULL),(3,'/pgadmin/gestionarOpcion.jsf',6,1,NULL),(4,'/pgadmin/gestionarMenu.jsf',7,1,NULL),(5,'/pages/gestionarEnte.jsf',8,1,NULL),(6,'/pages/gestionarBalance.jsf',9,1,NULL),(7,'/pages/gestionarCuentasPuc.jsf',10,1,NULL),(8,'/pages/gestionarFormato.jsf',12,1,NULL),(9,'/pages/gestionarCasilla.jsf',13,1,NULL);
	   opcion.setUrl("/pgadmin/gestionarUsuario.jsf");
	   Menu menu=new Menu();
		 // menu.setId(0L);
		   menu.setDescripcion("Test-Crear-Menu");
		   menu.setNombre("Test-Nombre-Menu");
		   
		   RequestDTO req=new RequestDTO();
		   req.setObject(menu);
		   MenuOpcionesFacade  menuSrv =lookupService(MenuOpcionesFacade.class);
		   menuSrv.guardarMenu(req);
		   opcion.setMenu(menu);
		   opcion.setTipo(ETipoOpcion.VISTA);
		   opcion.setFecCambio(DateUtils.getFechaSistema());
		   req=new RequestDTO();
		   req.setObject(opcion);
		  ResponseDTO resp= menuSrv.guardarOpcion(req); 
		  opcion= (Opcion) resp.getObject();
		   log.info(opcion.getUrl()+ " creado exitosamente  numero " + opcion.getIdOpcion());
		   assertNotNull(opcion.getIdOpcion());
	
	 System.out.println("-------------------------TEST FIN-----------------------");
     // assertNotNull(newMember.getId());
  
	   //System.out.println("-------------------------TEST FIN-----------------------");
   }*/
   
   
  
}
