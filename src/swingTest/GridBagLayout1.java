package swingTest;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

public class GridBagLayout1{
	//添加控件到容器中
	public static void addComp(JPanel jp,Component c,GridBagConstraints constraints,int x,int y,int w,int h) {
		
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		jp.add(c,constraints);
		
		
	}

}
