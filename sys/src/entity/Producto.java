package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name = "producto")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = 20)
	private String idproducto;

	@Column(name = "desc_corta", nullable = false, length = 50)
	private String descCorta;

	@Column(nullable = false, length = 75)
	private String descripcion;

	@Column(name = "es_descarte")
	private int esDescarte;

	@Column(name = "es_terminado")
	private int esTerminado;

	@Column(name = "es_venta")
	private int esVenta;

	@Column(name = "es_servicio", precision = 1, scale = 0)
	private int esServicio;

	@Override
	public String toString() {
		return "Producto [idproducto=" + idproducto + ", descCorta="
				+ descCorta + ", descripcion=" + descripcion + ", esDescarte="
				+ esDescarte + ", esTerminado=" + esTerminado + ", esVenta="
				+ esVenta + ", subgrupo=" + subgrupo + ", marca=" + marca
				+ ", unimedida=" + unimedida + "]";
	}

	// bi-directional many-to-one association to Subgrupo
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo"),
			@JoinColumn(name = "idsubgrupo", referencedColumnName = "idsubgrupo") })
	private Subgrupo subgrupo;

	// bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name = "idmarca", referencedColumnName = "idmarca")
	private Marca marca;

	// bi-directional many-to-one association to Unimedida
	@ManyToOne
	@JoinColumn(name = "idunimedida", referencedColumnName = "idunimedida")
	private Unimedida unimedida;

	public Producto() {
	}

	public String getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(String idproducto) {
		this.idproducto = idproducto;
	}

	public String getDescCorta() {
		return this.descCorta;
	}

	public void setDescCorta(String descCorta) {
		this.descCorta = descCorta;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEsDescarte() {
		return this.esDescarte;
	}

	public void setEsDescarte(int esDescarte) {
		this.esDescarte = esDescarte;
	}

	public int getEsTerminado() {
		return this.esTerminado;
	}

	public void setEsTerminado(int esTerminado) {
		this.esTerminado = esTerminado;
	}

	public int getEsVenta() {
		return this.esVenta;
	}

	public void setEsVenta(int esVenta) {
		this.esVenta = esVenta;
	}

	public Subgrupo getSubgrupo() {
		return this.subgrupo;
	}

	public void setSubgrupo(Subgrupo subgrupo) {
		this.subgrupo = subgrupo;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Unimedida getUnimedida() {
		return this.unimedida;
	}

	public void setUnimedida(Unimedida unimedida) {
		this.unimedida = unimedida;
	}

	public String historial() {
		return "Código: " + idproducto + ", descCorta: " + descCorta
				+ ", descripcion=" + descripcion + ", esDescarte: "
				+ esDescarte + ", esTerminado: " + esTerminado + ", esVenta: "
				+ esVenta + ", subgrupo: " + subgrupo.getDescripcion()
				+ ", marca: " + ((marca.getIdmarca()==null)?"":marca.getIdmarca()) + ", unimedida: "
				+ ((unimedida.getDescripcion()==null)?"":unimedida.getDescripcion());
	}

	public int getEsServicio() {
		return esServicio;
	}

	public void setEsServicio(int esServicio) {
		this.esServicio = esServicio;
	}
}