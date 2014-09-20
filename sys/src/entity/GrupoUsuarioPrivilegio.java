package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GrupoUsuarioPrivilegio
 *
 */
@Entity
public class GrupoUsuarioPrivilegio implements Serializable {

	private static final long serialVersionUID = 1L;

	public GrupoUsuarioPrivilegio() {
		super();
	}

	@EmbeddedId
	private GrupoUsuarioPrivilegioPK id;
	@ManyToOne
	@JoinColumn(name = "idgrupo_usuario", referencedColumnName = "idgrupo_usuario", insertable = false, updatable = false)
	private GrupoUsuario grupoUsuario;
	@ManyToOne
	@JoinColumn(name = "idformulario", referencedColumnName = "idformulario", insertable = false, updatable = false)
	private SysFormulario sysFormulario;
	@Column(precision = 1, scale = 0)
	private int ver;
	@Column(precision = 1, scale = 0)
	private int modificar;
	@Column(precision = 1, scale = 0)
	private int crear;
	@Column(precision = 1, scale = 0)
	private int eliminar;

	public GrupoUsuarioPrivilegioPK getId() {
		return id;
	}

	public void setId(GrupoUsuarioPrivilegioPK id) {
		this.id = id;
	}

	public GrupoUsuario getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

	public SysFormulario getSysFormulario() {
		return sysFormulario;
	}

	public void setSysFormulario(SysFormulario sysFormulario) {
		this.sysFormulario = sysFormulario;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	public int getModificar() {
		return modificar;
	}

	public void setModificar(int modificar) {
		this.modificar = modificar;
	}

	public int getCrear() {
		return crear;
	}

	public void setCrear(int crear) {
		this.crear = crear;
	}

	public int getEliminar() {
		return eliminar;
	}

	public void setEliminar(int eliminar) {
		this.eliminar = eliminar;
	}

}
