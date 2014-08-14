package vista.contenedores;

import dao.SysFormularioDAO;
import entity.SysFormulario;

public class TxtSysFormulario extends JXTextFieldEntityAC<SysFormulario> {
	SysFormularioDAO sysformdao = new SysFormularioDAO();
	
	private static final long serialVersionUID = 1L;

	public TxtSysFormulario() {
		super(getFormulario(),
				new String[] { "Cod. Formulario", "Descripción" }, new int[] {
						200, 350 });
		refrescar();
	}

	public void cargarDatos(SysFormulario entity) {
		if (entity == null) {
			setText("");
		} else {
			setText(entity.getIdformulario());
		}
	}

	@Override
	public boolean coincideBusqueda(SysFormulario entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdformulario().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		else
			return false;
	}

	@Override
	public Object[] entity2Object(SysFormulario entity) {
		return new Object[] { entity.getIdformulario(), entity.getDescripcion() };
	}

	public void refrescar() {
		setData(sysformdao.findAll());
	}

	@Override
	public void cargaDatos() {
		cargarDatos(getSeleccionado());
	}
	
	@Override
	public int getMinimoBusqueda() {
		return 1;
	}
}
