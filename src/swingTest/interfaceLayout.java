package swingTest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class interfaceLayout {
	/**
	 * 界面初始化
	 * */
	static final int WIDTH = 1000;
	static final int HEIGHT = 800;
	static final int ROW = 25;
	static final int LINE = 25;
	public static void main(String[] args){
		interfaceInitial();
	}
	public  static void interfaceInitial() { 
		JFrame jf = new JFrame();
		jf.setTitle("加密系统");
		jf.setSize(WIDTH, HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		//中间容器，面板
		JPanel panel = new JPanel();
		jf.setContentPane(panel);
		panel.setLayout(new BorderLayout());
		
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
		//使用GridBagLayout布局
		JPanel contentPanel = new JPanel();
		jf.setContentPane(contentPanel);
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//设置在桌面中间弹出窗口
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSise = tk.getScreenSize();
		int x = (screenSise.width-WIDTH)/2;
		int y = (screenSise.height-HEIGHT)/2;
		jf.setLocation(x,y);
		//初始化组件
		JLabel label1 = new JLabel("明文");
		JLabel label2 = new JLabel("秘钥");
		JLabel lable3 = new JLabel("密文");
		JTextArea plaintext = new JTextArea(ROW,LINE);
		JTextArea key = new JTextArea(ROW,LINE);
		JTextArea ciphertext = new JTextArea(ROW,LINE);
		//设置文本域可换行
		plaintext.setLineWrap(true);
		key.setLineWrap(true);
		ciphertext.setLineWrap(true);
		
		plaintext.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		key.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		ciphertext.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		JButton encrypt = new JButton("加密");
		JButton decrypt = new JButton("解密");
		//设置constrains
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(0, 0, 10, 0);
		//用add方法添加组件
		GridBagLayout1.addComp(contentPanel, label1, constraints, 0, 0, 1, 1);
		GridBagLayout1.addComp(contentPanel, label2, constraints, 1, 0, 1, 1);
		GridBagLayout1.addComp(contentPanel, lable3, constraints, 2, 0, 1, 1);
		GridBagLayout1.addComp(contentPanel, plaintext, constraints, 0, 1, 1, 1);
		GridBagLayout1.addComp(contentPanel, key, constraints, 1, 1, 1, 1);
		GridBagLayout1.addComp(contentPanel, ciphertext, constraints, 2, 1, 1, 1);
		GridBagLayout1.addComp(contentPanel, encrypt, constraints, 0, 2, 1, 1);
		GridBagLayout1.addComp(contentPanel, decrypt, constraints, 2, 2, 1, 1);
		
		
		
		jf.setVisible(true);
		
		
		
	}

}
