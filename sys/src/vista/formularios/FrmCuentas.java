package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import dao.CuentaDAO;
import entity.Cuenta;

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

public class FrmCuentas extends AbstractMaestro {

	private static final long serialVersionUID = 1L;

	private Cuenta cuenta;

	private CuentaDAO cuentaDAO = new CuentaDAO();

	private List<Cuenta> cuentas = new ArrayList<Cuenta>();
	private JTable tblLista;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;	

	public FrmCuentas() {
		super("Cuentas Contables");
		
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
		
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCdigo)
						.addComponent(lblDescripcin))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
							.addGap(44)))
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
										.addComponent(lblDescripcin)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)))
					.addContainerGap())
		);
		pnlContenido.setLayout(groupLayout);

		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setCuenta(getCuentas().get(selectedRow));
						else
							setCuenta(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	@Override
	public void nuevo() {
		setCuenta(new Cuenta());
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {
		getCuentaDAO().crear_editar(getCuenta());
	}
	
	@Override
	public void llenarDesdeVista() {
		getCuenta().setId(txtCodigo.getText());
		getCuenta().setDescripcion(txtDescripcion.getText());
	};

	@Override
	public void eliminar() {
		setEstado(VISTA);
		vista_noedicion();
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public CuentaDAO getCuentaDAO() {
		return cuentaDAO;
	}

	public void setCuentaDAO(CuentaDAO cuentaDAO) {
		this.cuentaDAO = cuentaDAO;
	}

	@Override
	public void llenar_datos() {
		if (getCuenta() != null) {
			txtCodigo.setText(getCuenta().getId());
			txtDescripcion.setText(getCuenta().getDescripcion());
		} else {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		}
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (Cuenta cuenta : getCuentas()) {
			model.addRow(new Object[] { cuenta.getId(), cuenta.getDescripcion() });
		}
		if (getCuentas().size() > 0) {
			setCuenta(getCuentas().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setCuentas(getCuentaDAO().findAll());
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		tblLista.setEnabled(false);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
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
	public boolean isValidaVista() {
		if (txtCodigo.getText().trim().isEmpty())
			return false;
		if (txtDescripcion.getText().trim().isEmpty())
			return false;
		return true;
	}

}
