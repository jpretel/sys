package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class ControladorOpciones {

	private JDesktopPane desktopPane;

	public ActionListener returnAction(final String opcion) {
		try {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						iniciarFormulario(opcion);
					} catch (InstantiationException | IllegalAccessException
							| ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			};
		} catch (Exception e) {
			return null;
		}
	}

	private void iniciarFormulario(String clase) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		String urlClase = "vista.formularios." + clase.trim();
		JInternalFrame frame = (JInternalFrame) Class.forName(urlClase)
				.newInstance();
		frame.setVisible(true);
		getDesktopPane().add(frame);
		try {
			frame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}
}
