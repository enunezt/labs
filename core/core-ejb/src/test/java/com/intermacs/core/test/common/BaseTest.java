package com.intermacs.core.test.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.security.client.SecurityClient;
import org.jboss.security.client.SecurityClientFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.enums.ETipoUsuario;
import com.intermacs.core.facade.CatalogoFacade;
import com.intermacs.core.facade.UsuarioFacade;
import com.intermacs.core.lugares.facade.LugaresFacade;
import com.intermacs.core.lugares.model.entidades.Ciudad;
import com.intermacs.core.lugares.model.entidades.Departamento;
import com.intermacs.core.model.entidades.CatalogoDet;
import com.intermacs.core.model.entidades.Usuario;
import com.intermacs.core.test.security.ExplicitCallbackHandler;
import com.intermacs.exceptions.ServicioFacadeExcepcion;
import com.intermacs.security.ContextoLoginModule;
import com.intermacs.security.ContextoPrincipal;



/**
 * Clase gnerica para los test
 * @author ENUNEZT
 *
 */

//@RunWith(Arquillian.class)
public class BaseTest  {
	@SuppressWarnings("rawtypes")
	protected static List<Class> lst=new ArrayList<Class>();  ;
	 @Inject
	   Logger log;
	 
	 /* @EJB
	   UsuarioFacade seettingsServicio; */
	  
	  //@EJB
	  LugaresFacade lugarSrv; 
	  
	 // @EJB
	  private CatalogoFacade catalogoService;
	  
	  
	  private ContextoLoginModule lm;
	    private Map<String, String> sharedState;
	    private Map<String, String> options;
	    private String loginName = "admin";
	    private String loginPassword = "admin";
	    private Subject subject;
	    public void setSubject(Subject subject) {
			this.subject = subject;
		}

		protected SecurityClient client;

	public BaseTest() {
		
	}
	
