package encryptionAlgorithm;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 

public class ThreeDES {
   //加密
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
           //加密失败，打日志
           ex.printStackTrace();
       } 
       return null;
	   
   }
   //解密
   
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
           //解密失败，打日志
           ex.printStackTrace();
       } 
	   return null;
   }
    
    //加密密钥，长度为24字节,明文应该是8字节的的倍数
   public static void main(String[] args) throws Exception {
       String plaintext ="woshimeishijiaxx";
       String keytext = "123456788765432112345678";
       String ciphertext = ThreeDESEncrypt(plaintext, keytext);
       System.out.println("密文："+ciphertext);
       System.out.println("明文： "+ThreeDESDecrypt(ciphertext, keytext));  
       
   }  
}
