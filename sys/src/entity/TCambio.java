package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TCambio
 *
 */
@Entity
public class TCambio implements Serializable {

	@EmbeddedId
	private TCambioPK id;
	@Column(precision = 17, scale = 6)
	private float compra;
	@Column(precision = 17, scale = 6)
	private float venta;

	@ManyToOne
	@JoinColumn(name = "idmoneda", referencedColumnName = "idmoneda", insertable = false, updatable = false)
	private Moneda moneda;

	private static final long serialVersionUID = 1L;

	public TCambio() {
		super();
	}

	public TCambioPK getId() {
		return id;
	}

	public void setId(TCambioPK id) {
		this.id = id;
	}

	public float getCompra() {
		return compra;
	}

	public void setCompra(float compra) {
		this.compra = compra;
	}

	public float getVenta() {
		return venta;
	}

	public void setVenta(float venta) {
		this.venta = venta;
	}

}
