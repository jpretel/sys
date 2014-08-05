package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Moneda
 *
 */
@Entity
@Table(name = "moneda")
public class Moneda implements Serializable {

	@Id
	@Column(length = 2)
	private String idmoneda;
	@Column(length = 75)
	private String descripcion;
	@Column(length = 10)
	private String simbolo;
	private static final long serialVersionUID = 1L;

	public Moneda() {
		super();
	}

	public String getIdmoneda() {
		return this.idmoneda;
	}

	public void setIdmoneda(String idmoneda) {
		this.idmoneda = idmoneda;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSimbolo() {
		return this.simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

}
