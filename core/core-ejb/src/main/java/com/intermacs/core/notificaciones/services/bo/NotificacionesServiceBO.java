package com.intermacs.core.notificaciones.services.bo;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.intermacs.commons.annotations.CoreUnitPersistence;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.enums.ETipoPlantilla;
import com.intermacs.core.model.entidades.Plantilla;
import com.intermacs.core.notificaciones.model.dto.NotificacionesDTO;
import com.intermacs.core.notificaciones.services.NotificacionesService;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.utils.StringUtils;

@Local(NotificacionesService.class)  
@Singleton  
@Startup  
public class NotificacionesServiceBO implements NotificacionesService{  
    public static final Logger logger = Logger.getLogger(NotificacionesServiceBO.class.getName());  
    public static final String EMAIL_SESSION_JNDI_PATH =  "java:jboss/mail/Central";  
    public static final String EMAIL_TAG_REMPLAZAR = "@contenido";  
    
    @CoreUnitPersistence
    @Inject   
    private EntityManager em;  
      
    //@Resource(name =EMAIL_SESSION_JNDI_PATH)  
    private Session mailSession; 
    
    @Resource 
  	SessionContext ctx;
      
   /* @PostConstruct  
    public void start() throws Exception {  
    } */ 

    @PreDestroy  
    public void destroy(){  
    }  
      
    //*****************[interface methods]***************//  
    //@Override 
    private boolean sendMail(NotificacionesDTO email){ /*Email email*/
    	
    	
    	try    {
    		
    		
    		
    		if(mailSession==null){
    			mailSession=getEmailSession();	
    		}
    		
    		MimeMessage m = new MimeMessage(mailSession);
    		
    			
			if (email.getCorreosDestino()==null || email.getCorreosDestino().isEmpty() || StringUtils.isNullOrEmpty(email.getAsunto())||
					StringUtils.isNullOrEmpty(email.getContenido())
							|| StringUtils.isNullOrEmpty(email.getCorreoRemitente())) {
				
				logger.log(Level.SEVERE, "Error enviando Mail: destinatario, asunto,contenido y remitente son obligatorios");   
				 return false;  

			}    		
    		
    		
    		Address from = new InternetAddress(email.getCorreoRemitente());
    		Address[] to=new Address[ email.getCorreosDestino().size()] ;
   		   int pos=0;
   		   for (String emailDestino : email.getCorreosDestino()) {
   			   
   			if(validateEmail(emailDestino))
    		{
   				to[pos++]=new InternetAddress(emailDestino);
    		}else{
    			logger.log(Level.SEVERE, "Error enviando Mail: destinatario correo invalido");       			
    		}
   			   
   			
   		}
    		
    		
    	//	Address[] to =(Address[]) email.getCorreosDestino().toArray();
   		// msg.setRecipients(Message.RecipientType.CC, ccAddress);
       //  msg.setRecipients(Message.RecipientType.BCC, bccAddress); 
    		m.setFrom(from);	m.setFrom();
    		m.setRecipients(Message.RecipientType.TO, to);
    		m.setSubject(email.getAsunto(),"UTF-8");
    		m.setSentDate(new java.util.Date());
    		//m.setText(email.getContenido(), "UTF-8", "html");
    		m.setContent(email.getContenido(),email.getTipo()+"; charset=UTF-8");
    		Transport.send(m);
    		logger.info("Correo enviado : from :"+email.getCorreoRemitente()+" asunto:"+email.getAsunto()+"");
    		 return true; 
    		}
    		catch (javax.mail.MessagingException e)
    		{
    		logger.log(Level.SEVERE, "Error in Sending Mail: ",e);    		
    		 return false;  
    		} catch (ServicioExcepcion e) {
    			logger.log(Level.SEVERE, "Error in Sending Mail: ",e);
    			return false; 
			}
       
    }  
      
   

	@Override
	public ResponseDTO enviarEmail(RequestDTO request) throws ServicioExcepcion {
		ResponseDTO response=new ResponseDTO();
		NotificacionesDTO email=(NotificacionesDTO) request.getObject();
		
		if(email!=null){
			/*if(email.isPersonasDestino()){
			for (Long destinatario : email.getPersonasDestino()) {    			
				String emailDestinatario=personaServicio.consultarEmailPersona(destinatario);
				if(validateEmail(emailDestinatario)){					
					email.addCorreoDestino(emailDestinatario); 
				}
			}}*/
			
			/*if(email.getIdPersonaRemitente()!=null && 
					!StringUtils.isNullOrEmpty(email.getCorreoRemitente())){
			String emailRemitente=personaServicio.consultarEmailPersona(email.getIdPersonaRemitente());
			if(validateEmail(emailRemitente)){					
				email.setCorreoRemitente(emailRemitente); 
			}
			}*/
			//completo contenido desde plantilla
			if(email.isOrigenPlantilla()){
				RequestDTO reqPlantilla=new RequestDTO();
				reqPlantilla.setParam(EParametro.ID_OBJECT, email.getIdPlantilla());
				
				ResponseDTO resp=consultarPlantilla(reqPlantilla);
				
				if(resp.isExisteResultado()){
					Plantilla plantilla=(Plantilla) resp.getObject();
					//"text/plain"
					if(ETipoPlantilla.HTML.equals(plantilla.getTipo())){
						email.setTipo("text/html");	 
					}
					
					String contenido=plantilla.getContenido();					
					String newContenido=contenido.replace(EMAIL_TAG_REMPLAZAR, email.getContenido());					
					email.setContenido(newContenido); 
						
				}				
			}
			

			response.setExisteResultado(sendMail(email));
		}
		
	 
		return response;
	}

	@Override
	public ResponseDTO consultarPlantilla(RequestDTO request)
			throws ServicioExcepcion {
	   Long idPlantilla=	(Long) request.getParam(EParametro.ID_OBJECT);		
		
   	 	Query query = em.createNamedQuery(Plantilla.NQ_findById);
             query.setParameter("idPlantilla", idPlantilla);
             Object resp=null;
		try {
			resp= query.getSingleResult();
		} catch (NoResultException e) {
			resp=null;
		}
		
		ResponseDTO repTO=new ResponseDTO(resp);
		repTO.setExisteResultado(resp!=null); 
		
		return repTO;
	}

	/*@Override
	protected EntityManager getEntityManager() {
				return em;
	}*/  
    
    private Session getEmailSession() throws ServicioExcepcion {  
        InitialContext context;
		try {
			context = new InitialContext();
			return (Session) context.lookup(EMAIL_SESSION_JNDI_PATH);
		} catch (NamingException e) {
			throw new ServicioExcepcion("Error obteniendo la conexión al servidor de correo.", e);
		}  
          
    }  
	
//TODO PASAR A UN UTILS	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
    /**
     * Validate given email with regular expression.
     *
     * @param email
     *            email for validation
     * @return true valid email, otherwise false
     */
    public static boolean validateEmail(String email) {
    	
    	if(StringUtils.isNullOrEmpty(email)){
    	return	false;
    	}
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
    
    
    
}  