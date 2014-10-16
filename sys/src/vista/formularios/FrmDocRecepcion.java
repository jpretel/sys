package vista.formularios;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vista.Sys;
import vista.barras.PanelBarraDocumento;
import vista.contenedores.CntConcepto;
import vista.contenedores.cntAlmacen;
import vista.contenedores.cntResponsable;
import vista.contenedores.cntSucursal;
import vista.controles.DSGTableModel;
import vista.controles.celleditor.TxtProducto;
import vista.formularios.listas.AbstractDocForm;
import vista.utilitarios.FormValidador;
import vista.utilitarios.StringUtils;
import vista.utilitarios.editores.FloatEditor;
import vista.utilitarios.renderers.FloatRenderer;

import javax.swing.JLabel;
import javax.swing.table.TableColumnModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import core.centralizacion.CentralizaAlm;
import core.centralizacion.ContabilizaAlmacen;
import core.centralizacion.ContabilizaComprasRecepcion;
import dao.AlmacenDAO;
import dao.ConceptoDAO;
import dao.DOrdenCompraDAO;
import dao.DetDocIngresoDAO;
import dao.DocingresoDAO;
import dao.GrupoCentralizacionDAO;
import dao.KardexCompraRecepcionDAO;
import dao.MonedaDAO;
import dao.OrdenCompraDAO;
import dao.ProductoDAO;
import dao.ResponsableDAO;
import dao.SucursalDAO;
import dao.UnimedidaDAO;
import entity.Almacen;
import entity.Asiento;
import entity.DOrdenCompra;
import entity.DetDocingreso;
import entity.DetDocingresoPK;
import entity.Docingreso;
import entity.KardexCompraRecepcion;
import entity.OrdenCompra;
import entity.Producto;
import entity.Sucursal;
import entity.Unimedida;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Dimension;

import vista.contenedores.CntGrupoCentralizacion;

import javax.swing.JTextField;

import vista.controles.FindButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vista.contenedores.CntMoneda;

