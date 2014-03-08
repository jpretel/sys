package vista.formularios;

import java.beans.PropertyVetoException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.MarcaDAO;
import entity.Marca;
import vista.barras.BarraMaestro;

public class frmMarca extends AbstractMaestro {

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

	public frmMarca(String titulo, BarraMaestro barra) {
		super(titulo, barra);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(32, 21, 46, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(32, 46, 67, 14);
		getContentPane().add(lblDescripcion);
		
		JLabel lblNomenclatura = new JLabel("Nombre Corto");
		lblNomenclatura.setBounds(32, 71, 67, 14);
		getContentPane().add(lblNomenclatura);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(108, 18, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(108, 43, 186, 20);
		getContentPane().add(txtDescripcion);
		
		txtNombreCorto = new JTextField();
		txtNombreCorto.setColumns(10);
		txtNombreCorto.setBounds(108, 71, 86, 20);
		getContentPane().add(txtNombreCorto);
	}
	
	public void grabar(){
		if (getEstado().equals(NUEVO)){
			Marca marc = new Marca();
			setMarca(marc);
		}
		getMarca().setIdmarca(this.txtCodigo.getText());
		getMarca().setDescripcion(this.txtDescripcion.getText());
		getMarca().setNomcorto(this.txtNombreCorto.getText());
		mdao.crear_editar(getMarca());
		super.grabar();
	}

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
	public void setSelected(boolean selected) throws PropertyVetoException {
		controlador.VariablesGlobales.home.getBarraMaestro().setVisible(
				selected);
		if (selected) {
			controlador.VariablesGlobales.home.getBarraMaestro()
					.setFormMaestro(this);
		}
		super.setSelected(selected);
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

}
