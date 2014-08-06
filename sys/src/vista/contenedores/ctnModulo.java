package vista.contenedores;

import dao.SysModuloDAO;
import entity.SysModulo;

public class ctnModulo extends AbstractCntBuscar<SysModulo>{

	/**
	 * gg
	 */
	private static final long serialVersionUID = 1L;
	SysModuloDAO sysModuloDAO = new SysModuloDAO();

	public ctnModulo() {
		super();
		refrescar();
	}
	@Override
	public void cargarDatos(SysModulo entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getIdmodulo());
			txtDescripcion.setText(entity.getDescripcion());
		}
		
	}

	@Override
	public boolean coincideBusqueda(SysModulo entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdmodulo().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad)) {
			return true;
		}
		return false;
	}

	@Override
	public Object[] entity2Object(SysModulo entity) {
		return new Object[] { entity.getIdmodulo(), entity.getDescripcion() };
	}

	@Override
	public void refrescar() {
		setData(sysModuloDAO.findAll());
	}

}
