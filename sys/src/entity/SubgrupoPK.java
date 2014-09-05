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

	@Column(nullable=false, length=3)
	private String idsubgrupo;

	@Column(name="idgrupo", length=3)
	private String idgrupo;

	public String getIdgrupo() {
		return idgrupo;
	}
	public void setIdgrupo(String idgrupo) {
		this.idgrupo = idgrupo;
	}
	public SubgrupoPK() {
	}
	public String getIdsubgrupo() {
		return this.idsubgrupo;
	}
	public void setIdsubgrupo(String idsubgrupo) {
		this.idsubgrupo = idsubgrupo;
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
			&& this.idgrupo.equals(castOther.idgrupo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idsubgrupo.hashCode();
		hash = hash * prime + this.idgrupo.hashCode();
		
		return hash;
	}
}