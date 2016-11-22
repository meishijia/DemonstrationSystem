package encryptionAlgorithm;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSA {
	public static void main(String[] args){
		String plaintext = "meishijia";
		byte[][] keyPair = keyPairGenerator();
		String publicKey = getPublicKey(keyPair);
		String privateKey = getPrivateKey(keyPair);
		System.out.println("公钥加密，私钥解密————加密:");
		String cipher = rsaPublicEncrypt(plaintext, publicKey);
		System.out.println(cipher);

		System.out.println("公钥加密，私钥解密————解密");
		String plain = rsaPrivateDecrypt(cipher,privateKey);
		System.out.println(plain);
		
		System.out.println("私钥加密，公钥解密————加密");	
		cipher = rsaPrivateEncrypt(plaintext,privateKey);
		System.out.println(cipher);
		
		System.out.println("私钥加密，公钥解密————解密");
		plain = rsaPublicDecrypt(cipher, publicKey);
		System.out.println(plain);
		
	}
	public static String getPublicKey(byte[][] keyPair){
		String publicKeyStr = Reverse.Hex.encodeHexStr(keyPair[0]);
		return publicKeyStr;
	}
	public static String getPrivateKey(byte[][] keyPair){
		String privateKeyStr = Reverse.Hex.encodeHexStr(keyPair[1]);
		return privateKeyStr;
	}
	public static byte[][] keyPairGenerator(){
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(512);;
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
			
			byte[][] bytes = new byte[2][];
			bytes[0] = rsaPublicKey.getEncoded();
			bytes[1] = rsaPrivateKey.getEncoded();
			return bytes;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//公钥加密，私钥解密————加密
	public static String rsaPublicEncrypt(String plaintext,String rsaPublicKey){	
		try {
			byte[] publicKeyBytes = Reverse.Hex.decodeHex(rsaPublicKey.toCharArray());
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] result = cipher.doFinal(plaintext.getBytes());
			String resultStr = Reverse.Hex.encodeHexStr(result);
			return resultStr;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	//公钥加密，私钥解密————解密
	public static String rsaPrivateDecrypt(String ciphertext,String rsaPrivateKey){
		
		try {
			byte[] privateKeyBytes = Reverse.Hex.decodeHex(rsaPrivateKey.toCharArray());
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] cipherBytes = Reverse.Hex.decodeHex(ciphertext.toCharArray());
			byte[] result = cipher.doFinal(cipherBytes);
			String resultStr = new String(result);
			return resultStr;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
	
	//私钥加密，公钥解密————加密
	public static String rsaPrivateEncrypt(String plaintext,String rsaPrivateKey) {
		try {
			byte[] privateKeyBytes = Reverse.Hex.decodeHex(rsaPrivateKey.toCharArray());
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] result = cipher.doFinal(plaintext.getBytes());
			String resultStr = Reverse.Hex.encodeHexStr(result);
			return resultStr;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	//私钥加密，公钥解密————解密
	public static String rsaPublicDecrypt(String ciphertext,String rsaPublicKey){
		try {
			byte[] publicKeyBytes = Reverse.Hex.decodeHex(rsaPublicKey.toCharArray());
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
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
