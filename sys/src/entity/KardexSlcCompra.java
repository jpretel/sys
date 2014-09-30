package entity;

import entity.Producto;
import entity.Unimedida;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: KardexSlcCompra
 *
 */
@Entity
@Table(name = "kardex_slc_compra")
public class KardexSlcCompra implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "idsolicitudcompra", referencedColumnName = "idsolicitudcompra", nullable = false),
			@JoinColumn(name = "item_slc", referencedColumnName = "item", nullable = false) })
	private DSolicitudCompra dsolicitudcompra;
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "idordencompra", referencedColumnName = "idordencompra"),
			@JoinColumn(name = "item_ord", referencedColumnName = "item") })
	private DOrdenCompra dordencompra;

	@ManyToOne
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name = "idunimedida", referencedColumnName = "idunimedida")
	private Unimedida unimedida;
	private int factor;
	@Column(precision = 17, scale = 6)
	private float cantidad;
	private static final long serialVersionUID = 1L;

	public KardexSlcCompra() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getFactor() {
		return this.factor;
	}

	public void setFactor(int factor) {
		this.factor = factor;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public DSolicitudCompra getDsolicitudcompra() {
		return dsolicitudcompra;
	}

	public void setDsolicitudcompra(DSolicitudCompra dsolicitudcompra) {
		this.dsolicitudcompra = dsolicitudcompra;
	}

	public DOrdenCompra getDordencompra() {
		return dordencompra;
	}

	public void setDordencompra(DOrdenCompra dordencompra) {
		this.dordencompra = dordencompra;
	}

}
