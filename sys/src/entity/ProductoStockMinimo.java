package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductoStockMinimo
 *
 */
@Entity
public class ProductoStockMinimo implements Serializable {

	@EmbeddedId
	private ProductoStockMinimoPK id;

	private static final long serialVersionUID = 1L;

	public ProductoStockMinimo() {
		super();
	}

	@ManyToOne
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto", insertable = false, updatable = false)
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)
	private Sucursal sucursal;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false),
			@JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen", insertable = false, updatable = false) })
	private Almacen almacen;

	@Column(precision = 17, scale = 6)
	private float cantidad;
	
	@Column(precision = 17, scale = 6)
	private float reposicion;
	
	public ProductoStockMinimoPK getId() {
		return id;
	}

	public void setId(ProductoStockMinimoPK id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getReposicion() {
		return reposicion;
	}

	public void setReposicion(float reposicion) {
		this.reposicion = reposicion;
	}
}
