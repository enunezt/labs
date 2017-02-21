package com.intermacs.utils;

import java.math.BigDecimal;

public class MathUtils {
	
	/**
	 * Suma los valores de los valores pasados como paramtros.
	 * Si el valor del ambos es null el resultado es null.
	 * Si uno de los dos dos es null retorna el valor no null.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static Integer sumar(Integer a, Integer b){
		if(a==null && b==null){
			return null;
		}
		int ai=a!=null?a.intValue():0;
		int bi=b!=null?b.intValue():0;
       return Integer.valueOf(ai+bi);
	}
	
	/**
	 * Suma los valores de los valores pasados como paramtros.
	 * Si el valor del ambos es null el resultado es 0.
	 * Si uno de los dos dos es null retorna el valor no null 0.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int sumarNull(Integer a, Integer b){
		if(a==null && b==null){
			return 0;
		}
		int ai=a!=null?a.intValue():0;
		int bi=b!=null?b.intValue():0;
       return ai+bi;
	}
	
	/**
	 * Suma los valores de los valores pasados como paramtros.
	 * Si el valor del ambos es null el resultado es 0.
	 * Si uno de los dos dos es null retorna el valor no null.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double sumarNull(Double a, Double b){
		if(a==null && b==null){
			return 0;
		}
		double ai=a!=null?a.doubleValue():0;
		double bi=b!=null?b.doubleValue():0;
       return ai+bi;
	}
	/**
	 * Redonden un entero segun al numero de digitos que se pase como argumento
	 * @param value
	 * @param numDigitos
	 * @return
	 */
	
	public static int roundInt(Integer value, int numDigitos){
		double d=Math.pow(10, numDigitos);
		double n=value.intValue()/d;
		double max=Math.ceil(n);
		return (int) (max*d);
	}
	
	/**
	 * Retorna un numero aleatorio segun el numero de cifras del argumento
	 * @param numCifras
	 * @return
	 */
	public static int getNumAleatorio(int numCifras){
		
	Double _val1=Math.random()*Math.pow(10, numCifras);
	
	return _val1.intValue();
	
	}
	
	/**
	 * Realiza el redondeo del numero pasado como parametro con la cantidad
	 * de decimales pasado como parametro.
	 * @param numero valor a redondear
	 * @param numeroDecimales numero de decimales a tener en cuenta
	 * @return
	 */
	public static Float redondear(float numero, int numeroDecimales){
		
		 BigDecimal _round=new BigDecimal(numero);
		  _round=_round.setScale(numeroDecimales,BigDecimal.ROUND_HALF_UP);
		return _round.floatValue();
	}
	/**
	 * Realiza el redondeo del numero pasado como parametro con la cantidad
	 * de decimales pasado como parametro. 
	 * @param numero
	 * @param numeroDecimales
	 * @return
	 */
	public static double redondear(double numero, int numeroDecimales)
	   {
	     String val = numero + "";
	     BigDecimal big = new BigDecimal(val);
	     big = big.setScale(numeroDecimales, BigDecimal.ROUND_HALF_UP);
	     
	     return big.doubleValue();
	   }
	
	/**
	 * Realiza el redondeo del numero pasado como parametro con la cantidad
	 * de decimales pasado como parametro. 
	 * @param numero
	 * @param numeroDecimales
	 * @return
	 */
	public static BigDecimal redondear(BigDecimal numero, int numeroDecimales)
	   {
	   
	     numero = numero.setScale(numeroDecimales, BigDecimal.ROUND_HALF_UP);
	     
	     return numero;
	   }
	
	
	public static void main(String...args ){
		
		/*System.out.println(roundInt(5, 1)); 
		System.out.println(roundInt(15, 1)); 
		System.out.println(roundInt(95, 1)); */
		
		System.out.println(getNumAleatorio(2)); 
		
	}

}
