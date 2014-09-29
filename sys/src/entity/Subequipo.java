package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the subequipo database table.
 * 
 */
@Entity
@Table(name="subequipo")
@NamedQuery(name="Subequipo.findAll", query="SELECT s FROM Subequipo s")
public class Subequipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubequipoPK id;

	@Column(nullable=false, length=75)
	private String descripcion;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="idequipo", nullable=false)
	private Equipo equipo;

	public Subequipo() {
	}

	public SubequipoPK getId() {
		return this.id;
	}

	public void setId(SubequipoPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}