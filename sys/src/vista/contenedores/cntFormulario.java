package vista.contenedores;
import entity.SysOpcion;
public class cntFormulario extends AbstractCntBuscar<SysOpcion> {
	private static final long serialVersionUID = 1L;

	public cntFormulario() {
		super();
		txtDescripcion.setBounds(107, 0, 220, 20);
		txtCodigo.setBounds(0, 0, 110, 20);
		setLayout(null);
	}

	@Override
	public void cargarDatos(SysOpcion entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getOpcion());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(SysOpcion entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getOpcion().toLowerCase().startsWith(cad) || entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		return false;
	}

	@Override
	public Object[] entity2Object(SysOpcion entity) {
		return new Object[] { entity.getOpcion(), entity.getDescripcion() };
	}

	@Override
	public String getEntityCode(SysOpcion entity) {
		return entity.getOpcion();
	}
}
