package vista.formularios;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.TableColumnModel;

import core.centralizacion.ContabilizaSlcCompras;
import vista.contenedores.cntAlmacen;
import vista.contenedores.cntResponsable;
import vista.contenedores.cntSucursal;
import vista.controles.DSGTableModel;
import vista.controles.celleditor.TxtProducto;
import vista.formularios.listas.AbstractDocForm;
import vista.utilitarios.FormValidador;
import vista.utilitarios.editores.FloatEditor;
import vista.utilitarios.renderers.FloatRenderer;
import dao.AlmacenDAO;
import dao.DSolicitudCompraDAO;
import dao.KardexSlcCompraDAO;
import dao.ProductoDAO;
import dao.ResponsableDAO;
import dao.SolicitudCompraDAO;
import dao.SucursalDAO;
import dao.UnimedidaDAO;
import entity.DSolicitudCompra;
import entity.DSolicitudCompraPK;
import entity.Producto;
import entity.SolicitudCompra;
import entity.Sucursal;
import entity.Unimedida;

public class FrmDocSolicitudCompra extends AbstractDocForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SolicitudCompraDAO solicitudcompraDAO = new SolicitudCompraDAO();
	private DSolicitudCompraDAO dsolicitudcompraDAO = new DSolicitudCompraDAO();
	private ProductoDAO productoDAO = new ProductoDAO();
	private UnimedidaDAO unimedidaDAO = new UnimedidaDAO();
	private AlmacenDAO almacenDAO = new AlmacenDAO();
	private KardexSlcCompraDAO kardexSlcCompraDAO = new KardexSlcCompraDAO();

	private TxtProducto txtProducto;
	private JLabel lblResponsable;
	private JLabel lblSucursal;
	private JLabel lblAlmacen;
	private JLabel lblGlosa;
	private JScrollPane scrollPaneDetalle;
	private cntResponsable cntResponsable;
	private cntSucursal cntSucursal;
	private cntAlmacen cntAlmacen;
	private JScrollPane scrlGlosa;
	private JTextArea txtGlosa;
	public JTable tblDetalle;

	private SolicitudCompra solicitudcompra;
	private List<DSolicitudCompra> dsolicitudcompras = new ArrayList<DSolicitudCompra>();

	public FrmDocSolicitudCompra() {
		super("Solicitud de Compra");
		txtFecha.setBounds(245, 11, 101, 22);
		txtNumero_2.setBounds(116, 12, 80, 20);
		txtSerie.setBounds(72, 12, 44, 20);

		setEstado(VISTA);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGap(0, 874, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGap(0, 681, Short.MAX_VALUE));

		this.lblResponsable = new JLabel("Responsable");
		this.lblResponsable.setBounds(10, 106, 74, 16);

		this.lblSucursal = new JLabel("Sucursal");
		this.lblSucursal.setBounds(10, 43, 51, 16);

		this.lblAlmacen = new JLabel("Almacen");
		this.lblAlmacen.setBounds(10, 70, 50, 16);

		this.lblGlosa = new JLabel("Glosa");
		this.lblGlosa.setBounds(399, 43, 32, 16);

		this.scrollPaneDetalle = new JScrollPane((Component) null);
		this.scrollPaneDetalle.setBounds(10, 133, 824, 243);

		tblDetalle = new JTable(new DSGTableModel(new String[] {
				"C�d. Producto", "Producto", "Cod. Medida", "Medida",
				"Cantidad" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				if (column == 1 || column == 2 || column == 3)
					return false;
				return getEditar();
			}

			@Override
			public void addRow() {
				if (validaCabecera())
					addRow(new Object[] { "", "", "", "", 0.0 });
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

		txtProducto = new TxtProducto(tblDetalle, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public void cargaDatos(Producto entity) {

				int row = tblDetalle.getSelectedRow();

				if (entity == null) {
					getDetalleTM().setValueAt("", row, 0);
					getDetalleTM().setValueAt("", row, 1);
					getDetalleTM().setValueAt("", row, 2);
					getDetalleTM().setValueAt("", row, 3);

				} else {

					setText(entity.getIdproducto());
					getDetalleTM().setValueAt(entity.getIdproducto(), row, 0);
					getDetalleTM().setValueAt(entity.getDescripcion(), row, 1);
					getDetalleTM().setValueAt(
							entity.getUnimedida().getIdunimedida(), row, 2);
					getDetalleTM().setValueAt(
							entity.getUnimedida().getDescripcion(), row, 3);

				}
				setSeleccionado(null);
			}
		};

		txtProducto.updateCellEditor();
		txtProducto.setData(productoDAO.findAll());
		getDetalleTM().setNombre_detalle("Detalle de Productos");
		getDetalleTM().setRepetidos(0);
		getDetalleTM().setScrollAndTable(scrollPaneDetalle, tblDetalle);

		this.tblDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.scrollPaneDetalle.setViewportView(this.tblDetalle);

		this.cntResponsable = new cntResponsable();
		this.cntResponsable.setBounds(72, 102, 309, 20);

		this.cntSucursal = new cntSucursal();
		this.cntSucursal.setBounds(72, 43, 309, 20);

		this.cntAlmacen = new cntAlmacen();
		this.cntAlmacen.setBounds(72, 70, 309, 20);
		cntAlmacen.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				cntAlmacen.setData(almacenDAO.getPorSucursal(cntSucursal
						.getSeleccionado()));
			}
		});

		this.scrlGlosa = new JScrollPane();
		this.scrlGlosa.setBounds(436, 43, 395, 79);

		this.txtGlosa = new JTextArea();
		this.scrlGlosa.setViewportView(this.txtGlosa);
		pnlPrincipal.setLayout(null);
		pnlPrincipal.add(this.lblSucursal);
		pnlPrincipal.add(this.cntSucursal);
		pnlPrincipal.add(this.lblAlmacen);
		pnlPrincipal.add(this.cntAlmacen);
		pnlPrincipal.add(this.lblResponsable);
		pnlPrincipal.add(this.cntResponsable);
		pnlPrincipal.add(this.lblGlosa);
		pnlPrincipal.add(this.scrlGlosa);
		pnlPrincipal.add(this.scrollPaneDetalle);

		txtProducto.updateCellEditor();
		txtProducto.setData(productoDAO.findAll());

		txtSerie.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg) {
				actualizaNumero(txtSerie.getText());
			}

			private void actualizaNumero(String serie) {
				int numero = solicitudcompraDAO.getPorSerie(serie);
				numero = numero + 1;
				if (numero > 0) {
					txtNumero_2.setValue(numero);
					txtFecha.requestFocus();
				}
			}
		});

		getDetalleTM().setNombre_detalle("Detalle de Productos");
		getDetalleTM().setObligatorios(0, 1, 2, 3, 4);
		getDetalleTM().setRepetidos(0);

		TableColumnModel tc = tblDetalle.getColumnModel();

		tc.getColumn(4).setCellEditor(new FloatEditor(2));
		tc.getColumn(4).setCellRenderer(new FloatRenderer(2));

		iniciar();
	}

	@Override
	public void nuevo() {
		setsolicitudcompra(new SolicitudCompra());
		getsolicitudcompra().setIdsolicitudcompra(System.nanoTime());
		txtSerie.requestFocus();
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		kardexSlcCompraDAO.borrarPorIdSolicitudCompra(getsolicitudcompra()
				.getIdsolicitudcompra());
		solicitudcompraDAO.crear_editar(getsolicitudcompra());

		for (DSolicitudCompra d : dsolicitudcompraDAO.aEliminar(
				getsolicitudcompra(), dsolicitudcompras)) {
			dsolicitudcompraDAO.remove(d);
		}

		for (DSolicitudCompra d : dsolicitudcompras) {
			if (dsolicitudcompraDAO.find(d.getId()) == null) {
				dsolicitudcompraDAO.create(d);
			} else {
				dsolicitudcompraDAO.edit(d);
			}
		}
		
		System.out.println("Va a contaili");
		boolean contabilizo = ContabilizaSlcCompras.ContabilizaSolicitud(getsolicitudcompra()
				.getIdsolicitudcompra());
		if (!contabilizo) {
			System.out.println("No Contabiliz�");
		}
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void llenar_datos() {
		getDetalleTM().limpiar();

		if (getsolicitudcompra() != null) {
			this.txtNumero_2.setValue(getsolicitudcompra().getNumero());
			this.txtSerie.setText(getsolicitudcompra().getSerie());

			cntResponsable.txtCodigo.setText((getsolicitudcompra()
					.getResponsable() == null) ? "" : getsolicitudcompra()
					.getResponsable().getIdresponsable());
			cntResponsable.llenar();
			cntSucursal.txtCodigo
					.setText((getsolicitudcompra().getSucursal() == null) ? ""
							: getsolicitudcompra().getSucursal()
									.getIdsucursal());
			Sucursal s = getsolicitudcompra().getSucursal();
			cntSucursal.llenar();
			if (s == null) {
				cntAlmacen.setData(null);
			} else {
				cntAlmacen.setData(almacenDAO.getPorSucursal(s));
			}
			cntAlmacen.txtCodigo
					.setText((getsolicitudcompra().getAlmacen() == null) ? ""
							: getsolicitudcompra().getAlmacen().getId()
									.getIdalmacen());
			cntAlmacen.llenar();

			dsolicitudcompras = dsolicitudcompraDAO
					.getPorSolicitudCompra(getsolicitudcompra());

			for (DSolicitudCompra d : dsolicitudcompras) {
				Producto p = d.getProducto();
				Unimedida u = d.getUnimedida();

				getDetalleTM().addRow(
						new Object[] { p.getIdproducto(), p.getDescripcion(),
								u.getIdunimedida(), u.getDescripcion(),
								d.getCantidad() });
			}

		} else {
			dsolicitudcompras = new ArrayList<DSolicitudCompra>();
		}
	}

	@Override
	public void llenar_lista() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_tablas() {
		cntSucursal.setData(new SucursalDAO().findAll());
		cntResponsable.setData(new ResponsableDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		this.txtSerie.setEditable(true);
		this.txtNumero_2.setEditable(true);
		this.txtFecha.setEditable(true);
		this.txtGlosa.setEditable(true);
		FormValidador.CntEdicion(true, this.cntResponsable,
				this.cntAlmacen, this.cntSucursal);
		getDetalleTM().setEditar(true);
	}

	@Override
	public void vista_noedicion() {
		this.txtSerie.setEditable(false);
		this.txtNumero_2.setEditable(false);
		this.txtFecha.setEditable(false);
		this.txtGlosa.setEditable(false);
		FormValidador.CntEdicion(false, this.cntResponsable,
				this.cntAlmacen, this.cntSucursal);
		getDetalleTM().setEditar(false);
	}

	@Override
	public void init() {

	}

	@Override
	public void actualiza_objeto(Object id) {
		setsolicitudcompra(solicitudcompraDAO.find(id));
		llenar_datos();

		getBarra().enVista();
		vista_noedicion();
	}

	@Override
	public void llenarDesdeVista() {
		Calendar c = Calendar.getInstance();
		c.setTime(txtFecha.getDate());
		Long idoc = getsolicitudcompra().getIdsolicitudcompra();
		// getIngreso().setGrupoCentralizacion(cntGrupoCentralizacion.getSeleccionado());
		getsolicitudcompra().setSerie(this.txtSerie.getText());
		getsolicitudcompra().setNumero(
				Integer.parseInt(this.txtNumero_2.getText()));
		getsolicitudcompra().setResponsable(
				this.cntResponsable.getSeleccionado());
		getsolicitudcompra().setSucursal(cntSucursal.getSeleccionado());
		getsolicitudcompra().setAlmacen(this.cntAlmacen.getSeleccionado());
		getsolicitudcompra().setDia(c.get(Calendar.DAY_OF_MONTH));
		getsolicitudcompra().setMes(c.get(Calendar.MONTH) + 1);
		getsolicitudcompra().setAnio(c.get(Calendar.YEAR));
		getsolicitudcompra().setFecha(
				(c.get(Calendar.YEAR) * 10000)
						+ ((c.get(Calendar.MONTH) + 1) * 100)
						+ c.get(Calendar.DAY_OF_MONTH));
		getsolicitudcompra().setGlosa(txtGlosa.getText());

		dsolicitudcompras = new ArrayList<DSolicitudCompra>();

		int rows = getDetalleTM().getRowCount();

		for (int row = 0; row < rows; row++) {
			DSolicitudCompra d = new DSolicitudCompra();
			DSolicitudCompraPK id = new DSolicitudCompraPK();

			String idproducto, idunimedida;

			idproducto = getDetalleTM().getValueAt(row, 0).toString();
			idunimedida = getDetalleTM().getValueAt(row, 2).toString();

			float cantidad;

			cantidad = Float.parseFloat(getDetalleTM().getValueAt(row, 4)
					.toString());

			Producto p = productoDAO.find(idproducto);
			Unimedida u = unimedidaDAO.find(idunimedida);

			id.setIdsolicitudcompra(idoc);
			id.setItem(row + 1);

			d.setId(id);
			d.setProducto(p);
			d.setUnimedida(u);
			d.setCantidad(cantidad);

			dsolicitudcompras.add(d);
		}
	}

	@Override
	public boolean isValidaVista() {
		boolean band = validaCabecera();
		return band;
	}

	public boolean validaCabecera() {

		return FormValidador.TextFieldObligatorios(cntResponsable.txtCodigo,
				cntSucursal.txtCodigo, cntAlmacen.txtCodigo);
	}

	public DSGTableModel getDetalleTM() {
		return ((DSGTableModel) tblDetalle.getModel());
	}

	public SolicitudCompra getsolicitudcompra() {
		return solicitudcompra;
	}

	public void setsolicitudcompra(SolicitudCompra solicitudcompra) {
		this.solicitudcompra = solicitudcompra;
	}
}
