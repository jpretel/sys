package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the subgrupo database table.
 * 
 */
@Embeddable
public class SubgrupoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(length=3)
	private String idsubgrupo;

	@Column(name="grupo_idgrupo", insertable=false, updatable=false, length=3)
	private String grupoIdgrupo;

	public SubgrupoPK() {
	}
	public String getIdsubgrupo() {
		return this.idsubgrupo;
	}
	public void setIdsubgrupo(String idsubgrupo) {
		this.idsubgrupo = idsubgrupo;
	}
	public String getGrupoIdgrupo() {
		return this.grupoIdgrupo;
	}
	public void setGrupoIdgrupo(String grupoIdgrupo) {
		this.grupoIdgrupo = grupoIdgrupo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubgrupoPK)) {
			return false;
		}
		SubgrupoPK castOther = (SubgrupoPK)other;
		return 
			this.idsubgrupo.equals(castOther.idsubgrupo)
			&& this.grupoIdgrupo.equals(castOther.grupoIdgrupo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idsubgrupo.hashCode();
		hash = hash * prime + this.grupoIdgrupo.hashCode();
		
		return hash;
	}
}