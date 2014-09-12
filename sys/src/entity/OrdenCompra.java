package entity;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: OrdenCompra
 *
 */
@Entity
public class OrdenCompra implements Serializable {

	@Id
	private Long idordencompra;

	private int numero;
	@Column(length = 4)
	private String serie;

	private int dia;
	private int mes;
	private int anio;
	private int aniomesdia;
	@ManyToOne
	@JoinColumn(name = "idmoneda", referencedColumnName = "idmoneda", insertable = false, updatable = false, nullable = false)
	private Moneda moneda;
	@Column(precision = 10, scale = 4)
	private float tcambio;
	@Column(precision = 10, scale = 4)
	private float tcmoneda;
	@ManyToOne
	@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false, nullable = false)
	private Sucursal sucursal;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false, nullable = false),
			@JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen", insertable = false, updatable = false, nullable = false) })
	private Almacen almacen;
	@ManyToOne
	@JoinColumn(name = "idresponsable", referencedColumnName = "idresponsable")
	private Responsable responsable;
	private String glosa;

	private static final long serialVersionUID = 1L;

	public OrdenCompra() {
		super();
	}

	public Long getIdordencompra() {
		return this.idordencompra;
	}

	public void setIdordencompra(Long idordencompra) {
		this.idordencompra = idordencompra;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public float getTcambio() {
		return tcambio;
	}

	public void setTcambio(float tcambio) {
		this.tcambio = tcambio;
	}

	public float getTcmoneda() {
		return tcmoneda;
	}

	public void setTcmoneda(float tcmoneda) {
		this.tcmoneda = tcmoneda;
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

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public int getAniomesdia() {
		return aniomesdia;
	}

	public void setAniomesdia(int aniomesdia) {
		this.aniomesdia = aniomesdia;
	}

}
