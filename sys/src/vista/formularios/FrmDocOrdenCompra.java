package vista.formularios;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vista.contenedores.cntAlmacen;
import vista.contenedores.cntResponsable;
import vista.contenedores.cntSucursal;
import vista.controles.DSGTableModel;
import vista.controles.celleditor.TxtProducto;
import vista.formularios.listas.AbstractDocForm;
import vista.utilitarios.FormValidador;
import vista.utilitarios.UtilMensajes;
import vista.utilitarios.editores.FloatEditor;
import vista.utilitarios.renderers.FloatRenderer;

import javax.persistence.Tuple;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import core.centralizacion.ContabilizaComprasRecepcion;
import dao.AlmacenDAO;
import dao.DOrdenCompraDAO;
import dao.ImpuestoDAO;
import dao.KardexSlcCompraDAO;
import dao.MonedaDAO;
import dao.OrdenCompraDAO;
import dao.ProductoDAO;
import dao.ProductoImpuestoDAO;
import dao.ResponsableDAO;
import dao.SolicitudCompraDAO;
import dao.SucursalDAO;
import dao.UnimedidaDAO;
import entity.DOrdenCompra;
import entity.DOrdenCompraPK;
import entity.Impuesto;
import entity.OrdenCompra;
import entity.Producto;
import entity.ProductoImpuesto;
import entity.SolicitudCompra;
import entity.Sucursal;
import entity.Unimedida;

import java.awt.Component;

