package vista;
	
import static vista.Sys.cfgInicio;

import javax.persistence.Tuple;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.eclipse.persistence.internal.jpa.querydef.TupleImpl;

import controlador.Mensajes;
import vista.formularios.*;
import core.inicio.ConfigInicial;
import core.inicio.SysCfgInicio;
import dao.ConsumidorDAO;
import dao.KardexDAO;
import dao.ProductoDAO;
import dao.SysModuloDAO;
import dao.UsuarioDAO;
import entity.Producto;
import entity.Usuario;

import java.awt.Dimension;
	
public class SysDesarrollo extends JFrame {
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
		Sys.desktoppane = desktopPane;
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
		
		Usuario u = new UsuarioDAO().find("ADMINISTRADOR");
		
		System.out.println(u.getIdusuario());
		
		ProductoDAO pdao = new ProductoDAO();
		Producto p = pdao.find("001");
		KardexDAO kdao = new KardexDAO();
		
		//System.out.println(kdao.getSaldoAntesDe(20140926, p, null, null));
		//System.out.println(kdao.getMovimientos(20140925, 20140927, p, null, null));
		
		for (Tuple t : kdao.getSaldosSucursalAlmacen(20140925,null,null)){
			System.out.println(t.get("producto"));
			System.out.println(t.get("cantidad"));
		}
		
		//		SysModuloDAO moduloDAO = new SysModuloDAO();
//		
//		moduloDAO.getModulos(u.getGrupoUsuario());
		/*
		cfgInicio.setTipo_creacion("");//No Hace Nada
		Sys.mensajes = new Mensajes("ESPANOL");
		SysDesarrollo frm = new SysDesarrollo();
		frm.setVisible(true);		
		
		FrmListaOrdenCompra frm4 = new FrmListaOrdenCompra();
		frm.getDesktopPane().add(frm4);
		
		FrmListaProductos frm5 = new FrmListaProductos();
		frm.getDesktopPane().add(frm5);
		
		
		
		System.out.println(kdao.getSaldoAntesDe(20140926, p, null, null));
		
		FrmUnimedida frm6 = new FrmUnimedida();
		frm.getDesktopPane().add(frm6);
		*/
	}
}