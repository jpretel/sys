package vista.formularios;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vista.contenedores.TxtDocFormulario;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import dao.DetDocIngresoDAO;
import dao.DocingresoDAO;
import entity.DetDocingreso;
import entity.DetDocingresoPK;
import entity.DocFormulario;
import entity.Docingreso;

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
	private Docingreso docIngreso;
	private List<DetDocingreso> DetDocingresoL;
	private DocingresoDAO docIngresoDAO = new DocingresoDAO();
	private DetDocIngresoDAO detDocingresoDAO = new DetDocIngresoDAO(); 
	private long Id;
	public List<DetDocingreso> getDetDocingresoL() {
		return DetDocingresoL;
	}

	public void setDetDocingresoL(List<DetDocingreso> detDocingresoL) {
		DetDocingresoL = detDocingresoL;
	}

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
		
		cntalmacen.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				actualizaSucursal();
			}

			private void actualizaSucursal() {
				cntalmacen.setSucursal(cntsucursal.getSeleccionado());
				cntalmacen.refrescar();				
			}
		});
		
		JLabel lblGlosa = new JLabel("Glosa");
		lblGlosa.setBounds(10, 79, 46, 14);
		panel.add(lblGlosa);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(66, 76, 222, 66);
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
		scrollPaneDetalle.setBounds(10, 153, 591, 154);
		
		final TxtProducto txtProducto = new TxtProducto();		
		DefaultCellEditor txtproducto = new DefaultCellEditor(txtProducto);
		tblDetalle.getColumn("IdProducto").setCellEditor(txtproducto);
		
		getDetalleTM().setNombre_detalle("Detalle de Productos");
		getDetalleTM().setRepetidos(0);
		getDetalleTM().setScrollAndTable(scrollPaneDetalle, tblDetalle);
		
		/*TableColumnModel cModel = tblDetalle.getColumnModel();
		cModel.getColumn(0).setCellEditor(new TableTextEditor(15, true));*/
		
		
		txtProducto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				getDetalleTM().setValueAt(txtProducto.getSeleccionado().getDescripcion(), tblDetalle.getSelectedRow(), tblDetalle.getSelectedColumn());
				getDetalleTM().setValueAt(txtProducto.getSeleccionado().getUnimedida().getIdunimedida(), tblDetalle.getSelectedRow(), tblDetalle.getSelectedColumn() + 1);
				getDetalleTM().setValueAt(txtProducto.getSeleccionado().getUnimedida().getDescripcion(), tblDetalle.getSelectedRow(), tblDetalle.getSelectedColumn() + 2);
			}
		});
		
		panel.add(scrollPaneDetalle);
		getContentPane().setLayout(groupLayout);
	}

	@Override
	public void nuevo() {
		docIngreso = new Docingreso();
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
			this.txtCorrrelativo.setText(docIngreso.getCorrelativo());
			this.datePicker.setDate(docIngreso.getFecha());
			this.cntmotivo.txtCodigo.setText(docIngreso.getIdmotivo());
			this.cntresponsable.txtCodigo.setText(docIngreso.getIdresponsable());
			this.cntalmacen.txtCodigo.setText(docIngreso.getIdalmacen());
		}
		if(getEstado().equals(NUEVO)){
			TxtDocFormulario txtDocFormulario = new TxtDocFormulario();
			txtCorrrelativo.setText(txtDocFormulario.getCorrelativo("FrmListaRecepcion"));
		
			Date xfecha = new Date();
	        datePicker.setDate(xfecha);
		}
	}

	@Override
	public void llenar_lista() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_tablas() {		
	}

	@Override
	public void vista_edicion() {
		this.txtCorrrelativo.setEditable(true);
		this.datePicker.setEditable(true);
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
		this.datePicker.setEditable(false);
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
		docIngreso.setIddocingreso(Id);
		docIngreso.setCorrelativo(this.txtCorrrelativo.getText());
		docIngreso.setSerie(this.txtCorrrelativo.getText().substring(0, 3));
		docIngreso.setNumero(this.txtCorrrelativo.getText().substring(5, this.txtCorrrelativo.getText().trim().length()));
		docIngreso.setFecha(this.datePicker.getDate());
		docIngreso.setIdmotivo(this.cntmotivo.txtCodigo.getText());
		docIngreso.setIdresponsable(this.cntresponsable.txtCodigo.getText());
		docIngreso.setIdsucursal(this.cntsucursal.txtCodigo.getText());
		docIngreso.setIdalmacen(this.cntalmacen.txtCodigo.getText());
		docIngreso.setGlosa("");
		setDetDocingresoL(new ArrayList<DetDocingreso>());
		for(int i = 0;i < getDetalleTM().getRowCount();i++){
			DetDocingresoPK detPK = new DetDocingresoPK();
			DetDocingreso det = new DetDocingreso();
			detPK.setIdingreso(Id);
			detPK.setIdproducto(getDetalleTM().getValueAt(i, 0).toString());
			det.setId(detPK);
			det.setDescripcion(getDetalleTM().getValueAt(i, 1).toString());
			det.setIdmedida(getDetalleTM().getValueAt(i, 2).toString());
			det.setCantidad(BigDecimal.valueOf(Double.parseDouble((getDetalleTM().getValueAt(i, 4).toString()))));
			det.setPrecio(BigDecimal.valueOf(Double.parseDouble((getDetalleTM().getValueAt(i, 5).toString()))));
			det.setImporte(BigDecimal.valueOf(Double.parseDouble((getDetalleTM().getValueAt(i, 6).toString()))));
			DetDocingresoL.add(det);
		}
	}

	@Override
	public boolean isValidaVista() {
		boolean band = validaCabecera();
		return band;
	}
	
	public boolean validaCabecera(){
		if (this.cntmotivo.txtCodigo.getText().isEmpty())
			return false;
		
		if (this.cntresponsable.txtCodigo.getText().isEmpty())
			return false;
		
		if (this.cntsucursal.txtCodigo.getText().isEmpty())
			return false;
		
		if (this.cntalmacen.txtCodigo.getText().isEmpty())
			return false;
		
		return true;
	}
	
	public DSGTableModel getDetalleTM(){
		return ((DSGTableModel) tblDetalle.getModel());
	}
}
