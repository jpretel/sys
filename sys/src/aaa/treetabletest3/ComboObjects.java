package aaa.treetabletest3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComboObjects implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		ComboObject co = (ComboObject) cb.getSelectedItem();
		System.out.println("co = " + co + "\n" + // calls toString method
				"co.name = " + co.name + "\n" + "co.id = " + co.id);
	}

	private JPanel getContent() {
		String[] names = { "John Monroe", "John Paul Jones",
				"Alexander Hamilton" };
		int[] ids = { 1002, 15, 390 };
		ComboObject[] cos = new ComboObject[names.length];
		for (int j = 0; j < cos.length; j++)
			cos[j] = new ComboObject(names[j], ids[j],2);
		JComboBox combo = new JComboBox(cos);
		combo.addActionListener(this);
		JPanel panel = new JPanel();
		panel.add(combo);
		return panel;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new ComboObjects().getContent(), "North");
		f.setSize(240, 100);
		f.setLocation(200, 200);
		f.setVisible(true);
	}
}

class ComboObject {
	String name;
	String phone;
	int id;
	int propertyToDisplay;
	public static final int DISPLAY_NAME = 1;
	public static final int DISPLAY_PHONE = 2;

	/**
	 * The p2D variable is sent to the constructor --- so to get a combo box
	 * showing the persons number ...
	 * 
	 * new ComboObject("jay","123-123-1234",ComboObject.DISPLAY_PHONE);
	 **/
	public ComboObject(String name, int id, int p2d) {
		this.name = name;
		this.id = id;
		propertyToDisplay = p2d;
	}

	public String toString() {
		if (propertyToDisplay == DISPLAY_NAME)
			return name;
		else if (propertyToDisplay == DISPLAY_PHONE)
			return phone;
		else
			return "I DONT UNDERSTAND THAT PROPERTY";
	}
}