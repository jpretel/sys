package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the det_docingreso database table.
 * 
 */
@Entity
@Table(name = "det_docsalida")
@NamedQuery(name = "DetDocsalida.findAll", query = "SELECT d FROM DetDocsalida d")
public class DetDocsalida implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetDocsalidaPK id;

	@Column(nullable = false, precision = 10, scale = 2)
	private float cantidad;

	@Column(nullable = false, precision = 10, scale = 2)
	private float importe;

	@Column(nullable = false, precision = 10, scale = 2)
	private float precio;

	@ManyToOne
	@JoinColumn(name = "idunimedida", referencedColumnName = "idunimedida")
	private Unimedida unimedida;

	@ManyToOne
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "iddocsalida", nullable = false, insertable = false, updatable = false)
	private Docsalida docsalida;

	@Column(length = 15)
	private String idconsumidor;
	
	/*
	 * Referencia
	 */
	@Column(length = 10)
	private String tipo_referencia;
	
	private long id_referencia;
	
	public DetDocsalida() {
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

	public void setDocsalida(Docsalida docsalida) {
		this.docsalida = docsalida;
	}

	public Docsalida getDocsalida() {
		return docsalida;
	}

	public String getIdconsumidor() {
		return idconsumidor;
	}

	public void setIdconsumidor(String idconsumidor) {
		this.idconsumidor = idconsumidor;
	}

	public void setId(DetDocsalidaPK id) {
		this.id = id;
	}

	public DetDocsalidaPK getId() {
		return this.id;
	}

	public Unimedida getUnimedida() {
		return unimedida;
	}

	public void setUnimedida(Unimedida unimedida) {
		this.unimedida = unimedida;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getTipo_referencia() {
		return tipo_referencia;
	}

	public void setTipo_referencia(String tipo_referencia) {
		this.tipo_referencia = tipo_referencia;
	}

	public long getId_referencia() {
		return id_referencia;
	}

	public void setId_referencia(long id_referencia) {
		this.id_referencia = id_referencia;
	}

}