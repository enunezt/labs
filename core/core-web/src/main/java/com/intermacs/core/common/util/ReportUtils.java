package com.intermacs.core.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 * Clase Generica para la generacion de reportes
 * @author ENUNEZT
 */
public class ReportUtils {
	
	private static Logger logger= Logger.getLogger(ReportUtils.class.getName());
	 public static String LOGO = "Logo_U_transparente.png";
	 public static String LOGO_BACKGROUND = "Logo_Unitropico_bckgr.png";
		public static String FILE_PDF="PDF";
		public static String FILE_XLS="XLS";
		public static String FILE_WORD="WORD";
	
	
	 public static void generarReportePDFLinkExtern(String pathReporte, java.util.Collection<?> listObject,Map<String,Object> parametros, String nombre) {
         ServletContext application = FacesUtils.getServletContext();
         String disenoReporte = application.getRealPath(pathReporte);
         JasperPrint print;        
         try {
        	 //Compilar el informe requiere org.codehaus.groovy
             //JasperReport informeCompilado1 =JasperCompileManager.compileReport(disenoReporte+".jrxml");    
             
             JasperReport informeCompilado=(JasperReport) JRLoader.loadObjectFromFile(disenoReporte+".jasper");
           
               if    (listObject!=null)      
               print = JasperFillManager.fillReport(informeCompilado, parametros, new JRBeanCollectionDataSource(listObject));
               else     
               print = JasperFillManager.fillReport(informeCompilado, parametros, new JREmptyDataSource());
               
               FacesUtils.getServletResponse().setContentType("application/pdf");
               FacesUtils.getServletResponse().setHeader("Content-Disposition", "attachment;filename="+nombre+".pdf");

              /* net.sf.jasperreports.engine.export.JRPdfExporter exporter = new JRPdfExporter();
                 exporter.setParameter( JRPdfExporterParameter.JASPER_PRINT, print );
                 exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, FacesUtils.getServletResponse().getOutputStream());                                  
                 exporter.exportReport();*/
                     //JasperExportManager.exportReportToPdfStream(print, FacesUtils

                          //.getServletResponse().getOutputStream());
                JasperExportManager.exportReportToPdfStream(print, FacesUtils.getServletResponse().getOutputStream());
                  FacesContext.getCurrentInstance().responseComplete();
         } catch (Exception e) {
        	 e.printStackTrace();
 			FacesUtils.addErrorMessage(e.getMessage());
         }     

       
   }
	
