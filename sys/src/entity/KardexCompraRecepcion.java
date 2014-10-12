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
@Table(name = "kardex_compra_recepcion")
public class KardexCompraRecepcion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "idordencompra", referencedColumnName = "idordencompra", nullable = false),
			@JoinColumn(name = "item_compra", referencedColumnName = "item", nullable = false) })
	private DOrdenCompra dordencompra;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "iddocingreso",referencedColumnName = "iddocingreso"),
		@JoinColumn(name = "item_recepcion",referencedColumnName = "item")
	})
	private DetDocingreso detdocingreso;

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
	
	private long idreferencia;

	public KardexCompraRecepcion() {
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

	public DOrdenCompra getDordencompra() {
		return dordencompra;
	}

	public void setDordencompra(DOrdenCompra dordencompra) {
		this.dordencompra = dordencompra;
	}

	public DetDocingreso getDetdocingreso() {
		return detdocingreso;
	}

	public void setDetdocingreso(DetDocingreso detdocingreso) {
		this.detdocingreso = detdocingreso;
	}

	public long getIdreferencia() {
		return idreferencia;
	}

	public void setIdreferencia(long idreferencia) {
		this.idreferencia = idreferencia;
	}
}