import vista.controles.CntReferenciaDoc;
import vista.contenedores.CntMoneda;
import vista.controles.DSGTextFieldNumber;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class FrmDocOrdenCompra extends AbstractDocForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private List<DetDocingreso> DetDocingresoL;
	private OrdenCompraDAO ordencompraDAO = new OrdenCompraDAO();
	private DOrdenCompraDAO dordencompraDAO = new DOrdenCompraDAO();
	private ProductoDAO productoDAO = new ProductoDAO();
	private UnimedidaDAO unimedidaDAO = new UnimedidaDAO();
	private AlmacenDAO almacenDAO = new AlmacenDAO();
	private ProductoImpuestoDAO pimptoDAO = new ProductoImpuestoDAO();
	private ImpuestoDAO impuestoDAO = new ImpuestoDAO();
	private KardexSlcCompraDAO kardexSlcDAO = new KardexSlcCompraDAO();

	private TxtProducto txtProducto;
	private JLabel lblResponsable;
	private JLabel lblSucursal;
	private JLabel lblAlmacen;
	private JLabel lblGlosa;
	private JScrollPane srlConsolidado;
	private cntResponsable cntResponsable;
	private cntSucursal cntSucursal;
	private cntAlmacen cntAlmacen;
	private JScrollPane scrlGlosa;
	private JTextArea txtGlosa;
	private JTable tblConsolidado;
	private JTable tblDetalle;

	private OrdenCompra ordencompra;
	private List<DOrdenCompra> dordencompras = new ArrayList<DOrdenCompra>();
	private JLabel lblReferencia;
	private CntReferenciaDoc cntReferenciaDoc;
	private CntMoneda cntMoneda;
	private DSGTextFieldNumber txtTCambio;
	private DSGTextFieldNumber txtTCMoneda;
	
	private JTabbedPane tabbedPane;
	private JPanel pnlConsolidado;
	private JPanel pnlDetalle;
	private JScrollPane srlDetalle;

	public FrmDocOrdenCompra() {
		super("Orden de Compra");

		setEstado(VISTA);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGap(0, 874, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGap(0, 681, Short.MAX_VALUE));

		this.lblResponsable = new JLabel("Responsable");
		this.lblResponsable.setBounds(10, 106, 74, 16);
		pnlPrincipal.add(this.lblResponsable);

		this.lblSucursal = new JLabel("Sucursal");
		this.lblSucursal.setBounds(10, 43, 51, 16);
		pnlPrincipal.add(this.lblSucursal);

		this.lblAlmacen = new JLabel("Almacen");
		this.lblAlmacen.setBounds(10, 70, 50, 16);
		pnlPrincipal.add(this.lblAlmacen);

		this.lblGlosa = new JLabel("Glosa");
		this.lblGlosa.setBounds(399, 43, 32, 16);
		pnlPrincipal.add(this.lblGlosa);

		this.cntResponsable = new cntResponsable();

		this.cntResponsable.setBounds(72, 102, 309, 20);
		pnlPrincipal.add(this.cntResponsable);

		this.cntSucursal = new cntSucursal();

		this.cntSucursal.setBounds(72, 43, 309, 20);
		pnlPrincipal.add(this.cntSucursal);

		this.cntAlmacen = new cntAlmacen();
		cntAlmacen.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				cntAlmacen.setData(almacenDAO.getPorSucursal(cntSucursal
						.getSeleccionado()));
			}
		});

		this.cntAlmacen.setBounds(72, 70, 309, 20);
		pnlPrincipal.add(this.cntAlmacen);

		this.scrlGlosa = new JScrollPane();
		this.scrlGlosa.setBounds(436, 43, 395, 52);
		pnlPrincipal.add(this.scrlGlosa);

		this.txtGlosa = new JTextArea();
		this.scrlGlosa.setViewportView(this.txtGlosa);

		this.lblReferencia = new JLabel("Referencia");
		this.lblReferencia.setBounds(400, 107, 74, 16);
		pnlPrincipal.add(this.lblReferencia);

		this.cntReferenciaDoc = new CntReferenciaDoc() {
			private static final long serialVersionUID = 1L;
			private SolicitudCompraDAO solicitudCompraDAO = new SolicitudCompraDAO();

			@Override
			public void buscarReferencia() {
				String serie;
				int numero;
				serie = this.txtSerie.getText();
				numero = Integer.parseInt(this.txtNumero.getText());
				SolicitudCompra solicitudCompra = solicitudCompraDAO
						.getPorSerieNumero(serie, numero);

				if (solicitudCompra != null) {
					List<Tuple> saldos = new KardexSlcCompraDAO()
							.getSaldoSolicitudCompra(solicitudCompra,
									getOrdencompra());
					for (Tuple t : saldos) {
						
						Producto p = (Producto) t.get("producto");
						float cantidad = (float) t.get("cantidad");
						System.out.println(p);
						System.out.println(cantidad);
					}
				} else {
					UtilMensajes.mensaje_alterta("DOC_NO_ENCONTRADO");
				}
			}
		};
		this.cntReferenciaDoc.setBounds(464, 102, 180, 20);
		pnlPrincipal.add(this.cntReferenciaDoc);

		this.cntMoneda = new CntMoneda();
		this.cntMoneda.setBounds(379, 12, 192, 20);
		pnlPrincipal.add(this.cntMoneda);

		this.txtTCambio = new DSGTextFieldNumber(4);
		this.txtTCambio.setBounds(594, 12, 74, 20);
		pnlPrincipal.add(this.txtTCambio);

		this.txtTCMoneda = new DSGTextFieldNumber(4);
		this.txtTCMoneda.setBounds(699, 12, 74, 20);
		pnlPrincipal.add(this.txtTCMoneda);
		this.txtTCMoneda.setValue(1);

		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.tabbedPane.setBounds(20, 133, 811, 243);
		pnlPrincipal.add(this.tabbedPane);

		this.pnlConsolidado = new JPanel();
		this.tabbedPane.addTab("Consolidado", null, this.pnlConsolidado, null);
		this.pnlConsolidado.setLayout(null);

		this.srlConsolidado = new JScrollPane((Component) null);
		this.srlConsolidado.setBounds(10, 11, 786, 193);
		this.pnlConsolidado.add(this.srlConsolidado);

		/*
		 * Tabla de Consolidado
		 */
		tblConsolidado = new JTable(new DSGTableModel(new String[] {
				"Cód. Producto", "Producto", "Cod. Medida", "Medida",
				"Cantidad", "P. Unit.", "V. Venta", "%Dscto", "Dscto.",
				"% Impto", "Impto.", "Total" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				if (column == 1 || column == 2 || column == 3 || column == 8
						|| column == 10 || column == 12)
					return false;
				return getEditar();
			}

			@Override
			public void addRow() {
				if (validaCabecera())
					addRow(new Object[] { "", "", "", "", 0.00, 0.00, 0.00,
							0.00, 0.00, 0.00, 0.00, 0.00 });
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
					String idproducto = getConsolidadoTM().getValueAt(row, 0)
							.toString();

					txtProducto.refresValue(idproducto);
					actualiza_detalle();
				}
			}
		};

		txtProducto = new TxtProducto(tblConsolidado, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public void cargaDatos(Producto entity) {

				int row = tblConsolidado.getSelectedRow(), col = 0;
				float antimpto = 0.0F;
				if (entity == null) {
					getConsolidadoTM().setValueAt("", row, 0);
					getConsolidadoTM().setValueAt("", row, 1);
					getConsolidadoTM().setValueAt("", row, 2);
					getConsolidadoTM().setValueAt("", row, 3);

					getConsolidadoTM().setValueAt(0.0F, row, 9);
				} else {
					List<ProductoImpuesto> imptos = pimptoDAO
							.getPorProducto(entity);
					float impto = 0.0F;

					for (ProductoImpuesto pi : imptos) {
						Impuesto i = impuestoDAO.find(pi.getId()
								.getIdimpuesto());
						impto += i.getTasa();
					}

					setText(entity.getIdproducto());
					getConsolidadoTM().setValueAt(entity.getIdproducto(), row, col);
					getConsolidadoTM().setValueAt(entity.getDescripcion(), row,
							col + 1);
					getConsolidadoTM().setValueAt(
							entity.getUnimedida().getIdunimedida(), row,
							col + 2);
					getConsolidadoTM().setValueAt(
							entity.getUnimedida().getDescripcion(), row,
							col + 3);
					try {
						antimpto = Float.parseFloat(getConsolidadoTM().getValueAt(
								row, 9).toString());

					} catch (Exception e) {
						antimpto = 0.0F;
					}

					if (antimpto == 0.0)
						getConsolidadoTM().setValueAt(impto, row, 9);

				}
				setSeleccionado(null);
			}
		};
		txtProducto.updateCellEditor();
		txtProducto.setData(productoDAO.findAll());
		getConsolidadoTM().setNombre_detalle("Detalle de Productos");
		getConsolidadoTM().setRepetidos(0);
		getConsolidadoTM().setScrollAndTable(srlConsolidado, tblConsolidado);
		
		this.tblConsolidado
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.srlConsolidado.setViewportView(this.tblConsolidado);


		this.pnlDetalle = new JPanel();
		this.tabbedPane.addTab("Detalle", null, this.pnlDetalle, null);
		this.pnlDetalle.setLayout(null);

		this.srlDetalle = new JScrollPane();
		this.srlDetalle.setBounds(10, 11, 786, 193);
		this.pnlDetalle.add(this.srlDetalle);

		txtProducto.updateCellEditor();
		txtProducto.setData(productoDAO.findAll());

		txtSerie.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg) {
				actualizaNumero(txtSerie.getText());
			}

			private void actualizaNumero(String serie) {
				int numero = ordencompraDAO.getPorSerie(serie);
				numero = numero + 1;
				if (numero > 0) {
					txtNumero_2.setValue(numero);
					txtFecha.requestFocus();
				}
			}
		});

		getConsolidadoTM().setNombre_detalle("Consolidado de Productos");
		getConsolidadoTM().setRepetidos(0);
		
		TableColumnModel tc = tblConsolidado.getColumnModel();

		tc.getColumn(4).setCellEditor(new FloatEditor(3));
		tc.getColumn(4).setCellRenderer(new FloatRenderer(3));

		tc.getColumn(5).setCellEditor(new FloatEditor(2));
		tc.getColumn(5).setCellRenderer(new FloatRenderer(2));

		tc.getColumn(6).setCellEditor(new FloatEditor(2));
		tc.getColumn(6).setCellRenderer(new FloatRenderer(2));

		tc.getColumn(7).setCellEditor(new FloatEditor(2));
		tc.getColumn(7).setCellRenderer(new FloatRenderer(2));

		tc.getColumn(8).setCellEditor(new FloatEditor(2));
		tc.getColumn(8).setCellRenderer(new FloatRenderer(2));

		tc.getColumn(9).setCellEditor(new FloatEditor(2));
		tc.getColumn(9).setCellRenderer(new FloatRenderer(2));

		tc.getColumn(10).setCellEditor(new FloatEditor(2));
		tc.getColumn(10).setCellRenderer(new FloatRenderer(2));

		tc.getColumn(11).setCellEditor(new FloatEditor(2));
		tc.getColumn(11).setCellRenderer(new FloatRenderer(2));

		// tc.getColumn(12).setCellRenderer(new ReferenciaDOCRenderer());

		/*
		 * Tabla de detalle
		 */

		tblDetalle = new JTable(new DSGTableModel("Documento", "Correlativo",
				"Cód. Producto", "Producto", "Cantidad") {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				if (column >= 0 && column <= 3)
					return false;
				return getEditar();
			}

			@Override
			public void addRow() {
				System.out.println("Opción no disponible");
			}
		});
		
		srlDetalle.setViewportView(tblDetalle);
		
		getDetalleTM().setNombre_detalle("Detalle de Productos");
		//getDetalleTM().setRepetidos(0);
		
		TableColumnModel tcd = tblDetalle.getColumnModel();

		tcd.getColumn(4).setCellEditor(new FloatEditor(3));
		tcd.getColumn(4).setCellRenderer(new FloatRenderer(3));

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
		ordencompraDAO.crear_editar(getOrdencompra());

		kardexSlcDAO.borrarPorIdOrdenCompra(ordencompra.getIdordencompra());

		for (DOrdenCompra d : dordencompraDAO.aEliminar(getOrdencompra(),
				dordencompras)) {
			dordencompraDAO.remove(d);
		}

		for (DOrdenCompra d : dordencompras) {
			if (dordencompraDAO.find(d.getId()) == null) {
				dordencompraDAO.create(d);
			} else {
				dordencompraDAO.edit(d);
			}
		}

		/*
		 * ContabilizaSlcCompras.ContabilizaOrdenCompra(getOrdencompra()
		 * .getIdordencompra());
		 */
		ContabilizaComprasRecepcion.ContabilizaComprasRecepcion(
				getOrdencompra().getIdordencompra(), 1, "Compra");
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void llenar_datos() {
		getConsolidadoTM().limpiar();

		if (getOrdencompra() != null) {
			this.txtNumero_2.setValue(getOrdencompra().getNumero());
			this.txtSerie.setText(getOrdencompra().getSerie());
			this.txtTCambio.setValue(getOrdencompra().getTcambio());
			this.txtTCMoneda.setValue(getOrdencompra().getTcmoneda());
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

			dordencompras = dordencompraDAO.getPorOrdenCompra(getOrdencompra());

			for (DOrdenCompra d : dordencompras) {
				Producto p = d.getProducto();
				Unimedida u = d.getUnimedida();

				getConsolidadoTM().addRow(
						new Object[] { p.getIdproducto(), p.getDescripcion(),
								u.getIdunimedida(), u.getDescripcion(),
								d.getCantidad(), d.getPrecio_unitario(),
								d.getVventa(), d.getPdescuento(),
								d.getDescuento(), d.getPimpuesto(),
								d.getImpuesto(), d.getImporte() });
			}

			// List<DetDocingreso> detDocIngresoL =
			// detDocingresoDAO.getPorIdIngreso(Long.parseLong(getIngreso().getIddocingreso()));
			// for(DetDocingreso ingreso : detDocIngresoL){
			// getConsolidadoTM().addRow(new
			// Object[]{ingreso.getId().getIdproducto(),ingreso.getDescripcion(),ingreso.getIdmedida(),"",ingreso.getCantidad(),ingreso.getPrecio(),ingreso.getPrecio()});
			// }
		} else {
			dordencompras = new ArrayList<DOrdenCompra>();
		}
	}

	private void actualiza_detalle() {
		int row = tblConsolidado.getSelectedRow();
		if (row > -1) {

			float pimpuesto, pdscto, cantidad, pu;
			float vventa, impuesto, dscto, importe;
			cantidad = Float.parseFloat(getConsolidadoTM().getValueAt(row, 4)
					.toString());
			pu = Float.parseFloat(getConsolidadoTM().getValueAt(row, 5).toString());
			pdscto = Float.parseFloat(getConsolidadoTM().getValueAt(row, 7)
					.toString());
			pimpuesto = Float.parseFloat(getConsolidadoTM().getValueAt(row, 9)
					.toString());

			vventa = cantidad * pu;
			dscto = vventa * pdscto / 100.00F;
			impuesto = (vventa - dscto) * pimpuesto / 100.00F;
			importe = vventa - dscto + impuesto;

			getConsolidadoTM().setValueAt(vventa, row, 6);
			getConsolidadoTM().setValueAt(dscto, row, 8);
			getConsolidadoTM().setValueAt(impuesto, row, 10);
			getConsolidadoTM().setValueAt(importe, row, 11);
			// getConsolidadoTM().setValueAt(new ReferenciaDOC(), row, 12);
		}
	}

	@Override
	public void llenar_lista() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_tablas() {
		cntMoneda.setData(new MonedaDAO().findAll());
		cntSucursal.setData(new SucursalDAO().findAll());
		cntResponsable.setData(new ResponsableDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		this.txtSerie.setEditable(true);
		this.txtNumero_2.setEditable(true);
		this.txtFecha.setEditable(true);
		this.txtTCMoneda.setEditable(true);
		this.txtTCambio.setEditable(true);
		this.txtGlosa.setEditable(true);
		FormValidador.CntEdicion(true, this.cntMoneda, this.cntResponsable,
				this.cntAlmacen, this.cntSucursal);
		getConsolidadoTM().setEditar(true);
	}

	@Override
	public void vista_noedicion() {
		this.txtSerie.setEditable(false);
		this.txtNumero_2.setEditable(false);
		this.txtFecha.setEditable(false);
		this.txtTCMoneda.setEditable(false);
		this.txtTCambio.setEditable(false);
		this.txtGlosa.setEditable(false);
		FormValidador.CntEdicion(false, this.cntMoneda, this.cntResponsable,
				this.cntAlmacen, this.cntSucursal);
		getConsolidadoTM().setEditar(false);
	}

	@Override
	public void init() {

	}

	@Override
	public void actualiza_objeto(Object id) {
		setOrdencompra(ordencompraDAO.find(id));
		llenar_datos();

		getBarra().enVista();
		vista_noedicion();
	}

	@Override
	public void llenarDesdeVista() {
		Calendar c = Calendar.getInstance();
		c.setTime(txtFecha.getDate());
		Long idoc = getOrdencompra().getIdordencompra();
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
		getOrdencompra().setFecha(
				(c.get(Calendar.YEAR) * 10000)
						+ ((c.get(Calendar.MONTH) + 1) * 100)
						+ c.get(Calendar.DAY_OF_MONTH));
		getOrdencompra().setGlosa(txtGlosa.getText());
		getOrdencompra().setTcambio(Float.parseFloat(txtTCambio.getText()));
		getOrdencompra().setTcmoneda(Float.parseFloat(txtTCMoneda.getText()));
		dordencompras = new ArrayList<DOrdenCompra>();

		int rows = getConsolidadoTM().getRowCount();

		for (int row = 0; row < rows; row++) {
			DOrdenCompra d = new DOrdenCompra();
			DOrdenCompraPK id = new DOrdenCompraPK();

			String idproducto, idunimedida;

			idproducto = getConsolidadoTM().getValueAt(row, 0).toString();
			idunimedida = getConsolidadoTM().getValueAt(row, 2).toString();

			float cantidad, precio_unitario, vventa, pdescuento, descuento, pimpuesto, impuesto, importe;

			cantidad = Float.parseFloat(getConsolidadoTM().getValueAt(row, 4)
					.toString());
			precio_unitario = Float.parseFloat(getConsolidadoTM()
					.getValueAt(row, 5).toString());

			vventa = Float.parseFloat(getConsolidadoTM().getValueAt(row, 6)
					.toString());

			pdescuento = Float.parseFloat(getConsolidadoTM().getValueAt(row, 7)
					.toString());

			descuento = Float.parseFloat(getConsolidadoTM().getValueAt(row, 8)
					.toString());

			pimpuesto = Float.parseFloat(getConsolidadoTM().getValueAt(row, 9)
					.toString());

			impuesto = Float.parseFloat(getConsolidadoTM().getValueAt(row, 10)
					.toString());

			importe = Float.parseFloat(getConsolidadoTM().getValueAt(row, 11)
					.toString());

			Producto p = productoDAO.find(idproducto);
			Unimedida u = unimedidaDAO.find(idunimedida);

			id.setIdordencompra(idoc);
			id.setItem(row + 1);

			d.setId(id);
			d.setProducto(p);
			d.setUnimedida(u);
			d.setCantidad(cantidad);
			d.setPrecio_unitario(precio_unitario);
			d.setVventa(vventa);
			d.setPdescuento(pdescuento);
			d.setDescuento(descuento);
			d.setPimpuesto(pimpuesto);
			d.setImpuesto(impuesto);
			d.setImporte(importe);

			dordencompras.add(d);
		}
	}

	@Override
	public boolean isValidaVista() {
		boolean band = validaCabecera();
		return band;
	}

	public boolean validaCabecera() {

		if (!FormValidador.CntObligatorios(cntMoneda, cntResponsable,
				cntSucursal, cntAlmacen))
			return false;

		return true;
	}

	public DSGTableModel getConsolidadoTM() {
		return ((DSGTableModel) tblConsolidado.getModel());
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
}
