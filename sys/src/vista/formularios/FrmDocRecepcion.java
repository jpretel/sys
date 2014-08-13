package vista.formularios;

import vista.contenedores.cntAlmacen;
import vista.contenedores.cntClieprov;
import vista.contenedores.cntMotivo;
import vista.contenedores.cntSucursal;
import vista.contenedores.txtidformulario;
import vista.contenedores.txtidproducto;
import vista.controles.DSGTableModel;
import vista.formularios.listas.AbstractDocForm;

import javax.swing.JLabel;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.mysql.jdbc.StringUtils;

import dao.DocumentoNumeroDAO;
import dao.MonedaDAO;
import entity.Moneda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDocRecepcion extends AbstractDocForm {
	private static final long serialVersionUID = 1L;
	private JTextField txtDestino;
	private JTextField txtContacto;
	private cntMotivo cntmotivo;
	private cntClieprov cntclieprov;
	private cntSucursal cntsucursal;
	private cntAlmacen cntalmacen;
	private JTable tblDetalle;
	private DocumentoNumeroDAO documentNumeroDAO= new DocumentoNumeroDAO(); 
	private vista.combobox.ComboBox cboCondicionPango;
	private List<String[]> condicionPagoL = new ArrayList<String[]>();
	private vista.combobox.ComboBox cboMonedas;
	private List<String[]> monedaL = new ArrayList<String[]>();
	private MonedaDAO monedaDAO= new MonedaDAO();
	private List<Moneda> emonedaL = monedaDAO.findAll();
	
	public FrmDocRecepcion() {
		super("Nota de Ingreso");
		txtCorrrelativo.setLocation(87, 8);
		getBarra().setBounds(0, 0, 594, 30);
		getContentPane().setLayout(null);
		
		JLabel lblRazonSocial = new JLabel("Razon Social");
		lblRazonSocial.setBounds(10, 93, 70, 14);
		getContentPane().add(lblRazonSocial);
		
		cntclieprov = new cntClieprov();
		cntclieprov.setBounds(87, 93, 267, 20);
		getContentPane().add(cntclieprov);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(10, 119, 70, 14);
		getContentPane().add(lblDestino);
		
		txtDestino = new JTextField();
		txtDestino.setBounds(87, 119, 267, 20);
		getContentPane().add(txtDestino);
		txtDestino.setColumns(10);
		
		JLabel lblCondicionPago = new JLabel("Condicion Pago");
		lblCondicionPago.setBounds(10, 173, 73, 14);
		getContentPane().add(lblCondicionPago);
		
		condicionPagoL.add(new String[]{"01","Contado"});
		condicionPagoL.add(new String[]{"02","Credito"});
		cboCondicionPango = new vista.combobox.ComboBox(condicionPagoL,1);		
		cboCondicionPango.setBounds(84, 173, 99, 20);
		getContentPane().add(cboCondicionPango);
		
		JLabel lblContacto = new JLabel("Contacto");
		lblContacto.setBounds(364, 93, 49, 14);
		getContentPane().add(lblContacto);
		
		txtContacto = new JTextField();
		txtContacto.setColumns(10);
		txtContacto.setBounds(415, 93, 169, 20);
		getContentPane().add(txtContacto);
		
		JLabel lblMoneda = new JLabel("Moneda");
		lblMoneda.setBounds(204, 173, 38, 14);
		getContentPane().add(lblMoneda);
		
		for (Moneda moneda : emonedaL){
			monedaL.add(new String[]{moneda.getIdmoneda(),moneda.getDescripcion()});
		}		
		cboMonedas = new vista.combobox.ComboBox(monedaL,1);
		cboMonedas.setBounds(252, 173, 99, 20);
		getContentPane().add(cboMonedas);
		
		JLabel lblMotivo = new JLabel("Concepto");
		lblMotivo.setBounds(10, 145, 61, 14);
		getContentPane().add(lblMotivo);
		
		cntmotivo = new cntMotivo();
		cntmotivo.setBounds(87, 145, 267, 20);
		getContentPane().add(cntmotivo);
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(364, 119, 49, 14);
		getContentPane().add(lblSucursal);
		
		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(364, 145, 49, 14);
		getContentPane().add(lblAlmacen);
		
		cntsucursal = new cntSucursal();
		cntsucursal.setBounds(415, 119, 180, 20);
		getContentPane().add(cntsucursal);
		
		cntalmacen = new cntAlmacen();
		cntalmacen.setBounds(415, 145, 180, 20);
		getContentPane().add(cntalmacen);
		
		cntalmacen.txtCodigo.addFocusListener(new FocusAdapter() {
				@Override	
				public void focusGained(FocusEvent arg0){
					actualizaSucursal();
				}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 208, 574, 143);		

		tblDetalle = new JTable(new DSGTableModel(new String[] {"Item", "IdProducto", "Producto","U.M. ","Cantidad","P.Unitario","Dscto %","V.Venta","Importe"}) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean evaluaEdicion(int row, int column) {
				if(column == 0 || column == 2 || column == 3){
					return false;
				}else{
					return getEditar();
				}
				
			}
		});
		
		final txtidproducto txtidproducto = new txtidproducto();		
		DefaultCellEditor editor = new DefaultCellEditor(txtidproducto);
		tblDetalle.getColumn("IdProducto").setCellEditor(editor);
		tblDetalle.getColumn("IdProducto").setPreferredWidth(120);
		
		txtidproducto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				getDetalleTM().setValueAt(txtidproducto.getDescripcion(), tblDetalle.getSelectedRow(), 2);
				getDetalleTM().setValueAt(txtidproducto.getIdMedida(), tblDetalle.getSelectedRow(), 3);
			}
		});
		
		txtidproducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ev) {	
				if(ev.getKeyCode() != 9)
					txtidproducto.mostrar(txtidproducto.getText());
			}
		});
		
		scrollPane.setViewportView(tblDetalle);
		tblDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getContentPane().add(scrollPane);
		
		JButton button = new JButton("I LINEA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String xitem = "001";
				if(getDetalleTM().getRowCount() > 0){						
					String item = getDetalleTM().getValueAt(tblDetalle.getRowCount(), 0).toString();
					xitem = org.codehaus.plexus.util.StringUtils.repeat("0",3-item.trim().length())+item;
				}
				final Object fila[] = {xitem,"","","",0.00,0.00,0.00,0.00,0.00};
				getDetalleTM().addRow(fila);
			}
		});
		button.setIcon(new ImageIcon(FrmDocRecepcion.class.getResource("/main/resources/iconos/table_row_insert.png")));
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setAlignmentY(0.0f);
		button.setBounds(356, 169, 83, 37);
		getContentPane().add(button);
	}

	@Override
	public void nuevo() {

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
		txtCorrrelativo.setEditable(true);
		this.cntclieprov.txtCodigo.setEditable(true);
		this.txtContacto.setEditable(true);
		this.cboCondicionPango.setEnabled(true);
		this.cboMonedas.setEnabled(true);
		this.txtDestino.setEditable(true);
		this.cntsucursal.txtCodigo.setEditable(true);
		this.cntalmacen.txtCodigo.setEditable(true);
		getDetalleTM().setEditar(true);
	}

	@Override
	public void vista_noedicion() {
		txtCorrrelativo.setEditable(false);
		this.cntclieprov.txtCodigo.setEditable(false);
		this.txtContacto.setEditable(false);
		this.cboCondicionPango.setEnabled(false);
		this.cboMonedas.setEnabled(false);
		this.txtDestino.setEditable(false);
		this.cntsucursal.txtCodigo.setEditable(false);
		this.cntalmacen.txtCodigo.setEditable(false);
		getDetalleTM().setEditar(false);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenarDesdeVista() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValidaVista() {
		// TODO Auto-generated method stub
		return false;
	}
	public DSGTableModel getDetalleTM(){
		return (DSGTableModel)tblDetalle.getModel();
	}
	
	/*public void cargarCombobox(List<DocFormulario> docformularioL){		
		ComboObject<Documento> documento;
		for(int i = 0 ; i < docformularioL.size() ; i++){
			documento = new ComboObject<Documento>(docformularioL.get(i).getDocumento(),docformularioL.get(i).getIddocumento());
			cboDocumento.addItem(documento);
		}	
		Documento xdocumento = docformularioL.get(0).getDocumento();
		if(documentNumeroDAO.getPorDocumento(xdocumento).size() > 0 ){
			DocumentoNumero documentoNumero = (DocumentoNumero) documentNumeroDAO.getPorDocumento(xdocumento).get(0); //Traer el primer documento y la serie
			txtCorrrelativo.setText(documentoNumero.getId().getSerie() +' '+documentoNumero.getNumero());
		}
	}*/
	
	public void actualizaSucursal() {
		cntalmacen.setSucursal(cntsucursal.getSeleccionado());
		cntalmacen.refrescar();
	}
	
}
