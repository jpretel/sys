package vista.contenedores;

import dao.ProductoDAO;
import entity.Producto;

public class TxtProducto extends JXTextFieldEntityAC<Producto> {
	ProductoDAO productoDAO = new ProductoDAO();
	
	private static final long serialVersionUID = 1L;

	public TxtProducto() {
		super(getFormulario(),
				new String[] { "Cod. Producto", "Descripción" }, new int[] {
						200, 350 });
		refrescar();
	}

	public void cargarDatos(Producto entity) {
		if (entity == null) {
			setText("");
		} else {
			setText(entity.getIdproductos());
		}
	}
	@Override
	public boolean coincideBusqueda(Producto entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdproductos().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		else
			return false;
	}

	@Override
	public Object[] entity2Object(Producto entity) {
		return new Object[] { entity.getIdproductos(), entity.getDescripcion() };
	}

	public void refrescar() {
		setData(productoDAO.findAll());
	}

	@Override
	public void cargaDatos() {
		cargarDatos(getSeleccionado());
	}
	
	@Override
	public int getMinimoBusqueda() {
		return 1;
	}
}
