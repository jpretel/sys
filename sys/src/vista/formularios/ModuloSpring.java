package vista.formularios;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import dao.ModuloDAO;
import entity.Modulo;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import vista.barras.BarraMaestro;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;

public class ModuloSpring extends AbstractMaestro{
	
	
	private static final long serialVersionUID = 1L;

	private Modulo cuenta;

	private ModuloDAO cuentaDAO = new ModuloDAO();

	private List<Modulo> cuentas = new ArrayList<Modulo>();
	private JTable tblLista;
	private JTextField txtID;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;

	public ModuloSpring(BarraMaestro barra) {
		
		
		JLabel lblID = new JLabel("C\u00F3digo");
		lblID.setBounds(227, 11, 46, 14);

		txtID = new JTextField();
		txtID.setBounds(276, 8, 122, 20);
		txtID.setColumns(10);
		
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
				
		JFrame frame = new JFrame("SpringLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		
		Component left = new JLabel("left");	
		Component right = new JTextField(15);
		
		contentPane.add(left);
		contentPane.add(right);
		layout.putConstraint(SpringLayout.WEST, left, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, left, 25, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, right, 40, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, right, 25, SpringLayout.NORTH, contentPane);
		
		frame.setSize(300,200);
		frame.setVisible(true);
		
		
		
		
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
		setCuenta(new Modulo());
		super.nuevo();
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {		
		getCuenta().setId(Integer.parseInt(txtID.getText()));
		getCuenta().setDescripcion(txtDescripcion.getText());
		getCuenta().setOpcion(txtCodigo.getText());
		getCuentaDAO().crear_editar(getCuenta());
		super.grabar();
	}

	@Override
	public void eliminar() {
		setEstado(VISTA);
		vista_noedicion();
	}

	

	public Modulo getCuenta() {
		return cuenta;
	}

	public void setCuenta(Modulo cuenta) {
		this.cuenta = cuenta;
	}

	public ModuloDAO getCuentaDAO() {
		return cuentaDAO;
	}

	public void setCuentaDAO(ModuloDAO cuentaDAO) {
		this.cuentaDAO = cuentaDAO;
	}
	
	

	@Override
	public void llenar_datos() {
		if (getCuenta() != null) {
			txtID.setText(""+getCuenta().getId());
			txtCodigo.setText(getCuenta().getOpcion());
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
		for (Modulo cuenta : getCuentas()) {
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

	public List<Modulo> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Modulo> cuentas) {
		this.cuentas = cuentas;
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtID.setEditable(true);
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		tblLista.setEnabled(false);
	}

	@Override
	public void vista_noedicion() {
		txtID.setEditable(false);
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		tblLista.setEnabled(true);
	}

	@Override
	protected void actualizaBarra() {
		getBarra().setVisible(isSelected());
		if (isSelected()) {
			getBarra().setFormMaestro(this);
		}
		if (getEstado().equals(VISTA)) {
			getBarra().enVista();
		} else {
			getBarra().enEdicion();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub
	}

	
}
