package entity;

import java.io.Serializable;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the det_docingreso database table.
 * 
 */
@Entity
@Table(name="det_docingreso")
@NamedQuery(name="DetDocingreso.findAll", query="SELECT d FROM DetDocingreso d")
public class DetDocingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetDocingresoPK id;

	@Column(nullable=false, precision=10, scale=2)
	private float cantidad;

	@Column(length=200)
	private String descripcion;

	@Column(nullable=false, length=4)
	private String idmedida;

	@Column(nullable=false, precision=10, scale=2)
	private float importe;

	@Column(nullable=false, precision=10, scale=2)
	private float precio;
	
	/*@ManyToOne
	@JoinColumn(name = "idingreso", referencedColumnName = "idingreso")
	private Docingreso docingreso;*/
	
	/*@OneToMany
	@JoinColumn(name="idproducto",referencedColumnName = "idproducto", nullable=false, insertable=false, updatable=false)
	private List<Producto> productos;*/

	
	public DetDocingreso() {
	}

	public DetDocingresoPK getId() {
		return this.id;
	}
	
	public void setId(DetDocingresoPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdmedida() {
		return this.idmedida;
	}

	public void setIdmedida(String idmedida) {
		this.idmedida = idmedida;
	}

	public float getImporte() {
		return this.importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}