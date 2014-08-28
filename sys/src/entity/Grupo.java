package entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=3)
	private String idgrupo;

	@Column(name="desc_corta", length=50)
	private String descCorta;

	@Column(nullable=false, length=75)
	private String descripcion;

	//bi-directional many-to-one association to Subgrupo
	@OneToMany(mappedBy="grupo")
	private List<Subgrupo> subgrupos;

	public Grupo() {
	}

	public String getIdgrupo() {
		return this.idgrupo;
	}

	public void setIdgrupo(String idgrupo) {
		this.idgrupo = idgrupo;
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

	public List<Subgrupo> getSubgrupos() {
		return this.subgrupos;
	}

	public void setSubgrupos(List<Subgrupo> subgrupos) {
		this.subgrupos = subgrupos;
	}

	public Subgrupo addSubgrupo(Subgrupo subgrupo) {
		getSubgrupos().add(subgrupo);
		subgrupo.setGrupo(this);

		return subgrupo;
	}

	public Subgrupo removeSubgrupo(Subgrupo subgrupo) {
		getSubgrupos().remove(subgrupo);
		subgrupo.setGrupo(null);

		return subgrupo;
	}

}