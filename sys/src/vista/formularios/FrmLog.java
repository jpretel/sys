package vista.formularios;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;

import dao.LogDAO;
import entity.Log;
import entity.SysOpcion;
import vista.controles.DSGTableModel;
import vista.utilitarios.MaestroTableModel;

public class FrmLog extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblLista = new JTable(new MaestroTableModel());
	JScrollPane scrollPane = new JScrollPane();
	
	Log log = new Log();
	LogDAO ldao = new LogDAO();
	
	protected JPanel pnlContenido;
	
	public static void main(String[] args) {
		new FrmLog();
	}
	
	public FrmLog(){
		setTitle("Log");
		setMaximizable(true);
		setIconifiable(false);
		setClosable(true);
		setVisible(true);
		setResizable(false);
		this.show();
		
		pnlContenido = new JPanel();
		getContentPane().add(pnlContenido, BorderLayout.CENTER);
		setBounds(100, 100, 700, 400);
		
		tblLista = new JTable(new DSGTableModel(new String[] { "Fecha",
				"Usuario", "Form/Accion", "Historia" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				return getEditar();
			}

			@Override
			public void addRow() {
				
			}		
			
		});
		
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
		
		llenar_datos();
	}
	
	public void llenar_datos() {
			
		getDetalleTM().limpiar();	
			
		tblLista.getColumnModel().getColumn(0).setPreferredWidth(80);
		tblLista.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblLista.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblLista.getColumnModel().getColumn(3).setPreferredWidth(300);
		for (Log obj : ldao.findAll()) {	
			getDetalleTM().addRow(
					new Object[] { obj.getDate(), obj.getUsuario(), obj.getNomFormulario() +"/"+ obj.getTipo(), obj.getLog() });
		}
			 
	}
		
	private DSGTableModel getDetalleTM() {
		return (DSGTableModel) tblLista.getModel();
		}
}
