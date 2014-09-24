package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductoImpuesto
 *
 */
@Entity
@Table(name = "producto_impuesto")
public class ProductoImpuesto implements Serializable {
	@EmbeddedId
	private ProductoImpuestoPK id;
	@ManyToOne
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto", insertable = false, updatable = false)
	private Producto producto;
	@ManyToOne
	@JoinColumn(name = "idimpuesto", referencedColumnName = "idreferencia", insertable = false, updatable = false)
	private Impuesto impuesto;

	private static final long serialVersionUID = 1L;

	public ProductoImpuesto() {
		super();
	}

	public ProductoImpuestoPK getId() {
		return id;
	}

	public void setId(ProductoImpuestoPK id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Impuesto getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}
}
