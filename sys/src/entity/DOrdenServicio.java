package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DOrdenCompra
 *
 */
@Entity
@Table(name = "dordenservicio")
public class DOrdenServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DOrdenServicioPK id;

	@ManyToOne
	@JoinColumn(name = "idordenservicio", referencedColumnName = "idordenservicio", insertable = false, updatable = false, nullable = false)
	private OrdenServicio ordenservicio;

	@ManyToOne
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "idunimedida", referencedColumnName = "idunimedida")
	private Unimedida unimedida;

	@Column(precision = 17, scale = 3)
	private float cantidad;
	
	@Column(precision = 17, scale = 2)
	private float precio_unitario;

	@Column(precision = 17, scale = 2)
	private float importe;

	public DOrdenServicio() {
		super();
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

	public float getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(float precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public DOrdenServicioPK getId() {
		return id;
	}

	public void setId(DOrdenServicioPK id) {
		this.id = id;
	}

	public OrdenServicio getOrdenservicio() {
		return ordenservicio;
	}

	public void setOrdenservicio(OrdenServicio ordenservicio) {
		this.ordenservicio = ordenservicio;
	}


}
