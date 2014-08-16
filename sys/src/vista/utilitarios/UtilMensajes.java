package vista.utilitarios;

import javax.swing.JOptionPane;

import vista.Sys;

public class UtilMensajes {
	private static String titulo = "BGC ERP";

	public static void mensaje_error(String mensaje) {
		JOptionPane.showMessageDialog(null, Sys.mensajes.getProperty(mensaje),
				titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void mensaje_error(String mensaje, String... params){
		String grandTotal = String.format(Sys.mensajes.getProperty(mensaje), params);
		JOptionPane.showMessageDialog(null, grandTotal,
				titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void mensaje_alterta(String mensaje) {
		JOptionPane.showMessageDialog(null, Sys.mensajes.getProperty(mensaje),
				titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mensaje_alterta(String mensaje, String... params){
		String grandTotal = String.format(Sys.mensajes.getProperty(mensaje), params);
		JOptionPane.showMessageDialog(null, grandTotal,
				titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}
