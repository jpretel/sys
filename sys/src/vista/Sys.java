package vista;

import java.io.File;

import static vista.utilitarios.UtilMensajes.mensaje_alterta;

import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controlador.Mensajes;
import vista.formularios.FrmLogin;
import core.inicio.ConectionManager;
import core.inicio.ConfigInicial;
import core.inicio.SysCfgInicio;
import core.security.Encryption;
import dao.GrupoUsuarioDAO;
import dao.UsuarioDAO;
import entity.GrupoUsuario;
import entity.Usuario;

public class Sys {
	public static final String EDICION = "EDICION";
	public static final String VISTA = "VISTA";
	public static final String NUEVO = "NUEVO";

	public final static String SYS_CONFIG = "config.properties";

	public static SysCfgInicio cfgInicio;

	public static Usuario usuario;
	
	public static Mensajes mensajes;
	private FrmSysConfig frm = new FrmSysConfig();
	public static MainFrame mainF;

	public static void main(String[] args) {
		try {
			salir: for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
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
		Sys sys = new Sys();
		sys.iniciar();
	}

	public void iniciar() {
		
		mensajes = new Mensajes("ESPANOL");
		
		File sys_file = new File(SYS_CONFIG);
		String[] datos = null;
		frm = new FrmSysConfig();
		frm.setLocationRelativeTo(null);
		boolean isOK;
		if (sys_file.exists()) {
			System.out.println("Existe");
			datos = ConfigInicial.LlenarConfig();
			if (datos != null) {
				cfgInicio = new SysCfgInicio();

				cfgInicio.setServidor(datos[0]);
				cfgInicio.setBase_datos(datos[1]);
				cfgInicio.setUsuario(datos[2]);
				cfgInicio.setClave(datos[3]);

				isOK = ConectionManager.isConexionOK(ConectionManager._mysql,
						cfgInicio);
				if (isOK) {
					abrir();
				} else {
					mensaje_alterta("ERROR_CONFIG");
					frm.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent arg0) {
							ConfigInicial.CrearConfig(frm.getCfgInicio());
							iniciar();
						}
					});

					frm.setVisible(true);
				}
			}
		} else {
			mensaje_alterta("NO_HAY_CONFIG");
			frm.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					ConfigInicial.CrearConfig(frm.getCfgInicio());
					iniciar();
				}
			});

			frm.setVisible(true);
		}
	}

	public void abrir() {
		frm.dispose();

		GrupoUsuario grpAdmin = new GrupoUsuarioDAO().find("ADM");

		if (grpAdmin == null) {
			grpAdmin = new GrupoUsuario();
			grpAdmin.setIdgrupoUsuario("ADM");
			grpAdmin.setDescripcion("Grupo de Administradores");
			grpAdmin.setEsAdministrador(1);
			new GrupoUsuarioDAO().create(grpAdmin);
		}

		if (new UsuarioDAO().getTotalPorGrupoUsuario(grpAdmin) == 0) {
			Usuario u = new Usuario();
			u.setIdusuario("ADMINISTRADOR");
			u.setEstado(1);
			u.setGrupoUsuario(grpAdmin);
			u.setNombres("USUARIO ADMINISTRADOR");
			u.setClave(Encryption.pss_encrypt("administrador"));
			new UsuarioDAO().create(u);
		}

		FrmLogin frm = new FrmLogin();
		frm.setVisible(true);

		frm.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				iniciaMainFrame();
			}
		});
	}

	private void iniciaMainFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				new MainFrame();
			}

		});
	}

}
