package com.intermacs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
 * Clase utlitaria para Fechas y tiempo
 * @author intermacs
 *
 */
public class DateUtils {
	
	public static final String LENGUAGE_LOCAL="es";
	/**Format yyyy-MM-dd**/
	public static final String YYYY_MM_DD="yyyy-MM-dd";
	
	
	/**Format dd/MM/yyyy**/
	public static final String F1_DD_MM_YYYY="dd/MM/yyyy";
	
	/**Format dd-MM-yyyy**/
	public static final String F2_DD_MM_YYYY="dd-MM-yyyy";
	
	/**Format dd-MM-yyyy HH:mm:ss**/
	public static final String F_DD_MM_YYYY_H_m_s="dd-MM-yyyy HH:mm:ss";
	
	
	
	
	
	//public static final String DD_MM_YYYY="yyyy-MM-dd";//dd-MM-yyyy
	/**
	 * Retorna fecha maxima futura tipo Date 
	 * @return
	 */
	public static Date getFechaComodinFutura(){
		Calendar cal=Calendar.getInstance();
		cal.set(9999, 11, 31);			
		return cal.getTime();	
	}
	
	/**
	 * Retorna Fecha Del sistema
	 * @return
	 */
	public static Date getFechaSistema(){		
		Calendar cal=Calendar.getInstance();					
		return cal.getTime();	
	}
	
	/**
	 * Retorna Fecha Del sistema
	 * @return
	 */
	public static Date getFechaActual(){		
		Calendar cal=Calendar.getInstance();					
		return cal.getTime();	
	}
	
	/**
	 * Retorna el mes de una fecha Dada
	 * @param fecha
	 * @return mes ó -1 si la fecha es nula
	 */
	public static int getMes(Date fecha){
		if(fecha!=null){
			Calendar cal=Calendar.getInstance();	
			cal.setTime(fecha);			
			return cal.get(Calendar.MONTH)+1;			
		}
		
		return -1;
	}
	
	/**
	 * Retorna el NOMBRE del mes de una fecha Dada
	 * @param fecha
	 * @return mes ó -1 si la fecha es nula
	 */
	public static String getNombreMes(Date fecha,Locale locale){
		
		if(fecha!=null){
			locale=locale==null?new Locale(LENGUAGE_LOCAL):locale;
			
			Calendar cal=Calendar.getInstance();	
			cal.setTime(fecha);		
			return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
			//return cal.get(Calendar.)+1;			
		}
		
		return null;
	}
	
	/**
	 * Retorna la fecha en españo  en formato: {num dia} de {nombre mes} del {num año}
	 * @param fecha
	 * @return {int dia} de {String mes} del {int año}
	 */
	public static String getNombreFecha(Date fecha,Locale locale){
		
		if(fecha!=null){
			locale=locale==null?new Locale(LENGUAGE_LOCAL):locale;
			
			Calendar cal=Calendar.getInstance();	
			cal.setTime(fecha);	
			String mes=cal.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
			int dia=cal.get(Calendar.DAY_OF_MONTH);
			int anio=cal.get(Calendar.YEAR);
			return dia+" de "+mes.toLowerCase()+ " del "+anio;
		}
		
		return null;
	}
	
	/**
	 * Retorna el dia de una fecha Dada
	 * @param fecha
	 * @return mes ó -1 si la fecha es nula
	 */
	public static int getDia(Date fecha){
		if(fecha!=null){
			Calendar cal=Calendar.getInstance();	
			cal.setTime(fecha);			
			return cal.get(Calendar.DAY_OF_MONTH);			
		}
		
		return -1;
	}
	
