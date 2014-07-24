package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sys_doc database table.
 * 
 */
@Entity
@Table(name="sys_doc")
@NamedQuery(name="SysDoc.findAll", query="SELECT s FROM SysDoc s")
public class SysDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sys_doc")
	private String sysDoc;

	private String descripcion;

	//bi-directional many-to-one association to SysDocDocser
	@OneToMany(mappedBy="sysDocBean")
	private List<SysDocDocser> sysDocDocsers;

	//bi-directional many-to-one association to SysDocFlujo
	@OneToMany(mappedBy="sysDocBean")
	private List<SysDocFlujo> sysDocFlujos;

	public SysDoc() {
	}

	public String getSysDoc() {
		return this.sysDoc;
	}

	public void setSysDoc(String sysDoc) {
		this.sysDoc = sysDoc;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<SysDocDocser> getSysDocDocsers() {
		return this.sysDocDocsers;
	}

	public void setSysDocDocsers(List<SysDocDocser> sysDocDocsers) {
		this.sysDocDocsers = sysDocDocsers;
	}

	public SysDocDocser addSysDocDocser(SysDocDocser sysDocDocser) {
		getSysDocDocsers().add(sysDocDocser);
		sysDocDocser.setSysDocBean(this);

		return sysDocDocser;
	}

	public SysDocDocser removeSysDocDocser(SysDocDocser sysDocDocser) {
		getSysDocDocsers().remove(sysDocDocser);
		sysDocDocser.setSysDocBean(null);

		return sysDocDocser;
	}

	public List<SysDocFlujo> getSysDocFlujos() {
		return this.sysDocFlujos;
	}

	public void setSysDocFlujos(List<SysDocFlujo> sysDocFlujos) {
		this.sysDocFlujos = sysDocFlujos;
	}

	public SysDocFlujo addSysDocFlujo(SysDocFlujo sysDocFlujo) {
		getSysDocFlujos().add(sysDocFlujo);
		sysDocFlujo.setSysDocBean(this);

		return sysDocFlujo;
	}

	public SysDocFlujo removeSysDocFlujo(SysDocFlujo sysDocFlujo) {
		getSysDocFlujos().remove(sysDocFlujo);
		sysDocFlujo.setSysDocBean(null);

		return sysDocFlujo;
	}

}