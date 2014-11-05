package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the sys_opcion database table.
 * 
 */
@Entity
@Table(name = "sys_opcion")
@NamedQuery(name = "SysOpcion.findAll", query = "SELECT s FROM SysOpcion s")
public class SysOpcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysOpcionPK id;

	@ManyToOne
	@JoinColumn(name = "idformulario", referencedColumnName = "idformulario", nullable = false, insertable = false, updatable = false)
	private SysFormulario sysFormulario;

	@Column
	private int prioridad;

	// bi-directional many-to-one association to SysGrupo
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "idtitulo", referencedColumnName = "idtitulo", nullable = false, insertable = false, updatable = false) })
	private SysGrupo sysGrupo;
	
	@ManyToOne
	@JoinColumn(name="idmodulo", referencedColumnName = "idmodulo", nullable=false, insertable=false, updatable=false)
	private SysModulo sysModulo;
	
	public SysOpcion() {
	}

	public SysOpcionPK getId() {
		return this.id;
	}

	public void setId(SysOpcionPK id) {
		this.id = id;
	}

	public int getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public SysGrupo getSysGrupo() {
		return this.sysGrupo;
	}

	public void setSysGrupo(SysGrupo sysGrupo) {
		this.sysGrupo = sysGrupo;
	}

	public SysFormulario getSysFormulario() {
		return sysFormulario;
	}

	public void setSysFormulario(SysFormulario sysFormulario) {
		this.sysFormulario = sysFormulario;
	}

	public SysModulo getSysModulo() {
		return sysModulo;
	}

	public void setSysModulo(SysModulo sysModulo) {
		this.sysModulo = sysModulo;
	}

}