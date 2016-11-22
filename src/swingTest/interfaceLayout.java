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
	 * �����ʼ��
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
		jf.setTitle("����ϵͳ");
		jf.setSize(WIDTH, HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		//�м����������
		JPanel panel = new JPanel();
		jf.setContentPane(panel);
		panel.setLayout(new BorderLayout());
		
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
		//ʹ��GridBagLayout����
		JPanel contentPanel = new JPanel();
		jf.setContentPane(contentPanel);
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//�����������м䵯������
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSise = tk.getScreenSize();
		int x = (screenSise.width-WIDTH)/2;
		int y = (screenSise.height-HEIGHT)/2;
		jf.setLocation(x,y);
		//��ʼ�����
		JLabel label1 = new JLabel("����");
		JLabel label2 = new JLabel("��Կ");
		JLabel lable3 = new JLabel("����");
		JTextArea plaintext = new JTextArea(ROW,LINE);
		JTextArea key = new JTextArea(ROW,LINE);
		JTextArea ciphertext = new JTextArea(ROW,LINE);
		//�����ı���ɻ���
		plaintext.setLineWrap(true);
		key.setLineWrap(true);
		ciphertext.setLineWrap(true);
		
		plaintext.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		key.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		ciphertext.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		JButton encrypt = new JButton("����");
		JButton decrypt = new JButton("����");
		//����constrains
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(0, 0, 10, 0);
		//��add����������
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
