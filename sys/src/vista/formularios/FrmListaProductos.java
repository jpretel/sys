package vista.formularios;
import java.util.List;
import dao.ProductoDAO;
import entity.Grupo;
import entity.Producto;
import entity.Subgrupo;
import vista.barras.BarraMaestro;

public class FrmListaProductos extends AbstractMaestroLista {
	private static final long serialVersionUID = 1L;
	private BarraMaestro barra;
	private ProductoDAO pdao = new ProductoDAO();
	private Object[] obj;
	private List<Producto> prodList = pdao.findAllbyProducto();
	private int nFilasP = prodList.size();
	private Producto producto;
	private Subgrupo sg;
	private Grupo g;
	public FrmListaProductos(String titulo, BarraMaestro barra) {
		super("Lista de Productos", barra);
		this.barra = barra;
		setBounds(100, 100, 600, 353);
		getContentPane().setLayout(null);
		// Inicializando la lista
		String columnas[] = { "Codigo", "Producto", "Grupo de Producto",
				"Subgrupo de Producto" };
		super.inicia_Lista(columnas, obj);
		llenar_lista();
	}
	
	@Override
	public void irFormulario() {		
		if (RetornarPk() instanceof Object){
			producto = pdao.find(RetornarPk());
		}
		FrmProductos frmproductos = new FrmProductos("Edicion de Productos",
				this.barra);
		super.init(frmproductos, getModo(), producto);
	}

	@Override
	public void llenar_lista() {
		for (int i = 0; i < nFilasP; i++) {
			producto = new Producto();
			sg = new Subgrupo();
			g = new Grupo();
			producto = (Producto) prodList.get(i);
			sg = producto.getSubgrupo();
			g = sg.getGrupo();
			obj = new Object[] {producto.getIdproductos(),
					producto.getDescripcion(), g.getDescripcion(),
					sg.getDescripcion() };
			modeloLista.addRow(obj);
		}
	}

	@Override
	public void vista_noedicion() {

	}

	@Override
	public void vista_edicion() {
		// TODO Auto-generated method stub
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
	public void llenar_tablas() {
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
