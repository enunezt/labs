/**
 * 
 */
package com.intermacs.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * @author eanunezt
 *
 */
public class ImagenUtils {

	/**
	 * 
	 */
	public ImagenUtils() {
		// TODO Auto-generated constructor stub
	}
	
	  public static BufferedImage getImagen(byte[] imagen) throws IOException {


	        ByteArrayInputStream bis = new ByteArrayInputStream(imagen);
	        Iterator<?> readers = ImageIO.getImageReadersByFormatName("png");//???

	        //ImageIO is a class containing static methods for locating ImageReaders
	        //and ImageWriters, and performing simple encoding and decoding.
	        ImageReader reader = (ImageReader) readers.next();
	        Object source = bis;
	        ImageInputStream iis = ImageIO.createImageInputStream(source);
	        reader.setInput(iis, true);
	        ImageReadParam param = reader.getDefaultReadParam();

	        BufferedImage image = reader.read(0, param);
	        //got an image file

	        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
	        //bufferedImage is the RenderedImage to be written

	        Graphics2D g2 = bufferedImage.createGraphics();
	        g2.setComposite(AlphaComposite.Clear);
	        g2.fillRect(0, 0, image.getWidth(),  image.getHeight());
	        g2.setComposite(AlphaComposite.Src);
	        g2.drawImage(image, null, null);
	        g2.dispose();

	        return bufferedImage;

	    }
	  
	  
	  public static ByteArrayOutputStream getImagenOutputStream(byte[] imagen) throws IOException {


	        ByteArrayInputStream bis = new ByteArrayInputStream(imagen);
	        Iterator<?> readers = ImageIO.getImageReadersByFormatName("png");//???

	        //ImageIO is a class containing static methods for locating ImageReaders
	        //and ImageWriters, and performing simple encoding and decoding.
	        ImageReader reader = (ImageReader) readers.next();
	        Object source = bis;
	        ImageInputStream iis = ImageIO.createImageInputStream(source);
	        reader.setInput(iis, true);
	        ImageReadParam param = reader.getDefaultReadParam();

	        BufferedImage image = reader.read(0, param);
	        //got an image file

	        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
	        //bufferedImage is the RenderedImage to be written

	        Graphics2D g2 = bufferedImage.createGraphics();
	        g2.drawImage(image, null, null);
	        g2.dispose();
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	         ImageIO.write(bufferedImage, "png", os);
	         return os;

	    }
	  
	  
	 public static ByteArrayOutputStream getImagenVacia() throws IOException{
		 //Graphic Text
         BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
       //  Graphics2D g2 = bufferedImg.createGraphics();
         //g2.drawString("Sin  Imagen", 0, 10);
         ByteArrayOutputStream os = new ByteArrayOutputStream();
         ImageIO.write(bufferedImg, "png", os);
         return os;
         
      //   graphicText = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png"); 

         //Chart
       /*  JFreeChart jfreechart = ChartFactory.createPieChart("Cities", createDataset(), true, true, false);
         File chartFile = new File("dynamichart");
         ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);*/
       //  chart = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
	 }
	 
	 
	 public static BufferedImage resize(BufferedImage image, int newWidth, int newHeight) {
	        int currentWidth = image.getWidth();
	        int currentHeight = image.getHeight();
	        BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
	        Graphics2D graphics2d = newImage.createGraphics();
	        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        graphics2d.drawImage(image, 0, 0, newWidth, newHeight, 0, 0,
	                currentWidth, currentHeight, null);
	        graphics2d.dispose();
	        return newImage;
	    }
	 /**
	  * 
	  * 
	  * @param ancho
	  * @param alto
	  * @param texto
	  * @return  String (org.apache.commons.codec.binary.Base64.encodeBase64)
	  * @throws IOException
	  */
	 public static String crarImagenEnBlanco(int ancho, int alto, String texto) throws IOException{
		 
		 BufferedImage bufferedImg = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
         Graphics2D g = bufferedImg.createGraphics();
         g.setComposite(AlphaComposite.Clear);
         g.fillRect(0, 0, ancho, alto);
         g.setColor(Color.white);
         g.fillRect(0, 0, ancho, alto);
         g.setColor(Color.black);
         if(texto!=null){
        	 int _xScal=Float.valueOf(ancho*0.1f).intValue();
        	 int _yScal=Float.valueOf(alto*0.1f).intValue();
         g.drawString(texto,0, 10);
         
         _xScal=Float.valueOf(ancho*0.8f).intValue();
    	 _yScal=Float.valueOf(alto*0.8f).intValue();
         g.scale(_xScal, _yScal);
         }
         
          ByteArrayOutputStream os = new ByteArrayOutputStream();
          ImageIO.write(bufferedImg, "png", os);
         return new String(org.apache.commons.codec.binary.Base64.encodeBase64(os.toByteArray()));
	 }
	 /**
	  * 
	  * @param bufferedImg
	  * @return String (org.apache.commons.codec.binary.Base64.encodeBase64)
	  * @throws IOException
	  */
 public static String crarImagen(BufferedImage bufferedImg) throws IOException{
		  ByteArrayOutputStream os = new ByteArrayOutputStream();
          ImageIO.write(bufferedImg, "png", os);
         return new String(org.apache.commons.codec.binary.Base64.encodeBase64(os.toByteArray()));
	 }
 
 /**
  * 
  * @param bufferedImg
  * @return String (org.apache.commons.codec.binary.Base64.encodeBase64)
  * @throws IOException
  */
public static String crarImagen(byte[] imagen) throws IOException{
	 BufferedImage bufferedImg = getImagen(imagen);
	
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
      ImageIO.write(bufferedImg, "png", os);
     return new String(org.apache.commons.codec.binary.Base64.encodeBase64(os.toByteArray()));
 }
}
