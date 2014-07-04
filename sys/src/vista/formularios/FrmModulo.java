package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import dao.ModuloDAO;
import entity.Modulo;

import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import vista.barras.BarraMaestro;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmModulo extends AbstractMaestro{
	
	private static final long serialVersionUID = 1L;

	private Modulo modulo;

	private ModuloDAO moduloDAO = new ModuloDAO();

	private List<Modulo> modulos = new ArrayList<Modulo>();
	private JTable tblLista;
	private JTextField txtID;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	public GroupLayout groupLayout = new GroupLayout(getContentPane());

	JLabel lblID = new JLabel("ID:");
	JLabel lblCdigo = new JLabel("Codigo");
	JLabel lblDescripcin = new JLabel("Descipcion");
	JScrollPane scrollPane = new JScrollPane();
	public FrmModulo(BarraMaestro barra) {
		super("Ingreso de Modulos", barra);
		
		
		lblID.setBounds(227, 11, 46, 14);

		txtID = new JTextField();
		txtID.setBounds(276, 8, 122, 20);
		txtID.setColumns(10);
		
		
		lblCdigo.setBounds(227, 11, 46, 14);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(276, 8, 122, 20);
		txtCodigo.setColumns(10);

		
		lblDescripcin.setBounds(227, 36, 75, 14);

		
		scrollPane.setBounds(10, 10, 207, 273);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(286, 33, 122, 20);
			
		//groupLayout.setHorizontalGroup(alineamientoHorizontal());
		//groupLayout.setVerticalGroup(alineamientoVertical());
		getContentPane().setLayout(groupLayout);
		
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblID)
							.addComponent(lblCdigo)
							.addComponent(lblDescripcin))
						.addGap(5)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtID, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
								.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
							//.addGroup(groupLayout.createSequentialGroup()
								.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addGap(44))
						.addContainerGap())
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(26)
								
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblCdigo)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
											.addComponent(txtID, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblID))
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
											.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblDescripcin)))))
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)))
						.addContainerGap())
			);

		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setmodulo(getmodulos().get(selectedRow));
						else
							setmodulo(null);
						llenar_datos();
					}
				});
		iniciar();
	}
	
	public ParallelGroup alineamientoHorizontal(){
		ParallelGroup parallelGroup = groupLayout.createParallelGroup(Alignment.LEADING);
		
		SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
		sequentialGroup.addContainerGap();	
		sequentialGroup.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE);
		sequentialGroup.addPreferredGap(ComponentPlacement.UNRELATED);		
		//parallelGroup.addGroup(sequentialGroup);
		
		SequentialGroup sequentialGroup2 = groupLayout.createSequentialGroup();
		sequentialGroup2.addComponent(lblCdigo);
		sequentialGroup2.addComponent(lblDescripcin);
		//parallelGroup.addGroup(sequentialGroup).addGap(5);		
		
		SequentialGroup sequentialGroup3 = groupLayout.createSequentialGroup();
		sequentialGroup3.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE);
		
		SequentialGroup sequentialGroup4 = groupLayout.createSequentialGroup();		
		//parallelGroup.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE);
		//sequentialGroup4 = groupLayout.createSequentialGroup();
		sequentialGroup4.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE);
		sequentialGroup4.addGap(44);
		
		
		//parallelGroup.addGroup(sequentialGroup.addContainerGap());
		
		
		parallelGroup.addGroup(sequentialGroup.addGroup(sequentialGroup2).addGap(5).addGroup(sequentialGroup3.addGroup(sequentialGroup4)).addContainerGap());
		
		
		return parallelGroup;
	}
	
	public ParallelGroup alineamientoVertical(){
		
		ParallelGroup parallelGroup = groupLayout.createParallelGroup(Alignment.LEADING);
		SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();	
		
		
		SequentialGroup sequentialGroup2 = groupLayout.createSequentialGroup();
		sequentialGroup2.addGap(26);
		//parallelGroup.addGroup(sequentialGroup);
		
		SequentialGroup sequentialGroup3 = groupLayout.createSequentialGroup();
		sequentialGroup3.addComponent(lblCdigo);
		//parallelGroup.addGroup(sequentialGroup);
		
		SequentialGroup sequentialGroup4 = groupLayout.createSequentialGroup();
		sequentialGroup4.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
		sequentialGroup4.addPreferredGap(ComponentPlacement.RELATED);
		//parallelGroup.addGroup(sequentialGroup);
		
		SequentialGroup sequentialGroup5 = groupLayout.createSequentialGroup();
		sequentialGroup5.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
		sequentialGroup5.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE);
		//parallelGroup.addGroup(sequentialGroup);
		
		SequentialGroup sequentialGroup6 = groupLayout.createSequentialGroup();
		sequentialGroup6.addContainerGap();
		sequentialGroup6.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE);
		
		//parallelGroup.addGroup(sequentialGroup.addGroup(sequentialGroup2.addGroup(sequentialGroup3).addGroup(sequentialGroup4).addGroup(sequentialGroup5)).addGroup(sequentialGroup6).addContainerGap());
		
		return parallelGroup;
	}

	@Override
	public void nuevo() {
		setmodulo(new Modulo());
		super.nuevo();
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {		
		getmodulo().setId(Integer.parseInt(txtID.getText()));
		getmodulo().setDescripcion(txtDescripcion.getText());
		getmodulo().setOpcion(txtCodigo.getText());
		getmoduloDAO().crear_editar(getmodulo());
		super.grabar();
	}

	@Override
	public void eliminar() {
		setEstado(VISTA);
		vista_noedicion();
	}

	public Modulo getmodulo() {
		return modulo;
	}

	public void setmodulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public ModuloDAO getmoduloDAO() {
		return moduloDAO;
	}

	public void setmoduloDAO(ModuloDAO moduloDAO) {
		this.moduloDAO = moduloDAO;
	}
		
	@Override
	public void llenar_datos() {
		if (getmodulo() != null) {
			txtID.setText(""+getmodulo().getId());
			txtCodigo.setText(getmodulo().getOpcion());
			txtDescripcion.setText(getmodulo().getDescripcion());
		} else {
			txtID.setText("");
			txtCodigo.setText("");
			txtDescripcion.setText("");
		}
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (Modulo modulo : getmodulos()) {
			model.addRow(new Object[] { modulo.getId(), modulo.getOpcion() , modulo.getDescripcion()});
		}
		if (getmodulos().size() > 0) {
			setmodulo(getmodulos().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setmodulos(getmoduloDAO().findAll());
	}

	public List<Modulo> getmodulos() {
		return modulos;
	}

	public void setmodulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtID.setEditable(true);
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		tblLista.setEnabled(false);
	}

	@Override
	public void vista_noedicion() {
		txtID.setEditable(false);
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
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

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub
	}

}
