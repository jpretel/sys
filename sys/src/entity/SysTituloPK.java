package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the sys_titulo database table.
 * 
 */
@Embeddable
public class SysTituloPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(nullable=false)
	private String idmodulo;

	@Column(nullable=false, length=3)
	private String idtitulo;

	public SysTituloPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SysTituloPK)) {
			return false;
		}
		SysTituloPK castOther = (SysTituloPK)other;
		return 
			this.idmodulo.equals(castOther.idmodulo)
			&& this.idtitulo.equals(castOther.idtitulo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idmodulo.hashCode();
		hash = hash * prime + this.idtitulo.hashCode();
		
		return hash;
	}
}