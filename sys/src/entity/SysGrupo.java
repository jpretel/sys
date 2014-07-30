package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sys_grupo database table.
 * 
 */
@Entity
@Table(name="sys_grupo")
@NamedQuery(name="SysGrupo.findAll", query="SELECT s FROM SysGrupo s")
public class SysGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysGrupoPK id;

	@Column(length=75)
	private String descripcion;

	//bi-directional many-to-one association to SysTitulo
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="idmodulo", referencedColumnName="idmodulo", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="idtitulo", referencedColumnName="idtitulo", nullable=false, insertable=false, updatable=false)
		})
	private SysTitulo sysTitulo;

	//bi-directional many-to-one association to SysOpcion
	@OneToMany(mappedBy="sysGrupo")
	private List<SysOpcion> sysOpcions;

	public SysGrupo() {
	}

	public SysGrupoPK getId() {
		return this.id;
	}

	public void setId(SysGrupoPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public SysTitulo getSysTitulo() {
		return this.sysTitulo;
	}

	public void setSysTitulo(SysTitulo sysTitulo) {
		this.sysTitulo = sysTitulo;
	}

	public List<SysOpcion> getSysOpcions() {
		return this.sysOpcions;
	}

	public void setSysOpcions(List<SysOpcion> sysOpcions) {
		this.sysOpcions = sysOpcions;
	}

	public SysOpcion addSysOpcion(SysOpcion sysOpcion) {
		getSysOpcions().add(sysOpcion);
		sysOpcion.setSysGrupo(this);

		return sysOpcion;
	}

	public SysOpcion removeSysOpcion(SysOpcion sysOpcion) {
		getSysOpcions().remove(sysOpcion);
		sysOpcion.setSysGrupo(null);

		return sysOpcion;
	}

}