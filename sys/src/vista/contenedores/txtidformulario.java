package vista.contenedores;
import dao.SysOpcionDAO;
import entity.SysOpcion;

public class txtidformulario extends AbstractTxtBuscar<SysOpcion>{
	SysOpcionDAO sysopcdao = new SysOpcionDAO(); 
	private static final long serialVersionUID = 1L;
	private String descripcion;
	
	public txtidformulario() {
		super();
		refrescar();
	}

	@Override
	public void cargarDatos(SysOpcion entity) {
		if(entity == null )
			txtCodigo.setText("");
		else{			
			setText(entity.getId().getIdopcion());
			setDescripcion(entity.getDescripcion());
		}
	}

	@Override
	public boolean coincideBusqueda(SysOpcion entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getOpcion().toLowerCase().startsWith(cad) || entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		else
			return false;
	}

	@Override
	public Object[] entity2Object(SysOpcion entity) {
		return new Object[] { entity.getId().getIdopcion(),entity.getDescripcion()};
	}

	@Override
	public void refrescar() {
		setData(sysopcdao.findAll());
		
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
