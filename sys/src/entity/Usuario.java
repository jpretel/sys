package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=30)
	private String clave;

	@Column(length=60)
	private String encClave;

	@Column(length=60)
	private String encUsu;

	@Column(length=25)
	private String usuario;

	public Usuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEncClave() {
		return this.encClave;
	}

	public void setEncClave(String encClave) {
		this.encClave = encClave;
	}

	public String getEncUsu() {
		return this.encUsu;
	}

	public void setEncUsu(String encUsu) {
		this.encUsu = encUsu;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}