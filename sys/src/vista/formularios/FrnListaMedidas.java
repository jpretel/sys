package vista.formularios;
import java.util.List;
import dao.UnimedidaDAO;
import entity.Unimedida;
import vista.barras.BarraMaestro;

public class FrnListaMedidas extends AbstractMaestroLista {
	private static final long serialVersionUID = 1L;
	private BarraMaestro barra;
	private Object[] obj;
	private Unimedida unimedida;
	private UnimedidaDAO udao = new UnimedidaDAO(); 
	private List<Unimedida> unimedidaList = udao.findAll();
	private int nFilasM = unimedidaList.size(); 
	public FrnListaMedidas(String titulo, BarraMaestro barra) {
		super(titulo, barra);
		this.barra = barra;
		setBounds(100, 100, 600, 353);
		getContentPane().setLayout(null);
		// Inicializando la lista
		String columnas[] = { "Codigo", "Descripcion", "Nomenclatura"};
		super.inicia_Lista(columnas, obj);
		llenar_lista();		
	}

	@Override
	public void irFormulario() {
		if (RetornarPk() instanceof Object){
			unimedida = udao.find(RetornarPk());
		}
		frmUnimedida frmunimedida = new frmUnimedida("Edicion de Unidades de Medida",
				this.barra);
		super.init(frmunimedida, getModo(), unimedida);

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
			unimedida = new Unimedida();
			unimedida = (Unimedida)unimedidaList.get(i);
			obj = new Object[] {unimedida.getIdunimedida(),
					unimedida.getDescripcion(), unimedida.getNomenclatura()};
			modeloLista.addRow(obj);
		}
	}

	@Override
	public void llenar_tablas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void vista_edicion() {
		
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
