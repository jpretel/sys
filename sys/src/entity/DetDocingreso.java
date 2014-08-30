package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the det_docingreso database table.
 * 
 */
@Entity
@Table(name="det_docingreso")
@NamedQuery(name="DetDocingreso.findAll", query="SELECT d FROM DetDocingreso d")
public class DetDocingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetDocingresoPK id;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal cantidad;

	@Column(length=200)
	private String descripcion;

	@Column(nullable=false, length=4)
	private String idmedida;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal importe;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal precio;

	public DetDocingreso() {
	}

	public DetDocingresoPK getId() {
		return this.id;
	}

	public void setId(DetDocingresoPK id) {
		this.id = id;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdmedida() {
		return this.idmedida;
	}

	public void setIdmedida(String idmedida) {
		this.idmedida = idmedida;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

}