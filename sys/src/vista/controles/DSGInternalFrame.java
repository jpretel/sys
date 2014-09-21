package vista.controles;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import vista.MainFrame;

public class DSGInternalFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DSGInternalFrame() {
		super();
		setFrameIcon(new ImageIcon(
				MainFrame.class.getResource("/main/resources/iconos/logo.png")));
	}

}
