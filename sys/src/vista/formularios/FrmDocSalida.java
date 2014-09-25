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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import dao.AlmacenDAO;
import dao.ConceptoDAO;
import dao.DetDocSalidaDAO;
import dao.DocsalidaDAO;
import dao.GrupoCentralizacionDAO;
import dao.MonedaDAO;
import dao.ProductoDAO;
import dao.ResponsableDAO;
import dao.SucursalDAO;
import dao.UnimedidaDAO;
import entity.Almacen;
import entity.DetDocsalida;
import entity.DetDocsalidaPK;
import entity.Docsalida;
import entity.Producto;
import entity.Sucursal;
import entity.Unimedida;
import vista.contenedores.CntGrupoCentralizacion;

public class FrmDocSalida extends AbstractDocForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblDetalle;
	private JScrollPane scrollPaneDetalle;
	private List<DetDocsalida> DetDocsalidaL;
	private DocsalidaDAO docSalidaDAO = new DocsalidaDAO();
	private DetDocSalidaDAO detDocsalidaDAO = new DetDocSalidaDAO();
	private MonedaDAO monedaDAO = new MonedaDAO();
	private AlmacenDAO almacenDAO = new AlmacenDAO();
	private CntConcepto cntConcepto;
	private cntResponsable cntResponsable;
	private cntSucursal cntSucursal;
	private cntAlmacen cntAlmacen;
	private cntSucursal cntSucursal_dest;
	private cntAlmacen cntAlmacen_dest;
	private JTextArea txtGlosa;
	private Docsalida salida;
	private Calendar calendar = Calendar.getInstance();
	private CntGrupoCentralizacion cntGrupoCentralizacion;
	private JScrollPane scrlGlosa;
	private JLabel lblOperacin;
	private TxtProducto txtProducto;
	public FrmDocSalida() {
		super("Nota de Salida");

		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(359, 43, 54, 16);
		pnlPrincipal.add(lblConcepto);

		JLabel lblResponsable = new JLabel("Responsable");
		lblResponsable.setBounds(12, 133, 74, 16);
		pnlPrincipal.add(lblResponsable);

		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(12, 71, 51, 16);
		pnlPrincipal.add(lblSucursal);

		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(465, 71, 50, 16);
		pnlPrincipal.add(lblAlmacen);

		JLabel lblGlosa = new JLabel("Observacion");
		lblGlosa.setBounds(465, 133, 68, 16);
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
				if (column == 1 || column == 3)
					return false;
				
				return getEditar();
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
		scrollPaneDetalle.setBounds(12, 197, 824, 187);

		txtProducto = new TxtProducto(tblDetalle, 0){
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

		cntConcepto.setBounds(410, 40, 338, 20);
		pnlPrincipal.add(cntConcepto);

		cntConcepto.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent evt) {
				if (cntConcepto.txtCodigo.getText().trim().length() > 0) {
					if (cntConcepto.getSeleccionado().getEsTransferencia() == 1) {
						cntSucursal_dest.setEditable(true);
						cntAlmacen_dest.setEditable(true);
					} else {
						cntSucursal_dest.setEditable(false);
						cntAlmacen_dest.setEditable(false);
					}
				}
			}
		});

		cntResponsable = new cntResponsable();
		cntResponsable.setBounds(116, 129, 297, 20);
		pnlPrincipal.add(cntResponsable);

		cntSucursal = new cntSucursal();
		cntSucursal.btnBuscar.setLocation(90, 3);
		cntSucursal.setBounds(116, 71, 297, 20);
		pnlPrincipal.add(cntSucursal);

		cntAlmacen = new cntAlmacen();
		cntAlmacen.setBounds(557, 71, 278, 20);
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
				int numero = docSalidaDAO.getPorSerie(serie);
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

		/* cntSucursal cntSucursal_ = new cntSucursal(); */
		cntSucursal_dest = new cntSucursal();

		cntSucursal_dest.setBounds(116, 102, 297, 20);
		pnlPrincipal.add(cntSucursal_dest);

		JLabel lblSucursalDestino = new JLabel("Sucursal Destino");
		lblSucursalDestino.setBounds(12, 98, 85, 16);
		pnlPrincipal.add(lblSucursalDestino);

		JLabel lblAlmacenDestino = new JLabel("Almacen Destino");
		lblAlmacenDestino.setBounds(465, 106, 85, 16);
		pnlPrincipal.add(lblAlmacenDestino);

		/* cntAlmacen cntAlmacen_ = new cntAlmacen(); */
		cntAlmacen_dest = new cntAlmacen();

		cntAlmacen_dest.setBounds(557, 102, 278, 20);
		pnlPrincipal.add(cntAlmacen_dest);

		cntSucursal_dest.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (cntSucursal_dest.txtCodigo.getText().trim().length() > 0) {
					cntAlmacen_dest.setData(new AlmacenDAO()
							.getPorSucursal(cntSucursal_dest.getSeleccionado()));
				}
			}
		});

		this.cntGrupoCentralizacion = new CntGrupoCentralizacion();
		this.cntGrupoCentralizacion.setBounds(72, 43, 237, 20);
		pnlPrincipal.add(this.cntGrupoCentralizacion);

		this.scrlGlosa = new JScrollPane();
		this.scrlGlosa.setBounds(557, 133, 279, 53);
		pnlPrincipal.add(this.scrlGlosa);

		txtGlosa = new JTextArea();
		this.scrlGlosa.setViewportView(this.txtGlosa);

		this.lblOperacin = new JLabel("Operaci\u00F3n");
		this.lblOperacin.setBounds(9, 45, 54, 16);
		pnlPrincipal.add(this.lblOperacin);
		iniciar();
	}

	@Override
	public void nuevo() {
		setSalida(new Docsalida());
		getSalida().setIddocsalida(System.nanoTime());
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		docSalidaDAO.crear_editar(getSalida());
		for (DetDocsalida det : getDetDocsalidaL()) {
			detDocsalidaDAO.crear_editar(det);
		}
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void llenar_datos() {

		if (getSalida() instanceof Docsalida && !getEstado().equals("NUEVO")) {
			String numero = StringUtils._padl(getSalida().getNumero(), 8, '0');

			Sucursal sucursal, sucursal_dest;
			Almacen almacen, almacen_dest;

			sucursal = getSalida().getSucursal();
			sucursal_dest = getSalida().getSucursal_dest();

			almacen = getSalida().getAlmacen();
			almacen_dest = getSalida().getAlmacen_dest();

			this.txtNumero_2.setText(numero);
			this.txtSerie.setText(getSalida().getSerie());
			// this.cntGrupoCentralizacion.setSeleccionado(getSalida().getGrupoCentralizacion());
			this.cntGrupoCentralizacion.txtCodigo.setText(getSalida()
					.getGrupoCentralizacion().getIdgcentralizacion());
			this.cntGrupoCentralizacion.llenar();
			// this.cntGrupoCentralizacion.txtDescripcion.setText(getSalida().getGrupoCentralizacion().getDescripcion());

			this.txtTipoCambio
					.setText(String.valueOf(getSalida().getTcambio()));
			this.txtTcMoneda.setText(String.valueOf(getSalida().getTcmoneda()));
			// this.cntMoneda.setSeleccionado(getSalida().getMoneda());
			this.cntMoneda.txtCodigo.setText(getSalida().getMoneda()
					.getIdmoneda());
			this.cntMoneda.llenar();
			// this.cntMoneda.txtDescripcion.setText(getSalida().getMoneda().getDescripcion());
			// this.cntConcepto.setSeleccionado(getSalida().getConcepto());
			this.cntConcepto.txtCodigo.setText(getSalida().getConcepto()
					.getIdconcepto());
			this.cntConcepto.llenar();
			// this.cntConcepto.txtDescripcion.setText(getSalida().getConcepto().getDescripcion());
			if (cntConcepto.getSeleccionado().getEsTransferencia() == 1) {
				cntSucursal_dest.txtCodigo.setEditable(true);
				cntAlmacen_dest.txtCodigo.setEditable(true);
			}
			// this.cntResponsable.setSeleccionado(getSalida().getResponsable());
			this.cntResponsable.txtCodigo.setText(getSalida().getResponsable()
					.getIdresponsable());
			this.cntResponsable.llenar();
			// this.cntResponsable.txtDescripcion.setText(getSalida().getResponsable().getNombre());
			// this.cntSucursal.setSeleccionado(getSalida().getAlmacen().getSucursal());
			if (sucursal == null) {
				this.cntSucursal.txtCodigo.setText("");
				this.cntAlmacen.setData(null);
			} else {
				this.cntSucursal.txtCodigo.setText(sucursal.getIdsucursal());
				this.cntAlmacen.setData(almacenDAO.getPorSucursal(sucursal));
			}
			this.cntSucursal.llenar();

			this.cntAlmacen.txtCodigo.setText((almacen == null) ? "" : almacen
					.getId().getIdalmacen());
			this.cntAlmacen.llenar();
			
			
			if (sucursal_dest == null) {
				this.cntSucursal_dest.txtCodigo.setText("");
				this.cntAlmacen_dest.setData(null);
			} else {
				this.cntSucursal_dest.txtCodigo.setText(sucursal_dest.getIdsucursal());
				this.cntAlmacen_dest.setData(almacenDAO.getPorSucursal(sucursal_dest));
			}
			this.cntSucursal_dest.llenar();

			this.cntAlmacen_dest.txtCodigo.setText((almacen_dest == null) ? "" : almacen_dest
					.getId().getIdalmacen());
			this.cntAlmacen_dest.llenar();
			
			
			this.txtGlosa.setText(getSalida().getGlosa());
			calendar.set(salida.getAnio(), salida.getMes() - 1, salida.getDia());
			this.txtFecha.setDate(calendar.getTime());
			List<DetDocsalida> detDocSalidaL = detDocsalidaDAO
					.getPorIdSalida(getSalida());
			getDetalleTM().limpiar();
			for (DetDocsalida salida : detDocSalidaL) {
				Unimedida unimedida = new UnimedidaDAO().find(salida
						.getIdmedida());
				getDetalleTM().addRow(
						new Object[] { salida.getId().getIdproducto(),
								salida.getDescripcion(),
								unimedida.getIdunimedida(),
								unimedida.getDescripcion(),
								salida.getCantidad(), salida.getPrecio(),
								salida.getImporte() });
			}
		}
	}

	public void CalculaImporte(float cantidad, float precio) {
		float xImporte = cantidad * precio;
		getDetalleTM().setValueAt(xImporte, tblDetalle.getSelectedRow(), 6);
	}
	
	private void actualiza_detalle() {
		int row = tblDetalle.getSelectedRow();
		if (row > -1) {
			float cantidad, precio, importe;
			
			cantidad = Float.parseFloat(getDetalleTM().getValueAt(row, 4)
					.toString());
			precio = Float.parseFloat(getDetalleTM().getValueAt(row, 5).toString());
			
			importe = cantidad * precio;

			getDetalleTM().setValueAt(importe, row, 6);
		}
	}

	@Override
	public void llenar_lista() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_tablas() {
		cntGrupoCentralizacion.setData(new GrupoCentralizacionDAO().findAll());
		cntMoneda.setData(monedaDAO.findAll());
		cntSucursal.setData(new SucursalDAO().findAll());
		cntConcepto.setData(new ConceptoDAO().getPorTipo("S"));
		cntResponsable.setData(new ResponsableDAO().findAll());
		txtProducto.setData(new ProductoDAO().findAll());
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
		this.cntConcepto.txtCodigo.setEditable(true);
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
		this.cntConcepto.txtCodigo.setEditable(false);
		this.cntResponsable.txtCodigo.setEditable(false);
		this.cntSucursal.txtCodigo.setEditable(false);
		this.cntAlmacen.txtCodigo.setEditable(false);
		this.txtTcMoneda.setEditable(false);
		this.txtTipoCambio.setEditable(false);
		this.txtGlosa.setEditable(false);
		this.cntSucursal_dest.txtCodigo.setEditable(false);
		this.cntAlmacen_dest.txtCodigo.setEditable(false);
		getDetalleTM().setEditar(false);
	}

	@Override
	public void init() {

	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public void llenarDesdeVista() {
		Long id = getSalida().getIddocsalida();
		getSalida().setIddocsalida(id);
		getSalida().setGrupoCentralizacion(
				cntGrupoCentralizacion.getSeleccionado());
		getSalida().setTcambio(Float.parseFloat(this.txtTipoCambio.getText()));
		getSalida().setTcmoneda(Float.parseFloat(this.txtTcMoneda.getText()));
		getSalida().setSerie(this.txtSerie.getText());
		getSalida().setNumero(Integer.parseInt(this.txtNumero_2.getText()));
		getSalida().setConcepto(this.cntConcepto.getSeleccionado());
		getSalida().setMoneda(cntMoneda.getSeleccionado());
		getSalida().setResponsable(this.cntResponsable.getSeleccionado());
		getSalida().setSucursal(this.cntSucursal.getSeleccionado());
		getSalida().setAlmacen(this.cntAlmacen.getSeleccionado());
		getSalida().setDia(this.txtFecha.getDate().getDate());
		getSalida().setMes(this.txtFecha.getDate().getMonth() + 1);
		getSalida().setAnio(this.txtFecha.getDate().getYear() + 1900);
		getSalida().setAniomesdia(
				((this.txtFecha.getDate().getYear() + 1900) * 10000)
						+ ((this.txtFecha.getDate().getMonth() + 1) * 100)
						+ this.txtFecha.getDate().getDate());
		getSalida().setGlosa(txtGlosa.getText());
		getSalida().setSucursal_dest(cntSucursal_dest.getSeleccionado());
		getSalida().setAlmacen_dest(cntAlmacen_dest.getSeleccionado());
		setDetDocsalidaL(new ArrayList<DetDocsalida>());
		for (int i = 0; i < getDetalleTM().getRowCount(); i++) {
			DetDocsalidaPK detPK = new DetDocsalidaPK();
			DetDocsalida det = new DetDocsalida();
			detPK.setIdsalida(id);
			detPK.setIdproducto(getDetalleTM().getValueAt(i, 0).toString());
			det.setId(detPK);
			det.setDocsalida(getSalida());
			det.setDescripcion(getDetalleTM().getValueAt(i, 1).toString());
			det.setIdmedida(getDetalleTM().getValueAt(i, 2).toString());
			det.setCantidad(Float.parseFloat((getDetalleTM().getValueAt(i, 4)
					.toString())));
			det.setPrecio(Float.parseFloat(getDetalleTM().getValueAt(i, 5)
					.toString()));
			det.setImporte(Float.parseFloat(getDetalleTM().getValueAt(i, 6)
					.toString()));
			getDetDocsalidaL().add(det);
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

	public List<DetDocsalida> getDetDocsalidaL() {
		return DetDocsalidaL;
	}

	public void setDetDocsalidaL(List<DetDocsalida> detDocsalidaL) {
		DetDocsalidaL = detDocsalidaL;
	}

	public Docsalida getSalida() {
		return salida;
	}

	public void setSalida(Docsalida salida) {
		this.salida = salida;
	}

	@Override
	public void actualiza_objeto(Object id) {
		setSalida(docSalidaDAO.find(id));
		llenar_datos();

		getBarra().enVista();
		vista_noedicion();
	}
}
