package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import vista.controles.JTextFieldLimit;
import vista.utilitarios.FormValidador;
import vista.utilitarios.MaestroTableModel;
import vista.utilitarios.UtilMensajes;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.SysFormularioDAO;
import entity.SysFormulario;

public class FrmSysFormulario extends AbstractMaestro {

	private static final long serialVersionUID = 1L;

	private SysFormulario formulario;

	private SysFormularioDAO formularioDAO = new SysFormularioDAO();

	private List<SysFormulario> formularios = new ArrayList<SysFormulario>();

	private JTable tblLista;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JLabel lblOpcin;
	private JTextField txtOpcion;
	private JLabel lblImgen;
	private JTextField txtImagen;

	public FrmSysFormulario() {
		super("Formularios");

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(227, 11, 46, 14);

		txtCodigo = new JTextField();
		this.txtCodigo.setName("C\u00F3digo");
		txtCodigo.setBounds(276, 8, 122, 20);
		txtCodigo.setColumns(10);
		txtCodigo.setDocument(new JTextFieldLimit(15, true));

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(227, 36, 75, 14);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 207, 273);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		txtDescripcion = new JTextField();
		this.txtDescripcion.setName("Descripci\u00F3n");
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(286, 33, 122, 20);
		txtDescripcion.setDocument(new JTextFieldLimit(75, false));

		this.lblOpcin = new JLabel("Opci\u00F3n");

		this.txtOpcion = new JTextField();
		this.txtOpcion.setName("Descripci\u00F3n");
		this.txtOpcion.setColumns(10);

		this.lblImgen = new JLabel("Im\u00E1gen");

		this.txtImagen = new JTextField();
		this.txtImagen.setName("Descripci\u00F3n");
		this.txtImagen.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(scrollPane,
												GroupLayout.DEFAULT_SIZE, 267,
												Short.MAX_VALUE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblCdigo)
																						.addComponent(
																								lblDescripcin))
																		.addGap(5)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								this.txtDescripcion,
																								GroupLayout.DEFAULT_SIZE,
																								183,
																								Short.MAX_VALUE)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												this.txtCodigo,
																												GroupLayout.DEFAULT_SIZE,
																												139,
																												Short.MAX_VALUE)
																										.addGap(44))))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				this.lblOpcin,
																				GroupLayout.PREFERRED_SIZE,
																				54,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(5)
																		.addComponent(
																				this.txtOpcion,
																				GroupLayout.PREFERRED_SIZE,
																				125,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				this.lblImgen,
																				GroupLayout.PREFERRED_SIZE,
																				54,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(5)
																		.addComponent(
																				this.txtImagen,
																				GroupLayout.PREFERRED_SIZE,
																				171,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
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
																		.addGap(26)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblCdigo)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												this.txtCodigo,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																this.txtDescripcion,
																																GroupLayout.PREFERRED_SIZE,
																																22,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblDescripcin))))
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(15)
																										.addComponent(
																												this.lblOpcin))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												this.txtOpcion,
																												GroupLayout.PREFERRED_SIZE,
																												22,
																												GroupLayout.PREFERRED_SIZE)))
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(15)
																										.addComponent(
																												this.lblImgen))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												this.txtImagen,
																												GroupLayout.PREFERRED_SIZE,
																												22,
																												GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				scrollPane,
																				GroupLayout.DEFAULT_SIZE,
																				234,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		pnlContenido.setLayout(groupLayout);

		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setFormulario(getFormularios().get(selectedRow));
						else
							setFormulario(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	@Override
	public void nuevo() {
		setFormulario(new SysFormulario());
		txtCodigo.requestFocus();
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {
		formularioDAO.crear_editar(getFormulario());
	}

	@Override
	public void eliminar() {
		if (getFormulario() != null) {
			formularioDAO.remove(getFormulario());
		}
	}

	@Override
	public void llenar_datos() {
		if (getFormulario() != null) {
			txtCodigo.setText(getFormulario().getIdformulario());
			txtDescripcion.setText(getFormulario().getDescripcion());
			txtOpcion.setText(getFormulario().getOpcion());
			txtImagen.setText(getFormulario().getImagen());
		} else {
			txtCodigo.setText("");
			txtDescripcion.setText("");
			txtOpcion.setText("");
			txtImagen.setText("");
		}
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (SysFormulario ob : getFormularios()) {
			model.addRow(new Object[] { ob.getIdformulario(),
					ob.getDescripcion() });
		}
		if (getFormularios().size() > 0) {
			setFormulario(getFormularios().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setFormularios(formularioDAO.findAll());
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		TextFieldsEdicion(true, txtDescripcion, txtImagen, txtOpcion);
		tblLista.setEnabled(false);
	}

	@Override
	public void vista_noedicion() {
		TextFieldsEdicion(false, txtCodigo, txtDescripcion, txtImagen,
				txtOpcion);
		tblLista.setEnabled(true);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenarDesdeVista() {
		getFormulario().setIdformulario(txtCodigo.getText());
		getFormulario().setDescripcion(txtDescripcion.getText());
		getFormulario().setOpcion(txtOpcion.getText());
		getFormulario().setImagen(txtImagen.getText());
	}

	@Override
	public boolean isValidaVista() {

		if (!FormValidador.TextFieldObligatorios(txtCodigo, txtDescripcion,
				txtOpcion))
			return false;

		if (getEstado().equals(NUEVO)) {
			if (formularioDAO.find(this.txtCodigo.getText().trim()) != null) {
				UtilMensajes.mensaje_alterta("CODIGO_EXISTE");
				this.txtCodigo.requestFocus();
				return false;
			}
		}

		return true;
	}

	@Override
	public void limpiarDetalle() {
		// TODO Auto-generated method stub

	}

	public SysFormulario getFormulario() {
		return formulario;
	}

	public void setFormulario(SysFormulario formulario) {
		this.formulario = formulario;
	}

	public List<SysFormulario> getFormularios() {
		return formularios;
	}

	public void setFormularios(List<SysFormulario> formularios) {
		this.formularios = formularios;
	}
}
