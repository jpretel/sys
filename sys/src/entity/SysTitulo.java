package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sys_titulo database table.
 * 
 */
@Entity
@Table(name="sys_titulo")
@NamedQuery(name="SysTitulo.findAll", query="SELECT s FROM SysTitulo s")
public class SysTitulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysTituloPK id;

	@Column(length=75)
	private String descripcion;

	//bi-directional many-to-one association to SysGrupo
	@OneToMany(mappedBy="sysTitulo")
	private List<SysGrupo> sysGrupos;

	//bi-directional many-to-one association to SysModulo
	@ManyToOne
	@JoinColumn(name="idmodulo", nullable=false, insertable=false, updatable=false)
	private SysModulo sysModulo;

	public SysTitulo() {
	}

	public SysTituloPK getId() {
		return this.id;
	}

	public void setId(SysTituloPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<SysGrupo> getSysGrupos() {
		return this.sysGrupos;
	}

	public void setSysGrupos(List<SysGrupo> sysGrupos) {
		this.sysGrupos = sysGrupos;
	}

	public SysGrupo addSysGrupo(SysGrupo sysGrupo) {
		getSysGrupos().add(sysGrupo);
		sysGrupo.setSysTitulo(this);

		return sysGrupo;
	}

	public SysGrupo removeSysGrupo(SysGrupo sysGrupo) {
		getSysGrupos().remove(sysGrupo);
		sysGrupo.setSysTitulo(null);

		return sysGrupo;
	}

	public SysModulo getSysModulo() {
		return this.sysModulo;
	}

	public void setSysModulo(SysModulo sysModulo) {
		this.sysModulo = sysModulo;
	}

}