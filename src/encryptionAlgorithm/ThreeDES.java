package encryptionAlgorithm;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 

public class ThreeDES {
   //����
   public static String ThreeDESEncrypt(String plaintext,String keytext){
	   try {
		   byte[] keyBytes = keytext.getBytes();
		   SecretKey deskey = new SecretKeySpec(keyBytes, "DESede");
           Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
           cipher.init(Cipher.ENCRYPT_MODE, deskey);
           byte[] result = cipher.doFinal(plaintext.getBytes());
           String resultStr = Reverse.Hex.encodeHexStr(result);
           return resultStr;
       } catch (Exception ex) {
           //����ʧ�ܣ�����־
           ex.printStackTrace();
       } 
       return null;
	   
   }
   //����
   
   public static String ThreeDESDecrypt(String ciphertext,String keytext){
	   try {
		   byte[] keyBytes = keytext.getBytes();
		   SecretKey deskey = new SecretKeySpec(keyBytes, "DESede");
           Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
           cipher.init(Cipher.DECRYPT_MODE,deskey);
           byte[] ciphertBytes = Reverse.Hex.decodeHex(ciphertext.toCharArray());
           byte[] result = cipher.doFinal(ciphertBytes);
           String resultStr = new String(result);
           return resultStr;
       } catch (Exception ex) {
           //����ʧ�ܣ�����־
           ex.printStackTrace();
       } 
	   return null;
   }
    
    //������Կ������Ϊ24�ֽ�,����Ӧ����8�ֽڵĵı���
   public static void main(String[] args) throws Exception {
       String plaintext ="woshimeishijiaxx";
       String keytext = "123456788765432112345678";
       String ciphertext = ThreeDESEncrypt(plaintext, keytext);
       System.out.println("���ģ�"+ciphertext);
       System.out.println("���ģ� "+ThreeDESDecrypt(ciphertext, keytext));  
       
   }  
}
