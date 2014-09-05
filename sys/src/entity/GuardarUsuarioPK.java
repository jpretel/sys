package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the guardar_usuario database table.
 * 
 */
@Embeddable
public class GuardarUsuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=50)
	private String namehost;

	@Column(unique=true, nullable=false, length=50)
	private String idusuario;

	public GuardarUsuarioPK() {
	}
	public String getNamehost() {
		return this.namehost;
	}
	public void setNamehost(String namehost) {
		this.namehost = namehost;
	}
	public String getIdusuario() {
		return this.idusuario;
	}
	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GuardarUsuarioPK)) {
			return false;
		}
		GuardarUsuarioPK castOther = (GuardarUsuarioPK)other;
		return 
			this.namehost.equals(castOther.namehost)
			&& this.idusuario.equals(castOther.idusuario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.namehost.hashCode();
		hash = hash * prime + this.idusuario.hashCode();
		
		return hash;
	}
}