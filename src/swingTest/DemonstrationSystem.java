package swingTest;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.sound.midi.VoiceStatus;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;


//import encryptionAlgorithm.AES;
import encryptionAlgorithm.AESNew;
import encryptionAlgorithm.Caesar;
import encryptionAlgorithm.DESCBC;
import encryptionAlgorithm.Playfair;
import encryptionAlgorithm.RSA;
import encryptionAlgorithm.Vigenere;

public class DemonstrationSystem {
	static final int WIDTH = 1000;
	static final int HEIGHT = 800;
	static int flag = 0;
	public static JButton encrypt = new JButton("����");
	public static JButton decrypt = new JButton("����");
	public static JTextArea plaintext = new JTextArea(10,20);
	public static JTextArea key = new JTextArea(10,20);
	public static JTextArea ciphertext = new JTextArea(20,20);
	public static JButton publicEncrypt = new JButton("��Կ����");
	public static JButton privateEncrypt = new JButton("˽Կ����");
	public static JButton publicDecrypt = new JButton("��Կ����");
	public static JButton privateDecrypt = new JButton("˽Կ����");
	public static JFrame jf = new JFrame();
	public static JPanel panel2 = new JPanel();
	public static Box box1 = Box.createVerticalBox();
	
	public static File plainFile;
	public static File keyFile;
	public static File cipherFile;
	
	
	
