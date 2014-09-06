package vista.contenedores;
import entity.Subgrupo;

public class cntSubGrupo extends AbstractCntBuscar<Subgrupo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void cargarDatos(Subgrupo entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getId().getIdsubgrupo());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(Subgrupo entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getId().getIdsubgrupo().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		return false;
	}

	@Override
	public Object[] entity2Object(Subgrupo entity) {
		return new Object[] { entity.getId().getIdsubgrupo(),
				entity.getDescripcion() };
	}
}
