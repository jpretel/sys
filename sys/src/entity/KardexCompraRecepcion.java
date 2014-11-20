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
	
	private long idreferencia;
	
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
}
