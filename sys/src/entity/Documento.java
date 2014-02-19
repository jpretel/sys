package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the documento database table.
 * 
 */
@Entity
@Table(name="documento")
@NamedQuery(name="Documento.findAll", query="SELECT d FROM Documento d")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=3)
	private String id;

	@Column(length=50)
	private String descripcion;

	@Column(precision=10)
	private BigDecimal factor;

	@Column(length=1)
	private String origen;

	@Column(name="tc_ajuste", length=1)
	private String tcAjuste;

	public Documento() {
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

	public BigDecimal getFactor() {
		return this.factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getTcAjuste() {
		return this.tcAjuste;
	}

	public void setTcAjuste(String tcAjuste) {
		this.tcAjuste = tcAjuste;
	}

}