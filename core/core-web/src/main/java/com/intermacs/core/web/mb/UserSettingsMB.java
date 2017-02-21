package com.intermacs.core.web.mb;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.panelmenu.PanelMenu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.ui.context.Theme;

import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.facade.UsuarioFacade;
import com.intermacs.core.model.entidades.Menu;
import com.intermacs.core.model.entidades.Usuario;
import com.intermacs.exceptions.ServicioFacadeExcepcion;
import com.intermacs.images.facade.ImagenFacade;
import com.intermacs.images.model.entidades.Imagen;



/**
 * User settings.
 *
 * @author
 */
@ManagedBean(name = "userSettingsMB")
@ViewScoped
public class UserSettingsMB extends BaseMB {
    @Inject
    private Logger log;

    @Inject
    private UsuarioFacade usuarioServicio;
 

    @Inject
    private ImagenFacade imagenServicio;


    private String usuario;
    private String pass;

    private Usuario userLogin;
    private Long fotoId;


    protected transient PanelMenu model;
    protected transient PanelMenu modelMobil;
    private Map<Long, Long> menu;
    private Map<Long, Menu> mapIdMenu;

    private Map<String, String> themes;
    private List<Theme> advancedThemes;
    private String theme;

    private String infoUser;
    private String urlFoto;
    private Integer codigoApp;

    public Integer getCodigoApp() {
        return codigoApp;
    }

    public void setCodigoApp(Integer codigoApp) {
        this.codigoApp = codigoApp;
    }


