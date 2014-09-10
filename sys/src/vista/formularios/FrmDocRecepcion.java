package vista.formularios;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import vista.contenedores.CntConcepto;
import vista.contenedores.TxtProducto;
import vista.contenedores.cntAlmacen;
import vista.contenedores.cntResponsable;
import vista.contenedores.cntSucursal;
import vista.controles.DSGTableModel;
import vista.formularios.listas.AbstractDocForm;

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
import dao.ConceptoDAO;
import dao.DetDocIngresoDAO;
import dao.DocingresoDAO;
import dao.DocumentoDAO;
import dao.DocumentoNumeroDAO;
import dao.GrupoCentralizacionDAO;
import dao.MonedaDAO;
import dao.ResponsableDAO;
import dao.SucursalDAO;
import entity.DetDocingreso;
import entity.DetDocingresoPK;
import entity.Docingreso;
import entity.Documento;
import entity.DocumentoNumeroPK;
import entity.Moneda;

public class FrmDocRecepcion extends AbstractDocForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblDetalle;
	private JScrollPane scrollPaneDetalle;
	private Docingreso docIngreso;
	private List<DetDocingreso> DetDocingresoL;
	private DocingresoDAO docIngresoDAO = new DocingresoDAO();
	private DetDocIngresoDAO detDocingresoDAO = new DetDocIngresoDAO();
	private long Id;
	private CntConcepto cntConcepto;
	private cntResponsable cntResponsable;
	private cntSucursal cntSucursal;
	private cntAlmacen cntAlmacen;
	private JTextArea txtGlosa;
	public FrmDocRecepcion() {
		super("Nota de Ingreso");		
	
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(117, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(null);
		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(12, 5, 54, 16);
		panel.add(lblConcepto);
		
		JLabel lblResponsable = new JLabel("Responsable");
		lblResponsable.setBounds(400, 5, 74, 16);
		panel.add(lblResponsable);
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(12, 33, 51, 16);
		panel.add(lblSucursal);
		
		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(400, 33, 50, 16);
		panel.add(lblAlmacen);
		
		JLabel lblGlosa = new JLabel("Glosa");
		lblGlosa.setBounds(12, 61, 32, 16);
		panel.add(lblGlosa);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(540, 5, 0, 16);
		panel.add(textArea);		
		 
		tblDetalle = new JTable(new DSGTableModel(new String[]{"IdProducto","Producto","IdMedida","Medida","Cantidad","Precio","Importe"}){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean evaluaEdicion(int row, int column) {
				if(column != 1 || column != 3)
					return getEditar();
				else
					return false;
			}
			@Override
			public void addRow() {
				if (validaCabecera())
					addRow(new Object[] {"","","" ,"",0.00,0.00,0.00});
				else
					JOptionPane.showMessageDialog(null, "Faltan datos en la cabecera");
			}
		});
		
		tblDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPaneDetalle = new JScrollPane(tblDetalle);
		scrollPaneDetalle.setBounds(12, 119, 747, 162);
		
		final TxtProducto txtProducto = new TxtProducto();		
		DefaultCellEditor txtproducto = new DefaultCellEditor(txtProducto);
		tblDetalle.getColumn("IdProducto").setCellEditor(txtproducto);
		
		getDetalleTM().setNombre_detalle("Detalle de Productos");
		getDetalleTM().setRepetidos(0);
		getDetalleTM().setScrollAndTable(scrollPaneDetalle, tblDetalle);		
		
		txtProducto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtProducto.getText().trim().length() > 0){
					getDetalleTM().setValueAt(txtProducto.getSeleccionado().getDescripcion(), tblDetalle.getSelectedRow(), tblDetalle.getSelectedColumn());
					getDetalleTM().setValueAt(txtProducto.getSeleccionado().getUnimedida().getIdunimedida(), tblDetalle.getSelectedRow(), tblDetalle.getSelectedColumn() + 1);
					getDetalleTM().setValueAt(txtProducto.getSeleccionado().getUnimedida().getDescripcion(), tblDetalle.getSelectedRow(), tblDetalle.getSelectedColumn() + 2);
				}
			}
		});
		
		panel.add(scrollPaneDetalle);
		
		cntConcepto = new CntConcepto();
		cntConcepto.txtDescripcion.setBounds(46, 0, 239, 20);
		cntConcepto.setBounds(81, 5, 285, 23);
		panel.add(cntConcepto);
		
		cntResponsable = new cntResponsable();
		cntResponsable.txtDescripcion.setBounds(46, 0, 223, 20);
		cntResponsable.setBounds(490, 5, 269, 23);
		panel.add(cntResponsable);
		
		cntSucursal = new cntSucursal();
		cntSucursal.txtDescripcion.setBounds(46, 0, 240, 20);
		cntSucursal.setBounds(80, 33, 286, 23);
		panel.add(cntSucursal);
		
		cntAlmacen = new cntAlmacen();
		cntAlmacen.txtDescripcion.setBounds(46, 0, 223, 20);
		cntAlmacen.setBounds(490, 33, 269, 23);
		panel.add(cntAlmacen);
		
		txtSerie.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg){
				String serie = txtSerie.getText().trim();
				if(serie.length() > 0){					
					String texto = org.codehaus.plexus.util.StringUtils.repeat("0",4 - serie.length()) + serie;
					txtSerie.setText(texto);
					actualizaNumero(txtSerie.getText());
				}
			}

			private void actualizaNumero(String serie) {
				DocumentoDAO documentoDAO = new DocumentoDAO();
				Documento documento = documentoDAO.getPorDocumento("FrmListaRecepcion");
				DocumentoNumeroDAO documentoNumeroDAO = new DocumentoNumeroDAO();
				DocumentoNumeroPK id = documentoNumeroDAO.getPorDocumento(documento).getId();
				if(id.getSerie().equals(serie)){
					String documentoN = documentoNumeroDAO.getPorDocumento1(documento, id);
					txtNumero_2.setText(documentoN);
				}
			}
		});
		
		cntSucursal.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(cntSucursal.txtCodigo.getText().trim().length() > 0){
					cntAlmacen.setData(new AlmacenDAO().getPorSucursal(cntSucursal.getSeleccionado()));
				}
			}
		});
		
		txtGlosa = new JTextArea();
		txtGlosa.setBounds(81, 61, 285, 48);
		panel.add(txtGlosa);
		getContentPane().setLayout(groupLayout);
		iniciar();
	}

	@Override
	public void nuevo() {
		docIngreso = new Docingreso();
		docIngreso.setMoneda(new Moneda());
		Id = System.nanoTime();
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		docIngresoDAO.crear_editar(docIngreso);
		for (DetDocingreso det : getDetDocingresoL()){
			detDocingresoDAO.crear_editar(det);
		}
		
		
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void llenar_datos() {
		if (docIngreso instanceof Docingreso){		
			this.txtNumero_2.setText(String.valueOf(docIngreso.getNumero()));
			this.txtSerie.setText(docIngreso.getSerie());
			this.cntMoneda.txtCodigo.setText(docIngreso.getMoneda().getIdmoneda());
			this.cntConcepto.txtCodigo.setText(docIngreso.getIdmotivo());
			this.cntResponsable.txtCodigo.setText(docIngreso.getIdresponsable());
			this.cntSucursal.txtCodigo.setText(docIngreso.getIdsucursal());
			this.cntAlmacen.txtCodigo.setText(docIngreso.getIdalmacen());
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
	}

	@Override
	public void vista_edicion() {
		this.txtSerie.setEditable(true);
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
		docIngreso.setSerie(this.txtSerie.getText());
		docIngreso.setNumero(Integer.parseInt(this.txtNumero_2.getText()));
		docIngreso.setIdmotivo(this.cntConcepto.txtCodigo.getText());
		Moneda moneda = new MonedaDAO().find(this.cntMoneda.txtCodigo.getText());
		docIngreso.setMoneda(moneda);
		docIngreso.setIdresponsable(this.cntResponsable.txtCodigo.getText());
		docIngreso.setIdsucursal(this.cntSucursal.txtCodigo.getText());
		docIngreso.setIdalmacen(this.cntAlmacen.txtCodigo.getText());
		docIngreso.setDia(txtFecha.getDate().getDay());
		docIngreso.setMes(this.txtFecha.getDate().getMonth());
		docIngreso.setAnio(this.txtFecha.getDate().getYear());
		docIngreso.setGlosa(txtGlosa.getText());
		setDetDocingresoL(new ArrayList<DetDocingreso>());
		for(int i = 0;i < getDetalleTM().getRowCount();i++){
			DetDocingresoPK detPK = new DetDocingresoPK();
			DetDocingreso det = new DetDocingreso();
			detPK.setIdingreso(Id);
			detPK.setIdproducto(getDetalleTM().getValueAt(i, 0).toString());
			det.setId(detPK);
			det.setDescripcion(getDetalleTM().getValueAt(i, 1).toString());
			det.setIdmedida(getDetalleTM().getValueAt(i, 2).toString());
			det.setCantidad(Float.parseFloat((getDetalleTM().getValueAt(i, 4).toString())));
			det.setPrecio(Float.parseFloat(getDetalleTM().getValueAt(i, 5).toString()));
			det.setImporte(Float.parseFloat(getDetalleTM().getValueAt(i, 6).toString()));
			DetDocingresoL.add(det);
		}
	}

	@Override
	public boolean isValidaVista() {
		boolean band = validaCabecera();
		return band;
	}
	
	public boolean validaCabecera(){
		if (this.cntGrupoCentralizacion.txtCodigo.getText().isEmpty())
			return false;
		
		if (this.cntMoneda.txtCodigo.getText().isEmpty())
			return false;
		
		if (this.txtTipoCambio.getText().isEmpty())
			return false;
		
		if (this.txtTcMoneda.getText().isEmpty())
			return false;
		
		if (this.cntConcepto.txtCodigo.getText().isEmpty())
			return false;
		
		if (this.cntResponsable.txtCodigo.getText().isEmpty())
			return false;
		
		if (this.cntSucursal.txtCodigo.getText().isEmpty())
			return false;
		
		if (this.cntAlmacen.txtCodigo.getText().isEmpty())
			return false;
		
		return true;
	}
	
	public DSGTableModel getDetalleTM(){
		return ((DSGTableModel) tblDetalle.getModel());
	}
	
	public List<DetDocingreso> getDetDocingresoL() {
		return DetDocingresoL;
	}

	public void setDetDocingresoL(List<DetDocingreso> detDocingresoL) {
		DetDocingresoL = detDocingresoL;
	}
}
