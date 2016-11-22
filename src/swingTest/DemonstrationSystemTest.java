package swingTest;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.sound.midi.VoiceStatus;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

public class DemonstrationSystemTest {
	static final int WIDTH = 1000;
	static final int HEIGHT = 800;
	static int flag = 0;
	public static JButton encrypt = new JButton("加密");
	public static JButton decrypt = new JButton("解密");
	public static JTextArea plaintext = new JTextArea(10,20);
	public static JTextArea key = new JTextArea(10,20);
	public static JTextArea ciphertext = new JTextArea(30,20);
	public static JButton publicEncrypt = new JButton("公钥加密");
	public static JButton privateEncrypt = new JButton("私钥加密");
	public static JButton publicDecrypt = new JButton("公钥解密");
	public static JButton privateDecrypt = new JButton("私钥解密");
	public static JFrame jf = new JFrame();
	public static JPanel panel1 = new JPanel();
	public static JPanel panel2 = new JPanel();
	public static JPanel panel3 = new JPanel();
	public static Box box1 = Box.createVerticalBox();
	
	//自定义监听器
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
			
			//公钥加密
			publicEncrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton getPublicKey = new JButton("获得公钥");	
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
			//私钥加密
			privateEncrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton getPrivateKey = new JButton("获得私钥");	
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
			//公钥解密
			publicDecrypt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String cipherStr = ciphertext.getText();
					String keyStr = key.getText();
					String plainStr = RSA.rsaPublicDecrypt(cipherStr, keyStr);
					plaintext.setText(plainStr);
				}
			});
			//私钥解密
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
	
	//构造器
    public DemonstrationSystemTest(){
		//顶层容器
				
				jf.setTitle("加密系统");
				jf.setSize(WIDTH, HEIGHT);
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setVisible(true);
				
				//中间容器，面板
				JPanel panel = new JPanel();
				jf.setContentPane(panel);
				panel.setLayout(new FlowLayout());
				
				//菜单条
				JMenuBar mbar = new JMenuBar();
				jf.setJMenuBar(mbar);
				
				//菜单
				JMenu jm1 = new JMenu("经典加密");
				JMenu jm2 = new JMenu("对称加密");
				JMenu jm3 = new JMenu("非对称加密");
				mbar.add(jm1);
				mbar.add(jm2);
				mbar.add(jm3);
				
				//菜单项
				JMenuItem itemCaeser = new JMenuItem("凯撒加密");
				JMenuItem itemPlayfair = new JMenuItem("PlayFair加密");
				JMenuItem itemVigenere = new JMenuItem("维吉尼亚加密");
				
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
				
				//向最大的中间面板在添加三个面板
				
				panel1.setPreferredSize(new Dimension(300,600));
				panel2.setPreferredSize(new Dimension(100, 600));
				panel3.setPreferredSize(new Dimension(300, 600));
				
				panel1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				panel2.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				panel3.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				
				panel.add(panel1);
				panel.add(panel2);
				panel.add(panel3);
				
				//第一个小面板中添加文本域
				
				
				plaintext.setLineWrap(true);
				key.setLineWrap(true);
				
				plaintext.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				key.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				
				JLabel label1 = new JLabel("明文");
				JLabel label2 = new JLabel("秘钥");
				
				panel1.add(box1);
				box1.add(label1);
				box1.add(plaintext);
				
				box1.add(label2);
				box1.add(key);
				
				//在第二个小面板中添加按钮
				
				panel2.add(encrypt);
				panel2.add(decrypt);
				
				//在第三个小面板中添加文本域
				
				ciphertext.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				ciphertext.setLineWrap(true);;
				
				JLabel lable3 = new JLabel("密文");
				panel3.add(lable3);
				panel3.add(ciphertext);
				
				
				
				jf.setVisible(true);
				//处理凯撒加密
				itemCaeser.addActionListener(new itemCaerseHandler());
				
				//处理Plfyfair加密/解密
				itemPlayfair.addActionListener(new itemPlayfairHandler());
				
				//处理维吉尼亚加密
				itemVigenere.addActionListener(new itemVigenereHandler());
				
				//处理DES加密 明文建议输入十六进制字符串 并且字符数是8个字节的倍数
				itemDes.addActionListener(new itemDESHandler());
				
			
				
				//处理AES加密 明文输入16个字节的整数倍的字符串，可以不是十六进制
				itemAes.addActionListener(new itemAESHandler());
				//处理RSA加密
				
				itemRsa.addActionListener(new itemRSAHandler());	
			}	
	
	
	
	
	public static void main(String[] args){
		
		DemonstrationSystemTest gui = new DemonstrationSystemTest();
	}
	
	
	//AES中用到的函数
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


