package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the det_docingreso database table.
 * 
 */
@Embeddable
public class DetDocingresoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private long iddocingreso;

	private int item;

	public DetDocingresoPK() {
	}
	public long getIdingreso() {
		return this.iddocingreso;
	}
	public void setIdingreso(long idingreso) {
		this.iddocingreso = idingreso;
	}
	
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
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
			(this.iddocingreso == castOther.iddocingreso);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.item;
		
		return hash;
	}
}