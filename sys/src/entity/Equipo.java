package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the equipo database table.
 * 
 */
@Entity
@Table(name="equipo")
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique=true, nullable=false, length=3)
	private String idequipo;

	@Column(name="desc_corta", length=50)
	private String descCorta;

	@Column(nullable=false, length=75)
	private String descripcion;

	//bi-directional many-to-one association to Subequipo
	@OneToMany(mappedBy="equipo")
	private List<Subequipo> subequipos;

	public Equipo() {
	}

	public String getIdequipo() {
		return this.idequipo;
	}

	public void setIdequipo(String idequipo) {
		this.idequipo = idequipo;
	}

	public String getDescCorta() {
		return this.descCorta;
	}

	public void setDescCorta(String descCorta) {
		this.descCorta = descCorta;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Subequipo> getSubequipos() {
		return this.subequipos;
	}

	public void setSubequipos(List<Subequipo> subequipos) {
		this.subequipos = subequipos;
	}

	public Subequipo addSubequipo(Subequipo subequipo) {
		getSubequipos().add(subequipo);
		subequipo.setEquipo(this);

		return subequipo;
	}

	public Subequipo removeSubequipo(Subequipo subequipo) {
		getSubequipos().remove(subequipo);
		subequipo.setEquipo(null);

		return subequipo;
	}

}