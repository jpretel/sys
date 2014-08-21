package vista.utilitarios;

import javax.swing.JFrame;
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
	
	public static void mensaje_alterta(JFrame frame, String mensaje) {
		JOptionPane.showMessageDialog(frame, Sys.mensajes.getProperty(mensaje),
				titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mensaje_alterta(String mensaje, String... params){
		String grandTotal = String.format(Sys.mensajes.getProperty(mensaje), params);
		JOptionPane.showMessageDialog(null, grandTotal,
				titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int msj_error(String mensaje){
		int seleccion = JOptionPane.showOptionDialog(null, Sys.mensajes.getProperty(mensaje), "Informacion del Sistema",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Si", "No"}, "Si");
			
		return seleccion;			
	}
}
