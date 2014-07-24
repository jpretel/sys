package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the sys_doc_flujo database table.
 * 
 */
@Embeddable
public class SysDocFlujoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="sys_doc", insertable=false, updatable=false)
	private String sysDoc;

	@Column(insertable=false, updatable=false)
	private String idflujo;

	public SysDocFlujoPK() {
	}
	public String getSysDoc() {
		return this.sysDoc;
	}
	public void setSysDoc(String sysDoc) {
		this.sysDoc = sysDoc;
	}
	public String getIdflujo() {
		return this.idflujo;
	}
	public void setIdflujo(String idflujo) {
		this.idflujo = idflujo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SysDocFlujoPK)) {
			return false;
		}
		SysDocFlujoPK castOther = (SysDocFlujoPK)other;
		return 
			this.sysDoc.equals(castOther.sysDoc)
			&& this.idflujo.equals(castOther.idflujo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sysDoc.hashCode();
		hash = hash * prime + this.idflujo.hashCode();
		
		return hash;
	}
}