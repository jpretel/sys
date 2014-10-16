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

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="idsolicitudcompra", referencedColumnName = "idsolicitudcompra")
	private SolicitudCompra solicitudcompra;
	
	@Column(length = 10)
	private String documento_referencia;
	private long id_referencia;
	private int item_referencia;

	@ManyToOne
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "idunimedida", referencedColumnName = "idunimedida")
	
	private Unimedida unimedida;
	
	private int factor;
	
	@Column(precision = 17, scale = 6)
	
	private float cantidad;
	

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

	public long getId_referencia() {
		return id_referencia;
	}

	public void setId_referencia(long id_referencia) {
		this.id_referencia = id_referencia;
	}

	public int getItem_referencia() {
		return item_referencia;
	}

	public void setItem_referencia(int item_referencia) {
		this.item_referencia = item_referencia;
	}

	public String getDocumento_referencia() {
		return documento_referencia;
	}

	public void setDocumento_referencia(String documento_referencia) {
		this.documento_referencia = documento_referencia;
	}

	public SolicitudCompra getSolicitudcompra() {
		return solicitudcompra;
	}

	public void setSolicitudcompra(SolicitudCompra solicitudcompra) {
		this.solicitudcompra = solicitudcompra;
	}

}
