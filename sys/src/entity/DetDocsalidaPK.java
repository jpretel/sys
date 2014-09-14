package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the det_docingreso database table.
 * 
 */
@Embeddable
public class DetDocsalidaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private long idsalida;

	@Column(unique=true, nullable=false, length=15)
	private String idproducto;

	public DetDocsalidaPK() {
	}
	
	public long getIdsalida() {
		return idsalida;
	}

	public void setIdsalida(long idsalida) {
		this.idsalida = idsalida;
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
		if (!(other instanceof DetDocsalidaPK)) {
			return false;
		}
		DetDocsalidaPK castOther = (DetDocsalidaPK)other;
		return 
			(this.idsalida == castOther.idsalida)
			&& this.idproducto.equals(castOther.idproducto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idproducto.hashCode();
		
		return hash;
	}
}