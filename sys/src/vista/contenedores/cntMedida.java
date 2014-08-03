package vista.contenedores;

import dao.UnimedidaDAO;
import entity.Unimedida;

public class cntMedida extends AbstractCntBuscar<Unimedida> {
	private static final long serialVersionUID = 1L;
	private UnimedidaDAO unimedidaDAO = new UnimedidaDAO();

	public cntMedida() {
		super();
		refrescar();
	}

	@Override
	public void cargarDatos(Unimedida entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getIdunimedida());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(Unimedida entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdunimedida().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		return false;
	}

	@Override
	public Object[] entity2Object(Unimedida entity) {
		return new Object[] { entity.getIdunimedida(), entity.getDescripcion() };
	}

	@Override
	public void refrescar() {
		setData(unimedidaDAO.findAll());
	}
}