	public static JButton choosePlain = new JButton("ѡ�������ļ�");
	public static JButton chooseKey = new JButton("ѡ����Կ�ļ�");
	public static JButton chooseCipher = new JButton("ѡ�������ļ�");
	public static JButton storeCipher = new JButton("�洢�����ļ�");
			
	
	//�Զ��������
	public class itemCaerseHandler implements ActionListener{
		  public void actionPerformed(ActionEvent e){
			  encrypt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String plain1 = plaintext.getText();
						String key1 = key.getText();
						String cipher1 = Caesar.encryptCaesar(plain1, key1);
						ciphertext.setText(cipher1);
					}
				});
				decrypt.addActionListener(new ActionListener() {		
					@Override
					public void actionPerformed(ActionEvent e) {
						String cipher1 = ciphertext.getText();
						String key1 = key.getText();
						String plain1 =Caesar.decryptCaesar(cipher1, key1);
						System.out.println();
						plaintext.setText(plain1);
						
					}
				});
		  }
	}
	public class itemPlayfairHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			encrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String plain1 = plaintext.getText();
					String key1 = key.getText();
					String cipher1 = Playfair.playFairEncrypt(plain1, key1);
					ciphertext.setText(cipher1);
				}
			});
			decrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String cipher1 = ciphertext.getText();
					String key1 = key.getText();
					String plain1 = Playfair.playFairDecrypt(cipher1, key1);
					plaintext.setText(plain1);
					
				}
			});
		}
	}
	public class itemVigenereHandler implements ActionListener{
		 public void actionPerformed(ActionEvent e){ 
			 encrypt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String plain1 = plaintext.getText();
						String key1 = key.getText();
						String cipher1 = Vigenere.vigenereEncrypt(plain1, key1);
						ciphertext.setText(cipher1);
					}
				});
				
				decrypt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String cipher1 = ciphertext.getText();
						String key1 = key.getText();
						String plain1 = Vigenere.vigenereDecrypt(cipher1, key1);
						plaintext.setText(plain1);
						
					}
				});
         } 
	}
	public class itemDESHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){ 
			 encrypt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String keyStr = key.getText();
						String plainStr = plaintext.getText();
						String cipherStr = DESCBC.encrypt(plainStr, keyStr);				
						ciphertext.setText(cipherStr);
					}
				});
				decrypt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String cipherStr = ciphertext.getText();
						String keyStr = key.getText();
						String plainStr = DESCBC.decrypt(cipherStr, keyStr);
						plaintext.setText(plainStr);						
					}
				});
        }
	}
	public class itemAESHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){ 
			encrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {						
						String keyStr = key.getText();
						String plainStr = plaintext.getText();
						AESNew aes = new AESNew(getRawKey(keyStr.getBytes()));
						byte[] result = aes.encrypt(plainStr.getBytes());
						String resultStr = parseByte2HexStr(result);
						ciphertext.setText(resultStr);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}			
				}
			});
			decrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {				
					try {						
						String cipherStr = ciphertext.getText();
						String keyStr = key.getText();
						AESNew aes = new AESNew(getRawKey(keyStr.getBytes()));
						byte[] cipherBytes = parseHexStr2Byte(cipherStr);
						byte[] result = aes.decrypt(cipherBytes);
						String resultStr =new String(result);
						plaintext.setText(resultStr);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}					
				}
			});
        }
	}
	public class itemRSAHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(flag == 0){
				panel2.remove(encrypt);
				panel2.remove(decrypt);
				jf.setVisible(true);
				
				panel2.add(publicEncrypt);
				panel2.add(privateEncrypt);
				panel2.add(publicDecrypt);
				panel2.add(privateDecrypt);
				
				jf.setVisible(true);
			}
			flag = 1;
			
			//��Կ����
			publicEncrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton getPublicKey = new JButton("��ù�Կ");	
					box1.add(getPublicKey);
					jf.setVisible(true);
					getPublicKey.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							byte[][] keyPair = RSA.keyPairGenerator();
							String publicKey = RSA.getPublicKey(keyPair);
							String privateKey = RSA.getPrivateKey(keyPair);
							key.setText(privateKey);
							String plainStr = plaintext.getText();
							String cipherStr = RSA.rsaPublicEncrypt(plainStr, publicKey);
							ciphertext.setText(cipherStr);										
						}
					});			
				}
			});
			//˽Կ����
			privateEncrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton getPrivateKey = new JButton("���˽Կ");	
					box1.add(getPrivateKey);
					jf.setVisible(true);
					getPrivateKey.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							byte[][] keyPair = RSA.keyPairGenerator();
							String privateKey = RSA.getPrivateKey(keyPair);
							String publicKey = RSA.getPublicKey(keyPair);
							key.setText(publicKey);
							String plainStr = plaintext.getText();
							String cipherStr = RSA.rsaPrivateEncrypt(plainStr, privateKey);
							ciphertext.setText(cipherStr);										
						}
					});			
				}
			});
			//��Կ����
			publicDecrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String cipherStr = ciphertext.getText();
					String keyStr = key.getText();
					String plainStr = RSA.rsaPublicDecrypt(cipherStr, keyStr);
					plaintext.setText(plainStr);
				}
			});
			//˽Կ����
			privateDecrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String cipherStr = ciphertext.getText();
					String keyStr = key.getText();
					String plainStr = RSA.rsaPrivateDecrypt(cipherStr, keyStr);
					plaintext.setText(plainStr);
				}
			});
		}
	}
	
	public class plainFileHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int result;
			JFileChooser fileChooser=new JFileChooser("C:\\Users\\asus1\\Desktop\\test");  
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
			fileChooser.setApproveButtonText("���ļ�");
			result = fileChooser.showOpenDialog(jf);
			plaintext.setText("");
			
			if(result == JFileChooser.APPROVE_OPTION){
				plainFile = fileChooser.getSelectedFile();
			}
	        if(plainFile.isDirectory()){  
	            System.out.println("�ļ���:"+plainFile.getAbsolutePath());  
	            JOptionPane.showMessageDialog(null, "��ѡ���ļ��������ļ���", 
	            		"����",JOptionPane.ERROR_MESSAGE);
				return;
	        }
	        //���ļ����ݴ����ı�����
	        if(plainFile != null){
	        	FileInputStream fileInStream = null;
	        	try{
					fileInStream = new FileInputStream(plainFile);			
				}
	        	catch(FileNotFoundException fe){
	        		JOptionPane.showMessageDialog(null, "�Ҳ������ļ�", 
		            		"����",JOptionPane.ERROR_MESSAGE);
					return;
				}
	        	
	        	int readByte;
				try{
					while((readByte = fileInStream.read()) != -1){
						plaintext.append(String.valueOf((char)readByte));
					}
				}
				catch(IOException ioe){
					JOptionPane.showMessageDialog(null, "��ȡ�ļ�����", 
		            		"����",JOptionPane.ERROR_MESSAGE);
					return;
				}
				finally {
					try{
						if(fileInStream != null){
							fileInStream.close();
						}
					}
					catch(IOException ioe2){}
				}
	        	
	        }
		}
	}
	public class keyFileHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int result;
			JFileChooser fileChooser=new JFileChooser("C:\\Users\\asus1\\Desktop\\test");  
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
			fileChooser.setApproveButtonText("���ļ�");
			result = fileChooser.showOpenDialog(jf);
			key.setText("");
			
			if(result == JFileChooser.APPROVE_OPTION){
				keyFile = fileChooser.getSelectedFile();
			}
	        if(keyFile.isDirectory()){  
	            System.out.println("�ļ���:"+keyFile.getAbsolutePath());  
	            JOptionPane.showMessageDialog(null, "��ѡ���ļ��������ļ���", 
	            		"����",JOptionPane.ERROR_MESSAGE);
				return;
	        }
	        //���ļ����ݴ����ı�����
	        if(keyFile != null){
	        	FileInputStream fileInStream = null;
	        	try{
					fileInStream = new FileInputStream(keyFile);			
				}
	        	catch(FileNotFoundException fe){
	        		JOptionPane.showMessageDialog(null, "�Ҳ������ļ�", 
		            		"����",JOptionPane.ERROR_MESSAGE);
					return;
				}
	        	
	        	int readByte;
				try{
					while((readByte = fileInStream.read()) != -1){
						key.append(String.valueOf((char)readByte));
					}
				}
				catch(IOException ioe){
					JOptionPane.showMessageDialog(null, "��ȡ�ļ�����", 
		            		"����",JOptionPane.ERROR_MESSAGE);
					return;
				}
				finally {
					try{
						if(fileInStream != null){
							fileInStream.close();
						}
					}
					catch(IOException ioe2){}
				}
	        	
	        }
		}
	}
	public class cipherFileHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int result;
			JFileChooser fileChooser=new JFileChooser("C:\\Users\\asus1\\Desktop\\test");  
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
			fileChooser.setApproveButtonText("���ļ�");
			result = fileChooser.showOpenDialog(jf);
			ciphertext.setText("");
			
			if(result == JFileChooser.APPROVE_OPTION){
				cipherFile = fileChooser.getSelectedFile();
			}
	        if(cipherFile.isDirectory()){  
	            System.out.println("�ļ���:"+cipherFile.getAbsolutePath());  
	            JOptionPane.showMessageDialog(null, "��ѡ���ļ��������ļ���", 
	            		"����",JOptionPane.ERROR_MESSAGE);
				return;
	        }
	        //���ļ����ݴ����ı�����
	        if(cipherFile != null){
	        	FileInputStream fileInStream = null;
	        	try{
					fileInStream = new FileInputStream(cipherFile);			
				}
	        	catch(FileNotFoundException fe){
	        		JOptionPane.showMessageDialog(null, "�Ҳ������ļ�", 
		            		"����",JOptionPane.ERROR_MESSAGE);
					return;
				}
	        	
	        	int readByte;
				try{
					while((readByte = fileInStream.read()) != -1){
						ciphertext.append(String.valueOf((char)readByte));
					}
				}
				catch(IOException ioe){
					JOptionPane.showMessageDialog(null, "��ȡ�ļ�����", 
		            		"����",JOptionPane.ERROR_MESSAGE);
					return;
				}
				finally {
					try{
						if(fileInStream != null){
							fileInStream.close();
						}
					}
					catch(IOException ioe2){}
				}
	        	
	        }
		}
	}
	
	public class storeCipherHandle implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int result;
			JFileChooser fileChooser=new JFileChooser("C:\\Users\\asus1\\Desktop\\test");
			result = fileChooser.showSaveDialog(jf);
			cipherFile = null;
			if(result == JFileChooser.APPROVE_OPTION){
				cipherFile = fileChooser.getSelectedFile();
			}
			FileOutputStream fileOutStream = null;
			if(cipherFile !=null){
				try{
					fileOutStream = new FileOutputStream(cipherFile);				
				}
				catch(FileNotFoundException fe){
					JOptionPane.showMessageDialog(null, "�Ҳ������ļ�", 
		            		"����",JOptionPane.ERROR_MESSAGE);
					return;
				}
				String content = ciphertext.getText();
				try{
					fileOutStream.write(content.getBytes());
				}
				catch(IOException ioe){
					JOptionPane.showMessageDialog(null, "д�ļ�����", 
		            		"����",JOptionPane.ERROR_MESSAGE);
					return;
				}
				finally{
					try{
						if(fileOutStream != null){
							fileOutStream.close();
						}
					}
					catch(IOException ioe2){}
				}
			}
		}
	}
	//������
    public DemonstrationSystem(){
		//��������
				
				jf.setTitle("����ϵͳ");
				jf.setSize(WIDTH, HEIGHT);
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setVisible(true);
				
				//�м����������
				JPanel panel = new JPanel();
				jf.setContentPane(panel);
				panel.setLayout(new FlowLayout());
				
				//�˵���
				JMenuBar mbar = new JMenuBar();
				jf.setJMenuBar(mbar);
				
				//�˵�
				JMenu jm1 = new JMenu("�������");
				JMenu jm2 = new JMenu("�ԳƼ���");
				JMenu jm3 = new JMenu("�ǶԳƼ���");
				mbar.add(jm1);
				mbar.add(jm2);
				mbar.add(jm3);
				
				//�˵���
				JMenuItem itemCaeser = new JMenuItem("��������");
				JMenuItem itemPlayfair = new JMenuItem("PlayFair����");
				JMenuItem itemVigenere = new JMenuItem("ά�����Ǽ���");
				
				JMenuItem itemDes = new JMenuItem("DES");	
				JMenuItem itemAes = new JMenuItem("AES");
				
				JMenuItem itemRsa = new JMenuItem("RSA");
				
				jm1.add(itemCaeser);
				jm1.add(itemPlayfair);
				jm1.add(itemVigenere);
				jm2.add(itemDes);
				jm2.add(itemAes);
				jm3.add(itemRsa);
				
				jf.setVisible(true);
				
				//�������м����������������
				JPanel panel1 = new JPanel();
				
				JPanel panel3 = new JPanel();
				
				panel1.setPreferredSize(new Dimension(300,500));
				panel2.setPreferredSize(new Dimension(100, 500));
				panel3.setPreferredSize(new Dimension(300, 500));
				
				panel1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				panel2.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				panel3.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				
				panel.add(panel1);
				panel.add(panel2);
				panel.add(panel3);
				
				//��һ��С���������ı���
				
				
				plaintext.setLineWrap(true);
				key.setLineWrap(true);
				plaintext.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				key.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				JLabel label1 = new JLabel("����");
				JLabel label2 = new JLabel("��Կ");
				choosePlain.addActionListener(new plainFileHandler());
				chooseKey.addActionListener(new keyFileHandler());
				
				panel1.add(box1);
				box1.add(label1);
				box1.add(plaintext);
				box1.add(choosePlain);
				box1.add(label2);
				box1.add(key);
				box1.add(chooseKey);
				
				//�ڵڶ���С�������Ӱ�ť
				
				panel2.add(encrypt);
				panel2.add(decrypt);
				
				//�ڵ�����С���������ı���
				
				ciphertext.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				ciphertext.setLineWrap(true);
				chooseCipher.addActionListener(new cipherFileHandler());
				storeCipher.addActionListener(new storeCipherHandle());
				
				JLabel lable3 = new JLabel("����");
				panel3.add(lable3);
				panel3.add(ciphertext);
				panel3.add(chooseCipher);
				panel3.add(storeCipher);
				
				
				jf.setVisible(true);
				//����������
				itemCaeser.addActionListener(new itemCaerseHandler());
				
				//����Plfyfair����/����
				itemPlayfair.addActionListener(new itemPlayfairHandler());
				
				//����ά�����Ǽ���
				itemVigenere.addActionListener(new itemVigenereHandler());
				
				//����DES���� ���Ľ�������ʮ�������ַ��� �����ַ�����8���ֽڵı���
				itemDes.addActionListener(new itemDESHandler());
				
			
				
				//����AES���� ��������16���ֽڵ����������ַ��������Բ���ʮ������
				itemAes.addActionListener(new itemAESHandler());
				//����RSA����
				
				itemRsa.addActionListener(new itemRSAHandler());	
			}	
	
	
	
	
	public static void main(String[] args){
		
		DemonstrationSystem gui = new DemonstrationSystem();
	}
	
	
	//AES���õ��ĺ���
	public static byte[] getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = null;
		sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(seed);
		kgen.init(128, sr); // 192 and 256 bits may not be available
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		return raw;
	}
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	public static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
					16).byteValue();
		return result;
	}
}		

