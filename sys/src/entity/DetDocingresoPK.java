package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the det_docingreso database table.
 * 
 */
@Embeddable
public class DetDocingresoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long iddocingreso;

	private String idproducto;

	public DetDocingresoPK() {
	}
	public long getIdingreso() {
		return this.iddocingreso;
	}
	public void setIdingreso(long idingreso) {
		this.iddocingreso = idingreso;
	}
	public String getIdproducto() {
		return this.idproducto;
	}
	public void setIdproducto(String idproducto) {
		this.idproducto = idproducto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetDocingresoPK)) {
			return false;
		}
		DetDocingresoPK castOther = (DetDocingresoPK)other;
		return 
			(this.iddocingreso == castOther.iddocingreso)
			&& this.idproducto.equals(castOther.idproducto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idproducto.hashCode();
		
		return hash;
	}
}