	/**
	 * Retorna el Año de una fecha Dada
	 * @param fecha
	 * @return año ó -1 si la fecha es nula
	 */
	public static int getAnio(Date fecha){
		if(fecha!=null){
			Calendar cal=Calendar.getInstance();	
			cal.setTime(fecha);			
			return cal.get(Calendar.YEAR);			
		}
		
		return -1;
	}
	
	
	/**
	 * Adiciona la cantidad de meses que se pasen como parametro
	 * @param fecha
	 * @param numMeses pude ser negativo (resta)
	 * @return fecha +- meses
	 */
	public static Date addMes(Date fecha,int numMeses){
		if(fecha!=null){
			Calendar cal=Calendar.getInstance();	
			cal.setTime(fecha);	
			cal.add(Calendar.MONTH, numMeses);
			return cal.getTime();			
		}
		
		return fecha;
	}
	
	
	/**
	 * Retorna la fecha con los valores pasados como parametros
	 * @param anio
	 * @param mes
	 * @param dia
	 * @return
	 */
	public static Date getDate(int anio, int mes, int dia){		
			Calendar cal=Calendar.getInstance();	
			cal.set(anio, mes-1, dia);
			return cal.getTime();	
	}
	/**
	 * Adiciona a la fecha la hora:minuto:segundo que se pase como parametro. 
	 * @param fecha Fecha a modificar
	 * @param horas horas a adicionar 0-24
	 * @param minutos munutos a adicionar
	 * @param segundos segundos a adicionar
	 * @return fecha modificada
	 */
	public static Date addHoraMinutoAFecha(Date fecha,int horas,int minutos, int segundos){
		Calendar cal=Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.HOUR_OF_DAY, horas);
		cal.add(Calendar.MINUTE, minutos);
		cal.add(Calendar.SECOND, segundos);
		return cal.getTime();
	}
	public static Date addDiasAFecha(Date fecha,int dias){
		Calendar cal=Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_YEAR,dias);
		return cal.getTime();
	}
	/**
	 * Adiciona a setDateHora la hora:minuto:segundo de getDateHora. 
	 * @param setDateHora
	 * @param getDateHora
	 * @return Date setDateHora modificada
	 */
	public static Date addHoraMinutoAFecha(Date setDateHora,Date getDateHora){
		
		if(setDateHora==null && getDateHora==null){
			return null;
		}else if(getDateHora==null){
			return setDateHora;
		}else if(setDateHora==null){
			return null;
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(setDateHora);
		
		Calendar cal2=Calendar.getInstance();
		cal2.setTime(getDateHora);		
		cal.add(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY)); 
		cal.add(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
		cal.add(Calendar.SECOND, cal2.get(Calendar.SECOND));
		return cal.getTime();
	}
	/**
	 * Da formato a la fecha dada con el formato dado
	 * @param fecha
	 * @param format
	 * @return Date formato
	 */
	public static Date formatDate(Date fecha, String format){
		if(fecha!=null){		
		String newstring = new SimpleDateFormat(format).format(fecha);
		try {
			return new SimpleDateFormat(format).parse(newstring);
		} catch (ParseException e) {
		
		}
		}
		return fecha; 
	
	}
	/**
	 * Da formato a la fecha dada con el formato dado
	 * @param fecha
	 * @param format
	 * @return Date formato
	 */
	public static String formatDateToString(Date fecha, String format){
		
		if(fecha==null){
			return "";
		}
		
		String newstring = new SimpleDateFormat(format).format(fecha);
		
		return newstring; 
	
	}
	
	/**
	 * Retorna Date
	 * @param fecha
	 * @param format
	 * @return Date
	 */
		public static Date formatStringToDate(String fecha,String format){
		
		if(format==null){
			return null;
		}
		
		try {
			return new SimpleDateFormat(format).parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;  
	
	}
		/**
		 * Verifica si date1 es mayor a date2
		 * @param date1
		 * @param date2
		 * @param pattern
		 * @return
		 */
		public static boolean isDate1MayorDate2(Date date1,Date date2,String pattern){
			
			//SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			/*Date date1 = sdf.parse("2009-12-31");
			Date date2 = sdf.parse("2010-01-31");*/			
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(date1);
			cal2.setTime(date2);
			return cal1.after(cal2);
			/*if(cal1.after(cal2)){//despues
				System.out.println("Date1 is after Date2");
			}
			
			if(cal1.before(cal2)){
				System.out.println("Date1 is before Date2");
			}
			
			if(cal1.equals(cal2)){
				System.out.println("Date1 is equal Date2");
			}*/
			
		}
	
public static void main(String... args){
	
System.out.println(getMes(new Date()));
System.out.println(getAnio(new Date()));
}
}
