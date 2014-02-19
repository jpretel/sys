package vista;

import java.awt.EventQueue;

import controlador.VariablesGlobales;


public class Main {

	public static void main(String[] args) {
		
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
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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