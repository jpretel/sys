package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the documento_numero database table.
 * 
 */
@Embeddable
public class DocumentoNumeroPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=3)
	private String iddocumento;

	@Column(nullable=false, length=3)
	private String idptoemision;

	@Column(nullable=false, length=4)
	private String serie;

	public DocumentoNumeroPK() {
	}
	public String getIddocumento() {
		return this.iddocumento;
	}
	public void setIddocumento(String iddocumento) {
		this.iddocumento = iddocumento;
	}
	public String getIdptoemision() {
		return this.idptoemision;
	}
	public void setIdptoemision(String idptoemision) {
		this.idptoemision = idptoemision;
	}
	public String getSerie() {
		return this.serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DocumentoNumeroPK)) {
			return false;
		}
		DocumentoNumeroPK castOther = (DocumentoNumeroPK)other;
		return 
			this.iddocumento.equals(castOther.iddocumento)
			&& this.idptoemision.equals(castOther.idptoemision)
			&& this.serie.equals(castOther.serie);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.iddocumento.hashCode();
		hash = hash * prime + this.idptoemision.hashCode();
		hash = hash * prime + this.serie.hashCode();
		
		return hash;
	}
}