package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import vista.utilitarios.MaestroTableModel;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.GrupoUsuarioDAO;
import dao.SubdiarioDAO;
import entity.GrupoUsuario;
import entity.Subdiario;

import javax.swing.JCheckBox;

public class FrmGrupoUsuario extends AbstractMaestro {

	private static final long serialVersionUID = 1L;

	private GrupoUsuario grupoUsuario;

	private GrupoUsuarioDAO grupoUsuarioDAO = new GrupoUsuarioDAO();

	private List<GrupoUsuario> gruposUsuario = new ArrayList<GrupoUsuario>();
	private JTable tblLista;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JCheckBox chkEsAdministrador;
	public FrmGrupoUsuario() {
		super("SubDiarios");

		getBarra().setFormMaestro(this);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(227, 11, 46, 14);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(276, 8, 122, 20);
		txtCodigo.setColumns(10);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(227, 36, 75, 14);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 207, 273);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(286, 33, 122, 20);
		
		chkEsAdministrador = new JCheckBox("Es Administrador");

		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCdigo)
								.addComponent(lblDescripcin))
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
									.addGap(44))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chkEsAdministrador, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(153)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCdigo)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDescripcin))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chkEsAdministrador))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
					.addContainerGap())
		);
		pnlContenido.setLayout(groupLayout);

		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setGrupoUsuario(getGruposUsuario().get(selectedRow));
						else
							setGrupoUsuario(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	@Override
	public void nuevo() {
		setGrupoUsuario(new GrupoUsuario());
	}

	@Override
	public void grabar() {
		 getGrupoUsuarioDAO().crear_editar(getGrupoUsuario());
	}

	@Override
	public void llenarDesdeVista() {
		getGrupoUsuario().setIdgrupoUsuario(txtCodigo.getText());
		getGrupoUsuario().setDescripcion(txtDescripcion.getText());
		getGrupoUsuario().setEsAdministrador(chkEsAdministrador.isSelected()? 1 : 0);
	};

	@Override
	public void eliminar() {
		if (getGrupoUsuario()!= null) {
			getGrupoUsuarioDAO().remove(getGrupoUsuario());
		}
	}

	@Override
	public void llenar_datos() {
		if (getGrupoUsuario() != null) {
			txtCodigo.setText(getGrupoUsuario().getIdgrupoUsuario());
			txtDescripcion.setText(getGrupoUsuario().getDescripcion());
			chkEsAdministrador.setSelected(getGrupoUsuario().getEsAdministrador() == 1);
		} else {
			txtCodigo.setText("");
			txtDescripcion.setText("");
			chkEsAdministrador.setSelected(true);
		}
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (GrupoUsuario obj: getGruposUsuario()) {
			model.addRow(new Object[] { obj.getIdgrupoUsuario(), obj.getDescripcion() });
		}
		if (getGruposUsuario().size() > 0) {
			setGrupoUsuario(getGruposUsuario().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setGruposUsuario(getGrupoUsuarioDAO().findAll());
	}
	
	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		chkEsAdministrador.setEnabled(true);
		tblLista.setEnabled(false);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		chkEsAdministrador.setEnabled(false);
		tblLista.setEnabled(true);
	}
	
	@Override
	public void anular() {
		// TODO Auto-generated method stub
		
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
	public boolean isValidaVista() {
		if (txtCodigo.getText().trim().isEmpty())
			return false;
		if (txtDescripcion.getText().trim().isEmpty())
			return false;
		return true;
	}

	public GrupoUsuario getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

	public GrupoUsuarioDAO getGrupoUsuarioDAO() {
		return grupoUsuarioDAO;
	}

	public void setGrupoUsuarioDAO(GrupoUsuarioDAO grupoUsuarioDAO) {
		this.grupoUsuarioDAO = grupoUsuarioDAO;
	}

	public List<GrupoUsuario> getGruposUsuario() {
		return gruposUsuario;
	}

	public void setGruposUsuario(List<GrupoUsuario> gruposUsuario) {
		this.gruposUsuario = gruposUsuario;
	}



	
}
