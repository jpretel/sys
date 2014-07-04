package dao;

import java.awt.Container;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class springPract {
	
	public static void main(String arg[]){
		JFrame frame = new JFrame("SpringLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		
		Component left = new JLabel("left");	
		Component right = new JTextField(15);
		
		contentPane.add(left);
		contentPane.add(right);
		layout.putConstraint(SpringLayout.WEST, left, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, left, 25, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, right, 40, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, right, 25, SpringLayout.NORTH, contentPane);
		
		frame.setSize(300,200);
		frame.setVisible(true);
		
	}

}
