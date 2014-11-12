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
	
	private long idordencompra;
	
	private int item_compra;
	
	private long iddocingreso;
	
	private int item_recepcion;
	
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

	public long getIdreferencia() {
		return idreferencia;
	}

	public void setIdreferencia(long idreferencia) {
		this.idreferencia = idreferencia;
	}

	public long getIdordencompra() {
		return idordencompra;
	}

	public void setIdordencompra(long idordencompra) {
		this.idordencompra = idordencompra;
	}

	public int getItem_compra() {
		return item_compra;
	}

	public void setItem_compra(int item_compra) {
		this.item_compra = item_compra;
	}

	public long getIddocingreso() {
		return iddocingreso;
	}

	public void setIddocingreso(long iddocingreso) {
		this.iddocingreso = iddocingreso;
	}

	public int getItem_recepcion() {
		return item_recepcion;
	}

	public void setItem_recepcion(int item_recepcion) {
		this.item_recepcion = item_recepcion;
	}
}
