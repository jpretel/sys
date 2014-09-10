package vista.utilitarios;

import javax.swing.JTextField;

public class FormValidador {
	public static void TextFieldsEdicion(boolean band, JTextField... texts) {
		for (JTextField text : texts) {
			text.setEditable(band);
		}
	}
	
	public static boolean TextFieldObligatorios(JTextField... texts) {
		for (JTextField text : texts) {
			if (text.getText().trim().isEmpty()) {
				UtilMensajes.mensaje_alterta("DATO_REQUERIDO", text.getName());
				text.requestFocus();
				return false;
			}
		}
		return true;
	}
}
