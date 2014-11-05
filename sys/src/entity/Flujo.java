package entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@NamedQuery(name="Flujo.findAll", query="SELECT f FROM Flujo f")
public class Flujo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=3)
	private String idflujo;

	@Column(nullable=false, length=75)
	private String descripcion;
		

	public Flujo() {
	}

	public String getIdflujo() {
		return this.idflujo;
	}

	public void setIdflujo(String idflujo) {
		this.idflujo = idflujo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}