		/**
		 * Método encargado de generar PDF y enviarlo al navegador
		 * @param urlReporte Ruta del Reporte .jasper
		 * @param parametros Parametros para el reporte
		 * @param beanCollectionDS colecci�n de beans usados c�moD ataSource
		 */
		public static void exportToPDF(String urlReporte,Map<String, Object> parametros,Collection<?> beanCollectionDS,String nombre){
			
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext ext = context.getExternalContext();
			try{
			byte[] bytes = JasperRunManager.runReportToPdf(urlReporte, parametros,				
					beanCollectionDS!=null?new JRBeanCollectionDataSource(beanCollectionDS):null);

			HttpServletResponse response = (HttpServletResponse) ext.getResponse();
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "attachment;filename="+nombre+".pdf");
			ServletOutputStream servletOutputStream = response.getOutputStream();

			servletOutputStream.write(bytes, 0, bytes.length);

			servletOutputStream.flush();
			servletOutputStream.close();
			FacesContext.getCurrentInstance().responseComplete();	
			}catch (JRException e) {
				logger.log(Level.SEVERE,"Error Generando reporte PDF",e); 
			}
			catch (IOException e) {
				logger.log(Level.SEVERE,"Error Generando reporte PDF",e); 
			} 
			
			
		}
		/**
		 * Método encargado de generar PDF y devolverlo al navegador
		 * @param pathReporte
		 * @param listObject
		 * @param parametros
		 * @param nombre
		 */
		 public static void generarReportePDFLink(String urlReporte, java.util.Collection<?> listObject,Map<String,Object> parametros, String nombre) {
	         ServletContext application = FacesUtils.getServletContext();
	         String disenoReporte = application.getRealPath(urlReporte);
	         JasperPrint print;        
	         try {
	        	  JasperReport informeCompilado=(JasperReport) JRLoader.loadObjectFromFile(disenoReporte);
	           
	               if    (listObject!=null)      
	               print = JasperFillManager.fillReport(informeCompilado, parametros, new JRBeanCollectionDataSource(listObject));
	               else     
	               print = JasperFillManager.fillReport(informeCompilado, parametros, new JREmptyDataSource());
	               OutputStream out=FacesUtils.getServletResponse().getOutputStream();
	               
	               
	               FacesUtils.getServletResponse().setContentType("application/pdf");
	               FacesUtils.getServletResponse().setHeader("Content-Disposition", "attachment;filename="+nombre+".pdf");
	               JasperExportManager.exportReportToPdfStream(print, FacesUtils.getServletResponse().getOutputStream());
	                 
	               if (out != null) {
	                       out.close();
	                   }
	                   FacesContext.getCurrentInstance().responseComplete();
	             
	               
	         } catch (Exception e) {
	        	 logger.log(Level.SEVERE,"Error Generando reporte PDF",e); 
	         }  
		 }
		 
		 
		 
		 /**
			 * Metodo para la generacion de reportes a Excel
			 * @param pathReporte
			 * @param listObject
			 * @param parametros
			 */
			@SuppressWarnings("rawtypes")
			public static void generarReporteXLSLink(String urlReporte, java.util.Collection<?> listObject,Map<String,Object> parametros, String nombre){
				ServletContext application = FacesUtils.getServletContext();
				String disenoReporte = application.getRealPath(urlReporte);
				JasperPrint print;
				try {
					 //Compilar el informe 
		           // JasperReport informeCompilado =JasperCompileManager.compileReport(disenoReporte+".jrxml");           
		            JasperReport informeCompilado=(JasperReport) JRLoader.loadObjectFromFile(disenoReporte);
		            			
					if	(listObject!=null)	
					print = JasperFillManager.fillReport(informeCompilado, parametros, new JRBeanCollectionDataSource(listObject));
					else     
					print = JasperFillManager.fillReport(informeCompilado, parametros, new JREmptyDataSource());
						    
					    //  JasperExportManager.exportReportToPdfStream(print,FacesUtils.getServletResponse().getOutputStream() );
					    FacesUtils.getServletResponse().setContentType("application/vnd.ms-excel");
						FacesUtils.getServletResponse().setHeader("Content-Disposition", "inline; filename=\""+nombre+".xls\"");
										
						
						 Map<String,String> dateFormats=new HashMap<String,String>();
						  dateFormats.put("DD/MM/YYYY","DD/MM/YYYY");
						  JRXlsExporter exporter=new JRXlsExporter();
						  SimpleXlsReportConfiguration repConfig=new SimpleXlsReportConfiguration();
						  exporter.setExporterInput(new SimpleExporterInput(print));
						  exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(FacesUtils.getServletResponse().getOutputStream()));
						  repConfig.setDetectCellType(Boolean.TRUE);
						  repConfig.setFormatPatternsMap(dateFormats);
						  repConfig.setIgnoreCellBackground(Boolean.FALSE); 
						  repConfig.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
						  repConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
						  //repConfig.setCollapseRowSpan(Boolean.TRUE); 	
						  //repConfig.setWhitePageBackground(Boolean.FALSE);
						  //exporter.setConfiguration(repConfig); 
				           
						  exporter.exportReport();
						
						FacesContext.getCurrentInstance().responseComplete();

				} catch (Exception e) {
					e.printStackTrace();
					FacesUtils.addErrorMessage(e.getMessage());
				}
			}
			
			
			
			 /**
			 * Metodo para la generacion de reportes a Excel
			 * @param pathReporte
			 * @param listObject
			 * @param parametros
			 */
			@SuppressWarnings("rawtypes")
			public static void generarReporteDocxLink(String urlReporte, java.util.Collection<?> listObject,Map<String,Object> parametros, String nombre){
				ServletContext application = FacesUtils.getServletContext();
				String disenoReporte = application.getRealPath(urlReporte);
				JasperPrint print;
				try {
					 //Compilar el informe 
		           // JasperReport informeCompilado =JasperCompileManager.compileReport(disenoReporte+".jrxml");           
		            JasperReport informeCompilado=(JasperReport) JRLoader.loadObjectFromFile(disenoReporte);
		            			
					if	(listObject!=null)	
					print = JasperFillManager.fillReport(informeCompilado, parametros, new JRBeanCollectionDataSource(listObject));
					else     
					print = JasperFillManager.fillReport(informeCompilado, parametros, new JREmptyDataSource());
						    
					    //  JasperExportManager.exportReportToPdfStream(print,FacesUtils.getServletResponse().getOutputStream() );
					    FacesUtils.getServletResponse().setContentType("application/docx");//application/rtf  //application/docx
						FacesUtils.getServletResponse().setHeader("Content-Disposition", "inline; filename=\""+nombre+".docx");
										
						
						 Map<String,String> dateFormats=new HashMap<String,String>();
						  dateFormats.put("DD/MM/YYYY","DD/MM/YYYY");
						 // JRXlsExporter exporter=new JRXlsExporter();
						  //JRRtfExporter exporter=new JRRtfExporter();
						  JRDocxExporter exporter=new JRDocxExporter();
						  SimpleDocxReportConfiguration _config=new SimpleDocxReportConfiguration();
						 /* _config.setFlexibleRowHeight(Boolean.FALSE);
						  _config.setFramesAsNestedTables(Boolean.TRUE); */
					
						  exporter.setExporterInput(new SimpleExporterInput(print));
					      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(FacesUtils.getServletResponse().getOutputStream()));
					    
					      exporter.setConfiguration(_config); 
					      
						 					  /*para los reportes en texto debemos expresar el tamaÃ±o de la pagina*/
				      /*      exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Integer(70).floatValue());
				            exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Integer(70).floatValue());
				            exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(15).floatValue());
				            exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(15).floatValue());
				    */
				           
						  exporter.exportReport();
						
						FacesContext.getCurrentInstance().responseComplete();

				} catch (Exception e) {
					e.printStackTrace();
					FacesUtils.addErrorMessage(e.getMessage());
				}
			}

}
