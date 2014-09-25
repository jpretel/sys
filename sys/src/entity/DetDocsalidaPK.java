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

	private long iddocsalida;

	private String idproducto;

	public DetDocsalidaPK() {
	}
	
	public long getIdsalida() {
		return iddocsalida;
	}

	public void setIdsalida(long iddocsalida) {
		this.iddocsalida = iddocsalida;
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
			(this.iddocsalida == castOther.iddocsalida)
			&& this.idproducto.equals(castOther.idproducto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idproducto.hashCode();
		
		return hash;
	}
}