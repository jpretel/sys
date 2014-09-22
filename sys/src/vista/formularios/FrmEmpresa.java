package vista.formularios;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import dao.EmpresaDAO;
import vista.Sys;
import vista.controles.DSGInternalFrame;
import vista.controles.JTextFieldLimit;
import vista.utilitarios.FormValidador;
import vista.utilitarios.UtilMensajes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmEmpresa extends DSGInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmpresaDAO empresaDAO = new EmpresaDAO();

	protected JPanel pnlContenido;
	protected JTextField txtRS;
	protected JTextField txtRUC;
	protected JTextField txtDireccion;

	public static void main(String[] args) {
		new FrmEmpresa();
	}

	public FrmEmpresa() {
		setTitle("Empresa");
		setMaximizable(false);
		setIconifiable(false);
		setClosable(true);
		setVisible(true);
		setResizable(false);
		this.show();

		pnlContenido = new JPanel();
		getContentPane().add(pnlContenido, BorderLayout.CENTER);
		setBounds(100, 100, 321, 172);
		JLabel lblClaveActual = new JLabel("Raz\u00F3n Social");

		JLabel lblNuevaClave = new JLabel("Ruc");

		JLabel lblRepitaClave = new JLabel("Direcci\u00F3n");

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// System.out.println(Sys.usuario.getIdusuario());
				if (isValidaVista()) {
					Verifica();
				}
			}
		});

		this.txtRS = new JTextField();
		this.txtRS.setName("Raz\u00F3n Social");
		this.txtRS.setColumns(10);
		this.txtRS.setDocument(new JTextFieldLimit(200, false));
		this.txtRS.setText(Sys.empresa.getRazon_social());
		
		this.txtRUC = new JTextField();
		this.txtRUC.setName("R.U.C");
		this.txtRUC.setColumns(10);
		this.txtRUC.setDocument(new JTextFieldLimit(12, false));
		this.txtRUC.setText(Sys.empresa.getRuc());
		
		this.txtDireccion = new JTextField();
		this.txtDireccion.setName("Direcci\u00F3n");
		this.txtDireccion.setColumns(10);
		this.txtDireccion.setDocument(new JTextFieldLimit(200, false));
		this.txtDireccion.setText(Sys.empresa.getDireccion());
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(10)
																		.addComponent(
																				lblClaveActual,
																				GroupLayout.PREFERRED_SIZE,
																				71,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				this.txtRS,
																				GroupLayout.PREFERRED_SIZE,
																				199,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(10)
																		.addComponent(
																				lblNuevaClave,
																				GroupLayout.PREFERRED_SIZE,
																				71,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				this.txtRUC,
																				GroupLayout.PREFERRED_SIZE,
																				199,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(10)
																		.addComponent(
																				lblRepitaClave,
																				GroupLayout.PREFERRED_SIZE,
																				71,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				this.txtDireccion,
																				GroupLayout.PREFERRED_SIZE,
																				199,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(9, Short.MAX_VALUE))
						.addGroup(
								Alignment.TRAILING,
								groupLayout
										.createSequentialGroup()
										.addContainerGap(73, Short.MAX_VALUE)
										.addComponent(btnCambiar,
												GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(btnCancelar,
												GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addGap(56)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(11)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblClaveActual)
														.addComponent(
																this.txtRS,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(14)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNuevaClave)
														.addComponent(
																this.txtRUC,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(14)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblRepitaClave)
														.addComponent(
																this.txtDireccion,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																btnCambiar)
														.addComponent(
																btnCancelar))
										.addGap(9)));
		getContentPane().setLayout(groupLayout);
	}

	public void Verifica() {

		Sys.empresa.setDireccion(txtDireccion.getText());
		Sys.empresa.setRazon_social(txtRS.getText());
		Sys.empresa.setRuc(txtRUC.getText());

		empresaDAO.edit(Sys.empresa);
		UtilMensajes.mensaje_alterta("ACTUALIZA_OK");
		this.dispose();
	}

	public boolean isValidaVista() {
		if (!FormValidador.TextFieldObligatorios(txtDireccion, txtRS, txtRUC)) {
			return false;
		}

		return true;
	}

	public void Limpiar() {
		txtDireccion.setText(null);
		txtRS.setText(null);
		txtRUC.setText(null);
		this.txtRS.requestFocus();
	}
}
