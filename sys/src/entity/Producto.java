package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idproductos;

	@Column(name="desc_corta")
	private String descCorta;

	private String descripcion;

	@Column(name="es_descarte")
	private int esDescarte;

	@Column(name="es_terminado")
	private int esTerminado;

	@Column(name="es_venta")
	private int esVenta;

	//bi-directional many-to-one association to Subgrupo
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="subgrupo_grupo_idgrupo", referencedColumnName="grupo_idgrupo"),
		@JoinColumn(name="subgrupo_idsubgrupo", referencedColumnName="idsubgrupo")
		})
	private Subgrupo subgrupo;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	private Marca marca;

	//bi-directional many-to-one association to Unimedida
	@ManyToOne
	private Unimedida unimedida;

	public Producto() {
	}

	public String getIdproductos() {
		return this.idproductos;
	}

	public void setIdproductos(String idproductos) {
		this.idproductos = idproductos;
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

}