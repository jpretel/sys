package vista.formularios;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import java.awt.BorderLayout;

import javax.swing.JTable;

import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;

import dao.HojaTrabajoStockDAO;
import vista.barras.PanelBarraMaestro;

import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmHojaTrabajoStock extends JInternalFrame{
	private JPanel contentPane;
	private JTable TBHojaTrabajo;
	private JButton btnCancelar;
	private JButton btnGenerar;
	JComboBox cboGrupo = new JComboBox();
	JComboBox cboMarca = new JComboBox();
	java.util.List<Object[]> listGrupo=new HojaTrabajoStockDAO().ListarGrupo();
	java.util.List<Object[]> listMarca=new HojaTrabajoStockDAO().ListarGrupo();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmHojaTrabajoStock frame = new FrmHojaTrabajoStock();
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
	
	public FrmHojaTrabajoStock()
	{
		setMaximizable(true);
		setFrameIcon(new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/logo.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("HOJA DE TRABAJO DE STOCK");
		setBounds(100, 100, 927, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//GroupLayout layout= new GroupLayout(this);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 891, 294);
		contentPane.add(scrollPane);
		
		TBHojaTrabajo = new JTable();
		TBHojaTrabajo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Producto", "Stock", "Stock Minimo", "Back Order", "Reque Por Atender", "Comprar", "Selecion"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TBHojaTrabajo.getColumnModel().getColumn(0).setResizable(false);
		TBHojaTrabajo.getColumnModel().getColumn(0).setPreferredWidth(29);
		TBHojaTrabajo.getColumnModel().getColumn(1).setPreferredWidth(305);
		TBHojaTrabajo.getColumnModel().getColumn(5).setPreferredWidth(114);
		TBHojaTrabajo.getColumnModel().getColumn(6).setPreferredWidth(78);
		TBHojaTrabajo.getColumnModel().getColumn(7).setResizable(false);
		scrollPane.setViewportView(TBHojaTrabajo);
		
		
		
		JButton btnSalir = new JButton("");
		btnSalir.setToolTipText("Salir");
		btnSalir.setToolTipText("Salir");
		btnSalir.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/salir.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		btnSalir.setBounds(851, 394, 50, 28);
		contentPane.add(btnSalir);
		
		btnCancelar = new JButton("");
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/cancelar.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		btnCancelar.setBounds(800, 394, 50, 28);
		contentPane.add(btnCancelar);
		//actualizar
		btnGenerar = new JButton("");
		btnGenerar.setToolTipText("Generar");
		btnGenerar.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/actualizar.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		btnGenerar.setBounds(748, 394, 50, 28);
		contentPane.add(btnGenerar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtros de Busqueda", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 891, 67);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblGrupo = new JLabel("Grupo :");
		lblGrupo.setBounds(10, 29, 66, 14);
		panel.add(lblGrupo);
		
		
		cboGrupo.setBounds(86, 26, 174, 20);
		panel.add(cboGrupo);
		
		JLabel lblMarca = new JLabel("Marca :");
		lblMarca.setBounds(289, 29, 60, 14);
		panel.add(lblMarca);
		
		
		cboMarca.setBounds(359, 26, 174, 20);
		panel.add(cboMarca);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimpiarTB();
				llenarHojaTrabajo(recuperarIdGrupo(),recuperarIdMarca());
			}
		});
		btnBuscar.setBounds(543, 20, 50, 28);
		btnBuscar.setIcon(new ImageIcon(new ImageIcon(PanelBarraMaestro.class
				.getResource("/main/resources/iconos/consultar.png")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_DEFAULT)));
		panel.add(btnBuscar);
		
		llenarHojaTrabajo("","");
		LlenarCombox();
	}
	
	
	
	public void llenarHojaTrabajo(String prmgrupo,String prmmarca)
	{
		
		DefaultTableModel model=(DefaultTableModel)TBHojaTrabajo.getModel();
		java.util.List<Object[]> listHojaTrabajoc=new HojaTrabajoStockDAO().ListarHojaTrabajo(prmgrupo,prmmarca);
		int i=1;
		for (Object[] objects : listHojaTrabajoc) {
			model.addRow(new Object[]{i,objects[1],(objects[2]!=null?objects[2]:"0.0"),
					(objects[3]!=null?objects[3]:"0.0"),
					(objects[4]!=null?objects[4]:"0.0"),
					(objects[5]!=null?objects[5]:"0.0"),"0.0",false});
			i++;
		}
		
	}
	
	public void LlenarCombox()
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
	
	public String recuperarIdGrupo()
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
	
	public String recuperarIdMarca()
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
	
	public void LimpiarTB()
	{
		for (int i = TBHojaTrabajo.getRowCount() -1; i >= 0; i--)
			 
	     {
	    	 ((DefaultTableModel)TBHojaTrabajo.getModel()).removeRow(i); 
	     }
	}
}

class JComponentTableCellRendere implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int row, int column) {
		
		  
	    return (JComponent) value;
	  }
	}

