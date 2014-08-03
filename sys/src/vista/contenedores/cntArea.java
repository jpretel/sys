package vista.contenedores;

import dao.AreaDAO;
import entity.Area;

public class cntArea extends AbstractCntBuscar<Area> {
	private static final long serialVersionUID = 1L;
	private AreaDAO areaDAO = new AreaDAO();

	public cntArea() {
		super();
		refrescar();
	}

	@Override
	public void cargarDatos(Area entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getIdarea());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(Area entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdarea().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		return true;
	}

	@Override
	public Object[] entity2Object(Area entity) {
		return new Object[] { entity.getIdarea(), entity.getDescripcion() };
	}

	@Override
	public void refrescar() {
		setData(areaDAO.findAll());
	}
}
