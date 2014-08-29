package vista.contenedores;

import dao.AlmacenDAO;
import entity.Almacen;
import entity.Sucursal;

public class cntAlmacen extends AbstractCntBuscar<Almacen> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AlmacenDAO almacenDAO = new AlmacenDAO();
	private Sucursal sucursal;

	public cntAlmacen() {
		super();
		refrescar();
	}

	@Override
	public void cargarDatos(Almacen entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getId().getIdalmacen());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(Almacen entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getId().getIdalmacen().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		return false;
	}

	@Override
	public Object[] entity2Object(Almacen entity) {
		return new Object[] { entity.getId().getIdalmacen(),
				entity.getDescripcion() };
	}

	@Override
	public void refrescar() {
		setData(almacenDAO.getPorSucursal(getSucursal()));
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
}
