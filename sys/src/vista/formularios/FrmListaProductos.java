package vista.formularios;

import java.util.List;

import dao.ProductoDAO;
import entity.Grupo;
import entity.Producto;
import entity.Subgrupo;

public class FrmListaProductos extends AbstractMaestroLista {
	private static final long serialVersionUID = 1L;

	private ProductoDAO pdao = new ProductoDAO();
	private Object[] obj;
	private List<Producto> prodList = pdao.findAll();
	private Producto producto;

	public FrmListaProductos() {
		super();
		String columnas[] = { "Codigo", "Producto", "Grupo de Producto",
				"Subgrupo de Producto" };
		super.inicia_Lista(columnas, obj);
		llenar_lista();
	}

	@Override
	public void irFormulario(String estado) {

		if (RetornarPk() instanceof Object) {
			producto = pdao.find(RetornarPk());
		}

		FrmProductos frmproductos = new FrmProductos();

		init(frmproductos, estado, producto);
	}

	@Override
	public void llenar_lista() {
		for (Producto p : prodList) {
			Subgrupo sg = p.getSubgrupo();
			Grupo g = (sg == null) ? null : sg.getGrupo();

			modeloLista.addRow(new Object[] { p.getIdproductos(),
					p.getDescripcion(),
					(g == null) ? null : g.getDescripcion(),
					(sg == null) ? null : sg.getDescripcion() });
		}
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
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
	public void llenarDesdeVista() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValidaVista() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub

	}
}
