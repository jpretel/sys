package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DOrdenCompra
 *
 */
@Entity
@Table(name = "dordencompra")
public class DOrdenCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DOrdenCompraPK id;

	@ManyToOne
	@JoinColumn(name = "idordencompra", referencedColumnName = "idordencompra", insertable = false, updatable = false, nullable = false)
	private OrdenCompra ordencompra;

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
	private float vventa;

	@Column(precision = 17, scale = 2)
	private float pdescuento;

	@Column(precision = 17, scale = 2)
	private float descuento;

	@Column(precision = 17, scale = 2)
	private float pimpuesto;

	@Column(precision = 17, scale = 2)
	private float impuesto;

	@Column(precision = 17, scale = 2)
	private float importe;

	public DOrdenCompra() {
		super();
	}

	public DOrdenCompraPK getId() {
		return id;
	}

	public void setId(DOrdenCompraPK id) {
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

	public float getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(float precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public float getVventa() {
		return vventa;
	}

	public void setVventa(float vventa) {
		this.vventa = vventa;
	}

	public float getPdescuento() {
		return pdescuento;
	}

	public void setPdescuento(float pdescuento) {
		this.pdescuento = pdescuento;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public float getPimpuesto() {
		return pimpuesto;
	}

	public void setPimpuesto(float pimpuesto) {
		this.pimpuesto = pimpuesto;
	}

	public float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public OrdenCompra getOrdencompra() {
		return ordencompra;
	}

	public void setOrdencompra(OrdenCompra ordencompra) {
		this.ordencompra = ordencompra;
	}
}
