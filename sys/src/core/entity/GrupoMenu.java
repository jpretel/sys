package core.entity;

import java.util.List;

public class GrupoMenu {
	private int posicion;
	private String descripcion;
	private String imagen;
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public List<OpcionMenu> getOpciones() {
		return opciones;
	}
	public void setOpciones(List<OpcionMenu> opciones) {
		this.opciones = opciones;
	}
	private List<OpcionMenu> opciones;
}