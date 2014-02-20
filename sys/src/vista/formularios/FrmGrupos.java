package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import vista.barras.BarraMaestro;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.GrupoDAO;
import entity.Grupo;

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
	private List<Grupo> grupoL = new ArrayList<Grupo>();
	private Grupo grupoE = new Grupo();
	private JTextField txtDescCorta;
	private JTable tblSubGrupo;
	private DefaultTableModel subgrupo;
	public FrmGrupos(BarraMaestro barra){
		super("Grupo de Productos",barra);
		setBounds(100, 100, 600, 353);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblCodigo = new JLabel("Codigoasasa");
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		
		JLabel lblDescCorta = new JLabel("Descripcion corta");
		
		txtDescCorta = new JTextField();
		txtDescCorta.setColumns(10);
		JLabel lblSubgrupos = new JLabel("SubGrupos");
		
		JScrollPane scrollPane_SubGrupo = new JScrollPane();
		
		subgrupo = new DefaultTableModel();	
		subgrupo.addColumn("Codigo");
		subgrupo.addColumn("Descripcion");
		tblSubGrupo = new JTable(subgrupo);
		scrollPane_SubGrupo.setViewportView(tblSubGrupo);
		tblSubGrupo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
		
		btnInsertarLinea = new JButton("Insertar Linea");	
	
		btnInsertarLinea.setIcon(new ImageIcon("C:\\SOLUTION\\Graphics\\insdetalle.bmp"));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
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
		getContentPane().setLayout(groupLayout);
		
		btnInsertarLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fila [] = {"",""};
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
		super.nuevo();

	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		getGrupo().setIdgrupo(this.txtCodigo.getText());
		getGrupo().setDescripcion(this.txtDescripcion.getText());
		getGrupo().setDescCorta(this.txtDescCorta.getText());
		gdao.crear_editar(getGrupo(), getGrupo().getIdgrupo());
		super.grabar();
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
		
	}
	@Override
	public void vista_noedicion() {
		this.txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		this.txtDescCorta.setEditable(false);
		this.btnInsertarLinea.setEnabled(false);
		tblLista.setEnabled(true);
		
	}
	@Override
	protected void actualizaBarra() {
		getBarra().setVisible(isSelected());
		if (isSelected()) {
			getBarra().setFormMaestro(this);
		}
		if (getEstado().equals(VISTA)) {
			getBarra().enVista();
		} else {
			getBarra().enEdicion();
		}
	}
}