    public String getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(String infoUser) {
        this.infoUser = infoUser;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public UserSettingsMB() {


        menu = new HashMap<Long, Long>();

		
		
    }
    



    @SuppressWarnings("unchecked")
    private void obtenerCredencialesusuario(HttpServletRequest request) throws ServicioFacadeExcepcion {

        Usuario userConsulta = new Usuario();
        userConsulta.setUsuario(usuario);
        userConsulta.setClaveLegible(pass);

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setObject(userConsulta);

        ResponseDTO resp =null;
        Object _estudiante=null; 

        resp =usuarioServicio.consultarUsuario(requestDTO);
        
        
        userLogin = (Usuario) resp.getObject();
        requestDTO.setObject(userLogin);

        requestDTO = new RequestDTO();
        requestDTO.setObject(userLogin);
        resp = usuarioServicio.consultarMenu(requestDTO);
        mapIdMenu = (Map<Long, Menu>) resp.getParam(EParametro.Map);//todo el menu


        //String ip = getClientIpAddr(request);

        String userIpAddress = "";//"RemoteAddr:"+ip+"-LocalAddr:"+request.getLocalAddr();

        try {
        	
        	userIpAddress = request.getHeader("X-FORWARDED-FOR");
        	if (userIpAddress == null) {
        		userIpAddress = request.getRemoteAddr();
        	}
        	if(userIpAddress==null)
            userIpAddress = Inet4Address.getLocalHost().getHostAddress();


        } catch (UnknownHostException e1) {
            log.log(Level.SEVERE, "Error obteniendo ips", e1);
        }
       // context = new SettingsContext(userIpAddress, userLogin.getPerfiles(), mapIdMenu, userLogin);
        //context.putParam(EParametroCartera.EntidadEstudiante.getName(),_estudiante );
        
        

        requestDTO = new RequestDTO();
        requestDTO.setParam(EParametro.User, userLogin.getIdUsuario());
        resp = imagenServicio.consultarIdFotoUsuario(requestDTO);
        fotoId = (Long) resp.getParam(EParametro.Imagen);
        //context.setFotoId(fotoId);

        if (fotoId != null) {
            String dirTMP = System.getProperty("java.io.tmpdir");
            requestDTO = new RequestDTO();
            requestDTO.setParam(EParametro.Imagen, fotoId);
            Imagen imgsrc = imagenServicio.consultarImagen(requestDTO);
            try {
                byte[] imageInByte = imgsrc.getContent();

                // convert byte array back to BufferedImage
                InputStream in = new ByteArrayInputStream(imageInByte);
                BufferedImage bImageFromConvert = ImageIO.read(in);
                urlFoto = dirTMP + imgsrc.getNombre() + "." + imgsrc.getExtension();
               // context.setUrlFoto(urlFoto);
                ImageIO.write(bImageFromConvert, imgsrc.getExtension().toString(), new File(urlFoto));


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        log.info(userIpAddress + " login()-->" + userLogin.getUsuario());

    }

    public String verificarUsuario() {
      
        return null;
    }

    public Map<String, String> getThemes() {
        return themes;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @PostConstruct
    public void inicial() {

        if (theme == null) {
            theme = "unitropico2";
        }


        /*if (context != null && context.getTema() != null) {
            theme = context.getTema();
        }*/

        advancedThemes = new ArrayList<Theme>();
        /*advancedThemes.add(new Theme("aristo", "aristo.png"));
        advancedThemes.add(new Theme("cupertino", "cupertino.png"));
        advancedThemes.add(new Theme("trontastic", "trontastic.png"));*/

        themes = new TreeMap<String, String>();
       
        themes.put("Blitzer", "blitzer");  
        themes.put("Cupertino", "cupertino"); 
        themes.put("Excite-Bike", "excite-bike");
        themes.put("Glass-X", "glass-x");
        themes.put("Humanity", "humanity");      
        themes.put("Pepper-Grinder", "pepper-grinder");
        
        themes.put("Redmond", "redmond");        
      
        themes.put("South-Street", "south-street"); 
        themes.put("Unitrópico", "unitropico");
        themes.put("Unitrópico A", "unitropico1");
        themes.put("Unitrópico B", "unitropico2");
        
        

        if (getModel() == null) {
            model = new PanelMenu();
            modelMobil = new PanelMenu();
            model.setModel(construirMenu());
            modelMobil.setModel(construirMenu());
           // getContext().setMenuUser(model);
        }
        
        
        

		
		/* File file = new File("c:\\Users\\enunezt\\Pictures\\foto.jpg");//Bibliotecas\Imágenes
		   byte[] bFile = new byte[(int) file.length()];
		   
		   try {
		   FileInputStream fileInputStream = new FileInputStream(file);
		   fileInputStream.read(bFile);
		   fileInputStream.close();
		   } catch (Exception e) {
		   	   log.log(Level.SEVERE, "testCrearOpcion", e);
		   }
		   Imagen img=new Imagen();
		   img.setIdEntidad(11L);
		   img.setNombre("imagenPart-"+1);
		   img.setTipo(EImagen.FOTO_PERFIL);
		   img.setFechaRegistro(UtilsDate.getFechaSistema());
		   img.setExtension(ETipoImagen.jpg);
		   img.setContent(bFile); 
		   
		   try {
			imagenServicio.crear(img) ;
			fotoId=img.getId();
			  log.info(img.getNombre()+ " creado exitosamente  numero " + img.getId());
		} catch (ServicioFacadeExcepcion e) {
		addErrorMessage(e);
		} catch (Exception e) {
			addErrorMessage(e);
		}*/

    }

    public void saveThemeEvent() {

        /* (context != null) {
            context.setTema(theme);
        }*/
        ///  gp.setTheme(theme);
    }

    public List<Theme> getAdvancedThemes() {
        return advancedThemes;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    private MenuModel construirMenu() {

 MenuModel mM = new DefaultMenuModel();
 DefaultSubMenu sbm2=new DefaultSubMenu();
 sbm2.setLabel("Principal");
 	DefaultMenuItem item=new DefaultMenuItem();
 		item.setValue("Index");
 		item.setUrl("../index.jsf");
 		sbm2.addElement(item);
 		
 		mM.addElement(sbm2); 

   

        return mM;
    }

    public String rec(long n, String path) {

        if (menu.get(new Long(n)) == null) {
            return path = path + n;
        } else {
            Long e = menu.get(new Long(n));

            path = path + n + "-";
            return rec(e, path);
        }

    }

    public PanelMenu getModel() {
        return model;
    }

    public void setModel(PanelMenu model) {
        this.model = model;
    }

    public Long getFotoId() {
        return fotoId;
    }

    public void setFotoId(Long fotoId) {
        this.fotoId = fotoId;
    }


    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public PanelMenu getModelMobil() {
        return modelMobil;
    }

    public void setModelMobil(PanelMenu modelMobil) {
        this.modelMobil = modelMobil;
    }


}