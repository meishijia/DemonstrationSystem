package encryptionAlgorithm;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DES {
	
	public static void  main(String[] args) {
		String plaintext = "meishijia";
		String key = keyGenerator();
	    System.out.println("秘钥："+key);
	    String result = desEncrypt(plaintext, key);
	    System.out.println("加密后："+result);
	    System.out.println("解密后："+desDecrypt(result, key));
		
	}
	public static String keyGenerator() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			String stringKey = Reverse.Hex.encodeHexStr(bytesKey);
			return stringKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String desEncrypt(String plaintext,String key) {
		
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = factory.generateSecret(desKey);
			
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
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
	
	public static String desDecrypt(String ciphertext,String key){	
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = factory.generateSecret(desKey);
			
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
			byte[] ciphertBytes = Reverse.Hex.decodeHex(ciphertext.toCharArray());
			byte[] result = cipher.doFinal(ciphertBytes);
			String resultStr = new String(result);
			return resultStr;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}


}

