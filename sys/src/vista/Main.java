package vista;

import java.awt.EventQueue;






import javax.swing.JDialog;
import javax.swing.JFrame;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.skin.OfficeSilver2007Skin;

import controlador.VariablesGlobales;
import core.inicio.ConfigInicial;


public class Main {

	public static void main(String[] args) {
		/*
		try {	
			salir:
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				System.out.println(info.getName());
				switch (info.getName()) {
				case "GTK+":
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break salir;
				case "Windows":
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break salir;
				default:
					javax.swing.UIManager
							.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
		}
		Tests
		*/
		ConfigInicial.CrearConfig();
		ConfigInicial.LlenarConfig();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//SubstanceLookAndFeel.setSkin(new OfficeSilver2007Skin());
					Home app = new Home("Sistema ERP");
					VariablesGlobales.home = app;
					app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
