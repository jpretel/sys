package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_doc_flujo database table.
 * 
 */
@Entity
@Table(name="sys_doc_flujo")
@NamedQuery(name="SysDocFlujo.findAll", query="SELECT s FROM SysDocFlujo s")
public class SysDocFlujo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysDocFlujoPK id;

	private int orden;

	//bi-directional many-to-one association to SysDoc
	@ManyToOne
	@JoinColumn(name="sys_doc")
	private SysDoc sysDocBean;

	//bi-directional many-to-one association to Flujo
	@ManyToOne
	@JoinColumn(name="idflujo")
	private Flujo flujo;

	public SysDocFlujo() {
	}

	public SysDocFlujoPK getId() {
		return this.id;
	}

	public void setId(SysDocFlujoPK id) {
		this.id = id;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public SysDoc getSysDocBean() {
		return this.sysDocBean;
	}

	public void setSysDocBean(SysDoc sysDocBean) {
		this.sysDocBean = sysDocBean;
	}

	public Flujo getFlujo() {
		return this.flujo;
	}

	public void setFlujo(Flujo flujo) {
		this.flujo = flujo;
	}

}