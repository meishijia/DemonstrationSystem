package encryptionAlgorithm;

import java.util.Scanner;

public class Caesar {
	public static String encryptCaesar(String plaintext,String number1) {
		String newstr = plaintext.toUpperCase();
		int number = Integer.valueOf(number1).intValue();
		char[] slist = newstr.toCharArray();
		for(int i = 0;i < slist.length;i++){
			slist[i] = (char) (((slist[i]-65 + number) % 26) + 65);
		}
		String ciphertext = new String(slist);
		return ciphertext;
	}
	public static String decryptCaesar(String ciphertext,String number1){
		String newstr = ciphertext.toUpperCase();
		int number = Integer.valueOf(number1).intValue();
		char[] slist = newstr.toCharArray();
		for(int i = 0;i < slist.length;i++){
			slist[i] = (char) (((slist[i]-65 - number) % 26) + 65);
		}
		String plaintext = new String(slist);
		return plaintext;
	}
	public static void main(String[] args){
		
		Scanner s = new Scanner(System.in);
		System.out.println("Input plaintext:");
		String plaintext  = s.nextLine();
		
		System.out.println("Input the key:");
		String n = s.nextLine();
		s.close();
		System.out.println("ciphertext is : ");
		System.out.println(encryptCaesar(plaintext, n));
		System.out.println("plaintext is : ");
		System.out.println(decryptCaesar(encryptCaesar(plaintext, n), n));
	}
}

