package entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the marca database table.
 * 
 */
@Entity
@NamedQuery(name="Marca.findAll", query="SELECT m FROM Marca m")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=3)
	private String idmarca;

	@Column(nullable=false, length=75)
	private String descripcion;

	@Column(length=30)
	private String nomcorto;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="marca")
	private List<Producto> productos;

	public Marca() {
	}

	public String getIdmarca() {
		return this.idmarca;
	}

	public void setIdmarca(String idmarca) {
		this.idmarca = idmarca;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNomcorto() {
		return this.nomcorto;
	}

	public void setNomcorto(String nomcorto) {
		this.nomcorto = nomcorto;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setMarca(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setMarca(null);

		return producto;
	}

}