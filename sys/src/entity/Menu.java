package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idmenu;

	private String descripcion;

	@Column(name="indice_ubi")
	private int indiceUbi;

	private String modulo;

	@Column(name="nombre_form")
	private String nombreForm;

	private String posicion;

	public Menu() {
	}

	public int getIdmenu() {
		return this.idmenu;
	}

	public void setIdmenu(int idmenu) {
		this.idmenu = idmenu;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIndiceUbi() {
		return this.indiceUbi;
	}

	public void setIndiceUbi(int indiceUbi) {
		this.indiceUbi = indiceUbi;
	}

	public String getModulo() {
		return this.modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getNombreForm() {
		return this.nombreForm;
	}

	public void setNombreForm(String nombreForm) {
		this.nombreForm = nombreForm;
	}

	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	public int RetornaIndicexPosicion(String Posicion){
		return this.getIndiceUbi();
	}

}