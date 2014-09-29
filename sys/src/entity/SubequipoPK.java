package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the subequipo database table.
 * 
 */
@Embeddable
public class SubequipoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=3)
	private String idsubequipo;

	@Column(name="equipo_idequipo", unique=true, nullable=false, length=3)
	private String equipoIdequipo;

	public SubequipoPK() {
	}
	public String getIdsubequipo() {
		return this.idsubequipo;
	}
	public void setIdsubequipo(String idsubequipo) {
		this.idsubequipo = idsubequipo;
	}
	public String getEquipoIdequipo() {
		return this.equipoIdequipo;
	}
	public void setEquipoIdequipo(String equipoIdequipo) {
		this.equipoIdequipo = equipoIdequipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubequipoPK)) {
			return false;
		}
		SubequipoPK castOther = (SubequipoPK)other;
		return 
			this.idsubequipo.equals(castOther.idsubequipo)
			&& this.equipoIdequipo.equals(castOther.equipoIdequipo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idsubequipo.hashCode();
		hash = hash * prime + this.equipoIdequipo.hashCode();
		
		return hash;
	}
}