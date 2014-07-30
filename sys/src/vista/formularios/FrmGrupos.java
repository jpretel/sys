package vista.formularios;

import java.util.ArrayList;
import java.util.List;
import vista.utilitarios.ButtonEditor;
import vista.utilitarios.ButtonRenderer;
import vista.utilitarios.MaestroTableModel;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.GrupoDAO;
import dao.SubgrupoDAO;
import entity.Grupo;
import entity.Subgrupo;
import entity.SubgrupoPK;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class FrmGrupos extends AbstractMaestro {

	private static final long serialVersionUID = 1L;
	private JTable tblLista;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JButton btnInsertarLinea;

	private GrupoDAO gdao = new GrupoDAO();
	private SubgrupoDAO sgDAO = new SubgrupoDAO();
 
	private List<Grupo> grupoL = new ArrayList<Grupo>();
	@SuppressWarnings("unused")
	private List<Subgrupo> subgrupoEL = new ArrayList<Subgrupo>();
	
	private Grupo grupoE = new Grupo();
	private JTextField txtDescCorta;
	private JTable tblSubGrupo;
	private DefaultTableModel subgrupo;
	JButton button=new JButton("");
	public FrmGrupos(){
		super("Familia de Productos");
		setBounds(100, 100, 600, 353);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblCodigo = new JLabel("Codigo");
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		//txtCodigo.
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		
		JLabel lblDescCorta = new JLabel("Descripcion corta");
		
		txtDescCorta = new JTextField();
		txtDescCorta.setColumns(10);
		JLabel lblSubgrupos = new JLabel("SubGrupos");
		
		JScrollPane scrollPane_SubGrupo = new JScrollPane();				
		//AutoCompleteDecorator.decorate(txtDescCorta, validValues, true);		
		btnInsertarLinea = new JButton("Insertar Linea");		
		subgrupo = new DefaultTableModel();		
		String columnNames[]=new String[] {"Codigo" , "Descripcion" , "Accion"};
		subgrupo.setColumnIdentifiers(columnNames);
		tblSubGrupo = new JTable(subgrupo);
		scrollPane_SubGrupo.setViewportView(tblSubGrupo);
		tblSubGrupo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		tblSubGrupo.getColumn("Accion").setCellRenderer(new ButtonRenderer());
		ButtonEditor buttonEditor = new ButtonEditor(new JCheckBox());		
		tblSubGrupo.getColumn("Accion").setCellEditor(buttonEditor);
		tblSubGrupo.getColumn("Accion").setPreferredWidth(30);		
		final JTextField textCodigo = new JTextField();
		final JTextField textDescripcoon = new JTextField();
		textDescripcoon.setHorizontalAlignment(JTextField.RIGHT);
		
		final TableColumn column0 = tblSubGrupo.getColumnModel().getColumn(0);
		final TableColumn column1 = tblSubGrupo.getColumnModel().getColumn(1);	
		column0.setCellEditor(new DefaultCellEditor(textCodigo));
		column1.setCellEditor(new DefaultCellEditor(textDescripcoon));
		
		/*aca comienzo*/
		buttonEditor.getButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				int seleccion = JOptionPane.showOptionDialog(null, "Desea Eliminar el Registro Seleccionado", "Informacion del Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Si", "No"}, "Si");
				if (seleccion == 0){								
				
					subgrupo.removeRow(tblSubGrupo.getSelectedRow());	
					
				}
			}
		});
		
	
		btnInsertarLinea.setIcon(new ImageIcon("C:\\SOLUTION\\Graphics\\insdetalle.bmp"));
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCodigo, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(txtCodigo, 108, 108, 108)
							.addGap(130))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescCorta, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtDescCorta, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
							.addGap(107))
						.addComponent(lblSubgrupos, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_SubGrupo, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnInsertarLinea, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
							.addGap(198))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(lblCodigo))
								.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblDescripcion))
								.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblDescCorta))
								.addComponent(txtDescCorta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSubgrupos)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addComponent(scrollPane_SubGrupo, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
							.addGap(11)
							.addComponent(btnInsertarLinea)))
					.addGap(6))
		);
		pnlContenido.setLayout(groupLayout);
	
		btnInsertarLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				final Object fila [] = {"","",};				
				subgrupo.addRow(fila);
			}
		});
		
		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override					
					public void valueChanged(ListSelectionEvent arg0) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setGrupo(getGrupos().get(selectedRow));
						else
							setGrupo(null);
						llenar_datos();						
					}
				});		
		iniciar();
	}
	@Override
	public void nuevo() {
		setGrupo(new Grupo());	
		limpiar_detalle();		
	}
	
	public void limpiar_detalle(){
		while (subgrupo.getRowCount() != 0) {
			subgrupo.removeRow(0);
		}
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		try
		{			
			String lcCodigo = this.txtCodigo.getText();				
			gdao.crear_editar(getGrupo());
			sgDAO.borrarPorGrupo(getGrupo());
			int nFilas = this.subgrupo.getRowCount();		
			for(int i = 0;i < nFilas;i++){
				SubgrupoPK sgpk1 = new SubgrupoPK();
				Subgrupo sg1 = new Subgrupo();
				sgpk1.setGrupoIdgrupo(lcCodigo);
				sgpk1.setIdsubgrupo(this.subgrupo.getValueAt(i, 0).toString());
				sg1.setDescripcion(this.subgrupo.getValueAt(i, 1).toString());
				sg1.setId(sgpk1);
				sg1.setGrupo(getGrupo());			
				sgDAO.create(sg1);
			}
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public boolean validar_detalle(){
		return false;
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub

	}
	@Override
	public void llenar_datos() {
		if(getGrupo() instanceof Grupo){
			this.txtCodigo.setText(getGrupo().getIdgrupo());
			this.txtDescripcion.setText(getGrupo().getDescripcion());
			this.txtDescCorta.setText(getGrupo().getDescCorta());
		}
		else{
			this.txtCodigo.setText(null);
			this.txtDescripcion.setText(null);
			this.txtDescCorta.setText(null);
		}
		llenar_detalle();		
	}
	
	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);
		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (Grupo grupo : getGrupos()) {
			model.addRow(new Object[] { grupo.getIdgrupo(), grupo.getDescripcion() });
		}
		if (getGrupos().size() > 0) {
			setGrupo(getGrupos().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
		llenar_detalle();	
	}
	
	private void llenar_detalle() {
		String Codigo = this.txtCodigo.getText();
		while(subgrupo.getRowCount() != 0){			
				subgrupo.removeRow(0);			
		}		
		for(Subgrupo subgrupoEnt : sgDAO.findAllbyGrupo()){			
			if(Codigo.equals(subgrupoEnt.getId().getGrupoIdgrupo())){
				subgrupo.addRow(new Object[] { subgrupoEnt.getId().getIdsubgrupo(), subgrupoEnt.getDescripcion()});
			}
		}
	}
	@Override
	public void llenar_tablas() {
		setGrupos(gdao.findAll());
	}
	
	public List<Grupo> getGrupos() {
		return this.grupoL;
	}

	public void setGrupos(List<Grupo> grupoL) {
		this.grupoL = grupoL;
	}
	
	public void setGrupo(Grupo grupoE) {
		this.grupoE = grupoE;
	}
	
	public Grupo getGrupo(){
		return this.grupoE;
	}
	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		this.txtDescCorta.setEditable(true);
		this.btnInsertarLinea.setEnabled(true);
		tblLista.setEnabled(false);
		tblSubGrupo.setEnabled(true);		
	}
	@Override
	public void vista_noedicion() {
		this.txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		this.txtDescCorta.setEditable(false);
		this.btnInsertarLinea.setEnabled(false);
		tblLista.setEnabled(true);
		tblSubGrupo.setEnabled(false);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub
		
	}
	//Este separado el dao de la vista
	@Override
	public void llenarDesdeVista() {
		getGrupo().setIdgrupo(this.txtCodigo.getText());
		getGrupo().setDescripcion(this.txtDescripcion.getText());
		getGrupo().setDescCorta(this.txtDescCorta.getText());			
	}
	@Override
	public boolean isValidaVista() {
		// TODO Auto-generated method stub
		return true;
	}
}
