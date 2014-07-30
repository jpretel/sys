package core.inicio;

public class SysCfgInicio {
	private String servidor;
	private String base_datos;
	private String usuario;
	private String clave;

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getBase_datos() {
		return base_datos;
	}

	public void setBase_datos(String base_datos) {
		this.base_datos = base_datos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getURL(int code_manager) {
		if (code_manager == ConectionManager._mysql)
			return "jdbc:mysql://" + getServidor() + "/" + getBase_datos();
		return null;
	}

	@Override
	public String toString() {
		return getServidor() + " - " + getBase_datos() + "| Usr:"
				+ getUsuario() + ", Pass: " + getClave();
	}
}
