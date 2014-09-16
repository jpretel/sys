package vista.formularios;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vista.contenedores.CntConcepto;
import vista.contenedores.TxtProducto;
import vista.contenedores.cntAlmacen;
import vista.contenedores.cntResponsable;
import vista.contenedores.cntSucursal;
import vista.controles.DSGTableModel;
import vista.controles.ManejaNumeros;
import vista.formularios.listas.AbstractDocForm;
import vista.utilitarios.FormValidador;
import vista.utilitarios.StringUtils;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import dao.AlmacenDAO;
import dao.ConceptoDAO;
import dao.DetDocSalidaDAO;
import dao.DocsalidaDAO;
import dao.GrupoCentralizacionDAO;
import dao.MonedaDAO;
import dao.ResponsableDAO;
import dao.SucursalDAO;
import dao.UnimedidaDAO;
import entity.DetDocsalida;
import entity.DetDocsalidaPK;
import entity.Docsalida;
import entity.Unimedida;
import java.awt.GridBagLayout;

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
	private long Id;
	private CntConcepto cntConcepto;
	private cntResponsable cntResponsable;
	private cntSucursal cntSucursal;
	private cntAlmacen cntAlmacen;
	private cntSucursal cntSucursal_dest;
	private cntAlmacen cntAlmacen_dest;
	private JTextArea txtGlosa;
	private Docsalida salida;
	private Calendar calendar = Calendar.getInstance();
	public FrmDocSalida() {
		super("Nota de Salida");		
		JPanel panel = new JPanel();
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
		
		JLabel lblGlosa = new JLabel("Observacion");
		lblGlosa.setBounds(12, 104, 68, 16);
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
					addRow(new Object[] {"","","" ,"",0,0,0});
				else
					JOptionPane.showMessageDialog(null, "Faltan datos en la cabecera");
			}
		});
		
		tblDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPaneDetalle = new JScrollPane(tblDetalle);
		scrollPaneDetalle.setBounds(12, 174, 824, 126);
		
		final TxtProducto txtProducto = new TxtProducto();		
		DefaultCellEditor txtproducto = new DefaultCellEditor(txtProducto);
		tblDetalle.getColumn("IdProducto").setCellEditor(txtproducto);
		
	
			final JTextField nCantidad = new JTextField();
			ManejaNumeros numero = new ManejaNumeros();
			nCantidad.addKeyListener(numero);
			final JTextField nPrecio = new JTextField();
			nPrecio.addKeyListener(numero);
			final JTextField nImporte = new JTextField();	
			nImporte.setEnabled(false);
			DefaultCellEditor nCantidadDE = new DefaultCellEditor(nCantidad);
			DefaultCellEditor nPrecioDE = new DefaultCellEditor(nPrecio);
			DefaultCellEditor nImporteDE = new DefaultCellEditor(nImporte);
			tblDetalle.getColumn("Cantidad").setCellEditor(nCantidadDE);
			tblDetalle.getColumn("Precio").setCellEditor(nPrecioDE);
			tblDetalle.getColumn("Importe").setCellEditor(nImporteDE);		
		
		getDetalleTM().setNombre_detalle("Detalle de Productos");
		getDetalleTM().setObligatorios(0,1,4,5,6);
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
		
		nCantidad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg){
					float xCantidad = 0,xPrecio = 0;
					if (!nCantidad.getText().trim().isEmpty())
						xCantidad = Float.parseFloat(nCantidad.getText());
	 
					if (!nPrecio.getText().trim().isEmpty())		
						xPrecio = Float.parseFloat(nPrecio.getText());
					
					CalculaImporte(xCantidad,xPrecio);					
			}
		});
		
		nPrecio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg){
				float xCantidad = 0,xPrecio = 0;
				if (!nCantidad.getText().trim().isEmpty())
					xCantidad = Float.parseFloat(nCantidad.getText());
 
				if (!nPrecio.getText().trim().isEmpty())		
					xPrecio = Float.parseFloat(nPrecio.getText());
				
				CalculaImporte(xCantidad,xPrecio);
			}
		});
		
		panel.add(scrollPaneDetalle);
		
		cntConcepto = new CntConcepto();
		cntConcepto.txtDescripcion.setBounds(46, 0, 292, 20);
		cntConcepto.setBounds(90, 5, 338, 23);
		panel.add(cntConcepto);
		
		cntConcepto.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent evt){
				if(cntConcepto.txtCodigo.getText().trim().length() > 0 ){
					if(cntConcepto.getSeleccionado().getEsTransferencia() == 1){
						cntSucursal_dest.txtCodigo.setEditable(true);
						cntAlmacen_dest.txtCodigo.setEditable(true);
					}
				}
			}
		});
		
		cntResponsable = new cntResponsable();
		cntResponsable.txtDescripcion.setBounds(46, 0, 263, 20);
		cntResponsable.setBounds(527, 5, 309, 23);
		panel.add(cntResponsable);
		
		cntSucursal = new cntSucursal();
		cntSucursal.btnBuscar.setLocation(90, 3);
		cntSucursal.txtDescripcion.setBounds(46, 0, 293, 20);
		cntSucursal.setBounds(90, 33, 339, 23);
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
				int numero = docSalidaDAO.getPorSerie(serie);
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
		txtGlosa.setBounds(90, 100, 335, 48);
		panel.add(txtGlosa);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(204, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		cntSucursal cntSucursal_ = new cntSucursal();
		GridBagLayout gridBagLayout = (GridBagLayout) cntSucursal_.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{20};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.columnWidths = new int[]{46, 106, 28};
		cntSucursal_.setBounds(90, 67, 339, 23);
		panel.add(cntSucursal_);
		
		JLabel lblSucursalDestino = new JLabel("Sucursal Destino");
		lblSucursalDestino.setBounds(10, 67, 85, 16);
		panel.add(lblSucursalDestino);
		
		JLabel lblAlmacenDestino = new JLabel("Almacen Destino");
		lblAlmacenDestino.setBounds(437, 67, 85, 16);
		panel.add(lblAlmacenDestino);
		
		cntAlmacen cntAlmacen_ = new cntAlmacen();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) cntAlmacen_.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{20};
		gridBagLayout_1.columnWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{46, 106, 28};
		cntAlmacen_.setBounds(527, 67, 309, 23);
		panel.add(cntAlmacen_);
		getContentPane().setLayout(groupLayout);
		iniciar();
	}

	@Override
	public void nuevo() {
		setSalida(new Docsalida());
		Id = System.nanoTime();
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		docSalidaDAO.crear_editar(getSalida());
		for (DetDocsalida det : getDetDocsalidaL()){
			detDocsalidaDAO.crear_editar(det);
		}		
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void llenar_datos() {
		if (getSalida() instanceof Docsalida && !getEstado().equals("NUEVO")){
			String numero = StringUtils._padl(getSalida().getNumero(), 8, '0');
			this.txtNumero_2.setText(numero);
			this.txtSerie.setText(getSalida().getSerie());
			this.cntGrupoCentralizacion.setSeleccionado(getSalida().getGrupoCentralizacion());
			this.cntGrupoCentralizacion.txtCodigo.setText(getSalida().getGrupoCentralizacion().getIdgcentralizacion());
			this.cntGrupoCentralizacion.txtDescripcion.setText(getSalida().getGrupoCentralizacion().getDescripcion());
			this.txtTipoCambio.setText(String.valueOf(getSalida().getTcambio()));
			this.txtTcMoneda.setText(String.valueOf(getSalida().getTcmoneda()));			
			this.cntMoneda.setSeleccionado(getSalida().getMoneda());
			this.cntMoneda.txtCodigo.setText(getSalida().getMoneda().getIdmoneda());
			this.cntMoneda.txtDescripcion.setText(getSalida().getMoneda().getDescripcion());
			this.cntConcepto.setSeleccionado(getSalida().getConcepto());
			this.cntConcepto.txtCodigo.setText(getSalida().getConcepto().getIdconcepto());
			this.cntConcepto.txtDescripcion.setText(getSalida().getConcepto().getDescripcion());
			if(cntConcepto.getSeleccionado().getEsTransferencia() == 1){
				cntSucursal_dest.txtCodigo.setEditable(true);
				cntAlmacen_dest.txtCodigo.setEditable(true);
			}
			this.cntResponsable.setSeleccionado(getSalida().getResponsable());
			this.cntResponsable.txtCodigo.setText(getSalida().getResponsable().getIdresponsable());
			this.cntResponsable.txtDescripcion.setText(getSalida().getResponsable().getNombre());
			this.cntSucursal.setSeleccionado(getSalida().getAlmacen().getSucursal());
			this.cntSucursal.txtCodigo.setText(getSalida().getAlmacen().getSucursal().getIdsucursal());
			this.cntSucursal.txtDescripcion.setText(getSalida().getAlmacen().getSucursal().getDescripcion());
			this.cntAlmacen.setSeleccionado(getSalida().getAlmacen());
			this.cntAlmacen.txtCodigo.setText(getSalida().getAlmacen().getId().getIdalmacen());
			this.cntAlmacen.txtDescripcion.setText(getSalida().getAlmacen().getDescripcion());
			this.cntSucursal_dest.txtCodigo.setText(getSalida().getAlmacen_dest().getSucursal().getIdsucursal());
			this.cntSucursal_dest.txtDescripcion.setText(getSalida().getAlmacen_dest().getSucursal().getDescripcion());
			this.cntAlmacen_dest.txtCodigo.setText(getSalida().getAlmacen_dest().getId().getIdalmacen());
			this.cntAlmacen_dest.txtDescripcion.setText(getSalida().getAlmacen_dest().getDescripcion());			
			this.txtGlosa.setText(getSalida().getGlosa());
			calendar.set(salida.getAnio(), salida.getMes() - 1 , salida.getDia());
			this.txtFecha.setDate(calendar.getTime());
			List<DetDocsalida> detDocSalidaL = detDocsalidaDAO.getPorIdSalida(getSalida());
			getDetalleTM().limpiar();
			for(DetDocsalida salida : detDocSalidaL){
				Unimedida unimedida = new UnimedidaDAO().find(salida.getIdmedida()); 
				getDetalleTM().addRow(new Object[]{salida.getId().getIdproducto(),salida.getDescripcion(),unimedida.getIdunimedida(),
						unimedida.getDescripcion(),salida.getCantidad(),salida.getPrecio(),salida.getPrecio()});				
			}
		}
	}
	
	public void CalculaImporte(float cantidad,float precio){
		float xImporte = cantidad * precio;
		getDetalleTM().setValueAt(xImporte, tblDetalle.getSelectedRow(), 6);
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
		cntSucursal_dest.txtCodigo.setEditable(false);
		cntAlmacen_dest.txtCodigo.setEditable(false);
		getDetalleTM().setEditar(false);
	}

	@Override
	public void init() {

	}

	@Override
	public void actualiza_objeto(Object entidad) {
		setSalida((Docsalida) entidad);
		Id = getSalida().getIddocsalida();
		iniciar();
	}


	@SuppressWarnings({ "deprecation"})
	@Override
	public void llenarDesdeVista() {
		getSalida().setIddocsalida(Id);
		getSalida().setGrupoCentralizacion(cntGrupoCentralizacion.getSeleccionado());
		getSalida().setTcambio(Float.parseFloat(this.txtTipoCambio.getText()));
		getSalida().setTcmoneda(Float.parseFloat(this.txtTcMoneda.getText()));
		getSalida().setSerie(this.txtSerie.getText());
		getSalida().setNumero(Integer.parseInt(this.txtNumero_2.getText()));
		getSalida().setConcepto(this.cntConcepto.getSeleccionado());
		getSalida().setMoneda(cntMoneda.getSeleccionado());
		getSalida().setResponsable(this.cntResponsable.getSeleccionado());
		getSalida().setAlmacen(this.cntAlmacen.getSeleccionado());
		getSalida().setDia(this.txtFecha.getDate().getDate());
		getSalida().setMes(this.txtFecha.getDate().getMonth() + 1);
		getSalida().setAnio(this.txtFecha.getDate().getYear() + 1900);
		getSalida().setAniomesdia(((this.txtFecha.getDate().getYear() + 1900) * 10000)+((this.txtFecha.getDate().getMonth() + 1) *100)+this.txtFecha.getDate().getDate());
		getSalida().setGlosa(txtGlosa.getText());
		getSalida().setAlmacen_dest(cntAlmacen_dest.getSeleccionado());
		setDetDocsalidaL(new ArrayList<DetDocsalida>());
		for(int i = 0;i < getDetalleTM().getRowCount();i++){
			DetDocsalidaPK detPK = new DetDocsalidaPK();
			DetDocsalida det = new DetDocsalida();
			detPK.setIdsalida(Id);
			detPK.setIdproducto(getDetalleTM().getValueAt(i, 0).toString());
			det.setId(detPK);
			det.setDescripcion(getDetalleTM().getValueAt(i, 1).toString()); 
			det.setIdmedida(getDetalleTM().getValueAt(i, 2).toString());
			det.setCantidad(Float.parseFloat((getDetalleTM().getValueAt(i, 4).toString())));
			det.setPrecio(Float.parseFloat(getDetalleTM().getValueAt(i, 5).toString()));
			det.setImporte(Float.parseFloat(getDetalleTM().getValueAt(i, 6).toString()));
			getDetDocsalidaL().add(det);
		}
	}

	@Override
	public boolean isValidaVista() {
		boolean band = validaCabecera();
		if(!band){
			band = validarDetalle();
		}
		return band;
	}
	
	public boolean validaCabecera(){
		return  FormValidador.TextFieldObligatorios(this.cntGrupoCentralizacion.txtCodigo,this.cntMoneda.txtCodigo,
				this.txtTipoCambio,this.txtTcMoneda,this.cntConcepto.txtCodigo,this.cntResponsable.txtCodigo,
				this.cntSucursal.txtCodigo,this.cntAlmacen.txtCodigo);
	}
	
	public boolean validarDetalle(){
		return getDetalleTM().esValido();
	}
	
	public DSGTableModel getDetalleTM(){
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
}
