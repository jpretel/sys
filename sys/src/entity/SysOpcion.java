package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_opcion database table.
 * 
 */
@Entity
@Table(name="sys_opcion")
@NamedQuery(name="SysOpcion.findAll", query="SELECT s FROM SysOpcion s")
public class SysOpcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysOpcionPK id;

	@Column(length=75)
	private String descripcion;

	@Column(length=75)
	private String imagen;
	
	@Column(length=75)
	private String opcion;
	
	private int prioridad;

	//bi-directional many-to-one association to SysGrupo
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="idgrupo", referencedColumnName="idgrupo", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="idmodulo", referencedColumnName="idmodulo", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="idtitulo", referencedColumnName="idtitulo", nullable=false, insertable=false, updatable=false)
		})
	private SysGrupo sysGrupo;

	public SysOpcion() {
	}

	public SysOpcionPK getId() {
		return this.id;
	}

	public void setId(SysOpcionPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
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

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

}