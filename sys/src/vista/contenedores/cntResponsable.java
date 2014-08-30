package vista.contenedores;

import dao.ResponsableDAO;
import entity.Responsable;

public class cntResponsable extends AbstractCntBuscar<Responsable> {
	private static final long serialVersionUID = 1L;
	private ResponsableDAO responsableDAO = new ResponsableDAO();

	public cntResponsable() {
		super();
		refrescar();
	}
	
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
	//Carga toda la Informacion en el ResulSet
	@Override
	public void refrescar() {
		setData(responsableDAO.findAll());
	}
}
