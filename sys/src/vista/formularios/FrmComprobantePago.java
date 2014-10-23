package vista.formularios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import javax.swing.UIManager;

import dao.ComprobantePagoDAO;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ScrollPaneConstants;

//import org.eclipse.persistence.internal.oxm.record.json.JSONParser.object_return;





import vista.controles.DSGDatePicker;
import vista.controles.DSGTableModelReporte;
import vista.utilitarios.JTableUtils;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class FrmComprobantePago extends JInternalFrame {

	private int filaseleccionada;
	private int rowselecionada;
	private JPanel contentPane;
	private DSGDatePicker txtFechaDoc;
	private JTextField txtProveedor;
	private DSGDatePicker txtFVence;
	private DSGDatePicker txtFechaRecep;
	private JTable TDDetalle;
	private JTextField txtVVenta;
	private JTextField txtNoGrav;
	private JTextField textField_9;
	private JTextField txtIGV;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	JComboBox cboTipoDoc = new JComboBox();
	JComboBox cboProvedor = new JComboBox();
	JComboBox cboAlmacen = new JComboBox();
	
	List<Object[]> ListProvedor=new ComprobantePagoDAO().ListarProveedor();
	
	JInternalFrame internalFrame = new JInternalFrame("Agregar Producto de Nota de Ingreso");
	private JTable TBNotaIngreso;
	Icon iconbusqueda = new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/GrillaBuscar.png"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmComprobantePago frame = new FrmComprobantePago();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
					//---------------------------------
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmComprobantePago() {
		setFrameIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/logo.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("REGISTRO DE COMPROBANTE DE PAGO");
		setBounds(100, 100, 1060, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 35, 1029, 144);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Comprobante de Pago", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel lblTipoDeDocumento = new JLabel("Tipo de Documento");
		lblTipoDeDocumento.setBounds(7, 24, 118, 14);
		panel_1.add(lblTipoDeDocumento);
		
		
		cboTipoDoc.setBounds(123, 24, 182, 20);
//		cboTipoDoc.setModel(new DefaultComboBoxModel(new String[] {"---Selecione---"}));
		panel_1.add(cboTipoDoc);
		contentPane.setLayout(null);
		internalFrame.setFrameIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/logo.png")));
		internalFrame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				for (int i = TBNotaIngreso.getRowCount() -1; i >= 0; i--)
					 
			     {
			    	 ((DefaultTableModel)TBNotaIngreso.getModel()).removeRow(i); 
			     }
			}
		});
		internalFrame.setClosable(true);
		internalFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		internalFrame.setBounds(0, 0, 698, 313);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 662, 239);
		internalFrame.getContentPane().add(scrollPane_1);
		
		TBNotaIngreso = new JTable();
		scrollPane_1.setViewportView(TBNotaIngreso);
		TBNotaIngreso.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "....", "Fecha", "NI", "Producto", "Cantidad", "Almacen", "N\u00B0 Guia", "Ref Alma", "Precio", "Importe"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Boolean.class, String.class, String.class, String.class, BigDecimal.class, String.class, String.class, String.class, BigDecimal.class, BigDecimal.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TBNotaIngreso.getColumnModel().getColumn(0).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(0).setPreferredWidth(29);
		TBNotaIngreso.getColumnModel().getColumn(1).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(1).setPreferredWidth(32);
		TBNotaIngreso.getColumnModel().getColumn(2).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(2).setPreferredWidth(52);
		TBNotaIngreso.getColumnModel().getColumn(3).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(3).setPreferredWidth(61);
		TBNotaIngreso.getColumnModel().getColumn(4).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(4).setPreferredWidth(219);
		TBNotaIngreso.getColumnModel().getColumn(5).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(5).setPreferredWidth(67);
		TBNotaIngreso.getColumnModel().getColumn(6).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(6).setPreferredWidth(77);
		TBNotaIngreso.getColumnModel().getColumn(7).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(7).setPreferredWidth(68);
		TBNotaIngreso.getColumnModel().getColumn(8).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(8).setPreferredWidth(86);
		TBNotaIngreso.getColumnModel().getColumn(9).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(9).setPreferredWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(9).setMinWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(9).setMaxWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(10).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(10).setPreferredWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(10).setMinWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(10).setMaxWidth(0);
		
		JButton btnCancelarDetalle = new JButton("Cancelar");
		btnCancelarDetalle.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmCancelar.png")));
		btnCancelarDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 internalFrame.setVisible(false);
				 for (int i = TBNotaIngreso.getRowCount() -1; i >= 0; i--)
				 
			     {
			    	 ((DefaultTableModel)TBNotaIngreso.getModel()).removeRow(i); 
			     }
			}
		});
		btnCancelarDetalle.setBounds(580, 254, 95, 23);
		internalFrame.getContentPane().add(btnCancelarDetalle);
		
		JButton btnAceptarDetalle = new JButton("Aceptar");
		btnAceptarDetalle.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmAgregar.png")));
		btnAceptarDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAceptarDetalleActionPerformed(arg0);
			}
		});
		btnAceptarDetalle.setBounds(486, 254, 93, 23);
		internalFrame.getContentPane().add(btnAceptarDetalle);
		internalFrame.setVisible(false);
		contentPane.add(panel_1);
		
		JLabel lblFechaDeDocumento = new JLabel("Fecha Documento");
		lblFechaDeDocumento.setBounds(350, 24, 106, 14);
		panel_1.add(lblFechaDeDocumento);
		
		txtFechaDoc = new DSGDatePicker();
		txtFechaDoc.setBounds(455, 21, 106, 20);
		panel_1.add(txtFechaDoc);
		//txtFechaDoc.setColumns(10);
		
		JLabel lblMoneda = new JLabel("Moneda");
		lblMoneda.setBounds(61, 49, 64, 14);
		panel_1.add(lblMoneda);
		
		JComboBox cboMoneda = new JComboBox();
		cboMoneda.setModel(new DefaultComboBoxModel(new String[] {"Nuevo Soles", "Dolares"}));
		cboMoneda.setBounds(123, 49, 118, 20);
		panel_1.add(cboMoneda);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(49, 74, 76, 14);
		panel_1.add(lblProveedor);
		
		txtProveedor = new JTextField();
		txtProveedor.setBounds(123, 74, 86, 20);
		panel_1.add(txtProveedor);
		txtProveedor.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(315, 96, 246, 14);
		panel_1.add(lblDireccion);
		
		JLabel lblFVencimiento = new JLabel("F. Vence");
		lblFVencimiento.setBounds(53, 114, 72, 14);
		panel_1.add(lblFVencimiento);
		
		txtFVence = new DSGDatePicker();;
		txtFVence.setBounds(123, 114, 86, 20);
		panel_1.add(txtFVence);
		//txtFVence.setColumns(10);
		
		cboProvedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Object[] objects : ListProvedor) {
					if(objects[1].toString().equalsIgnoreCase(cboProvedor.getSelectedItem().toString()))
					{
						txtProveedor.setText(objects[2].toString());
					}
				}
				
			}
		});
		
		
		cboProvedor.setBounds(222, 74, 339, 20);
		panel_1.add(cboProvedor);
		
		JLabel lblFechaRecepcion = new JLabel("Fecha Recepcion");
		lblFechaRecepcion.setBounds(354, 49, 102, 14);
		panel_1.add(lblFechaRecepcion);
		
		txtFechaRecep = new DSGDatePicker();
		txtFechaRecep.setBounds(455, 46, 106, 20);
		panel_1.add(txtFechaRecep);
	//	txtFechaRecep.setColumns(10);
		
		
		cboAlmacen.setBounds(418, 111, 143, 20);
		panel_1.add(cboAlmacen);
		
		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(362, 114, 58, 14);
		panel_1.add(lblAlmacen);
		
		JLabel lblVVenta = new JLabel("V. Venta");
		lblVVenta.setBounds(59, 429, 55, 14);
		contentPane.add(lblVVenta);
		
		txtVVenta = new JTextField();
		txtVVenta.setBounds(113, 426, 86, 20);
		contentPane.add(txtVVenta);
		txtVVenta.setColumns(10);
		
		JLabel lblVVNo = new JLabel("V. V. No Grav");
		lblVVNo.setBounds(209, 429, 79, 14);
		contentPane.add(lblVVNo);
		
		txtNoGrav = new JTextField();
		txtNoGrav.setBounds(281, 426, 86, 20);
		contentPane.add(txtNoGrav);
		txtNoGrav.setColumns(10);
		
		JCheckBox chckbxRenta = new JCheckBox("Renta");
		chckbxRenta.setBounds(385, 425, 65, 23);
		contentPane.add(chckbxRenta);
		
		textField_9 = new JTextField();
		textField_9.setBounds(456, 426, 62, 20);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblIgv = new JLabel("IGV");
		lblIgv.setBounds(555, 429, 28, 14);
		contentPane.add(lblIgv);
		
		txtIGV = new JTextField();
		txtIGV.setBounds(593, 426, 69, 20);
		contentPane.add(txtIGV);
		txtIGV.setColumns(10);
		
		JLabel lblPercep = new JLabel("Percep");
		lblPercep.setBounds(688, 429, 46, 14);
		contentPane.add(lblPercep);
		
		textField_11 = new JTextField();
		textField_11.setBounds(744, 426, 96, 20);
		contentPane.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(858, 429, 46, 14);
		contentPane.add(lblTotal);
		
		textField_12 = new JTextField();
		textField_12.setBounds(914, 426, 120, 20);
		contentPane.add(textField_12);
		textField_12.setColumns(10);
		
		JCheckBox chckbxTieneDetraccion = new JCheckBox("Tiene Detraccion");
		chckbxTieneDetraccion.setBounds(5, 456, 134, 23);
		contentPane.add(chckbxTieneDetraccion);
		
		textField_13 = new JTextField();
		textField_13.setBounds(140, 459, 46, 20);
		contentPane.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel label = new JLabel("%");
		label.setBounds(196, 462, 21, 14);
		contentPane.add(label);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/SalirFrm1.png")));
		btnSalir.setBounds(945, 482, 89, 23);
		contentPane.add(btnSalir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmCancelar.png")));
		btnCancelar.setBounds(850, 482, 95, 23);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmGuardar.png")));
		btnGuardar.setBounds(755, 482, 96, 23);
		contentPane.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmEliminar.png")));
		btnEliminar.setBounds(667, 482, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmEditar.png")));
		btnEditar.setBounds(579, 482, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmNuevo.png")));
		btnNuevo.setBounds(491, 482, 89, 23);
		contentPane.add(btnNuevo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 190, 1029, 228);
		contentPane.add(scrollPane);
		
		TDDetalle = new JTable();
		TDDetalle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TDDetallemouseClicked(arg0);
			}
		});
		TDDetalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
								if(arg0.getKeyCode()==39){
									MathContext mc = new MathContext(10);
									BigDecimal dat1 = new BigDecimal(TDDetalle.getModel().getValueAt(TDDetalle.getSelectedRow(), 2).toString());
									BigDecimal dat2 = new BigDecimal(TDDetalle.getModel().getValueAt(TDDetalle.getSelectedRow(), 7).toString());
									TDDetalle.getModel().setValueAt((dat1.multiply(dat2).round(mc)), TDDetalle.getSelectedRow(), 8);
									CalcularTotales();
									System.out.println("key "+arg0.getKeyCode());
									
										
									
							
			}
			};
		});
		
		TDDetalle.setCellSelectionEnabled(true);
		
		
		scrollPane.setViewportView(TDDetalle);
		TDDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TDDetalle.setColumnSelectionAllowed(true);
		
		
		
		//-------------------------
		//scrollPane.setRowHeaderView(JTableUtils.buildRowHeader(TDDetalle,TDDetalle.getModel()));
		
		
		Panel panel = new Panel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(5, 5, 1029, 24);
		panel.setLayout(null);
		
		JLabel lblComprobanteDePago = new JLabel("COMPROBANTE DE PAGO");
		lblComprobanteDePago.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblComprobanteDePago.setForeground(Color.WHITE);
		lblComprobanteDePago.setBounds(10, 4, 232, 14);
		panel.add(lblComprobanteDePago);
		lblComprobanteDePago.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(panel);
		
		LlenarCombox();
		CrearTabla();
	}
	
	public void CalcularTotales()
	{
		try{
		BigDecimal vventa= new BigDecimal("0.0");
		BigDecimal dat1= new BigDecimal("0.0");
		BigDecimal dat2= new BigDecimal("0.0");
		BigDecimal subtotal= new BigDecimal("0.0");
		BigDecimal IGV= new BigDecimal("0.18");
		
		int n=TDDetalle.getRowCount();
		for(int i=0;i<n;i++){
			 dat1 = new BigDecimal(TDDetalle.getModel().getValueAt(i, 2).toString());
			 dat2 = new BigDecimal(TDDetalle.getModel().getValueAt(i, 6).toString());
			 subtotal= dat1.multiply(dat2);
			
			 vventa=vventa.add(subtotal);
			
		}	
		this.txtVVenta.setText(vventa.toString());
		this.txtIGV.setText(vventa.multiply(IGV).toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void btnAgregarDetActionPerformed(MouseEvent evt)
	{
		
		try{
		int i=TDDetalle.getRowCount();
		Object datos[]={i+1,false
				,0,
				"",
				new JLabel("", iconbusqueda, JLabel.CENTER),
				"",
				.00,
				.00,
				.00,
				.00,
				false,false,
				0.0,new JButton(iconbusqueda)};
		
		 ((DefaultTableModel)TDDetalle.getModel()).addRow(datos);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void btnQuitarDetActionPerformed(MouseEvent evt)
	{
		try{
		int j=TDDetalle.getRowCount();
		for(int t=0;t<j;t++)
		{
			boolean estado=(boolean)TDDetalle.getModel().getValueAt(t, 1);
			if(estado){
				 
				 ((DefaultTableModel)TDDetalle.getModel()).removeRow(t);
				 
				 j--;
				 t=0;
			}
		/*	else
			{
				int d=(j>t?t+1:t);
				int n=(int)TDDetalle.getModel().getValueAt(t, 0);
				System.out.println(n);
				TDDetalle.getModel().setValueAt(n+1, d, 0);}	
		*/}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void TDDetallemouseClicked(MouseEvent evt)
	{
		try{
		int columna =TDDetalle.columnAtPoint(evt.getPoint());
	     rowselecionada = TDDetalle.rowAtPoint(evt.getPoint());
		filaseleccionada = TDDetalle.rowAtPoint(evt.getPoint());
		 System.out.println(columna);
		// if (TDDetalle.getModel().getColumnClass(columna).equals(JButton.class)) {
			
			 if(columna==13){
			 //if(TDDetalle.getModel().getColumnName(arg0))
				 internalFrame.setBounds(contentPane.getWidth()/6, contentPane.getHeight()/4, 698, 313);
			     internalFrame.setVisible(true);
			     LlenarTBNotaIngreso();
			 
		//	 }
		 }
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void TDDetallemousePresd(MouseEvent evt)
	{
       
	}
	public void btnAceptarDetalleActionPerformed(ActionEvent evt)
	{
	try{
		int i=TBNotaIngreso.getRowCount();
		int j=TDDetalle.getRowCount();
		int n=0;
		for(int t=0;t<i;t++)
		{
			boolean estado=(boolean)TBNotaIngreso.getModel().getValueAt(t, 1);
			if(estado){
				Object datos[]={j+n,false
						,TBNotaIngreso.getModel().getValueAt(t, 5),
						TBNotaIngreso.getModel().getValueAt(t, 4),
						new JLabel(iconbusqueda),
						"",
						0,
						TBNotaIngreso.getModel().getValueAt(t, 9),
						TBNotaIngreso.getModel().getValueAt(t, 10),
						0,
						false,false,
						0,new JButton(iconbusqueda)};
				
				 ((DefaultTableModel)TDDetalle.getModel()).addRow(datos);
				 n++;
			}
		}
		CalcularTotales();
		internalFrame.setVisible(false);
		((DefaultTableModel)TDDetalle.getModel()).removeRow(filaseleccionada);
		filaseleccionada=-1;
	} catch (Exception e) {
		// TODO: handle exception
	}
	}
	
	public void LlenarTBNotaIngreso()
	{
		try {
			List<Object[]> listNotaIngreso=new ComprobantePagoDAO().ListarNotaIngreso(1);
			int i=1;
			if(listNotaIngreso.size()>0){
			for (Object[] objects : listNotaIngreso) {
				BigDecimal dat1=new BigDecimal(objects[3].toString());
				BigDecimal dat2=new BigDecimal(objects[7].toString());
				BigDecimal dat3=new BigDecimal(objects[8].toString());
				Object datos[]={i
						,false,
						(objects[0]!=null?objects[0].toString():""),
						objects[1].toString(),
						objects[2].toString(),
						dat1,
						objects[4].toString(),
						objects[5].toString(),
						objects[6].toString(),
						dat2,
						dat3
						};
				
				 ((DefaultTableModel)TBNotaIngreso.getModel()).addRow(datos);
				 i++;
			}
			}
		} catch (Exception e) {
			System.out.println("Formulario");
			e.printStackTrace();
		}
		
	}
	
	public  void LlenarCombox()
	{
		try {
			List<Object[]> listTipoDoc=new ComprobantePagoDAO().ListarTipoDoc();
			cboTipoDoc.addItem("Selecione Documento");
			for (Object[] objects : listTipoDoc) {
				cboTipoDoc.addItem(objects[1]);
			}
			
			
			cboProvedor.addItem("Selecione Proveedor");
			for (Object[] objects : ListProvedor) {
				cboProvedor.addItem(objects[1]);
			}
			
			String prmstrsucursal="001";
			List<Object[]> ListAlmacen=new ComprobantePagoDAO().ListarAlmacen(prmstrsucursal);
			cboAlmacen.addItem("Selecione Almacen");
			for (Object[] objects : ListAlmacen) {
				cboAlmacen.addItem(objects[0]);
			}
		} catch (Exception e) {
			System.out.println("Formulario");
			e.printStackTrace();
		}
		
		
	}

	public void CrearTabla() 
	{
		try {
			
		
		
		this.TDDetalle.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "", "Cantidad", "Producto", "..", "U. Medida", "V. Venta", "P. Unitario", "Importe", "Costo Prov.", "Percepcion", "Exon IGV", "Nota Ingreso", "..."
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Boolean.class, BigDecimal.class, Object.class, JLabel.class, Object.class, BigDecimal.class, BigDecimal.class, BigDecimal.class, BigDecimal.class, Boolean.class, Boolean.class, Object.class, JButton.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TDDetalle.getColumnModel().getColumn(0).setResizable(false);
		TDDetalle.getColumnModel().getColumn(0).setPreferredWidth(16);
		TDDetalle.getColumnModel().getColumn(1).setResizable(false);
		TDDetalle.getColumnModel().getColumn(1).setPreferredWidth(16);
		TDDetalle.getColumnModel().getColumn(2).setResizable(false);
		TDDetalle.getColumnModel().getColumn(2).setPreferredWidth(61);
		TDDetalle.getColumnModel().getColumn(3).setPreferredWidth(289);
		TDDetalle.getColumnModel().getColumn(4).setResizable(false);
		TDDetalle.getColumnModel().getColumn(4).setPreferredWidth(25);
		TDDetalle.getColumnModel().getColumn(5).setResizable(false);
		TDDetalle.getColumnModel().getColumn(5).setPreferredWidth(61);
		TDDetalle.getColumnModel().getColumn(6).setResizable(false);
		TDDetalle.getColumnModel().getColumn(6).setPreferredWidth(64);
		TDDetalle.getColumnModel().getColumn(7).setResizable(false);
		TDDetalle.getColumnModel().getColumn(7).setPreferredWidth(67);
		TDDetalle.getColumnModel().getColumn(8).setResizable(false);
		TDDetalle.getColumnModel().getColumn(8).setPreferredWidth(69);
		TDDetalle.getColumnModel().getColumn(9).setResizable(false);
		TDDetalle.getColumnModel().getColumn(9).setPreferredWidth(76);
		TDDetalle.getColumnModel().getColumn(10).setResizable(false);
		TDDetalle.getColumnModel().getColumn(10).setPreferredWidth(77);
		TDDetalle.getColumnModel().getColumn(11).setResizable(false);
		TDDetalle.getColumnModel().getColumn(11).setPreferredWidth(64);
		TDDetalle.getColumnModel().getColumn(12).setResizable(false);
		TDDetalle.getColumnModel().getColumn(12).setPreferredWidth(78);
		TDDetalle.getColumnModel().getColumn(13).setResizable(false);
		TDDetalle.getColumnModel().getColumn(13).setPreferredWidth(24);
		TDDetalle.setShowVerticalLines(false);
		
		Border headeBorder=UIManager.getBorder("TableHeader.cellBorder");
		
		Icon redIcon = new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/GrillaQuitar1.png"));
		Icon redIcon2 = new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/GrillaAgregar1.png"));
		
		
		JLabel blueLabel = new JLabel("", redIcon, JLabel.CENTER);
	 //   blueLabel.setBorder(headeBorder);
	    
	    JLabel blueLabel1 = new JLabel("", redIcon2, JLabel.CENTER);
	 //   blueLabel1.setBorder(headeBorder);
	    //TDDetalle TableColumnModel columnModel =  TDDetalle . getColumnModel();
		TDDetalle.getTableHeader().addMouseListener(new MouseAdapter() {
			   @Override
			      public void mouseClicked(MouseEvent mouseEvent) {
			        int index = TDDetalle.columnAtPoint(mouseEvent.getPoint());
			        
			        System.out.println("entro");
			        if (index == 0) {
			        	btnAgregarDetActionPerformed(mouseEvent);
			        }
			        if (index == 1) {
			        	btnQuitarDetActionPerformed(mouseEvent);
			        }
			       // TDDetallemouseClicked(mouseEvent);
			      };
		});
		TableColumnModel columnModel =  TDDetalle . getColumnModel();
		TableColumn colum=columnModel.getColumn(0);
		TableColumn colum1=columnModel.getColumn(1);
		TableCellRenderer renderer = new JComponentTableCellRenderer();
		colum.setHeaderRenderer(renderer);
		colum.setHeaderValue(blueLabel1);
		colum1.setHeaderRenderer(renderer);
		colum1.setHeaderValue(blueLabel);
		
		
		
		
		TDDetalle.setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
            	return (Component) objeto;
            }
        });
		
		TDDetalle.setDefaultRenderer(JLabel.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
            	return (Component) objeto;
            }
        });
		
		
		
		//TableCellRenderer render =new JComponentTableCellRenderer();
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
}

class JComponentTableCellRenderer implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int row, int column) {
		
		  
	    return (JComponent) value;
	  }
	}

