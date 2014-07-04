package vista.formularios;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import core.dao.ConsultaRUC;
import core.entity.Consultar;
import core.security.Encryption;
import dao.UsuarioDAO;
import entity.Usuario;
import vista.MainFrame;
import vista.barras.BarraMaestro;
import vista.utilitarios.MaestroTableModel;

public class FrmConsultarRUC extends AbstractMaestro implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Consultar cons;
	private static List<Consultar> consulta = new ArrayList<Consultar>();
	
	private JTable tblLista;
	private static DefaultTableModel dtm= new DefaultTableModel();
	
	private JTextField txtRUC = new JTextField(8);
	private JButton btnBuscar = new JButton("Buscar");
	public GroupLayout groupLayout = new GroupLayout(getContentPane());

	JLabel lblRUC = new JLabel("RUC:");
	JScrollPane scrollPane = new JScrollPane();
	
	public FrmConsultarRUC(BarraMaestro barra){
		super("..::Consultar RUC::..", barra);
		
		

		tblLista = new JTable(dtm);
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				//dtm.addColumn("Tipo");
				//dtm.addColumn("Descripcion");
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblRUC)
						.addComponent(txtRUC)
						.addComponent(btnBuscar)
				)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
				)
		);
		
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(lblRUC)
						.addComponent(txtRUC)
						.addComponent(btnBuscar)
				)
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
				)
		);
		
		getContentPane().setLayout(groupLayout);
		
		btnBuscar.addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent evento) {
		String[] listaRUC, list1, list2;
		int cont = 0;
		ConsultaRUC cons = new ConsultaRUC();		
		consulta = cons.Consulta(txtRUC.getText());		
		//list = new String[listaRUC.length];		
		
		Iterator<Consultar> nombreIterator = consulta.iterator();
		list1 = new String[consulta.size()];
		list2 = new String[consulta.size()];
		
		while(nombreIterator.hasNext()){
			
			Consultar elemento = nombreIterator.next();
			
			list1[cont] = elemento.getTipo();	
			list2[cont] = elemento.getDescripcion();
			//if(elemento.getTipo().equals("Padrones :")){
				//dtm.addRow(new Object[] { elemento.getTipo(), elemento.getDescripcion()});
			//}
			//System.out.println(list1[cont] + " " + list2[cont]);
			cont++;
		}
		
		int partida;
		int i;
		for(i=0; i<list1.length-1; i++){
			if(list1[i].equals("Padrones :")){
				partida = i;
				
				for(int b=i+1; b<list1.length;b++){
					dtm.addRow(new Object[] {list1[b], list2[b]});
					
					
				}
				break;
			}
		}
		
	}

	@Override
	public void anular() {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vista_noedicion() {
		// TODO Auto-generated method stub
		
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
	protected void actualizaBarra() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
