package vista.formularios;


import java.awt.Component;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import dao.MarcaDAO;
import entity.Marca;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;

import vista.utilitarios.MaestroTableModel;

public class FrmMarca extends AbstractMaestro {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtNombreCorto;
	private JTable tblLista;
	private Marca marca;
	private MarcaDAO mdao= new MarcaDAO();
	private List<Marca> Marcas = new ArrayList<Marca>();
	
	public List<Marca> getMarcas() {
		return Marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		Marcas = marcas;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public FrmMarca() {
		super("Marca de Productos");
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(32, 21, 46, 14);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(32, 46, 67, 14);
		
		JLabel lblNomenclatura = new JLabel("Nombre Corto");
		lblNomenclatura.setBounds(32, 71, 67, 14);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(108, 18, 86, 20);
		txtCodigo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(108, 43, 186, 20);
		
		txtNombreCorto = new JTextField();
		txtNombreCorto.setColumns(10);
		txtNombreCorto.setBounds(108, 71, 86, 20);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNomenclatura)
							.addGap(18)
							.addComponent(txtNombreCorto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodigo)
								.addComponent(lblDescripcion))
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()					
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCodigo, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(50)
								.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescripcion)
								.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomenclatura)
								.addComponent(txtNombreCorto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		pnlContenido.setLayout(groupLayout);
		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override					
					public void valueChanged(ListSelectionEvent arg0) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setMarca(getMarcas().get(selectedRow));
						else
							setMarca(null);
						llenar_datos();						
					}
				});
		iniciar();
	}
	
	public void grabar(){
		 getMdao().crear_editar(getMarca());
	}
	public MarcaDAO getMdao() {
		return mdao;
	}

	public void setMdao(MarcaDAO mdao) {
		this.mdao = mdao;
	}

	public void llenarDesdeVista() {
		getMarca().setIdmarca(this.txtCodigo.getText());
		getMarca().setDescripcion(this.txtDescripcion.getText());
		getMarca().setNomcorto(this.txtNombreCorto.getText());
	};

	@Override
	public void anular() {
		// TODO Auto-generated method stub
	}

	@Override
	public void llenar_datos() {
		if(getMarca() instanceof Marca){
			this.txtCodigo.setText(getMarca().getIdmarca());
			this.txtDescripcion.setText(getMarca().getDescripcion());
			this.txtNombreCorto.setText(getMarca().getNomcorto());
		}else{
			this.txtCodigo.setText(null);
			this.txtDescripcion.setText(null);
			this.txtNombreCorto.setText(null);
		}

	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);
		MaestroTableModel modelo = (MaestroTableModel)tblLista.getModel();
		modelo.limpiar();
		for(Marca marca: getMarcas()){
			modelo.addRow(new Object[] {marca.getIdmarca(),marca.getDescripcion()});
		}
		if(getMarcas().size() > 0){
			setMarca(getMarcas().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setMarcas(mdao.findAll());

	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		txtNombreCorto.setEditable(true);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		txtNombreCorto.setEditable(false);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualiza_objeto(Object entidad) {
		marca = (Marca)entidad;
		this.setMarca(marca);
		this.llenar_datos();
		this.vista_noedicion();
		
	}

	@Override
	public void nuevo() {
		setMarca(new Marca());
	}

	@Override
	public boolean isValidaVista() {
		if (this.txtCodigo.getText().trim().isEmpty())
			return false;
		if (this.txtDescripcion.getText().trim().isEmpty())
			return false;
		return true;
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
}
