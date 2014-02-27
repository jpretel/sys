package core.entity;

import java.util.List;

public class TituloMenu {
	
	private int posicion;
	private String descripcion;
	private List<GrupoMenu> grupos;
	
	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public List<GrupoMenu> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoMenu> grupos) {
		this.grupos = grupos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}