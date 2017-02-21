package com.intermacs.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BundleUtils {	
	
	public static String  BUNDLE_APPS="core";
	public static String  DPTO_LOCAL="core.departamento";
	public static String  MUNICIPIO_LOCAL="core.municipio";
	
	/**
	 * Retorna ResourceBundle que se pase como parametro
	 * @param pathBundle
	 * @return
	 */
	public static ResourceBundle getBundle(String pathBundle){
		
		return ResourceBundle.getBundle(pathBundle);//"co.paquete.conf"
	}
	
	/**
	 * Retorna el valor de la llave que se pase como parmetro.
	 * Archivo cartera.properties
	 * @param key
	 * @return
	 */
	public static String getValBundleApps(String key){		
		return getBundle(BUNDLE_APPS).getString(key);
	}
	
	
	
	public static String getString(String value, Object... params  ) {
	        try {
	            return MessageFormat.format(value, params);
	        } catch (MissingResourceException e) {
	            return '!' + value + '!';
	        }
	    }
	

}
