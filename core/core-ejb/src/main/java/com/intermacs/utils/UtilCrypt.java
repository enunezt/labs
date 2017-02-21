package com.intermacs.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;




public class UtilCrypt {
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;

    public UtilCrypt() throws Exception {
        myEncryptionKey = "EncriptarClaveApps-ClaveAppsEncriptar";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }


    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    private String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString.getBytes());
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
    
    public static String encriptar(String data) throws Exception{
    	 //UtilCrypt td= new UtilCrypt();
    	return get_SHA_256(data);
    }
    
    private static String desencriptar(String data) throws Exception{
   	 UtilCrypt td= new UtilCrypt();
   	return td.encrypt(data);
   }
    
    
  
    
    private static String get_SHA_256(String encryptedString)
    {   
    	String retorno=encryptedString;
    	
    	try {
    	
    	 MessageDigest md = MessageDigest.getInstance("SHA-256");
         md.update(encryptedString.getBytes());
         
         byte byteData[] = md.digest();
  
         //convert the byte to hex format method 1
        /* StringBuffer sb = new StringBuffer();
         for (int i = 0; i < byteData.length; i++) {
          sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
         }
      
         System.out.println("Hex format : " + sb.toString());
         */
         //convert the byte to hex format method 2
         StringBuffer hexString = new StringBuffer();
     	for (int i=0;i<byteData.length;i++) {
     		String hex=Integer.toHexString(0xff & byteData[i]);
    	     	if(hex.length()==1) hexString.append('0');
    	     	hexString.append(hex);
     	}
     	//System.out.println("Hex format : " + hexString.toString());
     	retorno=hexString.toString();
    }
    catch (NoSuchAlgorithmException e)
    {
        e.printStackTrace();
    }
    	
    	return retorno;
       
    }
     
   

    public static void main(String args []) throws Exception
    {
        UtilCrypt td= new UtilCrypt();

        String target="1234";
        String encrypted=td.encrypt(target);
       // String decrypted=td.decrypt(encrypted);
        
        encrypted= UtilCrypt.encriptar(target);
        System.out.println("String To Encrypt: "+ target);
        System.out.println("Encrypted String: " + encrypted);
        
        
         target="123456";
         encrypted=td.encrypt(target);
       // String decrypted=td.decrypt(encrypted);
        
        encrypted= UtilCrypt.encriptar(target);
        System.out.println("String To Encrypt: "+ target);
        System.out.println("Encrypted String: " + encrypted);

      /*  System.out.println("String To Encrypt: "+ target);
        System.out.println("Encrypted String: " + encrypted);
        System.out.println("Decrypted String: " + decrypted);
        
       target="4321";
       encrypted=td.encrypt(target);
       decrypted=td.decrypt(encrypted);

        System.out.println("String To Encrypt: "+ target);
        System.out.println("Encrypted String: " + encrypted);
        System.out.println("Decrypted String: " + decrypted);
        
        target="12345";
        encrypted=td.encrypt(target);
        decrypted=td.decrypt(encrypted);

         System.out.println("String To Encrypt: "+ target);
         System.out.println("Encrypted String: " + encrypted);
         System.out.println("Decrypted String: " + decrypted);*/

    }
}