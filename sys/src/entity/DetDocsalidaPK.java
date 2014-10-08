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

	private int item;

	public DetDocsalidaPK() {
	}
	
	public long getIdsalida() {
		return iddocsalida;
	}

	public void setIdsalida(long iddocsalida) {
		this.iddocsalida = iddocsalida;
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
		if (!(other instanceof DetDocsalidaPK)) {
			return false;
		}
		DetDocsalidaPK castOther = (DetDocsalidaPK)other;
		return 
			(this.iddocsalida == castOther.iddocsalida);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.item;
		
		return hash;
	}
}