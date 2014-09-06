package vista.contenedores;
import entity.Grupo;

public class cntGrupo extends AbstractCntBuscar<Grupo> {
	private static final long serialVersionUID = 1L;

	@Override
	public void cargarDatos(Grupo entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getIdgrupo());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(Grupo entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdgrupo().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad)) {
			return true;
		}
		return false;
	}

	@Override
	public Object[] entity2Object(Grupo entity) {
		return new Object[] { entity.getIdgrupo(), entity.getDescripcion() };
	}
}
