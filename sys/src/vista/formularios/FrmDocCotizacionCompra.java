package vista.formularios;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vista.contenedores.cntResponsable;
import vista.controles.DSGTableModel;
import vista.controles.celleditor.TxtProducto;
import vista.formularios.listas.AbstractDocForm;
import vista.utilitarios.FormValidador;
import vista.utilitarios.UtilMensajes;
import vista.utilitarios.editores.FloatEditor;
import vista.utilitarios.renderers.FloatRenderer;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import core.centralizacion.ContabilizaSlcCompras;
import dao.ClieprovDAO;
import dao.DCotizacionCompraDAO;
import dao.ImpuestoDAO;
//import dao.KardexSlcCompraDAO;
import dao.MonedaDAO;
import dao.CotizacionCompraDAO;
import dao.ProductoDAO;
import dao.ProductoImpuestoDAO;
import dao.ResponsableDAO;
import dao.UnimedidaDAO;
import entity.DCotizacionCompra;
import entity.DCotizacionCompraPK;
import entity.Impuesto;
import entity.CotizacionCompra;
import entity.Producto;
import entity.ProductoImpuesto;
import entity.Unimedida;

import java.awt.Component;

import vista.contenedores.CntMoneda;
import vista.controles.DSGTextFieldNumber;
import vista.contenedores.CntClieprov;

