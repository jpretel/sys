package entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the cuenta database table.
 * 
 */
@Entity
@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c")
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=10)
	private String idcuenta;

	@Column(nullable=false, length=75)
	private String descripcion;

	@Column(name="tc_ajuste", length=40)
	private String tcAjuste;

	public Cuenta() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTcAjuste() {
		return this.tcAjuste;
	}

	public void setTcAjuste(String tcAjuste) {
		this.tcAjuste = tcAjuste;
	}

	public String getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(String idcuenta) {
		this.idcuenta = idcuenta;
	}

}