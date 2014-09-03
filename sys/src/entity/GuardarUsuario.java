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

	@Column(nullable=false, length=100)
	private String contra;

	@Column(nullable=false, length=30)
	private String usuario;

	public GuardarUsuario() {
	}

	public GuardarUsuarioPK getId() {
		return this.id;
	}

	public void setId(GuardarUsuarioPK id) {
		this.id = id;
	}

	public String getContra() {
		return this.contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}