public class FrmDocRecepcion extends AbstractDocForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblDetalle;
	private JScrollPane scrollPaneDetalle;
	private List<DetDocingreso> DetDocingresoL;
	private DocingresoDAO docIngresoDAO = new DocingresoDAO();
	private DetDocIngresoDAO detDocingresoDAO = new DetDocIngresoDAO();
	private UnimedidaDAO unimedidaDAO = new UnimedidaDAO();
	private AlmacenDAO almacenDAO = new AlmacenDAO();
	private ProductoDAO productoDAO = new ProductoDAO();
	private CntConcepto cntConcepto;
	private cntResponsable cntResponsable;
	private cntSucursal cntSucursal;
	private cntAlmacen cntAlmacen;
	private JTextArea txtGlosa;
	private Docingreso ingreso;
	private Calendar calendar = Calendar.getInstance();
	private CntGrupoCentralizacion cntGrupoCentralizacion;
	private JLabel lblOperacin;
	private JScrollPane scrlGlosa;
	private OrdenCompraDAO ordencompraDAO = new OrdenCompraDAO();
	private DOrdenCompraDAO dordencompraDAO = new DOrdenCompraDAO();
	private TxtProducto txtProducto;
	private JLabel lblNewLabel;
	private JTextField txtSerieCompra;
	private JTextField txtNumeroCompra;
	private FindButton findButton;
	private OrdenCompra ordencompra = null;

	public FrmDocRecepcion() {
		super("Nota de Ingreso");
		lblTipoCambio.setLocation(590, 15);
		this.cntGrupoCentralizacion = new CntGrupoCentralizacion();
		this.cntGrupoCentralizacion.setBounds(72, 40, 192, 20);
		pnlPrincipal.add(this.cntGrupoCentralizacion);

		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(8, 71, 54, 16);
		pnlPrincipal.add(lblConcepto);

		this.lblOperacin = new JLabel("Operaci\u00F3n");
		this.lblOperacin.setBounds(8, 44, 54, 16);
		pnlPrincipal.add(this.lblOperacin);
		getBarra().setSize(new Dimension(418, 40));

		pnlPrincipal.setPreferredSize(new Dimension(400, 400));
		pnlPrincipal.setMinimumSize(new Dimension(400, 400));
		pnlPrincipal.setBounds(new Rectangle(0, 0, 400, 400));
		pnlPrincipal.setLayout(null);

		JLabel lblResponsable = new JLabel("Responsable");
		lblResponsable.setBounds(424, 71, 74, 16);
		pnlPrincipal.add(lblResponsable);

		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(8, 108, 51, 16);
		pnlPrincipal.add(lblSucursal);

		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(424, 108, 50, 16);
		pnlPrincipal.add(lblAlmacen);

		JLabel lblGlosa = new JLabel("Glosa");
		lblGlosa.setBounds(424, 135, 32, 16);
		pnlPrincipal.add(lblGlosa);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(540, 5, 0, 16);
		pnlPrincipal.add(textArea);

		tblDetalle = new JTable(new DSGTableModel(new String[] { "IdProducto",
				"Producto", "IdMedida", "Medida", "Cantidad", "Precio",
				"Importe", "OC" }) {
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
					addRow(new Object[] { "", "", "", "", 0, 0, 0, null });
				else
					JOptionPane.showMessageDialog(null,
							"Faltan datos en la cabecera");
			}
		}) {
			private static final long serialVersionUID = 1L;

			public void changeSelection(int row, int column, boolean toggle,
					boolean extend) {
				super.changeSelection(row, column, toggle, extend);
				if (row > -1) {
					String idproducto = getDetalleTM().getValueAt(row, 0)
							.toString();

					txtProducto.refresValue(idproducto);
					actualiza_detalle();
				}
			}
		};

		tblDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPaneDetalle = new JScrollPane(tblDetalle);
		scrollPaneDetalle.setBounds(8, 214, 824, 170);

		txtProducto = new TxtProducto(tblDetalle, 0) {
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
			}

		};

		txtProducto.updateCellEditor();

		getDetalleTM().setNombre_detalle("Detalle de Productos");
		getDetalleTM().setObligatorios(0, 1, 4, 5, 6);
		getDetalleTM().setRepetidos(0);
		getDetalleTM().setScrollAndTable(scrollPaneDetalle, tblDetalle);

		TableColumnModel tc = tblDetalle.getColumnModel();

		tc.getColumn(4).setCellEditor(new FloatEditor(3));
		tc.getColumn(4).setCellRenderer(new FloatRenderer(3));

		tc.getColumn(5).setCellEditor(new FloatEditor(2));
		tc.getColumn(5).setCellRenderer(new FloatRenderer(2));

		tc.getColumn(6).setCellEditor(new FloatEditor(2));
		tc.getColumn(6).setCellRenderer(new FloatRenderer(2));
		tc.getColumn(7).setWidth(0);
		tc.getColumn(7).setMinWidth(0);
		tc.getColumn(7).setMaxWidth(0);

		pnlPrincipal.add(scrollPaneDetalle);

		cntConcepto = new CntConcepto();
		cntConcepto.setBounds(72, 69, 338, 20);
		pnlPrincipal.add(cntConcepto);

		cntResponsable = new cntResponsable();
		cntResponsable.setBounds(508, 69, 309, 20);
		pnlPrincipal.add(cntResponsable);

		cntSucursal = new cntSucursal();
		cntSucursal.setBounds(72, 106, 339, 20);
		pnlPrincipal.add(cntSucursal);

		cntAlmacen = new cntAlmacen();
		cntAlmacen.setBounds(508, 106, 309, 20);
		pnlPrincipal.add(cntAlmacen);

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
				int numero = docIngresoDAO.getPorSerie(serie);
				numero = numero + 1;
				if (numero > 0) {
					txtNumero_2.setText(String.valueOf(numero));
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

		this.scrlGlosa = new JScrollPane();
		this.scrlGlosa.setBounds(506, 142, 325, 61);
		pnlPrincipal.add(this.scrlGlosa);

		txtGlosa = new JTextArea();
		scrlGlosa.setViewportView(txtGlosa);

		this.lblNewLabel = new JLabel("Doc. Anexo");
		this.lblNewLabel.setBounds(8, 148, 62, 14);
		pnlPrincipal.add(this.lblNewLabel);

		this.txtSerieCompra = new JTextField();
		this.txtSerieCompra.setBounds(72, 145, 44, 20);
		pnlPrincipal.add(this.txtSerieCompra);
		this.txtSerieCompra.setColumns(10);

		txtSerieCompra.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent evt) {
				if (txtSerieCompra.isEditable()) {
					String texto = org.codehaus.plexus.util.StringUtils.repeat(
							"0", 4 - txtSerieCompra.getText().length())
							+ txtSerieCompra.getText();
					txtSerieCompra.setText(texto);
				}
			}
		});

		this.txtNumeroCompra = new JTextField();
		this.txtNumeroCompra.setColumns(10);
		this.txtNumeroCompra.setBounds(116, 145, 80, 20);
		pnlPrincipal.add(this.txtNumeroCompra);

		txtNumeroCompra.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent evt) {
				if (txtNumeroCompra.isEditable()) {
					String texto = org.codehaus.plexus.util.StringUtils.repeat(
							"0", 8 - txtNumeroCompra.getText().length())
							+ txtNumeroCompra.getText();
					txtNumeroCompra.setText(texto);
				}
			}
		});

		this.findButton = new FindButton();
		findButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				llenarDetalleRecepcion();
			}
		});
		this.findButton.setBounds(198, 145, 20, 20);
		pnlPrincipal.add(this.findButton);

		cntConcepto.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent evt) {
				if (cntConcepto.txtCodigo.getText().trim().length() > 0) {
					if (cntConcepto.getSeleccionado().getSolcitaCompra() > 0) {
						txtSerieCompra.setEditable(true);
						txtNumeroCompra.setEditable(true);
						findButton.setEnabled(true);
					} else {
						txtSerieCompra.setEditable(false);
						txtNumeroCompra.setEditable(false);
						findButton.setEnabled(false);
					}
				}
			}
		});
		iniciar();
	}

	public void llenarDetalleRecepcion() {
		String serie = this.txtSerieCompra.getText();
		String numero = this.txtNumeroCompra.getText();
		int nFilas = getDetalleTM().getRowCount();
		int opc = -1;
		if(nFilas > 0 ){
			opc = JOptionPane.showConfirmDialog(null, "Se borarra el detalle actual y cargara el detalle de la compra", "¿Desea Continuar?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		}
		if(opc == -1 || opc == 0){
			getDetalleTM().limpiar();			
			if (serie.trim().length() > 0 && numero.trim().length() > 0) {
				ordencompra = ordencompraDAO.getPorSerieNumero(serie, numero);
				KardexCompraRecepcionDAO kardexCompraRecepcionDAO = new KardexCompraRecepcionDAO();
				List<DOrdenCompra> lDOrdenCompras = dordencompraDAO
						.getPorOrdenCompra(ordencompra);
				for (DOrdenCompra dordencompra : lDOrdenCompras) {
					float sum_cantidad = 0;
					for (KardexCompraRecepcion kardexCompraRecepcion : kardexCompraRecepcionDAO
							.getPorDOrdenCompra(dordencompra)) {
						sum_cantidad = sum_cantidad
								+ (kardexCompraRecepcion.getCantidad() * kardexCompraRecepcion
										.getFactor());
					}
					if (sum_cantidad == 0){						
						JOptionPane.showMessageDialog(null, "Esta Orden de compra fue recepcionado en su totalidad");
						this.txtSerieCompra.setText("");
						this.txtNumeroCompra.setText("");
					}else{
						getDetalleTM().addRow(
								new Object[] {
										dordencompra.getProducto().getIdproducto(),
										dordencompra.getProducto().getDescripcion(),
										dordencompra.getUnimedida().getIdunimedida(),
										dordencompra.getUnimedida().getDescripcion(),
										sum_cantidad,
										dordencompra.getPrecio_unitario(),
										dordencompra.getImporte(), dordencompra });
					}
				}
			}
		}
	}

	@Override
	public void nuevo() {
		Calendar calendar = Calendar.getInstance();
		int anio, mes, dia;
		anio = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH) + 1;
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		setIngreso(new Docingreso());
		getIngreso().setIddocingreso(System.nanoTime());
		getIngreso().setAnio(anio);
		getIngreso().setMes(mes);
		getIngreso().setDia(dia);
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		docIngresoDAO.crear_editar(getIngreso());
		for (DetDocingreso det : getDetDocingresoL()) {
			detDocingresoDAO.crear_editar(det);
		}
		ContabilizaComprasRecepcion.ContabilizaComprasRecepcion(getIngreso()
				.getIddocingreso(), -1, "Recepcion");
		 ContabilizaAlmacen.ContabilizarIngreso(getIngreso());
		 CentralizaAlm.CentralizaIngreso(getIngreso().getIddocingreso());		 
		setIngreso(docIngresoDAO.find(getIngreso().getIddocingreso()));
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void llenar_datos() {
		if (getIngreso() != null && !getEstado().equals("NUEVO")) {

			Sucursal sucursal;
			Almacen almacen;

			sucursal = getIngreso().getSucursal();
			almacen = getIngreso().getAlmacen();

			String numero = StringUtils._padl(getIngreso().getNumero(), 8, '0');
			this.txtNumero_2.setText(numero);
			this.txtSerie.setText(getIngreso().getSerie());

			this.cntGrupoCentralizacion.txtCodigo.setText((getIngreso()
					.getGrupoCentralizacion() == null) ? "" : getIngreso()
					.getGrupoCentralizacion().getIdgcentralizacion());
			this.cntGrupoCentralizacion.llenar();

			// this.txtTipoCambio.setValue(getIngreso().getTcambio());
			// this.txtTcMoneda.setValue(getIngreso().getTcmoneda());
			
			this.lblTipoCambio.setVisible(false);
			this.txtTipoCambio.setVisible(false);
			this.lblTcMoneda.setVisible(false);
			this.txtTcMoneda.setVisible(false);
			
			this.cntMoneda.txtCodigo
					.setText((getIngreso().getMoneda() == null) ? ""
							: getIngreso().getMoneda().getIdmoneda());

			this.cntMoneda.llenar();

			this.cntMoneda.txtDescripcion
					.setText((getIngreso().getMoneda() == null) ? ""
							: getIngreso().getMoneda().getDescripcion());

			this.cntConcepto.txtCodigo
					.setText((getIngreso().getConcepto() == null) ? ""
							: getIngreso().getConcepto().getIdconcepto());

			this.cntConcepto.llenar();

			if (this.cntConcepto.getSeleccionado().getSolcitaCompra() == 1) {
				txtSerieCompra
						.setText(getIngreso().getOrdencompra().getSerie());
				String xnumero = StringUtils._padl(getIngreso().getOrdencompra().getNumero(), 8,
						'0');
				txtNumeroCompra.setText(xnumero);
			}
			this.cntResponsable.txtCodigo.setText((getIngreso()
					.getResponsable() == null) ? "" : getIngreso()
					.getResponsable().getIdresponsable());

			this.cntResponsable.llenar();

			this.cntSucursal.txtCodigo.setText((sucursal == null) ? ""
					: sucursal.getIdsucursal());
			this.cntSucursal.llenar();
			if (sucursal == null) {
				cntAlmacen.setData(null);
			} else {
				cntAlmacen.setData(almacenDAO.getPorSucursal(sucursal));
			}

			this.cntAlmacen.txtCodigo.setText((almacen == null) ? "" : almacen
					.getId().getIdalmacen());
			this.cntAlmacen.llenar();

			this.txtGlosa.setText(getIngreso().getGlosa());
			calendar.set(ingreso.getAnio(), ingreso.getMes() - 1,
					ingreso.getDia());

			this.txtFecha.setDate(calendar.getTime());
			List<DetDocingreso> detDocIngresoL = detDocingresoDAO
					.getPorIdIngreso(getIngreso());
			getDetalleTM().limpiar();
			for (DetDocingreso ingreso : detDocIngresoL) {
				Unimedida unimedida = ingreso.getUnimedida();
				Producto p = ingreso.getProducto();
				getDetalleTM().addRow(
						new Object[] { p.getIdproducto(), p.getDescripcion(),
								unimedida.getIdunimedida(),
								unimedida.getDescripcion(),
								ingreso.getCantidad(), ingreso.getPrecio(),
								ingreso.getPrecio(),
								ingreso.getDordencompra()});
			}
		}
	}

	@Override
	public void llenar_lista() {

	}

	@Override
	public void llenar_tablas() {
		cntGrupoCentralizacion.setData(new GrupoCentralizacionDAO().findAll());
		cntMoneda.setData(new MonedaDAO().findAll());
		cntSucursal.setData(new SucursalDAO().findAll());
		cntConcepto.setData(new ConceptoDAO().getPorTipo("I"));
		cntResponsable.setData(new ResponsableDAO().findAll());
		txtProducto.setData(new ProductoDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		// Verificar que no este en un proceso hacia adelante esto falta.....VTR
		boolean band;
		band = getEstado().equals(NUEVO);

		FormValidador.TextFieldsEdicion(band, this.txtSerie, this.txtNumero_2);
			
		this.txtSerie.setEditable(true);
		this.txtNumero_2.setEditable(true);
		this.txtFecha.setEditable(true);
		this.txtGlosa.setEditable(true);
		if (cntConcepto.txtCodigo.getText().trim().length() > 0) {
			if (cntConcepto.getSeleccionado().getSolcitaCompra() > 0) {
				txtSerieCompra.setEditable(true);
				txtNumeroCompra.setEditable(true);
				findButton.setEnabled(true);
			} else {
				txtSerieCompra.setEditable(false);
				txtNumeroCompra.setEditable(false);
				findButton.setEnabled(false);
			}
		} else {
			txtSerieCompra.setEditable(false);
			txtNumeroCompra.setEditable(false);
			findButton.setEnabled(false);
		}

		FormValidador.CntEdicion(true,this.cntGrupoCentralizacion,
				this.cntMoneda, this.cntConcepto, this.cntResponsable,
				this.cntSucursal, this.cntAlmacen);
		getDetalleTM().setEditar(true);
	}

	@Override
	public void vista_noedicion() {
		this.txtFecha.setEditable(false);
		this.txtSerie.setEditable(false);
		this.txtNumero_2.setEditable(false);
		this.txtGlosa.setEditable(false);
		this.txtSerieCompra.setEditable(false);
		this.txtNumeroCompra.setEditable(false);
		this.findButton.setEnabled(false);
		FormValidador.CntEdicion(false, this.cntGrupoCentralizacion,
				this.cntMoneda, this.cntConcepto, this.cntResponsable,
				this.cntSucursal, this.cntAlmacen);
		getDetalleTM().setEditar(false);
	}

	@Override
	public void init() {

	}

	@Override
	public void actualiza_objeto(Object id) {
		setIngreso(docIngresoDAO.find(id));
		llenar_datos();
		getBarra().enVista();
		vista_noedicion();

	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public void llenarDesdeVista() {
		Long Id = getIngreso().getIddocingreso();
		System.out.println(Id);
		getIngreso().setGrupoCentralizacion(
				cntGrupoCentralizacion.getSeleccionado());
		getIngreso().setSerie(this.txtSerie.getText());
		getIngreso().setNumero(Integer.parseInt(this.txtNumero_2.getText()));
		getIngreso().setConcepto(this.cntConcepto.getSeleccionado());
		if (cntConcepto.getSeleccionado().getSolcitaCompra() > 0) {
			if (ordencompra != null)
				getIngreso().setOrdencompra(ordencompra);
		}
		
		getIngreso().setMoneda(cntMoneda.getSeleccionado());
		getIngreso().setResponsable(this.cntResponsable.getSeleccionado());
		getIngreso().setSucursal(this.cntSucursal.getSeleccionado());
		getIngreso().setAlmacen(this.cntAlmacen.getSeleccionado());
		getIngreso().setDia(this.txtFecha.getDate().getDate());
		getIngreso().setMes(this.txtFecha.getDate().getMonth() + 1);
		getIngreso().setAnio(this.txtFecha.getDate().getYear() + 1900);
		getIngreso().setAniomesdia(
				((this.txtFecha.getDate().getYear() + 1900) * 10000)
						+ ((this.txtFecha.getDate().getMonth() + 1) * 100)
						+ this.txtFecha.getDate().getDate());
		getIngreso().setGlosa(txtGlosa.getText());
		setDetDocingresoL(new ArrayList<DetDocingreso>());
		for (int i = 0; i < getDetalleTM().getRowCount(); i++) {
			DetDocingresoPK detPK = new DetDocingresoPK();
			DetDocingreso det = new DetDocingreso();
			Unimedida unimedida = unimedidaDAO.find(getDetalleTM().getValueAt(
					i, 2).toString());
			Producto producto = productoDAO.find(getDetalleTM()
					.getValueAt(i, 0).toString());

			detPK.setIdingreso(Id);
			detPK.setItem(i + 1); // Actualizamos la posicion
			det.setId(detPK);
			det.setDescripcion(getDetalleTM().getValueAt(i, 1).toString());
			det.setUnimedida(unimedida);
			det.setCantidad(Float.parseFloat((getDetalleTM().getValueAt(i, 4)
					.toString())));
			det.setPrecio(Float.parseFloat(getDetalleTM().getValueAt(i, 5)
					.toString()));
			det.setImporte(Float.parseFloat(getDetalleTM().getValueAt(i, 6)
					.toString()));
			det.setProducto(producto);
			det.setDordencompra((DOrdenCompra) (getDetalleTM().getValueAt(i, 7)));
			DetDocingresoL.add(det);
		}
	}

	@Override
	public boolean isValidaVista() {
		boolean band = validaCabecera();
		if (!band) {
			band = validarDetalle();
		}
		return band;
	}

	private void actualiza_detalle() {
		int row = tblDetalle.getSelectedRow();
		if (row > -1) {
			float cantidad, precio, importe;

			cantidad = Float.parseFloat(getDetalleTM().getValueAt(row, 4)
					.toString());
			precio = Float.parseFloat(getDetalleTM().getValueAt(row, 5)
					.toString());
			importe = cantidad * precio;
			getDetalleTM().setValueAt(importe, row, 6);
		}
	}

	public boolean validaCabecera() {
		return FormValidador.TextFieldObligatorios(
				this.cntGrupoCentralizacion.txtCodigo,
				this.cntMoneda.txtCodigo, this.cntConcepto.txtCodigo,
				this.cntResponsable.txtCodigo, this.cntSucursal.txtCodigo,
				this.cntAlmacen.txtCodigo);
	}

	public boolean validarDetalle() {
		return getDetalleTM().esValido();
	}

	public DSGTableModel getDetalleTM() {
		return ((DSGTableModel) tblDetalle.getModel());
	}

	public List<DetDocingreso> getDetDocingresoL() {
		return DetDocingresoL;
	}

	public void setDetDocingresoL(List<DetDocingreso> detDocingresoL) {
		DetDocingresoL = detDocingresoL;
	}

	public Docingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Docingreso ingreso) {
		this.ingreso = ingreso;
	}

	@Override
	public void doVerAsiento() {
		Asiento asiento = getIngreso().getAsiento();
		FrmAsientoDoc frmAsiento = new FrmAsientoDoc();
		frmAsiento.actualiza_objeto(asiento.getIdasiento(), "VISTA");
		Sys.desktoppane.add(frmAsiento);
		frmAsiento.moveToFront();
	}

	@Override
	public void initBarra() {
		int AnchoCabecera = 850;
		barra = new PanelBarraDocumento('A');
		barra.setMinimumSize(new Dimension(AnchoCabecera, 40));
		barra.setPreferredSize(new Dimension(AnchoCabecera, 40));
		barra.setBounds(0, 0, AnchoCabecera, 42);
		barra.setFormMaestro(this);
		FlowLayout flowLayout = (FlowLayout) barra.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(barra, BorderLayout.NORTH);
	}
}
