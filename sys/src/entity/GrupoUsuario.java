package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grupo_usuario database table.
 * 
 */
@Entity
@Table(name="grupo_usuario")
@NamedQuery(name="GrupoUsuario.findAll", query="SELECT g FROM GrupoUsuario g")
public class GrupoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="idgrupo_usuario", unique=true, nullable=false, length=3)
	private String idgrupoUsuario;

	@Column(length=75)
	private String descripcion;

	@Column(name="es_administrador")
	private int esAdministrador;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="grupoUsuario")
	private List<Usuario> usuarios;

	public GrupoUsuario() {
	}

	public String getIdgrupoUsuario() {
		return this.idgrupoUsuario;
	}

	public void setIdgrupoUsuario(String idgrupoUsuario) {
		this.idgrupoUsuario = idgrupoUsuario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEsAdministrador() {
		return this.esAdministrador;
	}

	public void setEsAdministrador(int esAdministrador) {
		this.esAdministrador = esAdministrador;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setGrupoUsuario(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setGrupoUsuario(null);

		return usuario;
	}

}