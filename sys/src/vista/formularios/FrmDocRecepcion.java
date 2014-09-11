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
import dao.GrupoCentralizacionDAO;
import dao.MonedaDAO;
import dao.ProductoDAO;
import dao.ResponsableDAO;
import dao.SucursalDAO;
import dao.UnimedidaDAO;
import entity.DetDocingreso;
import entity.DetDocingresoPK;
import entity.Docingreso;
import entity.Unimedida;

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
	private long Id;
	private CntConcepto cntConcepto;
	private cntResponsable cntResponsable;
	private cntSucursal cntSucursal;
	private cntAlmacen cntAlmacen;
	private JTextArea txtGlosa;
	private Docingreso ingreso;
	
	public FrmDocRecepcion() {
		super("Nota de Ingreso");		
	
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(109)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(12, 5, 54, 16);
		panel.add(lblConcepto);
		
		JLabel lblResponsable = new JLabel("Responsable");
		lblResponsable.setBounds(437, 5, 74, 16);
		panel.add(lblResponsable);
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(12, 33, 51, 16);
		panel.add(lblSucursal);
		
		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(437, 33, 50, 16);
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
		scrollPaneDetalle.setBounds(12, 119, 824, 170);
		
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
		cntConcepto.txtDescripcion.setBounds(46, 0, 292, 20);
		cntConcepto.setBounds(81, 5, 338, 23);
		panel.add(cntConcepto);
		
		cntResponsable = new cntResponsable();
		cntResponsable.txtDescripcion.setBounds(46, 0, 263, 20);
		cntResponsable.setBounds(527, 5, 309, 23);
		panel.add(cntResponsable);
		
		cntSucursal = new cntSucursal();
		cntSucursal.txtDescripcion.setBounds(46, 0, 293, 20);
		cntSucursal.setBounds(80, 33, 339, 23);
		panel.add(cntSucursal);
		
		cntAlmacen = new cntAlmacen();
		cntAlmacen.txtDescripcion.setBounds(46, 0, 263, 20);
		cntAlmacen.setBounds(527, 33, 309, 23);
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
				int numero = docIngresoDAO.getPorSerie(serie);
				numero = numero + 1;
				if(numero > 0){
					txtNumero_2.setText(String.valueOf(numero));
					txtNumero_2.requestFocus();
					txtFecha.requestFocus();
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
		txtGlosa.setBounds(81, 61, 338, 48);
		panel.add(txtGlosa);
		getContentPane().setLayout(groupLayout);
		iniciar();
	}

	@Override
	public void nuevo() {
		setIngreso(new Docingreso());
		Id = System.nanoTime();
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		docIngresoDAO.crear_editar(getIngreso());
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
		if (getIngreso() instanceof Docingreso && !getEstado().equals("NUEVO")){		
			this.txtNumero_2.setText(String.valueOf(getIngreso().getNumero()));
			this.txtSerie.setText(getIngreso().getSerie());
			this.cntGrupoCentralizacion.setSeleccionado(getIngreso().getGrupoCentralizacion());
			this.cntGrupoCentralizacion.txtCodigo.setText(getIngreso().getGrupoCentralizacion().getIdgcentralizacion());
			this.cntGrupoCentralizacion.txtDescripcion.setText(getIngreso().getGrupoCentralizacion().getDescripcion());
			this.cntMoneda.setSeleccionado(getIngreso().getMoneda());
			this.cntMoneda.txtCodigo.setText(getIngreso().getMoneda().getIdmoneda());
			this.cntMoneda.txtDescripcion.setText(getIngreso().getMoneda().getDescripcion());
			this.cntConcepto.setSeleccionado(getIngreso().getConcepto());
			this.cntConcepto.txtCodigo.setText(getIngreso().getConcepto().getIdconcepto());
			this.cntConcepto.txtDescripcion.setText(getIngreso().getConcepto().getDescripcion());
			this.cntResponsable.setSeleccionado(getIngreso().getResponsable());
			this.cntResponsable.txtCodigo.setText(getIngreso().getResponsable().getIdresponsable());
			this.cntResponsable.txtDescripcion.setText(getIngreso().getResponsable().getNombre());
			this.cntSucursal.setSeleccionado(getIngreso().getAlmacen().getSucursal());
			this.cntSucursal.txtCodigo.setText(getIngreso().getAlmacen().getSucursal().getIdsucursal());
			this.cntSucursal.txtDescripcion.setText(getIngreso().getAlmacen().getSucursal().getDescripcion());
			this.cntAlmacen.setSeleccionado(getIngreso().getAlmacen());
			this.cntAlmacen.txtCodigo.setText(getIngreso().getAlmacen().getId().getIdalmacen());
			this.cntAlmacen.txtDescripcion.setText(getIngreso().getAlmacen().getDescripcion());
			List<DetDocingreso> detDocIngresoL = detDocingresoDAO.getPorIdIngreso(getIngreso());
			for(DetDocingreso ingreso : detDocIngresoL){
				Unimedida unimedida = new UnimedidaDAO().find(ingreso.getIdmedida()); 
				getDetalleTM().addRow(new Object[]{ingreso.getId().getIdproducto(),ingreso.getDescripcion(),unimedida.getIdunimedida(),
						unimedida.getDescripcion(),ingreso.getCantidad(),ingreso.getPrecio(),ingreso.getPrecio()});				
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
		getDetalleTM().setEditar(false);
	}

	@Override
	public void init() {

	}

	@Override
	public void actualiza_objeto(Object entidad) {
		setIngreso((Docingreso) entidad);
		Id = getIngreso().getIddocingreso();
		iniciar();
	}


	@SuppressWarnings({ "deprecation"})
	@Override
	public void llenarDesdeVista() {
		getIngreso().setIddocingreso(Id);
		getIngreso().setGrupoCentralizacion(cntGrupoCentralizacion.getSeleccionado());
		getIngreso().setTcambio(Float.parseFloat(this.txtTipoCambio.getText()));
		getIngreso().setTcmoneda(Float.parseFloat(this.txtTcMoneda.getText()));
		getIngreso().setSerie(this.txtSerie.getText());
		getIngreso().setNumero(Integer.parseInt(this.txtNumero_2.getText()));
		getIngreso().setConcepto(this.cntConcepto.getSeleccionado());
		getIngreso().setMoneda(cntMoneda.getSeleccionado());
		getIngreso().setResponsable(this.cntResponsable.getSeleccionado());
		getIngreso().setAlmacen(this.cntAlmacen.getSeleccionado());
		getIngreso().setDia(txtFecha.getDate().getDay());
		getIngreso().setMes(this.txtFecha.getDate().getMonth());
		getIngreso().setAnio(this.txtFecha.getDate().getYear());
		getIngreso().setGlosa(txtGlosa.getText());
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

	public Docingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Docingreso ingreso) {
		this.ingreso = ingreso;
	}
}
