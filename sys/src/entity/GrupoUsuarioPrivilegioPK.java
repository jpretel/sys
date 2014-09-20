package entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Embeddable;

/**
 * ID class for entity: GrupoUsuarioPrivilegi
 *
 */ 
@Embeddable
public class GrupoUsuarioPrivilegioPK  implements Serializable {   
   
	         
	private String idgrupo_usuario;         
	private String idformulario;
	private static final long serialVersionUID = 1L;

	public GrupoUsuarioPrivilegioPK() {}

	

	public String getIdgrupo_usuario() {
		return this.idgrupo_usuario;
	}

	public void setIdgrupo_usuario(String idgrupo_usuario) {
		this.idgrupo_usuario = idgrupo_usuario;
	}
	

	public String getIdformulario() {
		return this.idformulario;
	}

	public void setIdformulario(String idformulario) {
		this.idformulario = idformulario;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof GrupoUsuarioPrivilegioPK)) {
			return false;
		}
		GrupoUsuarioPrivilegioPK other = (GrupoUsuarioPrivilegioPK) o;
		return true
			&& (getIdgrupo_usuario() == null ? other.getIdgrupo_usuario() == null : getIdgrupo_usuario().equals(other.getIdgrupo_usuario()))
			&& (getIdformulario() == null ? other.getIdformulario() == null : getIdformulario().equals(other.getIdformulario()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getIdgrupo_usuario() == null ? 0 : getIdgrupo_usuario().hashCode());
		result = prime * result + (getIdformulario() == null ? 0 : getIdformulario().hashCode());
		return result;
	}
   
   
}
