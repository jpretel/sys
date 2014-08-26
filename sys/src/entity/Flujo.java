package entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the flujo database table.
 * 
 */
@Entity
@NamedQuery(name="Flujo.findAll", query="SELECT f FROM Flujo f")
public class Flujo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=3)
	private String idflujo;

	@Column(nullable=false, length=75)
	private String descripcion;

	//bi-directional many-to-one association to SysDocFlujo
	@OneToMany(mappedBy="flujo")
	private List<SysDocFlujo> sysDocFlujos;

	public Flujo() {
	}

	public String getIdflujo() {
		return this.idflujo;
	}

	public void setIdflujo(String idflujo) {
		this.idflujo = idflujo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<SysDocFlujo> getSysDocFlujos() {
		return this.sysDocFlujos;
	}

	public void setSysDocFlujos(List<SysDocFlujo> sysDocFlujos) {
		this.sysDocFlujos = sysDocFlujos;
	}

	public SysDocFlujo addSysDocFlujo(SysDocFlujo sysDocFlujo) {
		getSysDocFlujos().add(sysDocFlujo);
		sysDocFlujo.setFlujo(this);

		return sysDocFlujo;
	}

	public SysDocFlujo removeSysDocFlujo(SysDocFlujo sysDocFlujo) {
		getSysDocFlujos().remove(sysDocFlujo);
		sysDocFlujo.setFlujo(null);

		return sysDocFlujo;
	}

}