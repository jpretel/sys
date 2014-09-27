package vista.formularios;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import dao.AlmacenDAO;
import dao.ConceptoDAO;
import dao.DetDocIngresoDAO;
import dao.DocingresoDAO;
import dao.GrupoCentralizacionDAO;
import dao.MonedaDAO;
import dao.ProductoDAO;
import dao.ResponsableDAO;
import dao.SucursalDAO;
import dao.UnimedidaDAO;
import entity.Almacen;
import entity.DetDocingreso;
import entity.DetDocingresoPK;
import entity.Docingreso;
import entity.Producto;
import entity.Sucursal;
import entity.Unimedida;

import java.awt.Rectangle;
import java.awt.Dimension;

import vista.contenedores.CntGrupoCentralizacion;
import javax.swing.JTextField;
import vista.controles.FindButton;

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
	private AlmacenDAO almacenDAO = new AlmacenDAO();
	// private long Id;
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

	private TxtProducto txtProducto;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JTextField textField_1;
	private FindButton findButton;

	public FrmDocRecepcion() {
		super("Nota de Ingreso");
		// pnlPrincipal.setSize(new Dimension(847, 60));
		// pnlPrincipal.setLocation(new Point(0, 41));

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

		// JPanel pnlPrincipal = new JPanel();
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
					addRow(new Object[] { "", "", "", "", 0, 0, 0 });
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
		
		this.textField = new JTextField();
		this.textField.setBounds(72, 145, 44, 20);
		pnlPrincipal.add(this.textField);
		this.textField.setColumns(10);
		
		this.textField_1 = new JTextField();
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(116, 145, 80, 20);
		pnlPrincipal.add(this.textField_1);
		
		this.findButton = new FindButton();
		this.findButton.setBounds(198, 145, 20, 20);
		pnlPrincipal.add(this.findButton);
		iniciar();
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
		ContabilizaAlmacen.ContabilizarIngreso(getIngreso());
		// String Mensaje = CentralizaAlm.CentralizaAlm(getIngreso()
		// .getIddocingreso());
		// System.out.println(Mensaje);
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void llenar_datos() {
		if (getIngreso() != null) {

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

			this.txtTipoCambio.setValue(getIngreso().getTcambio());

			this.txtTcMoneda.setValue(getIngreso().getTcmoneda());

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
				Unimedida unimedida = new UnimedidaDAO().find(ingreso
						.getIdmedida());
				getDetalleTM().addRow(
						new Object[] { ingreso.getId().getIdproducto(),
								ingreso.getDescripcion(),
								unimedida.getIdunimedida(),
								unimedida.getDescripcion(),
								ingreso.getCantidad(), ingreso.getPrecio(),
								ingreso.getPrecio() });
			}
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
		cntConcepto.setData(new ConceptoDAO().findAll());
		cntResponsable.setData(new ResponsableDAO().findAll());
		txtProducto.setData(new ProductoDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		boolean band;

		band = getEstado().equals(NUEVO);

		FormValidador.TextFieldsEdicion(band, this.txtSerie, this.txtNumero_2);
		FormValidador.TextFieldsEdicion(true, this.txtTcMoneda,
				this.txtTipoCambio);

		this.txtFecha.setEditable(true);
		this.txtGlosa.setEditable(true);

		FormValidador.CntEdicion(true, this.cntGrupoCentralizacion,
				this.cntMoneda, this.cntConcepto, this.cntResponsable,
				this.cntSucursal, this.cntAlmacen);
		getDetalleTM().setEditar(true);
	}

	@Override
	public void vista_noedicion() {

		this.txtFecha.setEditable(false);
		this.txtGlosa.setEditable(false);

		FormValidador.TextFieldsEdicion(false, this.txtTcMoneda,
				this.txtTipoCambio, this.txtSerie, this.txtNumero_2);

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

		getIngreso().setIddocingreso(Id);
		getIngreso().setGrupoCentralizacion(
				cntGrupoCentralizacion.getSeleccionado());
		getIngreso().setTcambio(Float.parseFloat(this.txtTipoCambio.getText()));
		getIngreso().setTcmoneda(Float.parseFloat(this.txtTcMoneda.getText()));
		getIngreso().setSerie(this.txtSerie.getText());
		getIngreso().setNumero(Integer.parseInt(this.txtNumero_2.getText()));
		getIngreso().setConcepto(this.cntConcepto.getSeleccionado());
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
			detPK.setIdingreso(Id);
			detPK.setIdproducto(getDetalleTM().getValueAt(i, 0).toString());
			det.setId(detPK);
			det.setDescripcion(getDetalleTM().getValueAt(i, 1).toString());
			det.setIdmedida(getDetalleTM().getValueAt(i, 2).toString());
			det.setCantidad(Float.parseFloat((getDetalleTM().getValueAt(i, 4)
					.toString())));
			det.setPrecio(Float.parseFloat(getDetalleTM().getValueAt(i, 5)
					.toString()));
			det.setImporte(Float.parseFloat(getDetalleTM().getValueAt(i, 6)
					.toString()));
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
				this.cntMoneda.txtCodigo, this.txtTipoCambio, this.txtTcMoneda,
				this.cntConcepto.txtCodigo, this.cntResponsable.txtCodigo,
				this.cntSucursal.txtCodigo, this.cntAlmacen.txtCodigo);
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
}
