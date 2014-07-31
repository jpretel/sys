package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the almacen database table.
 * 
 */
@Embeddable
public class AlmacenPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=3)
	private String idsucursal;

	@Column(nullable=false, length=4)
	private String idalmacen;

	public AlmacenPK() {
	}
	public String getIdsucursal() {
		return this.idsucursal;
	}
	public void setIdsucursal(String idsucursal) {
		this.idsucursal = idsucursal;
	}
	public String getIdalmacen() {
		return this.idalmacen;
	}
	public void setIdalmacen(String idalmacen) {
		this.idalmacen = idalmacen;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AlmacenPK)) {
			return false;
		}
		AlmacenPK castOther = (AlmacenPK)other;
		return 
			this.idsucursal.equals(castOther.idsucursal)
			&& this.idalmacen.equals(castOther.idalmacen);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idsucursal.hashCode();
		hash = hash * prime + this.idalmacen.hashCode();
		
		return hash;
	}
}