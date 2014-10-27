package vista.formularios;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.List;

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

public class FrmHojaTrabajoStock extends JInternalFrame{
	private JPanel contentPane;
	private JTable TBHojaTrabajo;
	private JButton btnCancelar;
	private JButton btnGenerar;
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
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 891, 372);
		contentPane.add(scrollPane);
		
		TBHojaTrabajo = new JTable();
		TBHojaTrabajo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "", "Producto", "...", "Stock", "Stock Minimo", "Back Order", "Reque Por Atender", "Comprar", "Selecion"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Boolean.class, Object.class, JButton.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TBHojaTrabajo.getColumnModel().getColumn(0).setResizable(false);
		TBHojaTrabajo.getColumnModel().getColumn(0).setPreferredWidth(21);
		TBHojaTrabajo.getColumnModel().getColumn(1).setResizable(false);
		TBHojaTrabajo.getColumnModel().getColumn(1).setPreferredWidth(22);
		TBHojaTrabajo.getColumnModel().getColumn(2).setPreferredWidth(305);
		TBHojaTrabajo.getColumnModel().getColumn(3).setPreferredWidth(20);
		TBHojaTrabajo.getColumnModel().getColumn(7).setPreferredWidth(114);
		TBHojaTrabajo.getColumnModel().getColumn(8).setPreferredWidth(78);
		TBHojaTrabajo.getColumnModel().getColumn(9).setResizable(false);
		scrollPane.setViewportView(TBHojaTrabajo);
		//-----TableColumnModel columnModel =  TBHojaTrabajo . getColumnModel();
		Border headeBorder=UIManager.getBorder("TableHeader.cellBorder");
		Icon redIcon = new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/GrillaQuitar1.png"));
		Icon redIcon2 = new ImageIcon(FrmComprobantePago.class.getResource("/main/resources/iconos/GrillaAgregar1.png"));
		JLabel blueLabel = new JLabel("", redIcon, JLabel.CENTER);
		blueLabel.setToolTipText("Eliminar Item Selecionada");
		JLabel blueLabel1 = new JLabel("", redIcon2, JLabel.CENTER);
	    blueLabel1.setToolTipText("Agregar Item");
	    TableColumnModel columnModel =  TBHojaTrabajo . getColumnModel();
		TableColumn colum=columnModel.getColumn(0);
		TableColumn colum1=columnModel.getColumn(1);
		TableCellRenderer renderer = new JComponentTableCellRenderer();
		colum.setHeaderRenderer(renderer);
		colum.setHeaderValue(blueLabel1);
		colum1.setHeaderRenderer(renderer);
		colum1.setHeaderValue(blueLabel);
		
		TBHojaTrabajo.setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
            	return (Component) objeto;
            }
        });
		
		TBHojaTrabajo.setDefaultRenderer(JLabel.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
            	return (Component) objeto;
            }
        });
		
		TBHojaTrabajo.getTableHeader().addMouseListener(new MouseAdapter() {
			   @Override
			      public void mouseClicked(MouseEvent mouseEvent) {
			        int index = TBHojaTrabajo.columnAtPoint(mouseEvent.getPoint());
			        
			        System.out.println("entro");
			        if (index == 0) {
			        	btnAgregarHojaActionPerformed(mouseEvent);
			        }
			        if (index == 1) {
			        	btnQuitarHojaActionPerformed(mouseEvent);
			        }
			       // TDDetallemouseClicked(mouseEvent);
			      };
		});
		
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
		
		llenarHojaTrabajo();
	}
	
	public void btnAgregarHojaActionPerformed(MouseEvent evt){
		DefaultTableModel model=(DefaultTableModel)TBHojaTrabajo.getModel();
		model.addRow(new Object[]{"",false,"",new JButton("..."),"0","0","0","0","0",false});
	}
	public void btnQuitarHojaActionPerformed(MouseEvent evt)
	{
		int j=TBHojaTrabajo.getRowCount();
		for(int t=0;t<j;t++)
		{
			boolean estado=(boolean)TBHojaTrabajo.getModel().getValueAt(t, 1);
			if(estado){
				 
				 ((DefaultTableModel)TBHojaTrabajo.getModel()).removeRow(t);
				 
				 j--;
				 t=0;
		}	
	}
	}
	
	public void llenarHojaTrabajo()
	{
		
		DefaultTableModel model=(DefaultTableModel)TBHojaTrabajo.getModel();
		java.util.List<Object[]> listHojaTrabajoc=new HojaTrabajoStockDAO().ListarHojaTrabajo();
		
		for (Object[] objects : listHojaTrabajoc) {
			model.addRow(new Object[]{"",false,objects[1],new JButton("..."),objects[2],objects[3],objects[4],objects[5],"0",false});	
		}
		
	}
	
}

class JComponentTableCellRendere implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int row, int column) {
		
		  
	    return (JComponent) value;
	  }
	}

