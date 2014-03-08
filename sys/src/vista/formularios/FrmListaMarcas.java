package vista.formularios;

import java.util.List;

import dao.MarcaDAO;
import entity.Marca;
import vista.barras.BarraMaestro;

public class FrmListaMarcas extends AbstractMaestroLista {
	private static final long serialVersionUID = 1L;
	private BarraMaestro barra;
	private Object[] obj;
	private Marca marca;
	private MarcaDAO mdao = new MarcaDAO();
	private List<Marca> marcaList = mdao.findAll();
	private int nFilasM = marcaList.size();
	public FrmListaMarcas(String titulo, BarraMaestro barra) {
		super(titulo, barra);
		this.barra = barra;
		setBounds(100, 100, 600, 353);
		getContentPane().setLayout(null);
		// Inicializando la lista
		String columnas[] = { "Codigo", "Descripcion", "Nombre Corto"};
		super.inicia_Lista(columnas, obj);
		llenar_lista();		
	}

	@Override
	public void irFormulario() {
		if (RetornarPk() instanceof Object){
			marca = mdao.find(RetornarPk());
		}
		frmMarca frmmarca = new frmMarca("Edicion de Marcas de Productos",
				this.barra);
		super.init(frmmarca, getModo(), marca);
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
		for (int i = 0; i < nFilasM; i++) {
			marca = new Marca();
			marca = (Marca)marcaList.get(i);
			obj = new Object[] {marca.getIdmarca(),
					marca.getDescripcion(), marca.getNomcorto()};
			modeloLista.addRow(obj);
		}
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
