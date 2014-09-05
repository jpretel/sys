package vista.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import vista.controles.DSGTableModel;
import vista.utilitarios.MaestroTableModel;
import vista.utilitarios.MaestroTreeTableModel;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.GrupoUsuarioDAO;
import dao.SubdiarioDAO;
import entity.Consumidor;
import entity.GrupoUsuario;
import entity.Subdiario;
import entity.SysOpcion;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import org.jdesktop.swingx.JXTreeTable;

public class FrmGrupoUsuario extends AbstractMaestro {

	private static final long serialVersionUID = 1L;

	private GrupoUsuario grupoUsuario;
	private SysOpcion sysopcion = new SysOpcion();

	private GrupoUsuarioDAO grupoUsuarioDAO = new GrupoUsuarioDAO();

	private List<GrupoUsuario> gruposUsuario = new ArrayList<GrupoUsuario>();
	private JTable tblLista;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JCheckBox chkEsAdministrador;
	private JXTreeTable tblOpciones;
	private MaestroTreeTableModel mttm = new MaestroTreeTableModel(new String[] { "Código", "Descripción" }){

		@Override
		public Object getValueAt(Object node, int column) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getChild(Object parent, int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getChildCount(Object parent) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getIndexOfChild(Object parent, Object child) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	};
	JScrollPane scrollPane2 = new JScrollPane();
	private JLabel lblIngreseOpciones;
	public FrmGrupoUsuario() {
		super("Perfil de Grupos");

		getBarra().setFormMaestro(this);

		JLabel lblCdigo = new JLabel("C\u00F3digo");

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");

		JScrollPane scrollPane = new JScrollPane();

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		
		chkEsAdministrador = new JCheckBox("Es Administrador");
		chkEsAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chkEsAdministrador.isSelected()){
					getAlmacenesTM().setEditar(false);
				}
			}
		});
		
		
		tblOpciones = new JXTreeTable();
		tblOpciones.setTreeTableModel(mttm);
		tblOpciones.setFillsViewportHeight(true);
		scrollPane2.setViewportView(tblOpciones);
		tblOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		getAlmacenesTM().setNombre_detalle("Código");
		getAlmacenesTM().setObligatorios(0, 1);
		getAlmacenesTM().setRepetidos(0);
		getAlmacenesTM().setScrollAndTable(scrollPane2, tblOpciones);
		
		lblIngreseOpciones = new JLabel("Ingrese Opciones");
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblCdigo)
							.addGap(26)
							.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblDescripcin)
							.addGap(5)
							.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(chkEsAdministrador)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblIngreseOpciones, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCdigo)
						.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblDescripcin))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
					.addGap(7)
					.addComponent(chkEsAdministrador)
					.addGap(7)
					.addComponent(lblIngreseOpciones)
					.addGap(3)
					.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
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
		//getDetalleTM().setEditar(true);
		
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
		getAlmacenesTM().setEditar(true);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		chkEsAdministrador.setEnabled(false);
		tblLista.setEnabled(true);
		getAlmacenesTM().setEditar(false);
	}
	
	@Override
	public void anular() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void limpiarDetalle() {		
		getAlmacenesTM().limpiar();
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
	
	public DSGTableModel getAlmacenesTM() {
		return ((DSGTableModel) tblOpciones.getModel());
	}
	
}

