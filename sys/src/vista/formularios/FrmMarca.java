package vista.formularios;


import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.MarcaDAO;
import entity.Marca;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmMarca extends AbstractMaestro {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtNombreCorto;
	private Marca marca;
	private MarcaDAO mdao= new MarcaDAO();
	
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public FrmMarca(String titulo) {
		super(titulo);
		
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
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomenclatura)
						.addComponent(lblDescripcion)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCodigo)
							.addGap(106)
							.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtNombreCorto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(51))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDescripcion))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(txtNombreCorto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(lblNomenclatura)
					.addContainerGap(182, Short.MAX_VALUE))
		);
		pnlContenido.setLayout(groupLayout);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_tablas() {
		// TODO Auto-generated method stub

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

}
