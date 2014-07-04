package core.entity;

public class Consultar {
	
	private String tipo;
	private String descripcion;
	
	
	public Consultar(String tipo, String descripcion) {
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
