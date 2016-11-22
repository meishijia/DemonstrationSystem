package encryptionAlgorithm;

import java.nio.CharBuffer;
import java.util.Scanner;

public class Vigenere {
	//维吉尼亚密码表明文字符为行数，密文字符为列数
	public static String vigenereEncrypt(String plaintext,String keytext) {
		String plaintext1 = plaintext.toUpperCase();
		String keytext1 = keytext.toUpperCase();
		char[] plain = plaintext1.toCharArray();
		char[] key = keytext1.toCharArray();
		CharBuffer cipher = CharBuffer.allocate(plain.length);
		int len = key.length;
		for(int i=0;i<plain.length;i+=len){
			int index = i;
			for(int j=0;j<len;j++){
				int ch = (plain[index]+key[j]-65);
				if (ch>90)
					cipher.append((char)(ch-90+64));
				else
					cipher.append((char)ch);
				index++;
				if(index>=plain.length){
					break;
				}
			}
		}
		String str = new String(cipher.array());	
		return str;
	}
	//解密
	public static String vigenereDecrypt(String ciphertext,String keytext){
		String ciphertext1 = ciphertext.toUpperCase();
		String keytext1 = keytext.toUpperCase();
		char[] cipher = ciphertext1.toCharArray();
		char[] key = keytext1.toCharArray();
		CharBuffer plain = CharBuffer.allocate(cipher.length);
		int len = key.length;
		for(int i=0;i<cipher.length;i+=len){
			int index = i;
			for(int j=0;j<len;j++){
				int ch = cipher[index] - key[j];
				if(ch<0){
					plain.append((char)(ch+26+65));
				}
				else
					plain.append((char)(ch+65));
				index++;
				if(index>=cipher.length){
					break;
				}
			}
		}
		String str = new String(plain.array());	
		return str;
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Plain:");
		String plaintext = s.nextLine();
		System.out.println("Key:");
		String key = s.nextLine();
		
		String cipher = vigenereEncrypt(plaintext, key);
		System.out.println("Cipher:");
		
		System.out.println(cipher);
		
		System.out.println("Plain:");
		System.out.println(vigenereDecrypt(cipher, key));
		s.close();
		
	}

}
