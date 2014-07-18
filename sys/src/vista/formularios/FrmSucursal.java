package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.utilitarios.MaestroTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import javax.swing.JButton;

import dao.AlmacenDAO;
import dao.SucursalDAO;
import entity.Almacen;
import entity.AlmacenPK;
import entity.Sucursal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmSucursal extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable tblLista;

	private JTable tblAlmacenes;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private FrmSucursalTableModel almacenesTM = new FrmSucursalTableModel();
	private Sucursal sucursal;

	private List<Sucursal> sucursales;
	private List<Almacen> almacenes;


	private SucursalDAO sucursalDAO = new SucursalDAO();

	private AlmacenDAO almacenDAO = new AlmacenDAO();

	private JButton btnILinea;
	private JButton btnBLinea;

	public FrmSucursal() {
		super("Sucursal / Almacen");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 199, 273);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNum = new JScrollPane();
		scrollPaneNum.setBounds(215, 65, 314, 219);

		tblAlmacenes = new JTable(almacenesTM);
		scrollPaneNum.setViewportView(tblAlmacenes);
		tblAlmacenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblCdigo = new JLabel("Cdigo");
		lblCdigo.setBounds(213, 18, 66, 14);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(213, 42, 66, 14);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(298, 15, 67, 20);
		txtCodigo.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(298, 39, 126, 20);
		txtDescripcion.setColumns(10);

		btnILinea = new JButton("I Linea");
		btnILinea.setBounds(375, 15, 65, 20);
		btnILinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final Object fila[] = { "", ""};
				almacenesTM.addRow(fila);
			}
		});

		btnBLinea = new JButton("B Linea");
		btnBLinea.setBounds(446, 15, 83, 20);
		btnBLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ind = tblAlmacenes.getSelectedRow();
				if (ind >= 0)
					almacenesTM.removeRow(ind);
			}
		});
		pnlContenido.setLayout(null);
		pnlContenido.add(scrollPane);
		pnlContenido.add(lblDescripcin);
		pnlContenido.add(lblCdigo);
		pnlContenido.add(txtDescripcion);
		pnlContenido.add(txtCodigo);
		pnlContenido.add(btnILinea);
		pnlContenido.add(btnBLinea);
		pnlContenido.add(scrollPaneNum);
		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setSucursal(getSucursales().get(selectedRow));
						else
							setSucursal(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	@Override
	public void nuevo() {
		setSucursal(new Sucursal());
	}

	@Override
	public void editar() {
		super.editar();
	}

	@Override
	public void anular() {

	}

	@Override
	public void grabar() {
		sucursalDAO.crear_editar(getSucursal());
		almacenDAO.borrarPorSucursal(getSucursal());
		for (Almacen num : getAlmacenes()) {
			almacenDAO.create(num);
		}
	}

	@Override
	public void llenarDesdeVista() {
		getSucursal().setIdsucursal(this.txtCodigo.getText().trim());
		getSucursal().setDescripcion(this.txtDescripcion.getText().trim());

		setAlmacenes(new ArrayList<Almacen>());

		for (int i = 0; i < almacenesTM.getRowCount(); i++) {
			AlmacenPK id = new AlmacenPK();
			Almacen alm = new Almacen();

			id.setIdsucursal(getSucursal().getIdsucursal());
			id.setIdalmacen(this.almacenesTM.getValueAt(i, 0).toString());
			
			alm.setId(id);
			alm.setDescripcion(this.almacenesTM.getValueAt(i, 1).toString());
			getAlmacenes().add(alm);
		}

	}

	@Override
	public void llenar_datos() {
		almacenesTM.limpiar();
		setAlmacenes(new ArrayList<Almacen>());
		if (getSucursal() != null) {
			txtCodigo.setText(getSucursal().getIdsucursal());
			txtDescripcion.setText(getSucursal().getDescripcion());
			setAlmacenes(almacenDAO.getPorSucursal(getSucursal()));
			
			for (Almacen alm : getAlmacenes()) {
				almacenesTM.addRow(new Object[] {
						alm.getId().getIdsucursal(), alm.getDescripcion() });
			}
		} else {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		}
	}

	@Override
	public boolean isValidaVista() {
		if (this.txtCodigo.getText().trim().isEmpty())
			return false;
		if (this.txtDescripcion.getText().trim().isEmpty())
			return false;
		return true;
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (Sucursal obj : getSucursales()) {
			model.addRow(new Object[] { obj.getIdsucursal(),
					obj.getDescripcion()});
		}
		if (getSucursales().size() > 0) {
			setSucursal(getSucursales().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setSucursales(sucursalDAO.findAll());
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		else
			txtCodigo.setEditable(false);
		txtDescripcion.setEditable(true);
		almacenesTM.setEditar(true);
		btnILinea.setEnabled(true);
		btnBLinea.setEnabled(true);

	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		almacenesTM.setEditar(false);
		btnILinea.setEnabled(false);
		btnBLinea.setEnabled(false);
	}

	/**
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the sucursales
	 */
	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	/**
	 * @param sucursales the sucursales to set
	 */
	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	/**
	 * @return the sucursalDAO
	 */
	public SucursalDAO getSucursalDAO() {
		return sucursalDAO;
	}

	/**
	 * @param sucursalDAO the sucursalDAO to set
	 */
	public void setSucursalDAO(SucursalDAO sucursalDAO) {
		this.sucursalDAO = sucursalDAO;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub

	}

	public FrmSucursalTableModel getNumeradoresTM() {
		return almacenesTM;
	}

	public void setNumeradoresTM(FrmSucursalTableModel numeradoresTM) {
		this.almacenesTM = numeradoresTM;
	}
	
	/**
	 * @return the almacenes
	 */
	public List<Almacen> getAlmacenes() {
		return almacenes;
	}

	/**
	 * @param almacenes the almacenes to set
	 */
	public void setAlmacenes(List<Almacen> almacenes) {
		this.almacenes = almacenes;
	}

	/**
	 * @return the almacenDAO
	 */
	public AlmacenDAO getAlmacenDAO() {
		return almacenDAO;
	}

	/**
	 * @param almacenDAO the almacenDAO to set
	 */
	public void setAlmacenDAO(AlmacenDAO almacenDAO) {
		this.almacenDAO = almacenDAO;
	}
}

class FrmSucursalTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	private boolean editar = false;

	public FrmSucursalTableModel() {
		addColumn("Cod. Almacen");
		addColumn("Descripción");
	}

	public boolean isCellEditable(int row, int column) {
		return editar;
	}

	public void limpiar() {
		while (getRowCount() != 0) {
			removeRow(0);
		}
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public boolean getEditar() {
		return this.editar;
	}
}
