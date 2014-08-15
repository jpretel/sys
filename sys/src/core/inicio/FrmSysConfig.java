package core.inicio;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.inicio.ConectionManager;
import core.inicio.SysCfgInicio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;

public class FrmSysConfig extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtServidor;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JTextField txtBD;

	private SysCfgInicio cfgInicio;

	private List<ChangeListener> listenerList = new ArrayList<ChangeListener>();

	/**
	 * Create the frame.
	 */
	public FrmSysConfig() {
		setAlwaysOnTop(true);
		setMinimumSize(new Dimension(350, 200));
		getContentPane().setMinimumSize(new Dimension(600, 600));
		setResizable(false);
		setTitle("Configuraci\u00F3n Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(22, 14, 46, 14);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(22, 39, 46, 14);

		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(22, 68, 46, 14);

		txtServidor = new JTextField();
		txtServidor.setBounds(107, 11, 225, 20);
		txtServidor.setColumns(10);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(107, 36, 149, 20);
		txtUsuario.setColumns(10);

		txtClave = new JPasswordField();
		txtClave.setBounds(107, 65, 149, 20);

		JButton btnAceptar = new JButton("Agregar");
		btnAceptar.setBounds(50, 124, 94, 23);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isValido()) {
					ChangeEvent ce = new ChangeEvent(this);
					for (ChangeListener listener : listenerList) {
						listener.stateChanged(ce);
					}
				} else {
					System.out.println("Roche");
				}
			}
		});

		JLabel lblBaseDeDatos = new JLabel("Base de Datos");
		lblBaseDeDatos.setBounds(22, 89, 80, 24);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(222, 124, 94, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});

		txtBD = new JTextField();
		txtBD.setBounds(106, 91, 106, 20);
		txtBD.setColumns(10);
		getContentPane().setLayout(null);
		getContentPane().add(lblServidor);
		getContentPane().add(txtServidor);
		getContentPane().add(btnAceptar);
		getContentPane().add(btnCancelar);
		getContentPane().add(lblBaseDeDatos);
		getContentPane().add(txtBD);
		getContentPane().add(lblUsuario);
		getContentPane().add(lblClave);
		getContentPane().add(txtClave);
		getContentPane().add(txtUsuario);
		//getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblServidor, txtServidor, lblUsuario, txtUsuario, lblClave, txtClave, lblBaseDeDatos, txtBD, btnAceptar, btnCancelar}));
	}

	private boolean isCamposValidos() {
		if (txtServidor.getText().trim().isEmpty()) {
			return false;
		}
		if (txtUsuario.getText().trim().isEmpty()) {
			return false;
		}
		
		if (new String(txtClave.getPassword()).isEmpty()) {
			return false;
		}
		if (txtUsuario.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean isValido() {
		if (!isCamposValidos()) {
			return false;
		}
		
		setCfgInicio(new SysCfgInicio());

		getCfgInicio().setServidor(txtServidor.getText().trim());
		getCfgInicio().setUsuario(txtUsuario.getText().trim());
		getCfgInicio().setClave(new String(txtClave.getPassword()));
		getCfgInicio().setBase_datos(txtBD.getText().trim());
		
		if (!ConectionManager.isConexionOK(ConectionManager._mysql, cfgInicio)) {
			return false;
		}

		return true;
	}

	public SysCfgInicio getCfgInicio() {
		return cfgInicio;
	}

	public void setCfgInicio(SysCfgInicio cfgInicio) {
		this.cfgInicio = cfgInicio;
	}

	public void addChangeListener(ChangeListener listener) {
		listenerList.add(listener);
	}
	
	private void cerrar(){
		this.dispose();
	}
}
