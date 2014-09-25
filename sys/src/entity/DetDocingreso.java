package entity;

import java.io.Serializable;

import javax.persistence.*;


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

	@Column(nullable=false, precision=10, scale=3)
	private float cantidad;

	@Column(length=200)
	private String descripcion;

	@Column(length=3 , nullable = false)
	private String idmedida;

	@Column(nullable=false, precision=10, scale=2)
	private float importe;

	@Column(nullable=false, precision=10, scale=2)
	private float precio;
	
	@ManyToOne
	@JoinColumn(name = "iddocingreso", referencedColumnName = "iddocingreso", insertable=false, updatable=false)
	private Docingreso docingreso;
	
	@ManyToOne
	@JoinColumn(name = "idproducto",insertable=false, updatable=false)
	private Producto producto;
	
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

	public float getImporte() {
		return this.importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	public String getIdmedida() {
		return idmedida;
	}

	public void setIdmedida(String idmedida) {
		this.idmedida = idmedida;
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

	public Docingreso getDocingreso() {
		return docingreso;
	}

	public void setDocingreso(Docingreso docingreso) {
		this.docingreso = docingreso;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}