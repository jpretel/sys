package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DOrdenCompra
 *
 */
@Entity
@Table(name = "dsolicitudcompra")
public class DSolicitudCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DSolicitudCompraPK id;

	@ManyToOne
	@JoinColumn(name = "idsolicitudcompra", referencedColumnName = "idsolicitudcompra", insertable = false, updatable = false, nullable = false)
	private SolicitudCompra solicitudcompra;

	@ManyToOne
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "idunimedida", referencedColumnName = "idunimedida")
	private Unimedida unimedida;

	@Column(precision = 17, scale = 3)
	private float cantidad;

	public DSolicitudCompra() {
		super();
	}

	public DSolicitudCompraPK getId() {
		return id;
	}

	public void setId(DSolicitudCompraPK id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Unimedida getUnimedida() {
		return unimedida;
	}

	public void setUnimedida(Unimedida unimedida) {
		this.unimedida = unimedida;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public SolicitudCompra getSolicitudcompra() {
		return solicitudcompra;
	}

	public void setSolicitudcompra(SolicitudCompra solicitudcompra) {
		this.solicitudcompra = solicitudcompra;
	}

	
}
