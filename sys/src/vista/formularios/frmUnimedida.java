package vista.formularios;

import java.beans.PropertyVetoException;

import vista.barras.BarraMaestro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.UnimedidaDAO;
import entity.Unimedida;

public class frmUnimedida extends AbstractMaestro {
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtNomenclatura;
	private Unimedida unimedida;
	private UnimedidaDAO udao = new UnimedidaDAO();

	public Unimedida getUnimedida() {		 
		return unimedida;
	}

	public void setUnimedida(Unimedida unimedida) {
		this.unimedida = unimedida;
	}

	public frmUnimedida(String titulo, BarraMaestro barra) {
		super(titulo, barra);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(32, 21, 46, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(32, 46, 67, 14);
		getContentPane().add(lblDescripcion);
		
		JLabel lblNomenclatura = new JLabel("Nomenclatura");
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
		
		txtNomenclatura = new JTextField();
		txtNomenclatura.setColumns(10);
		txtNomenclatura.setBounds(108, 71, 86, 20);
		getContentPane().add(txtNomenclatura);
		// TODO Auto-generated constructor stub
	}

	public void grabar(){
		try {
			if (getEstado().equals(NUEVO)){
				Unimedida unim = new Unimedida();
				setUnimedida(unim);
			}
			getUnimedida().setIdunimedida(this.txtCodigo.getText());
			getUnimedida().setDescripcion(this.txtDescripcion.getText());
			getUnimedida().setNomenclatura(this.txtNomenclatura.getText());			
			udao.crear_editar(getUnimedida());		
			super.grabar();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}	
	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_datos() {
		if (getUnimedida() instanceof Unimedida) {		
			this.txtCodigo.setText(this.getUnimedida().getIdunimedida());
			this.txtDescripcion.setText(this.getUnimedida().getDescripcion());
			this.txtNomenclatura.setText(this.getUnimedida().getNomenclatura());
		} else {			
			this.txtCodigo.setText(null);
			this.txtDescripcion.setText(null);
			this.txtNomenclatura.setText(null);
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
		txtNomenclatura.setEditable(true);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		txtNomenclatura.setEditable(false);
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
		unimedida = (Unimedida)entidad;
		this.setUnimedida(unimedida);
		this.llenar_datos();
		this.vista_noedicion();	
	}

	@Override
	public void nuevo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void llenarDesdeVista() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidaVista() {
		// TODO Auto-generated method stub
		return false;
	}
}
