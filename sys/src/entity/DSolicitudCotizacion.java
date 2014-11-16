package entity;

import entity.Producto;
import entity.Unimedida;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DRequerimiento
 *
 */
@Entity
@Table(name = "dsolicitudcotizacion")
public class DSolicitudCotizacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DSolicitudCotizacionPK id;

	@ManyToOne
	@JoinColumn(name = "idsolicitudcotizacion", referencedColumnName = "idsolicitudcotizacion", insertable = false, updatable = false)
	private SolicitudCotizacion solicitud;

	@ManyToOne
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "idunimedida", referencedColumnName = "idunimedida")
	private Unimedida unimedida;

	@Column(precision = 17, scale = 6)
	private float cantidad;

	public DSolicitudCotizacion() {
		super();
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Unimedida getUnimedida() {
		return this.unimedida;
	}

	public void setUnimedida(Unimedida unimedida) {
		this.unimedida = unimedida;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public DSolicitudCotizacionPK getId() {
		return id;
	}

	public void setId(DSolicitudCotizacionPK id) {
		this.id = id;
	}

	public SolicitudCotizacion getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudCotizacion solicitud) {
		this.solicitud = solicitud;
	}

}
