package vista.formularios;
//.....................
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;

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
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.UIManager;

import dao.ComprobantePagoDAO;
import dao.HojaTrabajoStockDAO;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ScrollPaneConstants;

//import org.eclipse.persistence.internal.oxm.record.json.JSONParser.object_return;





















import vista.Sys;
import vista.barras.PanelBarraMaestro;
import vista.controles.DSGDatePicker;
import vista.controles.DSGTableModelReporte;
import vista.utilitarios.JTableUtils;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.apache.batik.css.engine.value.css2.SrcManager;
import org.apache.poi.ss.formula.functions.Replace;




import com.jhlabs.image.ErodeFilter;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
public class FrmComprobantePago extends JInternalFrame {

	//---variables para la cabecera--- Open
	private int prmintcomprobante=0;
	private int prmintestado=0;
	//---------------------End-
	
	
	private int filaseleccionada;
	private int rowselecionada;
	private JPanel contentPane;
	private DSGDatePicker txtFechaDoc;
	private JTextField txtProveedor;
	private DSGDatePicker txtFVence;
	private JTable TDDetalle;
	private JTextField txtVVenta;
	private JTextField txtNoGrav;
	private JTextField txtRenta;
	private JTextField txtIGV;
	private JTextField txtPercep;
	private JTextField txtTotal;
	private JTextField txtDetraccion;
	JComboBox cboTipoDoc = new JComboBox();
	JComboBox cboProvedor = new JComboBox();
	JComboBox cboAlmacen = new JComboBox();
	JComboBox cboMoneda = new JComboBox();
	
	JButton btnSalir = new JButton("");
	JButton btnCancelar = new JButton("");
	JButton btnGuardar = new JButton("");
	JButton btnEliminar = new JButton("");
	JButton btnEditar = new JButton("");
	JButton btnNuevo = new JButton("");
	
