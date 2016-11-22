package encryptionAlgorithm;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AES {
	public static String keyGenerator() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			String keyStr = Reverse.Hex.encodeHexStr(bytesKey);
			return keyStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void  main(String[] args) {
		String plaintext = "meishijia";
	    String key = keyGenerator();
	    String result = aesEncrypt(plaintext, key);
	    System.out.println("加密后："+result);
	    System.out.println("解密后："+aesDecrypt(result, key));
		
	}
	
	public static String aesEncrypt(String plaintext,String key) {
		
		try {
			SecureRandom random = new SecureRandom();
			byte[] keyBytes = Reverse.Hex.decodeHex(key.toCharArray());
			SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
			
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
			
			byte[] result = cipher.doFinal(plaintext.getBytes());
			String resultStr = Reverse.Hex.encodeHexStr(result);
			//String resultStr = Hex.encodeHexString(result);
			return resultStr;		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static String aesDecrypt(String ciphertext,String key){	
		try {
			SecureRandom random = new SecureRandom();
			byte[] keyBytes = Reverse.Hex.decodeHex(key.toCharArray());
			SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
			
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
			byte[] cipherBytes = Reverse.Hex.decodeHex(ciphertext.toCharArray());
			byte[] result = cipher.doFinal(cipherBytes);
			String resultStr = new String(result);
			return resultStr;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}


}


