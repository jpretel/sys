package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the sys_grupo database table.
 * 
 */
@Embeddable
public class SysGrupoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private String idmodulo;

	@Column
	private String idtitulo;

	@Column(nullable=false, length=3)
	private String idgrupo;

	public SysGrupoPK() {
	}
	public String getIdmodulo() {
		return this.idmodulo;
	}
	public void setIdmodulo(String idmodulo) {
		this.idmodulo = idmodulo;
	}
	public String getIdtitulo() {
		return this.idtitulo;
	}
	public void setIdtitulo(String idtitulo) {
		this.idtitulo = idtitulo;
	}
	public String getIdgrupo() {
		return this.idgrupo;
	}
	public void setIdgrupo(String idgrupo) {
		this.idgrupo = idgrupo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SysGrupoPK)) {
			return false;
		}
		SysGrupoPK castOther = (SysGrupoPK)other;
		return 
			this.idmodulo.equals(castOther.idmodulo)
			&& this.idtitulo.equals(castOther.idtitulo)
			&& this.idgrupo.equals(castOther.idgrupo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idmodulo.hashCode();
		hash = hash * prime + this.idtitulo.hashCode();
		hash = hash * prime + this.idgrupo.hashCode();
		
		return hash;
	}
}