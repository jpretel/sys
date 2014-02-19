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
	private String id;

	private String descripcion;

	@Column(name="tc_ajuste")
	private String tcAjuste;

	public Cuenta() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

}