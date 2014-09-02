package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the guardar_usuario database table.
 * 
 */
@Entity
@Table(name="guardar_usuario")
@NamedQuery(name="GuardarUsuario.findAll", query="SELECT g FROM GuardarUsuario g")
public class GuardarUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GuardarUsuarioPK id;

	public GuardarUsuario() {
	}

	public GuardarUsuarioPK getId() {
		return this.id;
	}

	public void setId(GuardarUsuarioPK id) {
		this.id = id;
	}

}