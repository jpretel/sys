package vista.contenedores;
import entity.Responsable;

public class cntResponsable extends AbstractCntBuscar<Responsable> {
	public cntResponsable() {
		txtDescripcion.setBounds(46, 0, 144, 20);
		txtCodigo.setBounds(0, 0, 46, 20);
		setLayout(null);
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void cargarDatos(Responsable entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getIdresponsable());
			txtDescripcion.setText(entity.getNombre());
		}
	}
	//opciones de Busqueda
	@Override
	public boolean coincideBusqueda(Responsable entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdresponsable().toLowerCase().startsWith(cad)
				|| entity.getNombre().toLowerCase().startsWith(cad))
			return true;
		return false;
	}
	//Informacion que se carga en el Windows Popup
	@Override
	public Object[] entity2Object(Responsable entity) {
		return new Object[] { entity.getIdresponsable(), entity.getNombre()};
	}
}