	List<Object[]> ListProvedor=new ComprobantePagoDAO().ListarProveedor();
	List<Object[]> ListAlmacen=null;
	List<Object[]> listTipoDoc=null;
	List<Object[]> listMoneda=null;
	JComboBox cboGrupo = new JComboBox();
	JComboBox cboMarca = new JComboBox();
	java.util.List<Object[]> listGrupo=new HojaTrabajoStockDAO().ListarGrupo();
	java.util.List<Object[]> listMarca=new HojaTrabajoStockDAO().ListarMarca();
	JInternalFrame internalFrame = new JInternalFrame("Agregar Producto de Nota de Ingreso");
	JInternalFrame iframeproducto = new JInternalFrame("Buscar Producto");
	private JTable TBNotaIngreso;
	BigDecimal IGV=new BigDecimal("1.18");
	Icon iconbusqueda = new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/GrillaBuscar.png"));
	private JTextField txtNDocumento;
	private JTable tbproducto;
	private JTextField txtNSerie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmComprobantePago frame = new FrmComprobantePago();
					frame.setVisible(true);
					frame.setLocation(5, 5);
					//168-563
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
		setMaximizable(true);
		setFrameIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/logo.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("REGISTRO DE COMPROBANTE DE PAGO");
		setBounds(100, 100, 1068, 563);
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
		iframeproducto.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		iframeproducto.setClosable(true);
		iframeproducto.setBounds(0, 281, 536, 253);
		contentPane.add(iframeproducto);
		iframeproducto.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 40, 500, 155);
		iframeproducto.getContentPane().add(scrollPane_2);
		
		tbproducto = new JTable();
		tbproducto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Codigo", "Descripcion", "Medida"
			}
		));
		tbproducto.getColumnModel().getColumn(0).setResizable(false);
		tbproducto.getColumnModel().getColumn(0).setPreferredWidth(26);
		tbproducto.getColumnModel().getColumn(1).setPreferredWidth(133);
		tbproducto.getColumnModel().getColumn(2).setPreferredWidth(291);
		scrollPane_2.setViewportView(tbproducto);
		
		JButton btnagregarproducto = new JButton("");
		btnagregarproducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregaProducto();
			}
		});
		btnagregarproducto.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmAgregar.png")));
		btnagregarproducto.setToolTipText("Agregar");
		btnagregarproducto.setBounds(410, 196, 50, 28);
		iframeproducto.getContentPane().add(btnagregarproducto);
		
		JButton btncancelarproducto = new JButton("");
		btncancelarproducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iframeproducto.setVisible(false);
			}
		});
		btncancelarproducto.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmCancelar.png")));
		btncancelarproducto.setToolTipText("Cancelar");
		btncancelarproducto.setBounds(460, 196, 50, 28);
		iframeproducto.getContentPane().add(btncancelarproducto);
		
		JLabel lblGrupo = new JLabel("Grupo :");
		lblGrupo.setBounds(10, 15, 46, 14);
		iframeproducto.getContentPane().add(lblGrupo);
		
		
		cboGrupo.setBounds(52, 12, 134, 20);
		iframeproducto.getContentPane().add(cboGrupo);
		
		JLabel lblMarca = new JLabel("Marca :");
		lblMarca.setBounds(200, 15, 46, 14);
		iframeproducto.getContentPane().add(lblMarca);
		
		
		cboMarca.setBounds(256, 9, 147, 20);
		iframeproducto.getContentPane().add(cboMarca);
		
		JButton btnbuscarproducto = new JButton("");
		btnbuscarproducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListarProductos(obtenerGrupo(),obetenerMarca());
			}
		});
		btnbuscarproducto.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/consultar.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		btnbuscarproducto.setBounds(410, 8, 50, 28);
		iframeproducto.getContentPane().add(btnbuscarproducto);
		iframeproducto.setVisible(false);
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
				"N\u00B0", "....", "Fecha", "NI", "Producto", "Cantidad", "Almacen", "N\u00B0 Guia", "Ref Alma", "Precio", "Importe",
				 "Iteam","Idproducto","IdOrdenCompra","Iddocingreso"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Boolean.class, String.class, String.class, String.class, BigDecimal.class, String.class, String.class, String.class, BigDecimal.class, BigDecimal.class,
				Object.class,String.class,Object.class,Object.class
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
		TBNotaIngreso.getColumnModel().getColumn(11).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(11).setPreferredWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(11).setMinWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(11).setMaxWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(12).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(12).setPreferredWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(12).setMinWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(12).setMaxWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(13).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(13).setPreferredWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(13).setMinWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(13).setMaxWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(14).setResizable(false);
		TBNotaIngreso.getColumnModel().getColumn(14).setPreferredWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(14).setMinWidth(0);
		TBNotaIngreso.getColumnModel().getColumn(14).setMaxWidth(0);
		
		JButton btnCancelarDetalle = new JButton("");
		btnCancelarDetalle.setToolTipText("Cancelar");
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
		btnCancelarDetalle.setBounds(620, 254, 50, 28);
		internalFrame.getContentPane().add(btnCancelarDetalle);
		
		JButton btnAceptarDetalle = new JButton("");
		btnAceptarDetalle.setToolTipText("Agregar");
		btnAceptarDetalle.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmAgregar.png")));
		btnAceptarDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAceptarDetalleActionPerformed(arg0);
			}
		});
		btnAceptarDetalle.setBounds(570, 254, 50, 28);
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
		cboMoneda.setBounds(123, 49, 118, 20);
		panel_1.add(cboMoneda);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(49, 74, 76, 14);
		panel_1.add(lblProveedor);
		
		txtProveedor = new JTextField();
		txtProveedor.setBounds(123, 74, 86, 20);
		panel_1.add(txtProveedor);
		txtProveedor.setColumns(10);
		
		
		
		
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
	//	txtFechaRecep.setColumns(10);
		
		
		cboAlmacen.setBounds(418, 111, 143, 20);
		panel_1.add(cboAlmacen);
		
		JLabel lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setBounds(362, 114, 58, 14);
		panel_1.add(lblAlmacen);
		
		JLabel lblNDocumento = new JLabel("N\u00B0 Documento");
		lblNDocumento.setBounds(577, 24, 94, 14);
		panel_1.add(lblNDocumento);
		
		txtNDocumento = new JTextField();
		txtNDocumento.setBounds(577, 37, 118, 20);
		panel_1.add(txtNDocumento);
		txtNDocumento.setColumns(10);
		
		JButton btnbuscar = new JButton("");
		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnbuscarActionListener(arg0);
			}
		});
		btnbuscar.setBounds(705, 35, 50, 28);
		btnbuscar.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/consultar.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		panel_1.add(btnbuscar);
		
		JLabel lblNSerie = new JLabel("N\u00B0 Serie");
		lblNSerie.setBounds(577, 74, 46, 14);
		panel_1.add(lblNSerie);
		
		txtNSerie = new JTextField();
		txtNSerie.setBounds(577, 93, 118, 20);
		panel_1.add(txtNSerie);
		txtNSerie.setColumns(10);
		
		JLabel lblVVenta = new JLabel("V. Venta");
		lblVVenta.setBounds(59, 429, 55, 14);
		contentPane.add(lblVVenta);
		
		txtVVenta = new JTextField();
		txtVVenta.setText("0.0");
		txtVVenta.setEnabled(false);
		txtVVenta.setBounds(113, 426, 86, 20);
		contentPane.add(txtVVenta);
		txtVVenta.setColumns(10);
		
		JLabel lblVVNo = new JLabel("V. V. No Grav");
		lblVVNo.setBounds(209, 429, 79, 14);
		contentPane.add(lblVVNo);
		
		txtNoGrav = new JTextField();
		txtNoGrav.setText("0.0");
		txtNoGrav.setBounds(281, 426, 86, 20);
		contentPane.add(txtNoGrav);
		txtNoGrav.setColumns(10);
		
		JCheckBox chckbxRenta = new JCheckBox("Renta");
		chckbxRenta.setBounds(385, 425, 65, 23);
		contentPane.add(chckbxRenta);
		
		txtRenta = new JTextField();
		txtRenta.setText("0.0");
		txtRenta.setBounds(456, 426, 62, 20);
		contentPane.add(txtRenta);
		txtRenta.setColumns(10);
		
		JLabel lblIgv = new JLabel("IGV");
		lblIgv.setBounds(555, 429, 28, 14);
		contentPane.add(lblIgv);
		
		txtIGV = new JTextField();
		txtIGV.setText("0.0");
		txtIGV.setEnabled(false);
		txtIGV.setBounds(593, 426, 69, 20);
		contentPane.add(txtIGV);
		txtIGV.setColumns(10);
		
		JLabel lblPercep = new JLabel("Percep");
		lblPercep.setBounds(688, 429, 46, 14);
		contentPane.add(lblPercep);
		
		txtPercep = new JTextField();
		txtPercep.setText("0.0");
		txtPercep.setEnabled(false);
		txtPercep.setBounds(744, 426, 96, 20);
		contentPane.add(txtPercep);
		txtPercep.setColumns(10);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(858, 429, 46, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setText("0.0");
		txtTotal.setEnabled(false);
		txtTotal.setBounds(914, 426, 120, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JCheckBox chckbxTieneDetraccion = new JCheckBox("Tiene Detraccion");
		chckbxTieneDetraccion.setBounds(5, 456, 134, 23);
		contentPane.add(chckbxTieneDetraccion);
		
		txtDetraccion = new JTextField();
		txtDetraccion.setText("0.0");
		txtDetraccion.setEnabled(false);
		txtDetraccion.setBounds(140, 459, 46, 20);
		contentPane.add(txtDetraccion);
		txtDetraccion.setColumns(10);
		
		JLabel label = new JLabel("%");
		label.setBounds(196, 462, 21, 14);
		contentPane.add(label);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		
		
		btnSalir.setToolTipText("Salir");
		btnSalir.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/salir.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		//btnSalir.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/SalirFrm1.png")));
		btnSalir.setBounds(984, 482, 50, 28);
		contentPane.add(btnSalir);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimpiarControl();
				activarcontrol(false);
				activarbuton(true, false, false, false, false);
				prmintcomprobante=-1;
				prmintestado=0;
			}
		});
		
		
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/cancelar.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
//		btnCancelar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmCancelar.png")));
		btnCancelar.setBounds(934, 482, 50, 28);
		contentPane.add(btnCancelar);
		
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGuardarActionPerfomed(e);
			}
		});
		btnGuardar.setToolTipText("Guardar");
		btnGuardar.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/grabar.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		//btnGuardar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmGuardar.png")));
		btnGuardar.setBounds(884, 482, 50, 28);
		contentPane.add(btnGuardar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prmintestado=3;
				GuardarComprobantePago();
			}
		});
		
		
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/eliminar.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		//btnEliminar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmEliminar.png")));
		btnEliminar.setBounds(834, 482, 50, 28);
		contentPane.add(btnEliminar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activarbuton(false, false, true, true, true);
				activarcontrol(true);
				prmintestado=2;
			}
		});
		
		
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/editar.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		//btnEditar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmEditar.png")));
		btnEditar.setBounds(784, 482, 50, 28);
		contentPane.add(btnEditar);
		
		
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNuevoActionPerformet(arg0);
			}
		});
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/nuevo.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		//btnNuevo.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/FrmNuevo.png")));
		btnNuevo.setBounds(734, 482, 50, 28);
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
				
								
			};
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try {
					if(arg0.getKeyCode()==10){
						
						if(prmintestado>0){
						BigDecimal dat1 = new BigDecimal(TDDetalle.getModel().getValueAt(TDDetalle.getSelectedRow(), 2).toString());
						BigDecimal dat2 = new BigDecimal(TDDetalle.getModel().getValueAt(TDDetalle.getSelectedRow(), 6).toString());
						BigDecimal dat3 = new BigDecimal(TDDetalle.getModel().getValueAt(TDDetalle.getSelectedRow(), 7).toString());
						BigDecimal IGV2=new BigDecimal("0.18");
						BigDecimal dat4 = new BigDecimal("0.0");
						
						if(TDDetalle.getSelectedColumn()==6)
						{
							dat4 = dat2.multiply(IGV2).add(dat2).setScale(2,  RoundingMode.HALF_DOWN);
							TDDetalle.getModel().setValueAt(dat4.setScale(2,  BigDecimal.ROUND_UNNECESSARY), TDDetalle.getSelectedRow(), 7);
							dat3 = new BigDecimal(TDDetalle.getModel().getValueAt(TDDetalle.getSelectedRow(), 7).toString());
						}else if(TDDetalle.getSelectedColumn()==7)
						{
							dat3 = new BigDecimal(TDDetalle.getModel().getValueAt(TDDetalle.getSelectedRow(), 7).toString());
							TDDetalle.getModel().setValueAt((dat3.divide(IGV,2, RoundingMode.HALF_UP)), TDDetalle.getSelectedRow(), 6);
						}
						
						TDDetalle.getModel().setValueAt((dat1.multiply(dat3).setScale(2,  BigDecimal.ROUND_UNNECESSARY)), TDDetalle.getSelectedRow(), 8);
						
						CalcularTotales();
						System.out.println("key "+arg0.getKeyCode());
						
						 if(TDDetalle.getModel().getValueAt(TDDetalle.getSelectedRow(), 18).toString().equalsIgnoreCase("0"))
						 {
								TDDetalle.getModel().setValueAt("2", TDDetalle.getSelectedRow(), 18);
						 }
					}
					}
				} catch (Exception e) {
					Error(e.toString());
				}
			}
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
		LlenarComboxproducto();
		CrearTabla();
		activarcontrol(false);
		activarbuton(true, false, false, false, false);
	}
	
	//--------------Buscar---------------
	public void btnbuscarActionListener(ActionEvent  evt)
	{
		try {
			
		Object comprobantepagodat=new ComprobantePagoDAO().DevolverComprobantePago(txtNDocumento.getText());
		
		
		if(comprobantepagodat!=null){
		Object[] comprobantepago=(Object[]) comprobantepagodat;
		String fecha=comprobantepago[4].toString();
		String fechavence=comprobantepago[5].toString();
		fecha=fecha.substring(8, 10)+"/"+fecha.substring(5, 7)+"/"+fecha.substring(0, 4);
		fechavence=fechavence.substring(8, 10)+"/"+fechavence.substring(5, 7)+"/"+fechavence.substring(0, 4);
		prmintcomprobante=Integer.valueOf(comprobantepago[0].toString());
		txtFechaDoc.getEditor().setText(fecha);
		txtFVence.getEditor().setText(fechavence);
		txtDetraccion.setText(comprobantepago[7].toString());
		txtIGV.setText(comprobantepago[8].toString());
		txtPercep.setText(comprobantepago[9].toString());
		txtNoGrav.setText(comprobantepago[10].toString());
		txtRenta.setText(comprobantepago[11].toString());
		txtTotal.setText(comprobantepago[13].toString());
		txtNSerie.setText(comprobantepago[1].toString());
		devuleveprovedor(comprobantepago[16].toString());
		devuelvetipodocumento(comprobantepago[3].toString());
		devuelveAlamcen(comprobantepago[19].toString());
		devuelveMoneda(comprobantepago[6].toString());
		System.out.println(comprobantepago[1].toString());
		int prmintcomprobante=Integer.parseInt(comprobantepago[0].toString());
		List<Object[]>ComprobanteDetalle=new ComprobantePagoDAO().DevolverComprobantePagoDetalle(prmintcomprobante);
		int n=1;
		for (Object[] objects : ComprobanteDetalle) {
			BigDecimal dat1=new BigDecimal(objects[3].toString());
			
			Object datos[]={n,false
					,objects[1].toString(),
					objects[18].toString(),
					new JButton(iconbusqueda),
					"",
					Integer.parseInt(objects[7].toString())==1?dat1:dat1.divide(IGV, 2, RoundingMode.HALF_UP)
					,
					dat1,
					objects[4].toString(),
					0,
					(Integer.parseInt(objects[6].toString())==1?true:false),
					(Integer.parseInt(objects[7].toString())==1?true:false),
					objects[19].toString(),new JButton(iconbusqueda),
					objects[0].toString(),objects[9].toString(),
					objects[12].toString(),
					objects[13].toString(),"0"
			        };
			//"IdDcomprobante","idProducto","idDNotaIngreso","IdOrdenCompra"
			 ((DefaultTableModel)TDDetalle.getModel()).addRow(datos);
		}
		CalcularTotales();
		activarbuton(true, true, false, false, false);
		}else
		{
			LimpiarControl();
			JOptionPane.showMessageDialog(this,
                    "No se Encontro Resultado",
                    "Comprobante Pago",
                    JOptionPane.INFORMATION_MESSAGE);

		}
		} catch (Exception e) {
			Error(e.toString());
		}
		
	}
	//------------------Devuelve Combox
	public void devuleveprovedor(String prmstrproveedor)
	{
		int i=1;
		for (Object[] objects : ListProvedor) {
			if(objects[0].toString().equalsIgnoreCase(prmstrproveedor))
			{
				cboProvedor.setSelectedIndex(i);
				txtProveedor.setText(objects[2].toString());
				
				
			}
			i++;
		}
	}
	
	public void devuelvetipodocumento(String prmstrtipodoc)
	{
		int i=1;
		for (Object[] objects : listTipoDoc) {
			if(objects[0].toString().equalsIgnoreCase(prmstrtipodoc))
			{
				cboTipoDoc.setSelectedIndex(i);
				
			}
			i++;
		}
	}
	
	public void devuelveAlamcen(String prmstralmacen)
	{
		int i=1;
		for (Object[] objects : ListAlmacen) {
			if(objects[0].toString().equalsIgnoreCase(prmstralmacen))
			{
				cboAlmacen.setSelectedIndex(i);
			}
			i++;
		}
	}
	
	public void devuelveMoneda(String prmstrmoneda)
	{
		int i=1;
		for (Object[] objects : listMoneda) {
			if(objects[0].toString().equalsIgnoreCase(prmstrmoneda))
			{
				cboMoneda.setSelectedIndex(i);
			}
			i++;
		}
	}
	//-----------------End Devuelve Combox
	//----------------------------------------------
	public String obtenerprovedor()
	{
		String result="";
		for (Object[] objects : ListProvedor) {
			if(objects[1].toString().equalsIgnoreCase(cboProvedor.getSelectedItem().toString()))
			{
				
				result=objects[0].toString();
			}
			
		}
		return result;
	}

	public String obtenertipodocumento()
	{
		String result="";
		for (Object[] objects : listTipoDoc) {
			if(objects[1].toString().equalsIgnoreCase(cboTipoDoc.getSelectedItem().toString()))
			{
				
				result=objects[0].toString();
			}
			
		}
		return result;
	}

	public String obtenerAlmacen()
	{
		String result="";
		for (Object[] objects : ListAlmacen) {
			if(objects[1].toString().equalsIgnoreCase(cboAlmacen.getSelectedItem().toString()))
			{
				
				result=objects[0].toString();
			}
			
		}
		return result;
	}

	public String obtenerMoneda()
	{
		String result="";
		for (Object[] objects : listMoneda) {
			if(objects[1].toString().equalsIgnoreCase(cboMoneda.getSelectedItem().toString()))
			{
				
				result=objects[0].toString();
			}
			
		}
		return result;
	}
	
	public String obtenerGrupo()
	{
		String codigo="";
		for (Object[] objects : listGrupo) {
			if(objects[1].toString().equalsIgnoreCase(cboGrupo.getSelectedItem().toString()))
			{
				codigo=objects[0].toString();
			}
		}
		return codigo;
	}
	
	public String obetenerMarca()
	{
		String codigo="";
		for (Object[] objects : listMarca) {
			if(objects[1].toString().equalsIgnoreCase(cboMarca.getSelectedItem().toString()))
			{
				codigo=objects[0].toString();
			}
		}
		return codigo;
	}
	//-----------------------------------------------
	
	public void CalcularTotales()
	{
		try{
		BigDecimal vventa= new BigDecimal("0.0");
		BigDecimal dat1= new BigDecimal("0.0");
		BigDecimal dat2= new BigDecimal("0.0");
		BigDecimal subtotal= new BigDecimal("0.0");
		BigDecimal total=new BigDecimal("0.0");
		BigDecimal percep=new BigDecimal(txtPercep.getText().toString());
		BigDecimal nograv=new BigDecimal(txtNoGrav.getText().toString());
		BigDecimal IGV1= new BigDecimal("0.18");
		
		int n=TDDetalle.getRowCount();
		BigDecimal IGVD=new BigDecimal("0.0");
		for(int i=0;i<n;i++){
			 dat1 = new BigDecimal(TDDetalle.getModel().getValueAt(i, 2).toString());
			 dat2 = new BigDecimal(TDDetalle.getModel().getValueAt(i, 6).toString());
			 subtotal= dat1.multiply(dat2);
			 vventa=vventa.add(subtotal).setScale(2,  BigDecimal.ROUND_UNNECESSARY);
			 
			 boolean estado=(boolean)TDDetalle.getModel().getValueAt(i, 11);
			 if(!estado)
			 {
				 BigDecimal puni=new BigDecimal(TDDetalle.getModel().getValueAt(i, 7).toString());
				 
				 IGVD=IGVD.add(puni.multiply(IGV1)).setScale(2,  BigDecimal.ROUND_HALF_UP);
			 }
			
		}
		
		
		this.txtVVenta.setText(vventa.toString());
//		BigDecimal IGVD=vventa.multiply(IGV).setScale(2,  BigDecimal.ROUND_HALF_UP);
		total=total.add(vventa).add(IGVD).add(percep).add(nograv);//.setScale(2,  BigDecimal.ROUND_UNNECESSARY);
		this.txtIGV.setText(IGVD.toString());
		System.out.println(total);
		this.txtTotal.setText(total.toString());
		
		} catch (Exception e) {
			Error(e.toString());
			e.printStackTrace();
		}
	}
	public void btnAgregarDetActionPerformed(MouseEvent evt)
	{
		
		try{
			if(prmintestado>0){
		int i=TDDetalle.getRowCount();
		Object datos[]={i+1,false
				,0,
				"",
				new JButton(iconbusqueda),
				"",
				.00,
				.00,
				.00,
				.00,
				false,false,
				0.0,new JButton(iconbusqueda),"","","","","1"};
		
		 ((DefaultTableModel)TDDetalle.getModel()).addRow(datos);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void btnQuitarDetActionPerformed(MouseEvent evt)
	{
		try{
			if(prmintestado>0){
		int j=TDDetalle.getRowCount();
		for(int t=0;t<j;t++)
		{
			boolean estado=(boolean)TDDetalle.getModel().getValueAt(t, 1);
			if(estado){
				 
				 
				 if(TDDetalle.getModel().getValueAt(t, 18).toString().trim().equalsIgnoreCase("1"))
				 {
					 ((DefaultTableModel)TDDetalle.getModel()).removeRow(t);
					 j--;
				 }else
				 {
					 TDDetalle.getModel().setValueAt("3", t, 18);
					 TDDetalle.getModel().setValueAt(false, t, 1);
					//TDDetalle.getColumnModel().getColumn(17).setMaxWidth(0);
					 TDDetalle.setRowHeight(t, 1);
				 }
				 
				 t=0;
			}
		/*	else
			{
				int d=(j>t?t+1:t);
				int n=(int)TDDetalle.getModel().getValueAt(t, 0);
				System.out.println(n);
				TDDetalle.getModel().setValueAt(n+1, d, 0);}	
		*/}
		CalcularTotales();
			}
		} catch (Exception e) {
			Error(e.toString());
			e.printStackTrace();
		}
	}
	
	public void TDDetallemouseClicked(MouseEvent evt)
	{
		try{
			if(prmintestado>0){
		int columna =TDDetalle.columnAtPoint(evt.getPoint());
	     rowselecionada = TDDetalle.rowAtPoint(evt.getPoint());
		filaseleccionada = TDDetalle.rowAtPoint(evt.getPoint());
		 System.out.println(columna);
		// if (TDDetalle.getModel().getColumnClass(columna).equals(JButton.class)) {
		 if(columna==4){
			 //if(TDDetalle.getModel().getColumnName(arg0))
				 iframeproducto.setBounds(contentPane.getWidth()/4, contentPane.getHeight()/4, 536, 253);
				 iframeproducto.setVisible(true);
			     //LlenarTBNotaIngreso();
			 
		//	 }
			    
		      }
			
			 if(columna==13){
			 //if(TDDetalle.getModel().getColumnName(arg0))
				 internalFrame.setBounds(contentPane.getWidth()/6, contentPane.getHeight()/4, 698, 313);
			     internalFrame.setVisible(true);
			     LlenarTBNotaIngreso();
			 
		//	 }
			    
		      }
			 if(columna==10)
		     {
		    	 BigDecimal dat1= new BigDecimal("0.0");
		 		BigDecimal dat2= new BigDecimal("0.2");
		 		BigDecimal percep= new BigDecimal("0.0");
		 		//BigDecimal IGV= new BigDecimal("0.18");
		 		
		 		int n=TDDetalle.getRowCount();
		 		for(int i=0;i<n;i++){
		 			boolean estado=(boolean)TDDetalle.getModel().getValueAt(i, 10);
		 			if(estado){
		 			 dat1 = new BigDecimal(TDDetalle.getModel().getValueAt(i, 8).toString());
		 			 percep=percep.add(dat1.multiply(dat2)).setScale(2,  RoundingMode.HALF_DOWN);
		 			 TDDetalle.getModel().setValueAt("2", TDDetalle.getSelectedRow(), 18);
		 			}
		 		}
		 		
		 		this.txtPercep.setText(percep.toString());
		 		CalcularTotales();
		     }
			 if(columna==11)
			 {
				 boolean estado=(boolean)TDDetalle.getModel().getValueAt(rowselecionada, 11);
				 BigDecimal puni=new BigDecimal(TDDetalle.getModel().getValueAt(rowselecionada, 7).toString());
				 System.out.println("Fila : "+rowselecionada+" - "+estado);
				 if(estado){
				 TDDetalle.getModel().setValueAt(puni,rowselecionada, 6);
				 }else{
					 TDDetalle.getModel().setValueAt(puni.divide(IGV,2, RoundingMode.HALF_UP),rowselecionada, 6);
				 }
				 TDDetalle.getModel().setValueAt("2", TDDetalle.getSelectedRow(), 18);
			 	CalcularTotales();
			 }
			 if(TDDetalle.getSelectedColumn()==4 && TDDetalle.getModel().getValueAt(TDDetalle.getSelectedRow(), 18).toString().equalsIgnoreCase("0"))
			 {
					TDDetalle.getModel().setValueAt("2", TDDetalle.getSelectedRow(), 18);
			 }
			}
		} catch (Exception e) {
			Error(e.toString());
			e.printStackTrace();
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
		String dcomprobantestr=TDDetalle.getModel().getValueAt(filaseleccionada, 14).toString();
		int dcomprobante=Integer.parseInt((dcomprobantestr.length()>0?dcomprobantestr:"0"));
		for(int t=0;t<i;t++)
		{
			boolean estado=(boolean)TBNotaIngreso.getModel().getValueAt(t, 1);
			if(estado){
				BigDecimal dat1=new BigDecimal(TBNotaIngreso.getModel().getValueAt(t, 9).toString());
				
				Object datos[]={j+n,false
						,TBNotaIngreso.getModel().getValueAt(t, 5),
						TBNotaIngreso.getModel().getValueAt(t, 4),
						new JButton(iconbusqueda),
						"",
						dat1.divide(IGV, 2, RoundingMode.HALF_UP),
						dat1,
						TBNotaIngreso.getModel().getValueAt(t, 10),
						0,
						false,false,
						TBNotaIngreso.getModel().getValueAt(t, 14),new JButton(iconbusqueda),
						dcomprobante,TBNotaIngreso.getModel().getValueAt(t, 12),
						TBNotaIngreso.getModel().getValueAt(t, 11),
						TBNotaIngreso.getModel().getValueAt(t, 13),(dcomprobante>0?"2":"1")
				        };
				//"IdDcomprobante","idProducto","idDNotaIngreso","IdOrdenCompra"
				 ((DefaultTableModel)TDDetalle.getModel()).addRow(datos);
				 n++;
			}
		}
		CalcularTotales();
		internalFrame.setVisible(false);
		((DefaultTableModel)TDDetalle.getModel()).removeRow(filaseleccionada);
		filaseleccionada=-1;
	} catch (Exception e) {
		Error(e.toString());
		e.printStackTrace();
	}
	}
	
	public void LlenarTBNotaIngreso()
	{
		try {
			String prmstridalmacen="000";
			for (Object[] objects : ListAlmacen) {
				if(objects[1].toString().equalsIgnoreCase(cboAlmacen.getSelectedItem().toString()))
				{
					prmstridalmacen=objects[0].toString();
					
					
				}
			}
			List<Object[]> listNotaIngreso=new ComprobantePagoDAO().ListarNotaIngreso(txtProveedor.getText().toString(),prmstridalmacen);
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
						dat3,
						objects[9],
						objects[10].toString(),
						objects[11],
						objects[12]
						};
				
				 ((DefaultTableModel)TBNotaIngreso.getModel()).addRow(datos);
				 i++;
			}
			}
		} catch (Exception e) {
			Error(e.toString());
			System.out.println("Formulario");
			e.printStackTrace();
		}
		
	}
	
	//-----------------Open
	public void btnNuevoActionPerformet(ActionEvent evt)
	{
		activarbuton(false, false, false, true, true);
		activarcontrol(true);
		LimpiarControl();
		prmintestado=1;
	}
	//-----------------End
	//----------------Open
	public void btnGuardarActionPerfomed(ActionEvent evt)
	{
		GuardarComprobantePago();
	}
	
	public void GuardarComprobantePago()
	{
		try {
			//01-10-2014 201-1-0
			if(validacioncontrol()){
		String cfecha=txtFechaDoc.getEditor().getText();
		String cfechavence=txtFVence.getEditor().getText();
		String prmxmlcabecera="<root>";
		prmxmlcabecera+="<Comprobante "
				+ "IdComprobante='"+prmintcomprobante+"' "
				+ "NSerie='"+txtNSerie.getText()+"' "
				+ "NDocumento='"+txtNDocumento.getText()+"' "
				+ "TipoDocumento='"+obtenertipodocumento()+"' "
				+ "Fecha='"+cfecha.substring(6, 10)+"-"+cfecha.substring(3, 5)+"-"+cfecha.substring(0, 2)+"' "
				+ "FechaVence='"+cfechavence.substring(6, 10)+"-"+cfechavence.substring(3, 5)+"-"+cfechavence.substring(0, 2)+"' "
				+ "Moneda='"+obtenerMoneda()+"' "
				+ "DetraccionPorcentaje='"+txtDetraccion.getText()+"' "
				+ "IGV='"+txtIGV.getText()+"' "
				+ "TotalPercepcion='"+txtPercep.getText()+"' "
				+ "TotalNoGravado='"+txtNoGrav.getText()+"' "
				+ "TotalRenta='"+txtRenta.getText()+"' "
				+ "TotalDescuento='0.0' "
				+ "Total='"+txtTotal.getText()+"' "
				+ "TipoCompra='0.0' "
				+ "OrdenCompra='0' "
				+ "Proveedor='"+obtenerprovedor()+"' "
				+ "Contabilidad='0' "
				+ "Almacen='"+obtenerAlmacen()+"' Accion='"+prmintestado+"'>";
		
		String prmxmldetalle="";
		int n=TDDetalle.getRowCount();
		for(int i=0;i<n;i++){
			 //dat1 = new BigDecimal(TDDetalle.getModel().getValueAt(i, 2).toString());
		prmxmldetalle+="<Dcomprobante "
				+ "Dcomprobante='"+TDDetalle.getModel().getValueAt(i, 14).toString()+"' "
				+ "Cantidad='"+TDDetalle.getModel().getValueAt(i, 2).toString()+"' "
				+ "Vventa='"+TDDetalle.getModel().getValueAt(i, 6).toString()+"' "
				+ "PUnitario='"+TDDetalle.getModel().getValueAt(i, 7).toString()+"' "
				+ "ImporteCosto='"+TDDetalle.getModel().getValueAt(i, 8).toString()+"' "
				+ "CostoProveedor='"+TDDetalle.getModel().getValueAt(i, 9).toString()+"' "
				+ "Percepcion='"+(TDDetalle.getModel().getValueAt(i, 10).toString().equalsIgnoreCase("false")? 0 : 1)+"' "
				+ "ExoneradoIGV='"+(TDDetalle.getModel().getValueAt(i, 11).toString().equalsIgnoreCase("false")? 0 : 1)+"' "
				+ "Gasto='0.0' "
				+ "Producto='"+TDDetalle.getModel().getValueAt(i, 15).toString()+"' "
				+ "conNC='0' "
				+ "ComprobantePago='0' "
				+ "DetalleNotaIngreso='"+TDDetalle.getModel().getValueAt(i, 16).toString()+"' "
				+ "OrdenCompra='"+TDDetalle.getModel().getValueAt(i, 17).toString()+"' Accion='"+TDDetalle.getModel().getValueAt(i, 18).toString()+"' />";
		}
		
		
		prmxmlcabecera+=prmxmldetalle+"</Comprobante></root>";
		prmxmlcabecera.replace((char)39, (char)34);
		
		int i=new ComprobantePagoDAO().InsertComprobantePago(prmxmlcabecera);
		System.out.println(i+" - "+prmxmlcabecera);
			
			JOptionPane.showMessageDialog(this,
                    "Guardado Correctamente",
                    "Comprobante Pago",
                    JOptionPane.INFORMATION_MESSAGE);
			LimpiarControl();
			activarcontrol(false);
			activarbuton(true, false, false, false, false);
		
			}
		
		} catch (Exception e) {
			Error(e.toString());
			e.printStackTrace();
		}
	}
	//----------------End
	
	public  void LlenarCombox()
	{
		try {
			listTipoDoc=new ComprobantePagoDAO().ListarTipoDoc();
			cboTipoDoc.addItem("Selecione Documento");
			for (Object[] objects : listTipoDoc) {
				cboTipoDoc.addItem(objects[1]);
			}
			
			
			cboProvedor.addItem("Selecione Proveedor");
			for (Object[] objects : ListProvedor) {
				cboProvedor.addItem(objects[1]);
			}
			
			String prmstrsucursal="001";
			
			ListAlmacen=new ComprobantePagoDAO().ListarAlmacen(prmstrsucursal);
			cboAlmacen.addItem("Selecione Almacen");
			for (Object[] objects : ListAlmacen) {
				cboAlmacen.addItem(objects[1]);
			}
			
			listMoneda=new ComprobantePagoDAO().ListarMoneda();
			cboMoneda.addItem("Selecione Moneda");
			for (Object[] objects : listMoneda) {
				cboMoneda.addItem(objects[1]);
			}
		} catch (Exception e) {
			Error(e.toString());
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
				"", "", "Cantidad", "Producto", "..", "U. Medida", "V. Venta", "P. Unitario", "Importe", "Costo Prov.", "Percepcion", "Exon IGV", "Nota Ingreso", "...", "IdDcomprobante", "idProducto", "idDNotaIngreso", "IdOrdenCompra", "Accion"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Boolean.class, BigDecimal.class, Object.class, JButton.class, Object.class, BigDecimal.class, BigDecimal.class, BigDecimal.class, BigDecimal.class, Boolean.class, Boolean.class, Object.class, JButton.class, Object.class, Object.class, Object.class, Object.class, Object.class
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
		TDDetalle.getColumnModel().getColumn(9).setMinWidth(0);
		TDDetalle.getColumnModel().getColumn(9).setMaxWidth(0);
		TDDetalle.getColumnModel().getColumn(10).setResizable(false);
		TDDetalle.getColumnModel().getColumn(10).setPreferredWidth(77);
		TDDetalle.getColumnModel().getColumn(11).setResizable(false);
		TDDetalle.getColumnModel().getColumn(11).setPreferredWidth(64);
		TDDetalle.getColumnModel().getColumn(12).setResizable(false);
		TDDetalle.getColumnModel().getColumn(12).setPreferredWidth(78);
		TDDetalle.getColumnModel().getColumn(13).setResizable(false);
		TDDetalle.getColumnModel().getColumn(13).setPreferredWidth(24);
		TDDetalle.getColumnModel().getColumn(14).setPreferredWidth(15);
		TDDetalle.getColumnModel().getColumn(14).setMinWidth(0);
		TDDetalle.getColumnModel().getColumn(14).setMaxWidth(0);
		TDDetalle.getColumnModel().getColumn(15).setMinWidth(0);
		TDDetalle.getColumnModel().getColumn(15).setMaxWidth(0);
		TDDetalle.getColumnModel().getColumn(16).setMinWidth(0);
		TDDetalle.getColumnModel().getColumn(16).setMaxWidth(0);
		TDDetalle.getColumnModel().getColumn(17).setPreferredWidth(94);
		TDDetalle.getColumnModel().getColumn(17).setMinWidth(0);
		TDDetalle.getColumnModel().getColumn(17).setMaxWidth(0);
		TDDetalle.getColumnModel().getColumn(18).setMinWidth(0);
		TDDetalle.getColumnModel().getColumn(18).setMaxWidth(0);
		TDDetalle.setShowVerticalLines(false);
		
		Border headeBorder=UIManager.getBorder("TableHeader.cellBorder");
		
		Icon redIcon = new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/GrillaQuitar1.png"));
		Icon redIcon2 = new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/GrillaAgregar1.png"));
		
		
		JLabel blueLabel = new JLabel("", redIcon, JLabel.CENTER);
		blueLabel.setToolTipText("Eliminar Item Selecionada");
	 //   blueLabel.setBorder(headeBorder);
	    
	    JLabel blueLabel1 = new JLabel("", redIcon2, JLabel.CENTER);
	    blueLabel1.setToolTipText("Agregar Item");
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
			Error(e.toString());
		}
	
	}
	
	public boolean validacioncontrol()
	{
		boolean estado=true;
		if(cboTipoDoc.getSelectedIndex()==0 && estado==true)
		{
			MessageValidacion("Debe Selecionar Tipo de Documento");
			estado=false;
		}
		if(cboMoneda.getSelectedIndex()==0 && estado==true)
		{
			MessageValidacion("Debe Selecionar Moneda");
			estado=false;
		}
		if(txtFechaDoc.getEditor().getText().length()<4 && estado==true)
		{
			MessageValidacion("Debe Ingresar Fecha de Documento");
			estado=false;
		}
		if(txtNDocumento.getText().trim().length()<4 && estado==true)
		{
			MessageValidacion("Debe Ingresar N° de Documento");
			estado=false;
		}
		if(cboProvedor.getSelectedIndex()==0 && estado==true)
		{
			MessageValidacion("Debe Selecionar Proveedor");
			estado=false;
		}
		
		if(cboAlmacen.getSelectedIndex()==0 && estado==true)
		{
			MessageValidacion("Debe Selecionar Almacen");
			estado=false;
		}
		
		if(txtFVence.getEditor().getText().length()<4 && estado==true)
		{
			MessageValidacion("Debe Ingresar Fecha de Vencimiento");
			estado=false;
		}
		
		if(TDDetalle.getModel().getRowCount()==0 && estado==true)
		{
			MessageValidacion("Debe Ingresar Detalle del Comprobante de Pago");
			estado=false;
		}else
		{
			
			for(int i=0;i<TDDetalle.getRowCount();i++){
				 
					if(TDDetalle.getModel().getValueAt(i, 15).toString().trim().length()<2 && estado==true)
					{
						MessageValidacion("Debe Selecionar Producto para la Fila "+(i+1));
						estado=false;
					}
			}
		}
		
		return estado;
	}

	public void activarcontrol(boolean estado)
	{
		cboTipoDoc.setEnabled(estado);
		cboMoneda.setEnabled(estado);
		cboProvedor.setEnabled(estado);
		cboAlmacen.setEnabled(estado);
		//txtNDocumento.setEnabled(estado);
		txtProveedor.setEnabled(estado);
		txtFechaDoc.setEnabled(estado);
		txtFVence.setEnabled(estado);
		txtNoGrav.setEnabled(estado);
		txtRenta.setEnabled(estado);
		txtNSerie.setEnabled(estado);
		TDDetalle.setEnabled(estado);
		
		
	}
	
	public void activarbuton(boolean estado1,boolean estado2,boolean estado3,boolean estado4, boolean estado5)
	{
		btnNuevo.setEnabled(estado1);
		btnEditar.setEnabled(estado2);
		btnEliminar.setEnabled(estado3);
		btnGuardar.setEnabled(estado4);
		btnCancelar.setEnabled(estado5);
		
	}
	
	public void LimpiarControl()
	{
		cboTipoDoc.setSelectedIndex(0);
		cboMoneda.setSelectedIndex(0);
		cboProvedor.setSelectedIndex(0);
		cboAlmacen.setSelectedIndex(0);
		//txtNDocumento.setEnabled(estado);
		txtProveedor.setText("");
		txtFechaDoc.getEditor().setText("");
		txtFVence.getEditor().setText("");
		txtNoGrav.setText("0.0");
		txtRenta.setText("0.0");
		txtNSerie.setText("");
		
		 for (int i = TDDetalle.getRowCount() -1; i >= 0; i--)
			 
	     {
	    	 ((DefaultTableModel)TDDetalle.getModel()).removeRow(i); 
	     }
		
	}
	
	public void Error(String mensaje)
	{
		JOptionPane.showMessageDialog(this,
                mensaje,
                "Comprobante Pago - Error",
                JOptionPane.ERROR_MESSAGE);

	}

	public void MessageValidacion(String mensaje)
	{
		JOptionPane.showMessageDialog(this,
                mensaje,
                "Comprobante Pago - Validacion",
                JOptionPane.WARNING_MESSAGE);
	}
	
	//-------------------Buscar producto-----------------------
	public void LlenarComboxproducto()
	{
		
		cboGrupo.addItem("Selecione Grupo");
		for (Object[] objects : listGrupo) {
			cboGrupo.addItem(objects[2]);
		}
		
		
		cboMarca.addItem("Selecione Marca");
		for (Object[] objects : listMarca) {
			cboMarca.addItem(objects[1]);
		}
	}
	
	public void ListarProductos(String prmstrgrupo,String prmstrmarca)
	{
		
		try {
			DefaultTableModel model=(DefaultTableModel)tbproducto.getModel();
			 for (int i = tbproducto.getRowCount() -1; i >= 0; i--)
			 {
				 model.removeRow(i);
		     }
			List<Object[]>lisproduto=new ComprobantePagoDAO().ListarProducto(prmstrgrupo, prmstrmarca);
			int i=1;
			 for (Object[] objects : lisproduto) {
				 model.addRow(new Object[]{
						 i,
						 objects[0],
						 objects[1],
						 objects[2]
						 });
				 i++;
			}
		} catch (Exception e) {
			Error(e.toString());
		}
	}
	
	public void AgregaProducto()
	{
		int f=TDDetalle.getSelectedRow();
		int fi=tbproducto.getSelectedRow();
		
		TDDetalle.getModel().setValueAt(tbproducto.getModel().getValueAt(fi, 2), f, 3);
		TDDetalle.getModel().setValueAt(tbproducto.getModel().getValueAt(fi, 3), f, 5);
		TDDetalle.getModel().setValueAt(tbproducto.getModel().getValueAt(fi, 1), f, 15);
		
		iframeproducto.setVisible(false);
	}
}

class JComponentTableCellRenderer implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int row, int column) {
		
		  
	    return (JComponent) value;
	  }
	}


