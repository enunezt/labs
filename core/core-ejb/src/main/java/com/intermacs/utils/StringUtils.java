package com.intermacs.utils;

import java.text.DecimalFormat;

public class StringUtils {
	public static final String FORMAT_INT_MILES="###,###.###";
	/**
	 * Define si un string es nulo o vacio
	 * @param value
	 * @return true sí es nulo ó vacio, false si no
	 */
	public static boolean isNullOrEmpty(String value){
		
		if(value==null)
			return true;
		else
		return	"".equals(value.trim());
	}
	
	/**
	 * Retorna el string conla primera letra en mayuscula.
	 * @return string con letra capital
	 */
	public static String getCapitalString(String arg){
		if(!isNullOrEmpty(arg)){
			
		return	arg.substring(0,1).toUpperCase().concat(arg.toLowerCase().substring(1, arg.length()));
		}
		
		return null;
		
		
	}
	/**
	 * Verifica si el string dado por parametro es numerico.
	 * @param aTexto
	 * @return
	 */
	 public static boolean isNumeric(String aTexto)
	   {
	     boolean numero = false;
	     try {
	       Long.parseLong(aTexto);
	       numero = true;
	     } catch (Exception e) {
	       numero = false;
	     }
	     return numero;
	   }
	
	//setText(campo.substring(0,1).toUpperCase().concat(campo.substring(1, campo.length()))+":")
	 
	 //"$" + new DecimalFormat("###,###.###").format(reciboFinal.getValAntcp())
	 /**
	  * Formate numero segun parametro format
	  * @param format
	  * @param number
	  * @return
	  */
	 public static String formaNumber(String format, Long number){
		 
		return new DecimalFormat(format).format(number); 
	 }

}
