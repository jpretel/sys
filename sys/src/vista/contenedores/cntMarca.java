package vista.contenedores;

import dao.MarcaDAO;
import entity.Marca;

public class cntMarca extends AbstractCntBuscar<Marca> {
	private static final long serialVersionUID = 1L;
	private MarcaDAO marcaDAO = new MarcaDAO();

	public cntMarca() {
		super();
		refrescar();
	}

	@Override
	public void cargarDatos(Marca entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getIdmarca());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(Marca entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdmarca().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		return false;
	}

	@Override
	public Object[] entity2Object(Marca entity) {
		return new Object[] { entity.getIdmarca(), entity.getDescripcion() };
	}

	@Override
	public void refrescar() {
		setData(marcaDAO.findAll());
	}

}
