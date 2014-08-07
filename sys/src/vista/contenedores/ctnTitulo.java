package vista.contenedores;

import dao.SysTituloDAO;
import entity.SysTitulo;

public class ctnTitulo extends AbstractCntBuscar<SysTitulo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SysTituloDAO sysTituloDAO = new SysTituloDAO();

	public ctnTitulo() {
		super();
		refrescar();
	}
	
	@Override
	public void cargarDatos(SysTitulo entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getId().getIdtitulo());
			txtDescripcion.setText(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(SysTitulo entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getId().getIdtitulo().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		return false;
	}

	@Override
	public Object[] entity2Object(SysTitulo entity) {
		return new Object[] { entity.getId().getIdtitulo(),
				entity.getDescripcion() };
	}

	@Override
	public void refrescar() {
		setData(sysTituloDAO.findAll());
	}

}
