package vista.contenedores;

import dao.SucursalDAO;
import entity.Sucursal;

public class cntSucursal extends AbstractCntBuscar<Sucursal> {
	private static final long serialVersionUID = 1L;
	private SucursalDAO sucursalDAO = new SucursalDAO();

	public cntSucursal() {
		super();
		refrescar();
	}

	@Override
	public void cargarDatos(Sucursal entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getIdsucursal());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(Sucursal entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdsucursal().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad)) {
			return true;
		}
		return false;
	}

	@Override
	public Object[] entity2Object(Sucursal entity) {
		return new Object[] { entity.getIdsucursal(), entity.getDescripcion() };
	}

	@Override
	public void refrescar() {
		setData(sucursalDAO.findAll());
	}
}
