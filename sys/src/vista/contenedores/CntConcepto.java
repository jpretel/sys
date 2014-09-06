package vista.contenedores;
import entity.Concepto;

public class CntConcepto extends AbstractCntBuscar<Concepto> {
	public CntConcepto() {
		txtDescripcion.setBounds(46, 0, 144, 20);
		txtCodigo.setBounds(0, 0, 46, 20);
		setLayout(null);
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void cargarDatos(Concepto entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getIdconcepto());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(Concepto entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdconcepto().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		return false;
	}

	@Override
	public Object[] entity2Object(Concepto entity) {
		return new Object[] { entity.getIdconcepto(), entity.getDescripcion() };
	}
}