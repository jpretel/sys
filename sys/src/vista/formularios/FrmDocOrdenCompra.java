package vista.formularios;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;

import vista.contenedores.cntAlmacen;
import vista.contenedores.cntResponsable;
import vista.contenedores.cntSucursal;
import vista.controles.DSGTableModel;
import vista.controles.celleditor.TxtProducto;
import vista.formularios.listas.AbstractDocForm;
import vista.utilitarios.FormValidador;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import dao.AlmacenDAO;
import dao.GrupoCentralizacionDAO;
import dao.MonedaDAO;
import dao.OrdenCompraDAO;
import dao.ProductoDAO;
import dao.ResponsableDAO;
import dao.SucursalDAO;
import entity.OrdenCompra;
import entity.Producto;
import entity.Sucursal;


import java.awt.Component;

public class FrmDocOrdenCompra extends AbstractDocForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblDetalle;
	private JScrollPane scrollPaneDetalle;
	// private List<DetDocingreso> DetDocingresoL;
	private OrdenCompraDAO ocDAO = new OrdenCompraDAO();

	private cntResponsable cntResponsable;
	private cntSucursal cntSucursal;
	private cntAlmacen cntAlmacen;
	private JTextArea txtGlosa;
	private OrdenCompra ordencompra;
	private ProductoDAO productoDAO = new ProductoDAO();
	// private SucursalDAO sucursalDAO = new SucursalDAO();
	private AlmacenDAO almacenDAO = new AlmacenDAO();
	protected JScrollPane scrollPane;

	private TxtProducto txtProducto;

	public FrmDocOrdenCompra() {
		super("Orden de Compra");
		cntGrupoCentralizacion.setVisible(false);
		lblGrupoDeCentralizacion.setVisible(false);

		setEstado(VISTA);
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(panel, Alignment.TRAILING,
				GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				groupLayout
						.createSequentialGroup()
						.addGap(109)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(null);

		JLabel lblResponsable = new JLabel("Responsable");
		lblResponsable.setBounds(12, 45, 74, 16);
		panel.add(lblResponsable);

		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(12, 11, 51, 16);
		panel.add(lblSucursal);

		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(439, 11, 50, 16);
		panel.add(lblAlmacen);

		JLabel lblGlosa = new JLabel("Glosa");
		lblGlosa.setBounds(439, 38, 32, 16);
		panel.add(lblGlosa);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(540, 5, 0, 16);
		panel.add(textArea);

		tblDetalle = new JTable(new DSGTableModel(new String[] { "IdProducto",
				"Producto", "IdMedida", "Medida", "Cantidad", "Precio",
				"Importe" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				if (column != 1 || column != 3)
					return getEditar();
				else
					return false;
			}

			@Override
			public void addRow() {
				if (validaCabecera())
					addRow(new Object[] { "", "", "", "", 0.00, 0.00, 0.00 });
				else
					JOptionPane.showMessageDialog(null,
							"Faltan datos en la cabecera");
			}
		}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void changeSelection(int row, int column, boolean toggle,
					boolean extend) {
				super.changeSelection(row, column, toggle, extend);
				if (row > -1) {
					String idproducto = getDetalleTM().getValueAt(row, 0)
							.toString();

					txtProducto.refresValue(idproducto);
				}
			}
		};

		tblDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPaneDetalle = new JScrollPane(tblDetalle);
		scrollPaneDetalle.setBounds(12, 95, 824, 220);

		txtProducto = new TxtProducto(tblDetalle, 0) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void cargaDatos(Producto entity) {

				int row = tblDetalle.getSelectedRow(), col = 0;
				if (entity == null) {
					getDetalleTM().setValueAt("", row, 0);
					getDetalleTM().setValueAt("", row, 1);
					getDetalleTM().setValueAt("", row, 2);
					getDetalleTM().setValueAt("", row, 3);
				} else {
					setText(entity.getIdproducto());
					getDetalleTM().setValueAt(entity.getIdproducto(), row, col);
					getDetalleTM().setValueAt(entity.getDescripcion(), row,
							col + 1);
					getDetalleTM().setValueAt(
							entity.getUnimedida().getIdunimedida(), row,
							col + 2);
					getDetalleTM().setValueAt(
							entity.getUnimedida().getDescripcion(), row,
							col + 3);
				}
				setSeleccionado(null);
			}
		}; // TxtProducto(tblDetalle,0);
		txtProducto.updateCellEditor();
		txtProducto.setData(productoDAO.findAll());
		getDetalleTM().setNombre_detalle("Detalle de Productos");
		getDetalleTM().setRepetidos(0);
		getDetalleTM().setScrollAndTable(scrollPaneDetalle, tblDetalle);

		panel.add(scrollPaneDetalle);

		cntResponsable = new cntResponsable();
		cntResponsable.txtDescripcion.setBounds(46, 0, 263, 20);
		cntResponsable.setBounds(80, 38, 309, 23);
		panel.add(cntResponsable);

		cntSucursal = new cntSucursal();
		cntSucursal.txtDescripcion.setBounds(46, 0, 293, 20);
		cntSucursal.setBounds(80, 8, 309, 23);
		panel.add(cntSucursal);

		cntAlmacen = new cntAlmacen();
		cntAlmacen.txtDescripcion.setBounds(46, 0, 263, 20);
		cntAlmacen.setBounds(497, 11, 309, 23);
		panel.add(cntAlmacen);

		txtSerie.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg) {
				String serie = txtSerie.getText().trim();
				if (serie.length() > 0) {
					String texto = org.codehaus.plexus.util.StringUtils.repeat(
							"0", 4 - serie.length()) + serie;
					txtSerie.setText(texto);
					actualizaNumero(txtSerie.getText());
				}
			}

			private void actualizaNumero(String serie) {
				int numero = ocDAO.getPorSerie(serie);
				numero = numero + 1;
				if (numero > 0) {
					txtNumero_2.setValue(numero);
					txtNumero_2.requestFocus();
					txtFecha.requestFocus();
				}
			}
		});

		cntSucursal.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (cntSucursal.txtCodigo.getText().trim().length() > 0) {
					cntAlmacen.setData(new AlmacenDAO()
							.getPorSucursal(cntSucursal.getSeleccionado()));
				}
			}
		});

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(494, 40, 326, 44);
		panel.add(this.scrollPane);

		txtGlosa = new JTextArea();
		this.scrollPane.setViewportView(this.txtGlosa);
		iniciar();
	}

	@Override
	public void nuevo() {
		setOrdencompra(new OrdenCompra());
		getOrdencompra().setIdordencompra(System.nanoTime());
		txtSerie.requestFocus();
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		ocDAO.crear_editar(getOrdencompra());
		// docIngresoDAO.crear_editar(getIngreso());
		// for (DetDocingreso det : getDetDocingresoL()){
		// detDocingresoDAO.crear_editar(det);
		// }
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
	}

	public void cargarDatos(Object id) {
		setOrdencompra(ocDAO.find(id));
		// setDasiento(dasientoDAO.getPorAsiento(getAsiento()));
	}

	@Override
	public void llenar_datos() {
		if (getOrdencompra() != null) {
			this.txtNumero_2.setValue(getOrdencompra().getNumero());
			this.txtSerie.setText(getOrdencompra().getSerie());
			// this.cntGrupoCentralizacion.txtCodigo.setText(getOrdencompra().getGrupoCentralizacion().getIdgcentralizacion());
			// this.cntGrupoCentralizacion.txtDescripcion.setText(getOrdencompra().getGrupoCentralizacion().getDescripcion());
			cntMoneda.txtCodigo
					.setText((getOrdencompra().getMoneda() == null) ? ""
							: getOrdencompra().getMoneda().getIdmoneda());
			cntMoneda.llenar();
			cntResponsable.txtCodigo
					.setText((getOrdencompra().getResponsable() == null) ? ""
							: getOrdencompra().getResponsable()
									.getIdresponsable());
			cntResponsable.llenar();
			cntSucursal.txtCodigo
					.setText((getOrdencompra().getSucursal() == null) ? ""
							: getOrdencompra().getSucursal().getIdsucursal());
			Sucursal s = getOrdencompra().getSucursal();
			cntSucursal.llenar();
			if (s == null) {
				cntAlmacen.setData(null);
			} else {
				cntAlmacen.setData(almacenDAO.getPorSucursal(s));
			}
			cntAlmacen.txtCodigo
					.setText((getOrdencompra().getAlmacen() == null) ? ""
							: getOrdencompra().getAlmacen().getId()
									.getIdalmacen());
			cntAlmacen.llenar();
			// List<DetDocingreso> detDocIngresoL =
			// detDocingresoDAO.getPorIdIngreso(Long.parseLong(getIngreso().getIddocingreso()));
			// for(DetDocingreso ingreso : detDocIngresoL){
			// getDetalleTM().addRow(new
			// Object[]{ingreso.getId().getIdproducto(),ingreso.getDescripcion(),ingreso.getIdmedida(),"",ingreso.getCantidad(),ingreso.getPrecio(),ingreso.getPrecio()});
			// }
		}
	}

	@Override
	public void llenar_lista() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_tablas() {
		cntGrupoCentralizacion.setData(new GrupoCentralizacionDAO().findAll());
		cntMoneda.setData(new MonedaDAO().findAll());
		cntSucursal.setData(new SucursalDAO().findAll());
		cntResponsable.setData(new ResponsableDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		this.txtSerie.setEditable(true);
		this.txtNumero_2.setEditable(true);
		this.txtFecha.setEditable(true);
		this.txtTcMoneda.setEditable(true);
		this.txtTipoCambio.setEditable(true);
		this.cntGrupoCentralizacion.txtCodigo.setEditable(true);
		this.cntMoneda.txtCodigo.setEditable(true);
		this.cntResponsable.txtCodigo.setEditable(true);
		this.cntSucursal.txtCodigo.setEditable(true);
		this.cntAlmacen.txtCodigo.setEditable(true);
		this.txtTcMoneda.setEditable(true);
		this.txtTipoCambio.setEditable(true);
		this.txtGlosa.setEditable(true);
		getDetalleTM().setEditar(true);
	}

	@Override
	public void vista_noedicion() {
		this.txtSerie.setEditable(false);
		this.txtNumero_2.setEditable(false);
		this.txtFecha.setEditable(false);
		this.txtTcMoneda.setEditable(false);
		this.txtTipoCambio.setEditable(false);
		this.cntGrupoCentralizacion.txtCodigo.setEditable(false);
		this.cntMoneda.txtCodigo.setEditable(false);
		this.cntResponsable.txtCodigo.setEditable(false);
		this.cntSucursal.txtCodigo.setEditable(false);
		this.cntAlmacen.txtCodigo.setEditable(false);
		this.txtTcMoneda.setEditable(false);
		this.txtTipoCambio.setEditable(false);
		this.txtGlosa.setEditable(false);
		getDetalleTM().setEditar(false);
	}

	@Override
	public void init() {

	}

	@Override
	public void actualiza_objeto(Object entidad) {

	}

	@Override
	public void llenarDesdeVista() {
		Calendar c = Calendar.getInstance();
		c.setTime(txtFecha.getDate());

		Long id = getOrdencompra().getIdordencompra();
		// getIngreso().setGrupoCentralizacion(cntGrupoCentralizacion.getSeleccionado());
		getOrdencompra().setSerie(this.txtSerie.getText());
		getOrdencompra()
				.setNumero(Integer.parseInt(this.txtNumero_2.getText()));
		getOrdencompra().setMoneda(cntMoneda.getSeleccionado());
		getOrdencompra().setResponsable(this.cntResponsable.getSeleccionado());
		getOrdencompra().setSucursal(cntSucursal.getSeleccionado());
		getOrdencompra().setAlmacen(this.cntAlmacen.getSeleccionado());
		getOrdencompra().setDia(c.get(Calendar.DAY_OF_MONTH));
		getOrdencompra().setMes(c.get(Calendar.MONTH) + 1);
		getOrdencompra().setAnio(c.get(Calendar.YEAR));
		getOrdencompra().setAniomesdia(
				(c.get(Calendar.YEAR) * 10000)
						+ ((c.get(Calendar.MONTH) + 1) * 100)
						+ c.get(Calendar.DAY_OF_MONTH));
		getOrdencompra().setGlosa(txtGlosa.getText());

		// setDetDocingresoL(new ArrayList<DetDocingreso>());
		// for(int i = 0;i < getDetalleTM().getRowCount();i++){
		// DetDocingresoPK detPK = new DetDocingresoPK();
		// DetDocingreso det = new DetDocingreso();
		// detPK.setIdingreso(Id);
		// detPK.setIdproducto(getDetalleTM().getValueAt(i, 0).toString());
		// det.setId(detPK);
		// det.setDescripcion(getDetalleTM().getValueAt(i, 1).toString());
		// det.setIdmedida(getDetalleTM().getValueAt(i, 2).toString());
		// det.setCantidad(Float.parseFloat((getDetalleTM().getValueAt(i,
		// 4).toString())));
		// det.setPrecio(Float.parseFloat(getDetalleTM().getValueAt(i,
		// 5).toString()));
		// det.setImporte(Float.parseFloat(getDetalleTM().getValueAt(i,
		// 6).toString()));
		// DetDocingresoL.add(det);
		// }
	}

	@Override
	public boolean isValidaVista() {
		boolean band = validaCabecera();
		return band;
	}

	public boolean validaCabecera() {
		// if (this.cntGrupoCentralizacion.txtCodigo.getText().isEmpty())
		// return false;
		//
		return FormValidador.TextFieldObligatorios(cntMoneda.txtCodigo,
				txtTipoCambio, cntResponsable.txtCodigo, cntSucursal.txtCodigo,
				cntAlmacen.txtCodigo);

	}

	public DSGTableModel getDetalleTM() {
		return ((DSGTableModel) tblDetalle.getModel());
	}

	public OrdenCompra getOrdencompra() {
		return ordencompra;
	}

	public void setOrdencompra(OrdenCompra ordencompra) {
		this.ordencompra = ordencompra;
	}

	public void actualiza_objeto(Object id, String estado) {
		// actualizar_tablas();
		cargarDatos(id);
		llenar_datos();

		getBarra().enVista();
		vista_noedicion();

		if (estado.equals(EDICION)) {
			editar();
		}
	}
}
