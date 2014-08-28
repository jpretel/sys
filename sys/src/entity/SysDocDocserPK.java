package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the sys_doc_docser database table.
 * 
 */
@Embeddable
public class SysDocDocserPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="sys_doc", unique=true, nullable=false, length=3)
	private String sysDoc;

	@Column(nullable=false, length=3)
	private String iddocumento;

	@Column(nullable=false, length=5)
	private String serie;

	public SysDocDocserPK() {
	}
	public String getSysDoc() {
		return this.sysDoc;
	}
	public void setSysDoc(String sysDoc) {
		this.sysDoc = sysDoc;
	}
	public String getIddocumento() {
		return this.iddocumento;
	}
	public void setIddocumento(String iddocumento) {
		this.iddocumento = iddocumento;
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
		if (!(other instanceof SysDocDocserPK)) {
			return false;
		}
		SysDocDocserPK castOther = (SysDocDocserPK)other;
		return 
			this.sysDoc.equals(castOther.sysDoc)
			&& this.iddocumento.equals(castOther.iddocumento)
			&& this.serie.equals(castOther.serie);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sysDoc.hashCode();
		hash = hash * prime + this.iddocumento.hashCode();
		hash = hash * prime + this.serie.hashCode();
		
		return hash;
	}
}