	@Before
	public void setUp() {
		log.info("-------------------------TEST INI Ejecución-----------------------");	
		try {
			client = SecurityClientFactory.getSecurityClient();
			//Object obj=;		   
		     client.setSimple(new ContextoPrincipal(subject, "admin", 1L,new Usuario(1l, "AP1-TEST", "AP2-TEST", "NOM-TEST", "12345", ETipoUsuario.ADMIN, "Test-JUNIT")),"admin");
		     //client.setSimple(new ContextoPrincipal("admin"),"admin");
		     client.login();
		     
		    /* try {
		    	 subject=(Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
					if(subject!=null){
					System.out.println("userSubject.getPrincipals()--->>>>>"+Arrays.deepToString(subject.getPrincipals().toArray()));
					}
				} catch (PolicyContextException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		   
		    
		    //client.login();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(client!=null)
				 client.logout();
			e.printStackTrace();
		}

	}
	
	@After
	public void setEnd() {
		
		
		if(client!=null)
			 client.logout();
			  log.info("-------------------------TEST FIN Ejecución-----------------------");
	}

	 @SuppressWarnings("rawtypes")
	protected static Class[] getListClazzes(){
		
		  
		   lst.add(com.intermacs.core.services.rol.RolServicio.class);
		   lst.add(com.intermacs.core.services.rol.bo.RolServicioBO.class);		 
		   lst.add(com.intermacs.core.facade.bo.RolFacadeBO.class);
		   lst.add(com.intermacs.core.facade.bo.UsuarioFacadeBO.class);
		   lst.add(com.intermacs.core.facade.RolFacade.class);
		   lst.add(com.intermacs.core.facade.UsuarioFacade.class);
		   lst.add(com.intermacs.core.model.entidades.Menu.class);
		   lst.add(com.intermacs.core.model.entidades.Opcion.class);
		   lst.add(com.intermacs.core.model.entidades.Rol.class);
		   lst.add(com.intermacs.core.model.entidades.RolOpcion.class);
		   lst.add(com.intermacs.core.model.entidades.RolOpcionPK.class);
		   lst.add(com.intermacs.core.model.entidades.Usuario.class);
		   lst.add(com.intermacs.core.model.UsuarioModel.class);
		   lst.add(com.intermacs.core.model.RolModel.class);
		   lst.add(com.intermacs.commons.annotations.Servicio.class);
		   lst.add(com.intermacs.commons.annotations.ServicioFacade.class);
	
		   lst.add(com.intermacs.core.services.GenericoServices.class);
		   lst.add(com.intermacs.core.model.entidades.Catalogo.class);
		   lst.add( com.intermacs.core.model.entidades.CatalogoDet.class);	 
		   
		
		   lst.add(com.intermacs.exceptions.ServicioExcepcion.class);
		   lst.add(com.intermacs.exceptions.ServicioFacadeExcepcion.class);
		   
		   lst.add(com.intermacs.core.test.common.BaseTest.class);
		   lst.add(com.intermacs.commons.dtos.BaseRequestResponseDTO.class);
		   lst.add(com.intermacs.commons.dtos.PaginationDTO.class);
		   lst.add(com.intermacs.commons.dtos.RequestDTO.class);
		   lst.add(com.intermacs.commons.dtos.ResponseDTO.class);
		   lst.add(com.intermacs.commons.dtos.IMensajesDTO.class   );
		   lst.add(com.intermacs.commons.dtos.MensajeErrorDTO.class);
		   lst.add(com.intermacs.commons.dtos.MensajeInfoDTO.class );
		   lst.add(com.intermacs.commons.dtos.MensajeWarnDTO.class );
		    lst.add(com.intermacs.commons.enums.EParametro.class);
		   lst.add(com.intermacs.commons.enums.IParametro.class);
		   lst.add(com.intermacs.core.enums.EParametrosAdmin.class);
		   lst.add(com.intermacs.core.services.users.UsuarioServicio.class);
		   lst.add(com.intermacs.core.services.users.bo.UsuarioServicioBO.class);
		   lst.add(com.intermacs.utils.UtilCrypt.class);
		   

	
		lst.add(com.intermacs.images.facade.bo.ImagenFacadeBO.class);
		lst.add(com.intermacs.images.facade.ImagenFacade.class);
		lst.add(com.intermacs.images.model.entidades.EImagen.class);
		lst.add(com.intermacs.images.model.entidades.Imagen.class);
		lst.add(com.intermacs.images.model.ImagenModel.class);
		lst.add(com.intermacs.images.service.bo.ImagenServicioBO.class);
		lst.add(com.intermacs.images.service.ImagenServicio.class);
		lst.add(com.intermacs.images.model.entidades.ETipoImagen.class);

		lst.add(com.intermacs.core.lugares.model.LugaresModel.class);
		lst.add(com.intermacs.core.lugares.facade.bo.LugaresFacadeBO.class);
		lst.add(com.intermacs.core.lugares.facade.LugaresFacade.class);
		lst.add(com.intermacs.core.lugares.services.LugaresServicio.class);
		lst.add(com.intermacs.core.lugares.services.bo.LugaresServicioBO.class);
		lst.add(com.intermacs.core.lugares.model.entidades.Pais.class);
		lst.add(com.intermacs.core.lugares.model.entidades.Departamento.class);
		lst.add(com.intermacs.core.lugares.model.entidades.Ciudad.class);

	
		lst.add(com.intermacs.core.facade.bo.CatalogoFacadeBO.class);
		lst.add(com.intermacs.core.facade.CatalogoFacade.class);
		lst.add(com.intermacs.core.model.entidades.Catalogo.class);
		lst.add(com.intermacs.core.model.entidades.CatalogoDet.class);
		lst.add(com.intermacs.core.services.catalogo.bo.CatalogoServicioBO.class);
		lst.add(com.intermacs.core.services.catalogo.CatalogoServicio.class);

		   lst.add(com.intermacs.utils.DateUtils.class);
		   lst.add(com.intermacs.core.enums.ETipoUsuario.class);
		   lst.add(com.intermacs.core.enums.ETipoOpcion.class);
		   lst.add(com.intermacs.utils.StringUtils.class);
		   
		   
		   lst.add(com.intermacs.core.notificaciones.model.dto.NotificacionesDTO.class);
		   lst.add(com.intermacs.core.enums.ETipoPlantilla.class);
		   lst.add(com.intermacs.core.model.entidades.Plantilla.class);
		   lst.add(com.intermacs.core.notificaciones.model.NotificacionesModel.class);
		   lst.add(com.intermacs.core.notificaciones.services.bo.NotificacionesServiceBO.class);
		   lst.add(com.intermacs.core.notificaciones.services.NotificacionesService.class);
		   lst.add(com.intermacs.core.model.entidades.EntidadAuditada.class);
		   
		   lst.add(com.intermacs.security.ContextoLoginModule.class);
		   lst.add(com.intermacs.security.ContextoPrincipal.class);
		   
		   lst.add(com.intermacs.core.model.entidades.RolUsuario.class);
		   lst.add(com.intermacs.core.model.entidades.RolUsuarioPK.class);
		   
		   lst.add(com.intermacs.core.test.ResourcesTest.class);
		   lst.add(com.intermacs.core.test.security.ExplicitCallbackHandler.class);
		   
		   lst.add(com.intermacs.core.test.security.JBossLoginContextFactory.class);
			 lst.add(com.intermacs.core.test.Util.class);
		   
		   
		   Class arr[]=new Class[lst.size()] ;
		   int pos=0;
		   for (Class class1 : lst) {
			   arr[pos++]=class1;
		}
		   
		   return arr;   
	   }
	 
	/* protected SettingsContext getContextoSettings(){
		    SettingsContext context=new SettingsContext();	
		   Usuario userConsulta=new Usuario();
		    userConsulta.setUsuario("Admin");
		    userConsulta.setClaveLegible("1234");
		    
		    RequestDTO requestDTO=new RequestDTO(context);
		    requestDTO.setObject(userConsulta);  
		    
		    ResponseDTO resp;
			try {
				resp = seettingsServicio.consultarUsuario(requestDTO);
				 Usuario  userLogin=(Usuario) resp.getObject();
					//context.setMapIdMenu(mapIdMenu);
				 String userIpAddress="127.1.1.1";
				 try {
						userIpAddress=Inet4Address.getLocalHost().getHostAddress();
					} catch (UnknownHostException e1) {
					log.log(Level.SEVERE, "Error obteniendo ips", e1);
					}
				 
				 context=new SettingsContext(userIpAddress, userLogin.getRoles(),userLogin);
					
			} catch (ServicioFacadeExcepcion e) {
				
				 log.log(Level.SEVERE, "Error consultando usuario para los test", e);
				 return null;
			}	
			log.info("Contexto para Tests creado exitosamente. "); 
			return context;
	   }*/
	 
	 protected Date getFecha(int year,int month, int date){
		   Calendar calendar= Calendar.getInstance();
		   
		   calendar.set(year, month, date);
		   
		   return calendar.getTime();
	   }
	 
	
	 
	 @SuppressWarnings("unchecked")
	protected Ciudad getCiudadTest(){
		 RequestDTO reqCiu=new RequestDTO();
		   reqCiu.setParam( EParametro.IdEntidad, new Integer(85));
		  Ciudad ciudad=null; 
		   try {
			   if(lugarSrv==null)
			lugarSrv=lookupService(LugaresFacade.class); 
			
			 ResponseDTO resp= lugarSrv.consultarDepartamento(reqCiu);
			   Departamento deptoSelect=(Departamento) resp.getObject();
			   
			 
			   reqCiu.setParam( EParametro.Entidad , deptoSelect);
			    
			resp = lugarSrv.consultarCiudades(reqCiu);
							   
			   ciudad=((List<Ciudad>)resp.getResultList()).iterator().next();
			   
		} catch (ServicioFacadeExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		  
		return ciudad;
	 }
	 
	 @SuppressWarnings("unchecked")
	    protected List<CatalogoDet> getCatalogo(int idCatalogo) {
		 
		 List<CatalogoDet> tipoDocumentoItems=null;
		
		    RequestDTO req = new RequestDTO();
		    req.setParam(EParametro.CATALOGO, idCatalogo);
		    try {
		    	if(catalogoService==null)
		    		catalogoService=lookupService(CatalogoFacade.class);
			ResponseDTO resp = catalogoService
				.consultarLstCatalogDetalle(req);
			tipoDocumentoItems = (List<CatalogoDet>) resp
				.getParam(EParametro.ResultList);
		    } catch (ServicioFacadeExcepcion e) {
		    	 log.log(Level.SEVERE, "Error consultando catalogo para los test", e);
		    }
		
		return tipoDocumentoItems;
	    }
	 
	/* protected Persona getPersonaTestSinId(Ciudad ciudad){
		  Persona persona=new Persona();	  
		  persona.setApellido1("APELLIDO_A");
		  persona.setApellido2("APELLIDO_B");
		  persona.setNombres("NOMBRES_A_B");
		  persona.setNacCiudad(ciudad);
		  persona.setFecNacimiento(getFecha(1977, 06, 12));
		  persona.setDireccion("CASA CENTRO PARQUE");
		  persona.setBarrio("MI BARRIO");
		  persona.setEstado("A");
		  persona.setNumDocumento(""+123456789);
		  persona.setTipoDocumento(getCatalogo(ICatalogo.TIPOS_DOCUMENTO).iterator().next());	
		  
		  return persona;
	  }*/
	 
	  @EJB
	   UsuarioFacade userSrv;
	 protected Usuario testLoginUser(String user,String pass) throws Exception {
		 Usuario userConsulta=new Usuario();
		   RequestDTO req=new RequestDTO();
		  
		    userConsulta.setUsuario(user);
		    userConsulta.setClaveLegible(pass);
		   req.setObject(userConsulta);
		   ResponseDTO resp=userSrv.consultarUsuario(req);
		   userConsulta=(Usuario) resp.getObject();
		   
		   return userConsulta;
		   
		}
	 
	
	   /*public void testIniciarTest() throws Exception {
		   log.info("-------------------------TEST INI Ejecución-----------------------");
		
	   }*/
	 
	 public void login() throws LoginException{
	        lm = new ContextoLoginModule();
	        sharedState = new HashMap<>();
	        options = new HashMap<>();
	        subject = new Subject();
	        lm.initialize(subject, new ExplicitCallbackHandler(loginName, loginPassword), sharedState, options);	        
	        lm.login();
	    }
	 
	 /*****/
	   private Map<String, Object>  services =  new HashMap<String, Object>();
	   private Context context;
	   private static final String BEAN_NAME_CONVETION_ADD = "BO";
	   @SuppressWarnings("unchecked")
	   public <S> S lookupService(Class<S> clazz) {
	       if (services.get(clazz.getName()) == null) {
	    	   
	           S service = null;
	           try {
	               context = getInitialContext();
	               String jndi = getLookupName(clazz);              
	               log.log(Level.INFO,"Looking up: " + jndi);
	               service = (S) context.lookup(jndi);
	               services.put(clazz.getName(), service);
	               return service;

	               } catch (Exception ex) {
	            
	               log.log(Level.SEVERE, "Could not get reference to AddressService ", ex);
	               } finally {
	               if (context != null) {
	                   try {
	                       context.close();
	                       } catch (NamingException e) {
	                    	   log.log(Level.SEVERE, e.getMessage(), e);
	                   }
	               }
	           }
	       }
	       try {
		    	 subject=(Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
					if(subject!=null){
					System.out.println("userSubject.getPrincipals()--->>>>>"+Arrays.deepToString(subject.getPrincipals().toArray()));
					}
				} catch (PolicyContextException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	       return (S)services.get(clazz.getName());
	   }
	   
	   private <S> String getLookupName(Class<S> clazz) {
	       return "java:module/" + clazz.getSimpleName() + BEAN_NAME_CONVETION_ADD + "!" + clazz.getName();
	   }
	   
	   private Context getInitialContext() {
	       try {
	           /*final Hashtable jndiProperties = new Hashtable();
	           jndiProperties.put(Context.URL_PKG_PREFIXES, JBOSS_EJB_CLIENT);
	           jndiProperties.put(JBOSS_NAMING_CONTEXT, true);*/
	    	   Properties env = new Properties();
	      		env.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
	           return /*new InitialContext(jndiProperties)*/new InitialContext(env);
	           } catch (NamingException e) {
	           log.log(Level.SEVERE, e.getMessage(), e);
	       }
	       return context;
	   }
}
