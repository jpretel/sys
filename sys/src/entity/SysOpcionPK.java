package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the sys_opcion database table.
 * 
 */
@Embeddable
public class SysOpcionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String idmodulo;

	private String idtitulo;

	private String idgrupo;

	@Column(nullable=false, length=15)
	private String idformulario;

	public SysOpcionPK() {
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
		if (!(other instanceof SysOpcionPK)) {
			return false;
		}
		SysOpcionPK castOther = (SysOpcionPK)other;
		return 
			this.idmodulo.equals(castOther.idmodulo)
			&& this.idtitulo.equals(castOther.idtitulo)
			&& this.idgrupo.equals(castOther.idgrupo)
			&& (this.idformulario == castOther.idformulario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idmodulo.hashCode();
		hash = hash * prime + this.idtitulo.hashCode();
		hash = hash * prime + this.idgrupo.hashCode();
		hash = hash * prime + this.idformulario.hashCode();
		
		return hash;
	}
	public String getIdformulario() {
		return idformulario;
	}
	public void setIdformulario(String idformulario) {
		this.idformulario = idformulario;
	}
}