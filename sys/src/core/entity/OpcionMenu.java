package core.entity;

public class OpcionMenu {
	private int posicion;
	private String descripcion;
	private String opcion;
	private int prioridad;
	
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	private String imagen;
}