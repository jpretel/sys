package vista.formularios;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vista.Sys;
import core.security.Encryption;
import dao.UsuarioDAO;
import entity.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class FrmLogin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<ChangeListener> listenerList = new ArrayList<ChangeListener>();

	public FrmLogin() {
		setMaximumSize(new Dimension(150, 130));
		setMinimumSize(new Dimension(150, 130));
		setTitle("Inicio de Sesi\u00F3n");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(10, 36, 46, 14);
		getContentPane().add(lblClave);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(66, 8, 217, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		txtClave = new JPasswordField();
		txtClave.setBounds(66, 33, 217, 20);
		getContentPane().add(txtClave);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarSesion();
			}
		});
		btnAceptar.setBounds(58, 72, 89, 23);
		getContentPane().add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(161, 72, 89, 23);
		getContentPane().add(btnCancelar);
	}

	private void iniciarSesion() {
		Sys.usuario = null;
		String idusuario, clave;
		idusuario = txtUsuario.getText();
		clave = new String(txtClave.getPassword());

		Usuario usuario = getUsuarioDAO().getPorUsuarioClave(idusuario,
				Encryption.encrypt(clave));

		if (usuario != null) {
			Sys.usuario = usuario;
			ChangeEvent ce = new ChangeEvent(this);
			for (ChangeListener listener : listenerList) {
				listener.stateChanged(ce);
			}
			this.dispose();
		} else
			System.out.println("Usuario o Clave incorrecta");

	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void addChangeListener(ChangeListener listener) {
		listenerList.add(listener);
	}
}
