package encryptionAlgorithm;


import java.nio.CharBuffer;

import java.util.Scanner;


public class Playfair {
	//����5*5���ܾ���
	public static char[][] constructMatrix(char[] key){
		char[][] matrix = new char[5][5];
		CharBuffer cb = CharBuffer.allocate(25);
		cb.append(key[0]);
		for(int i = 0;i < key.length;i++){
			if(!contains(cb.array(),key[i])){
				cb.append(key[i]);
			}
		}
		char ch = 'a';
		while(ch <= 'z'){
			if (!contains(cb.array(), ch)){
				cb.append(ch++);
				continue;
			}
			ch++;
		}
		int index = 0;
		for (int i = 0;i < 5;i++){
			for(int j = 0;j < 5;j++){
				matrix[i][j] = cb.get(index++);
			}
		}
		/*for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}*/
		return matrix;
	}
	
	//�ж��Ƿ��Ѿ�����
	public static boolean contains(char[] array,char ch){
		for (int i = 0;i < array.length;i++){
			if (ch == array[i] || ch == 'i'&&array[i] == 'j' || ch == 'j'&&array[i] == 'i')
				return true;
		}
		return false;
	}
	//�ַ��ھ����е�λ��
	public static int[] position(char[][] matrix,char ch){
		int flag = 0;
		int[] pos = {25,25};
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if (ch == matrix[i][j] || ch == 'i'&&matrix[i][j] == 'j' || ch == 'j'&&matrix[i][j] == 'i'){
					pos[0] = i;
					pos[1] = j;
					flag = 1;
					break;
				}
			}
			if(flag==1){
				break;
			}
		}
		return pos;
	}
	
	//��������
	public static char[] dealPlain(char[] plain){
		CharBuffer plain1 = CharBuffer.allocate(plain.length*2);
		if(plain.length % 2 == 0){
			for(int i=0;i<plain.length;i+=2){
				if(plain[i]==plain[i+1]){
					plain1.append(plain[i]).append('x').append(plain[i+1]);
				}
				else{
					plain1.append(plain[i]).append(plain[i+1]);
				}
			}
		}
		else{
			for(int i=0;i<plain.length-1;i+=2){
				if(plain[i]==plain[i+1]){
					plain1.append(plain[i]).append('x').append(plain[i+1]);
				}
				else{
					plain1.append(plain[i]).append(plain[i+1]);
				}
			}
			plain1.append(plain[plain.length-1]);
		}
		if(plain1.length()%2!=0){
			plain1.append('x');
		}
		int len = plain.length*2-plain1.length();
		char[] array = new char[len];
		for(int i=0;i<len;i++){
			array[i] = plain1.get(i);
		}
		return array;
	}
	//��д����
	//1,���������������Ϊһ�ԣ�������i��j�������һ����������ĸ��ͬһ�У�����ͬһ���ұߵĴ���
	//2,�����ͬһ�У�����ͬһ������Ĵ���
	//3,������ڲ�ͬ���к��У������������ϵ���ĸ���������õ�һ����ĸ���У��ڶ�����ĸ���д���
	public static String playFairEncrypt(String plaintext,String key){
		char[] plain1 = plaintext.replaceAll(" ","").toLowerCase().toCharArray();
		char[] key1 = key.replaceAll(" ","").toCharArray();
		char[][] matrix = constructMatrix(key1);
		char[] plain = dealPlain(plain1);
		CharBuffer cipher = CharBuffer.allocate(plain.length);
		int[] pos1 = new int[2];
		int[] pos2 = new int[2];
		for(int i=0;i<plain.length;i+=2){
			pos1 = position(matrix, plain[i]);
			pos2 = position(matrix, plain[i+1]);
			int x,y;
			if(pos1[0]==pos2[0]){
				x = pos1[0];
				y = (pos1[1]+1)%5;
				cipher.append(matrix[x][y]);
				y = (pos2[1]+1)%5;
				cipher.append(matrix[x][y]);
			}
			else if(pos1[1]==pos2[1]){
				x = (pos1[0]+1)%5;
				y = pos1[1];
				cipher.append(matrix[x][y]);
				x = (pos2[0]+1)%5;
				cipher.append(matrix[x][y]);
			}
			else{
				x = pos1[0];
				y = pos2[1];
				cipher.append(matrix[x][y]);
				x = pos2[0];
				y = pos1[1];
				cipher.append(matrix[x][y]);
			}	
		}
		String str = new String(cipher.array());
		return str;
	}
	//����
	public static String playFairDecrypt(String ciphertext,String key){
		char[] cipher = ciphertext.replaceAll(" ","").toCharArray();
		char[] key1 = key.replaceAll(" ","").toCharArray();
		char[][] matrix = constructMatrix(key1);
		CharBuffer plain = CharBuffer.allocate(cipher.length);
		int[] pos1 = new int[2];
		int[] pos2 = new int[2];
		for(int i=0;i<cipher.length;i+=2){
			pos1 = position(matrix, cipher[i]);
			pos2 = position(matrix, cipher[i+1]);
			int x,y;
			if(pos1[0]==pos2[0]){
				x = pos1[0];
				y = (pos1[1]+4)%5;
				plain.append(matrix[x][y]);
				y = (pos2[1]+4)%5;
				plain.append(matrix[x][y]);
			}
			else if(pos1[1]==pos2[1]){
				x = (pos1[0]+4)%5;
				y = pos1[1];
				plain.append(matrix[x][y]);
				x = (pos2[0]+4)%5;
				plain.append(matrix[x][y]);
			}
			else{
				x = pos1[0];
				y = pos2[1];
				plain.append(matrix[x][y]);
				x = pos2[0];
				y = pos1[1];
				plain.append(matrix[x][y]);
			}	
		}
		String str = new String(plain.array());
		return str;
		
	}
	
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		System.out.println("plaintext:");
		String plaintext = s.nextLine();
		System.out.println("key:");
		String key = s.nextLine();
		String cipher = playFairEncrypt(plaintext, key);
		System.out.println("Cipher:");
		System.out.println(cipher);
		System.out.println("Plain:");
		String plain = playFairDecrypt(cipher, key);
		System.out.println(plain);
		s.close();
		
	}
}
