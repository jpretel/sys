package core.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the modulo database table.
 * 
 */
@Entity
@Table(name="modulo")
@NamedQuery(name="Modulo.findAll", query="SELECT m FROM Modulo m")
public class Modulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;
	
	@Column
	private int posicion;
	
	@Column(length=50)
	private String descripcion;

	@Column(length=50)
	private String icono;
	
	@Column(length=20)
	private String opcion;

	public Modulo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

}