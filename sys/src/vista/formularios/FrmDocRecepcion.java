package vista.formularios;

import vista.contenedores.TxtProducto;
import vista.contenedores.cntAlmacen;
import vista.contenedores.cntMotivo;
import vista.contenedores.cntResponsable;
import vista.contenedores.cntSucursal;
import vista.controles.DSGTableModel;
import vista.formularios.listas.AbstractDocForm;
import vista.utilitarios.editores.TableTextEditor;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.TableColumnModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class FrmDocRecepcion extends AbstractDocForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private cntMotivo cntmotivo;
	private cntResponsable cntresponsable;
	private cntSucursal cntsucursal;
	private cntAlmacen cntalmacen;
	private JTable tblDetalle;
	private JScrollPane scrollPaneDetalle;
	public FrmDocRecepcion() {
		super("Nota de Ingreso");
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(87)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(10, 11, 46, 14);
		panel.add(lblConcepto);
		
		cntmotivo = new cntMotivo();
		cntmotivo.setBounds(66, 11, 222, 21);
		panel.add(cntmotivo);
		
		JLabel lblResponsable = new JLabel("Responsable");
		lblResponsable.setBounds(298, 11, 71, 14);
		panel.add(lblResponsable);
		
		cntresponsable = new cntResponsable();
		cntresponsable.setBounds(379, 11, 222, 21);
		panel.add(cntresponsable);
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(10, 44, 46, 14);
		panel.add(lblSucursal);
		
		cntsucursal = new cntSucursal();
		cntsucursal.setBounds(66, 44, 222, 21);
		panel.add(cntsucursal);
		
		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(298, 44, 71, 14);
		panel.add(lblAlmacen);
		
		cntalmacen = new cntAlmacen();
		cntalmacen.setBounds(379, 44, 222, 21);
		panel.add(cntalmacen);
		
		JLabel lblGlosa = new JLabel("Glosa");
		lblGlosa.setBounds(10, 79, 46, 14);
		panel.add(lblGlosa);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(66, 76, 222, 66);
		panel.add(textArea);		
		 
		tblDetalle = new JTable(new DSGTableModel(new String[]{"Item","IdProducto","Producto","IdMedida","Medida","Cantidad","Precio","Importe"}){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean evaluaEdicion(int row, int column) {
				return getEditar();
			}
			@Override
			public void addRow() {
				addRow(new Object[] { "", "","","" ,"",0.00,0.00,0.00});				
			}
		});
		
		tblDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPaneDetalle = new JScrollPane(tblDetalle);
		scrollPaneDetalle.setBounds(10, 153, 591, 154);
		
		final TxtProducto txtProducto = new TxtProducto();		
		DefaultCellEditor txtproducto = new DefaultCellEditor(txtProducto);
		tblDetalle.getColumn("IdProducto").setCellEditor(txtproducto);
		
		getDetalleTM().setNombre_detalle("Detalle de Productos");
		getDetalleTM().setRepetidos(0);
		getDetalleTM().setScrollAndTable(scrollPaneDetalle, tblDetalle);
		
		TableColumnModel cModel = tblDetalle.getColumnModel();
		cModel.getColumn(0).setCellEditor(new TableTextEditor(3, true));
		
		panel.add(scrollPaneDetalle);
		getContentPane().setLayout(groupLayout);
	}

	@Override
	public void nuevo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_datos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_lista() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_tablas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void vista_edicion() {
		this.txtCorrrelativo.setEditable(true);
		this.txtTcMoneda.setEditable(true);
		this.txtTipoCambio.setEditable(true);
		this.cntmotivo.txtCodigo.setEditable(true);
		this.cntresponsable.txtCodigo.setEditable(true);
		this.cntsucursal.txtCodigo.setEditable(true);
		this.cntalmacen.txtCodigo.setEditable(true);
		getDetalleTM().setEditar(true);
	}

	@Override
	public void vista_noedicion() {
		this.txtCorrrelativo.setEditable(false);
		this.txtTcMoneda.setEditable(false);
		this.txtTipoCambio.setEditable(false);
		this.cntmotivo.txtCodigo.setEditable(false);
		this.cntresponsable.txtCodigo.setEditable(false);
		this.cntsucursal.txtCodigo.setEditable(false);
		this.cntalmacen.txtCodigo.setEditable(false);
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

	}

	@Override
	public boolean isValidaVista() {
		
		return false;
	}
	
	public DSGTableModel getDetalleTM(){
		return ((DSGTableModel) tblDetalle.getModel());
	}
}
