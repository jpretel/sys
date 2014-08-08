package vista.contenedores;

import dao.MotivosDAO;
import entity.Motivo;

public class cntMotivo extends AbstractCntBuscar<Motivo> {
	private static final long serialVersionUID = 1L;
	private MotivosDAO motivoDAO = new MotivosDAO();

	public cntMotivo() {
		super();
		refrescar();
	}

	@Override
	public void cargarDatos(Motivo entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getIdmotivo());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(Motivo entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdmotivo().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		return true;
	}

	@Override
	public Object[] entity2Object(Motivo entity) {
		return new Object[] { entity.getIdmotivo(), entity.getDescripcion() };
	}

	@Override
	public void refrescar() {
		setData(motivoDAO.findAll());
	}
}
