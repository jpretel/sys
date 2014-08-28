package vista;

import static vista.Sys.cfgInicio;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import controlador.Mensajes;
import vista.formularios.*;
import core.inicio.ConfigInicial;
import core.inicio.SysCfgInicio;
import dao.UsuarioDAO;
import entity.SysOpcion;

import java.awt.Dimension;

public class SysDesarrollo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktopPane;
	
	public SysDesarrollo() {
		//setSize(new Dimension(529, 463));
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//Se establece el tamaño minimo del SysDesarrollo
		setMinimumSize(new Dimension(800, 600)); 
		setTitle(":::FORMULRIO PARA PRUEBAS:::");
		//Termina SysDesarrollo al darle click en el boton Cerrar del extremo superior derechi 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
	}
	
	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}
	
	public static void main(String[] args) {
		String[] datos = null;
		
		datos = ConfigInicial.LlenarConfig();

		cfgInicio = new SysCfgInicio();
		cfgInicio.setServidor(datos[0]);
		cfgInicio.setBase_datos(datos[1]);
		cfgInicio.setUsuario(datos[2]);
		cfgInicio.setClave(datos[3]);
		
		//UsuarioDAO usuarioDAO = new UsuarioDAO();
		//Sys.usuario = usuarioDAO.find("ADMINISTRADOR");
		
		Sys.mensajes = new Mensajes("ESPANOL");
		
		SysDesarrollo frm = new SysDesarrollo();
		frm.setVisible(true);
		
		
		FrmListaProductos frame2 = new FrmListaProductos();
		frm.getDesktopPane().add(frame2);	
		
		FrmProductos frame1 = new FrmProductos();
		frm.getDesktopPane().add(frame1);	
	}
	
}