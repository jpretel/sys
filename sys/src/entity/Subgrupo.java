package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subgrupo database table.
 * 
 */
@Entity
@NamedQuery(name="Subgrupo.findAll", query="SELECT s FROM Subgrupo s")
public class Subgrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubgrupoPK id;

	private String descripcion;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="subgrupo")
	private List<Producto> productos;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	private Grupo grupo;

	public Subgrupo() {
	}

	public SubgrupoPK getId() {
		return this.id;
	}

	public void setId(SubgrupoPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setSubgrupo(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setSubgrupo(null);

		return producto;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}