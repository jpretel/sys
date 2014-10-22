package vista.formularios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Panel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Font;
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
public class FrmComprobantePago extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtFechaDoc;
	private JTextField txtProveedor;
	private JTextField txtFVence;
	private JTextField txtFechaRecep;
	private JTextField txtNDoc;
	private JTextField FechaRegistro;
	private JTextField txtFechaModificacion;
	private JTable TDDetalle;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	JComboBox cboTipoDoc = new JComboBox();
	JComboBox cboProvedor = new JComboBox();
	JComboBox cboAlmacen = new JComboBox();
	JButton btnAgregarDet = new JButton("+");
	JInternalFrame internalFrame = new JInternalFrame("Agregar Producto de Nota de Ingreso");
	private JTable TBNotaIngreso;
	

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
		setIconifiable(true);
		setClosable(true);
		setTitle("REGISTRO DE COMPROBANTE DE PAGO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 35, 919, 144);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Comprobante de Pago", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel lblTipoDeDocumento = new JLabel("Tipo de Documento");
		lblTipoDeDocumento.setBounds(7, 24, 118, 14);
		panel_1.add(lblTipoDeDocumento);
		
		
		cboTipoDoc.setBounds(123, 24, 182, 20);
//		cboTipoDoc.setModel(new DefaultComboBoxModel(new String[] {"---Selecione---"}));
		panel_1.add(cboTipoDoc);
		contentPane.setLayout(null);
		internalFrame.setClosable(true);
		
		
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
				"N\u00B0", "....", "Fecha", "NI", "Producto", "Cantidad", "Almacen", "N\u00B0 Guia", "Ref Alma"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
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
		
		JButton btnCancelarDetalle = new JButton("Cancelar");
		btnCancelarDetalle.setBounds(583, 254, 89, 23);
		internalFrame.getContentPane().add(btnCancelarDetalle);
		
		JButton btnAceptarDetalle = new JButton("Aceptar");
		btnAceptarDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAceptarDetalleActionPerformed(arg0);
			}
		});
		btnAceptarDetalle.setBounds(493, 254, 89, 23);
		internalFrame.getContentPane().add(btnAceptarDetalle);
		internalFrame.setVisible(false);
		contentPane.add(panel_1);
		
		JLabel lblFechaDeDocumento = new JLabel("Fecha Documento");
		lblFechaDeDocumento.setBounds(350, 24, 106, 14);
		panel_1.add(lblFechaDeDocumento);
		
		txtFechaDoc = new JTextField();
		txtFechaDoc.setBounds(455, 21, 106, 20);
		panel_1.add(txtFechaDoc);
		txtFechaDoc.setColumns(10);
		
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
		
		txtFVence = new JTextField();
		txtFVence.setBounds(123, 114, 86, 20);
		panel_1.add(txtFVence);
		txtFVence.setColumns(10);
		
		
		cboProvedor.setBounds(222, 74, 339, 20);
		panel_1.add(cboProvedor);
		
		JLabel lblFechaRecepcion = new JLabel("Fecha Recepcion");
		lblFechaRecepcion.setBounds(354, 49, 102, 14);
		panel_1.add(lblFechaRecepcion);
		
		txtFechaRecep = new JTextField();
		txtFechaRecep.setBounds(455, 46, 106, 20);
		panel_1.add(txtFechaRecep);
		txtFechaRecep.setColumns(10);
		
		
		cboAlmacen.setBounds(418, 111, 143, 20);
		panel_1.add(cboAlmacen);
		
		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(362, 114, 58, 14);
		panel_1.add(lblAlmacen);
		
		JLabel lblNDoc = new JLabel("N\u00B0 Doc");
		lblNDoc.setBounds(587, 24, 64, 14);
		panel_1.add(lblNDoc);
		
		txtNDoc = new JTextField();
		txtNDoc.setBounds(643, 21, 102, 20);
		panel_1.add(txtNDoc);
		txtNDoc.setColumns(10);
		
		JButton btnBuscar = new JButton("Grabar");
		btnBuscar.setBounds(745, 21, 26, 20);
		panel_1.add(btnBuscar);
		
		JLabel lblFechaRegistro = new JLabel("Fecha Registro");
		lblFechaRegistro.setBounds(643, 49, 128, 14);
		panel_1.add(lblFechaRegistro);
		
		FechaRegistro = new JTextField();
		FechaRegistro.setBounds(643, 71, 128, 20);
		panel_1.add(FechaRegistro);
		FechaRegistro.setColumns(10);
		
		JLabel lblFechaModificacion = new JLabel("Fecha Modificacion");
		lblFechaModificacion.setBounds(643, 96, 128, 14);
		panel_1.add(lblFechaModificacion);
		
		txtFechaModificacion = new JTextField();
		txtFechaModificacion.setBounds(643, 111, 128, 20);
		panel_1.add(txtFechaModificacion);
		txtFechaModificacion.setColumns(10);
		
		JLabel lblVVenta = new JLabel("V. Venta");
		lblVVenta.setBounds(59, 429, 55, 14);
		contentPane.add(lblVVenta);
		
		textField_7 = new JTextField();
		textField_7.setBounds(113, 426, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblVVNo = new JLabel("V. V. No Grav");
		lblVVNo.setBounds(209, 429, 79, 14);
		contentPane.add(lblVVNo);
		
		textField_8 = new JTextField();
		textField_8.setBounds(281, 426, 86, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JCheckBox chckbxRenta = new JCheckBox("Renta");
		chckbxRenta.setBounds(385, 425, 65, 23);
		contentPane.add(chckbxRenta);
		
		textField_9 = new JTextField();
		textField_9.setBounds(456, 426, 62, 20);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblIgv = new JLabel("IGV");
		lblIgv.setBounds(528, 429, 28, 14);
		contentPane.add(lblIgv);
		
		textField_10 = new JTextField();
		textField_10.setBounds(550, 426, 55, 20);
		contentPane.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblPercep = new JLabel("Percep");
		lblPercep.setBounds(622, 429, 55, 14);
		contentPane.add(lblPercep);
		
		textField_11 = new JTextField();
		textField_11.setBounds(674, 426, 86, 20);
		contentPane.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(770, 429, 46, 14);
		contentPane.add(lblTotal);
		
		textField_12 = new JTextField();
		textField_12.setBounds(816, 426, 98, 20);
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
		btnSalir.setBounds(825, 482, 89, 23);
		contentPane.add(btnSalir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(737, 482, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(649, 482, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(561, 482, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(473, 482, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(385, 482, 89, 23);
		contentPane.add(btnNuevo);
		
		
		btnAgregarDet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAgregarDetActionPerformed(arg0);
			}
		});
		
		
		btnAgregarDet.setHorizontalAlignment(SwingConstants.TRAILING);
		btnAgregarDet.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAgregarDet.setBounds(5, 178, 41, 14);
		contentPane.add(btnAgregarDet);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 190, 919, 228);
		contentPane.add(scrollPane);
		
		TDDetalle = new JTable();
		TDDetalle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TDDetallemouseClicked(arg0);
			}
		});
		TDDetalle.setCellSelectionEnabled(true);
		scrollPane.setViewportView(TDDetalle);
		TDDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TDDetalle.setColumnSelectionAllowed(true);
		
		Panel panel = new Panel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(5, 5, 919, 24);
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

	public void btnAgregarDetActionPerformed(ActionEvent evt)
	{
		
		int i=TDDetalle.getRowCount();
		Object datos[]={i+1
				,0.0,
				"",
				new JButton("..."),
				"",
				0.0,
				0.0,
				0.0,
				0.0,
				false,false,
				0.0,new JButton("..")};
		
		 ((DefaultTableModel)TDDetalle.getModel()).addRow(datos);
	}
	public void TDDetallemouseClicked(MouseEvent evt)
	{
		int columna = TDDetalle.columnAtPoint(evt.getPoint());
		System.out.println(columna);
		 if (TDDetalle.getModel().getColumnClass(columna).equals(JButton.class)) {
			 if(columna==12){
			 //if(TDDetalle.getModel().getColumnName(arg0))
				 internalFrame.setBounds(contentPane.getWidth()/6, contentPane.getHeight()/4, 698, 313);
			     internalFrame.setVisible(true);
			     LlenarTBNotaIngreso();
			 
			 }
		 }
	}
	
	public void btnAceptarDetalleActionPerformed(ActionEvent evt)
	{
		int i=TBNotaIngreso.getRowCount();
		int j=TDDetalle.getRowCount()+1;
		for(int t=0;t<i;t++)
		{
			boolean estado=(boolean)TBNotaIngreso.getModel().getValueAt(t, 1);
			if(estado){
				Object datos[]={j+t
						,TBNotaIngreso.getModel().getValueAt(t, 5),
						TBNotaIngreso.getModel().getValueAt(t, 4),
						new JButton("..."),
						"",
						0.0,
						0.0,
						0.0,
						0.0,
						false,false,
						0.0,new JButton("..")};
				
				 ((DefaultTableModel)TDDetalle.getModel()).addRow(datos);
			}
		}
	}
	
	public void LlenarTBNotaIngreso()
	{
		List<Object[]> listNotaIngreso=new ComprobantePagoDAO().ListarNotaIngreso(1);
		int i=0;
		for (Object[] objects : listNotaIngreso) {
			Object datos[]={i+1
					,false,
					objects[2],
					objects[3],
					objects[4],
					objects[5],
					objects[6],
					objects[7],
					objects[8],
					};
			
			 ((DefaultTableModel)TDDetalle.getModel()).addRow(datos);
		}
	}
	
	public  void LlenarCombox()
	{
		List<Object[]> listTipoDoc=new ComprobantePagoDAO().ListarTipoDoc();
		
		for (Object[] objects : listTipoDoc) {
			cboTipoDoc.addItem(objects[1]);
		}
		
		List<Object[]> ListProvedor=new ComprobantePagoDAO().ListarProveedor();
		
		for (Object[] objects : ListProvedor) {
			cboProvedor.addItem(objects[0]);
		}
		
		String prmstrsucursal="001";
		List<Object[]> ListAlmacen=new ComprobantePagoDAO().ListarAlmacen(prmstrsucursal);
		
		for (Object[] objects : ListAlmacen) {
			cboAlmacen.addItem(objects[0]);
		}
		
	}

	public void CrearTabla()
	{
		
		this.TDDetalle.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Cantidad", "Producto", "..", "U. Medida", "V. Venta", "P. Unitario", "Importe", "Costo Prov.", "Percepcion", "Exon IGV", "Nota Ingreso", "..."
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Float.class, Object.class, JButton.class, Object.class, Float.class, Float.class, Float.class, Float.class, Boolean.class, Boolean.class, Object.class, JButton.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TDDetalle.getColumnModel().getColumn(0).setResizable(false);
		TDDetalle.getColumnModel().getColumn(0).setPreferredWidth(24);
		TDDetalle.getColumnModel().getColumn(1).setResizable(false);
		TDDetalle.getColumnModel().getColumn(1).setPreferredWidth(61);
		TDDetalle.getColumnModel().getColumn(2).setPreferredWidth(289);
		TDDetalle.getColumnModel().getColumn(3).setResizable(false);
		TDDetalle.getColumnModel().getColumn(3).setPreferredWidth(25);
		TDDetalle.getColumnModel().getColumn(4).setResizable(false);
		TDDetalle.getColumnModel().getColumn(4).setPreferredWidth(61);
		TDDetalle.getColumnModel().getColumn(5).setResizable(false);
		TDDetalle.getColumnModel().getColumn(5).setPreferredWidth(64);
		TDDetalle.getColumnModel().getColumn(6).setResizable(false);
		TDDetalle.getColumnModel().getColumn(6).setPreferredWidth(67);
		TDDetalle.getColumnModel().getColumn(7).setResizable(false);
		TDDetalle.getColumnModel().getColumn(7).setPreferredWidth(69);
		TDDetalle.getColumnModel().getColumn(8).setResizable(false);
		TDDetalle.getColumnModel().getColumn(8).setPreferredWidth(76);
		TDDetalle.getColumnModel().getColumn(9).setResizable(false);
		TDDetalle.getColumnModel().getColumn(9).setPreferredWidth(77);
		TDDetalle.getColumnModel().getColumn(10).setResizable(false);
		TDDetalle.getColumnModel().getColumn(10).setPreferredWidth(64);
		TDDetalle.getColumnModel().getColumn(11).setResizable(false);
		TDDetalle.getColumnModel().getColumn(11).setPreferredWidth(78);
		TDDetalle.getColumnModel().getColumn(12).setResizable(false);
		TDDetalle.getColumnModel().getColumn(12).setPreferredWidth(24);
		
		TDDetalle.setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                 return (Component) objeto;
            }
        });
		
		
		
	
	}
}