public class FrmDocCotizacionCompra extends AbstractDocForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CotizacionCompraDAO cotizacioncompraDAO = new CotizacionCompraDAO();
	private DCotizacionCompraDAO dcotizacioncompraDAO = new DCotizacionCompraDAO();
	
	private ProductoDAO productoDAO = new ProductoDAO();
	private UnimedidaDAO unimedidaDAO = new UnimedidaDAO();
	private ProductoImpuestoDAO pimptoDAO = new ProductoImpuestoDAO();
	private ImpuestoDAO impuestoDAO = new ImpuestoDAO();
	private ClieprovDAO clieprovDAO = new ClieprovDAO();

	private TxtProducto txtProducto;
	private JLabel lblResponsable;
	private JLabel lblGlosa;
	private JScrollPane srlConsolidado;
	private cntResponsable cntResponsable;
	private JScrollPane scrlGlosa;
	private JTextArea txtGlosa;
	private JTable tblConsolidado;

	private CotizacionCompra cotizacioncompra;
	private List<DCotizacionCompra> dcotizacioncompras = new ArrayList<DCotizacionCompra>();
	
	private CntMoneda cntMoneda;
	private DSGTextFieldNumber txtTCambio;
	private DSGTextFieldNumber txtTCMoneda;
	private CntClieprov cntClieprov;
	private JLabel lblProveedor;

	public FrmDocCotizacionCompra() {
		super("Cotizacion de Compra");

		setEstado(VISTA);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGap(0, 874, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGap(0, 681, Short.MAX_VALUE));

		this.lblResponsable = new JLabel("Responsable");
		this.lblResponsable.setBounds(10, 74, 74, 16);
		pnlPrincipal.add(this.lblResponsable);

		this.lblGlosa = new JLabel("Glosa");
		this.lblGlosa.setBounds(399, 43, 32, 16);
		pnlPrincipal.add(this.lblGlosa);

		this.cntResponsable = new cntResponsable();

		this.cntResponsable.setBounds(72, 74, 309, 20);
		pnlPrincipal.add(this.cntResponsable);

		this.scrlGlosa = new JScrollPane();
		this.scrlGlosa.setBounds(436, 43, 395, 52);
		pnlPrincipal.add(this.scrlGlosa);

		this.txtGlosa = new JTextArea();
		this.scrlGlosa.setViewportView(this.txtGlosa);
		
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

		/*
		 * Tabla de Consolidado
		 */

		txtSerie.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg) {
				actualizaNumero(txtSerie.getText());
			}

			private void actualizaNumero(String serie) {
				int numero = cotizacioncompraDAO.getPorSerie(serie);
				numero = numero + 1;
				if (numero > 0) {
					txtNumero_2.setValue(numero);
					txtFecha.requestFocus();
				}
			}
		});

		this.srlConsolidado = new JScrollPane((Component) null);
		this.srlConsolidado.setBounds(10, 105, 821, 279);
		pnlPrincipal.add(this.srlConsolidado);
		tblConsolidado = new JTable(new DSGTableModel(new String[] {
				"Cód. Producto", "Producto", "Cod. Medida", "Medida",
				"Cantidad", "P. Unit.", "V. Venta", "%Dscto", "Dscto.",
				"% Impto", "Impto.", "Total" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				if (column == 1 || column == 2 || column == 3 || column == 6
						|| column == 8 || column == 10 || column == 11)
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
					getConsolidadoTM().setValueAt(entity.getIdproducto(), row,
							col);
					getConsolidadoTM().setValueAt(entity.getDescripcion(), row,
							col + 1);
					getConsolidadoTM().setValueAt(
							entity.getUnimedida().getIdunimedida(), row,
							col + 2);
					getConsolidadoTM().setValueAt(
							entity.getUnimedida().getDescripcion(), row,
							col + 3);
					try {
						antimpto = Float.parseFloat(getConsolidadoTM()
								.getValueAt(row, 9).toString());

					} catch (Exception e) {
						antimpto = 0.0F;
					}

					if (antimpto == 0.0)
						getConsolidadoTM().setValueAt(impto, row, 9);

				}
				setSeleccionado(null);
			}
		};
		getConsolidadoTM().setScrollAndTable(srlConsolidado, tblConsolidado);

		this.tblConsolidado
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.srlConsolidado.setViewportView(this.tblConsolidado);
		
		this.cntClieprov = new CntClieprov();
		this.cntClieprov.setBounds(72, 43, 309, 20);
		this.cntClieprov.setData(clieprovDAO.findAll());
		pnlPrincipal.add(this.cntClieprov);
		
		this.lblProveedor = new JLabel("Proveedor");
		this.lblProveedor.setBounds(11, 43, 50, 16);
		pnlPrincipal.add(this.lblProveedor);

		txtProducto.updateCellEditor();
		txtProducto.setData(productoDAO.findAll());
		getConsolidadoTM().setNombre_detalle("Detalle de Productos");
		getConsolidadoTM().setRepetidos(0);

		TableColumnModel tc = tblConsolidado.getColumnModel();

		getConsolidadoTM().setNombre_detalle("Consolidado de Productos");
		getConsolidadoTM().setRepetidos(0);

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

		iniciar();
	}

	@Override
	public void nuevo() {
		setOrdencompra(new CotizacionCompra());
		getCotizacioncompra().setIdcotizacioncompra(System.nanoTime());
		txtSerie.requestFocus();
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		cotizacioncompraDAO.crear_editar(getCotizacioncompra());

		//kardexSlcDAO.borrarPorIdOrdenCompra(ordencompra.getIdordencompra());

		
		for (DCotizacionCompra d : dcotizacioncompraDAO.aEliminar(getCotizacioncompra(),
				dcotizacioncompras)) {
			dcotizacioncompraDAO.remove(d);
		}

		for (DCotizacionCompra d : dcotizacioncompras) {
			if (dcotizacioncompraDAO.find(d.getId()) == null) {
				dcotizacioncompraDAO.create(d);
			} else {
				dcotizacioncompraDAO.edit(d);
			}
		}
		ContabilizaSlcCompras.ContabilizaCotizacion(getCotizacioncompra().getIdcotizacioncompra());
	}

	@Override
	public void eliminar() {
		int opcion = UtilMensajes.mensaje_sino("DESEA_ELIMINAR_DOC");
		if (opcion == 0) {
			// Borrar Kardex
			//kardexSlcDAO.borrarPorIdOrdenCompra(ordencompra.getIdordencompra());

			// Borrar Detalle y Consolidado
			//ddcotizacioncompraDAOborrarPorOrdenCompra(ordencompra);
			// Borrar Cabecera
			cotizacioncompraDAO.remove(cotizacioncompra);
			cotizacioncompra = null;
		}
	}

	@Override
	public void llenar_datos() {
		getConsolidadoTM().limpiar();

		if (getCotizacioncompra() != null) {
			this.txtNumero_2.setValue(getCotizacioncompra().getNumero());
			this.txtSerie.setText(getCotizacioncompra().getSerie());
			this.txtTCambio.setValue(getCotizacioncompra().getTcambio());
			this.txtTCMoneda.setValue(getCotizacioncompra().getTcmoneda());
			// this.cntGrupoCentralizacion.txtCodigo.setText(getCotizacioncompra().getGrupoCentralizacion().getIdgcentralizacion());
			// this.cntGrupoCentralizacion.txtDescripcion.setText(getCotizacioncompra().getGrupoCentralizacion().getDescripcion());
			cntMoneda.txtCodigo
					.setText((getCotizacioncompra().getMoneda() == null) ? ""
							: getCotizacioncompra().getMoneda().getIdmoneda());
			cntMoneda.llenar();
			cntResponsable.txtCodigo
					.setText((getCotizacioncompra().getResponsable() == null) ? ""
							: getCotizacioncompra().getResponsable()
									.getIdresponsable());
			cntResponsable.llenar();
			
			cntClieprov.txtCodigo.setText((getCotizacioncompra().getClieprov() == null) ? ""
					: getCotizacioncompra().getClieprov().getIdclieprov());
			cntClieprov.llenar();

			dcotizacioncompras = dcotizacioncompraDAO.getPorCotizacionCompra(getCotizacioncompra());
			
			for (DCotizacionCompra d : dcotizacioncompras) {
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
			dcotizacioncompras = new ArrayList<DCotizacionCompra>();
		}
	}

	private void actualiza_detalle() {
		int row = tblConsolidado.getSelectedRow();
		if (row > -1) {

			float pimpuesto, pdscto, cantidad, pu;
			float vventa, impuesto, dscto, importe;
			cantidad = Float.parseFloat(getConsolidadoTM().getValueAt(row, 4)
					.toString());
			try {
				pu = Float.parseFloat(getConsolidadoTM().getValueAt(row, 5)
						.toString());
			} catch (Exception e) {
				pu = 0.0F;
			}
			try {
				pdscto = Float.parseFloat(getConsolidadoTM().getValueAt(row, 7)
						.toString());
			} catch (Exception e) {
				pdscto = 0.0F;
			}
			try {
				pimpuesto = Float.parseFloat(getConsolidadoTM().getValueAt(row,
						9).toString());
			} catch (Exception e) {
				pimpuesto = 0.0F;
			}

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
				this.cntClieprov);
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
				 this.cntClieprov);
		getConsolidadoTM().setEditar(false);
	}

	@Override
	public void init() {

	}

	@Override
	public void actualiza_objeto(Object id) {
		setOrdencompra(cotizacioncompraDAO.find(id));
		llenar_datos();

		getBarra().enVista();
		vista_noedicion();
	}

	@Override
	public void llenarDesdeVista() {
		Calendar c = Calendar.getInstance();
		c.setTime(txtFecha.getDate());
		Long idoc = getCotizacioncompra().getIdcotizacioncompra();
		// getIngreso().setGrupoCentralizacion(cntGrupoCentralizacion.getSeleccionado());
		getCotizacioncompra().setSerie(this.txtSerie.getText());
		getCotizacioncompra()
				.setNumero(Integer.parseInt(this.txtNumero_2.getText()));
		getCotizacioncompra().setMoneda(cntMoneda.getSeleccionado());
		getCotizacioncompra().setResponsable(this.cntResponsable.getSeleccionado());
		getCotizacioncompra().setClieprov(this.cntClieprov.getSeleccionado());
		getCotizacioncompra().setDia(c.get(Calendar.DAY_OF_MONTH));
		getCotizacioncompra().setMes(c.get(Calendar.MONTH) + 1);
		getCotizacioncompra().setAnio(c.get(Calendar.YEAR));
		getCotizacioncompra().setFecha(
				(c.get(Calendar.YEAR) * 10000)
						+ ((c.get(Calendar.MONTH) + 1) * 100)
						+ c.get(Calendar.DAY_OF_MONTH));
		getCotizacioncompra().setGlosa(txtGlosa.getText());
		getCotizacioncompra().setTcambio(Float.parseFloat(txtTCambio.getText()));
		getCotizacioncompra().setTcmoneda(Float.parseFloat(txtTCMoneda.getText()));
		dcotizacioncompras = new ArrayList<DCotizacionCompra>();

		int rows = getConsolidadoTM().getRowCount();

		for (int row = 0; row < rows; row++) {
			DCotizacionCompra d = new DCotizacionCompra();
			DCotizacionCompraPK id = new DCotizacionCompraPK();

			String idproducto, idunimedida;

			idproducto = getConsolidadoTM().getValueAt(row, 0).toString();
			idunimedida = getConsolidadoTM().getValueAt(row, 2).toString();

			float cantidad, precio_unitario, vventa, pdescuento, descuento, pimpuesto, impuesto, importe;

			cantidad = Float.parseFloat(getConsolidadoTM().getValueAt(row, 4)
					.toString());
			precio_unitario = Float.parseFloat(getConsolidadoTM().getValueAt(
					row, 5).toString());

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

			id.setIdcotizacioncompra(idoc);
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

			dcotizacioncompras.add(d);
		}
	}

	@Override
	public boolean isValidaVista() {
		boolean band = validaCabecera();
		return band;
	}

	public boolean validaCabecera() {

		if (!FormValidador.CntObligatorios(cntMoneda, cntResponsable,
				cntClieprov))
			return false;

		return true;
	}
	
	public DSGTableModel getConsolidadoTM() {
		return ((DSGTableModel) tblConsolidado.getModel());
	}

	public CotizacionCompra getCotizacioncompra() {
		return cotizacioncompra;
	}

	public void setOrdencompra(CotizacionCompra cotizacioncompra) {
		this.cotizacioncompra = cotizacioncompra;
	}
}
