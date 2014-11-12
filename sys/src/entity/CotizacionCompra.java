package entity;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: OrdenCompra
 *
 */
@Entity
public class CotizacionCompra implements Serializable {

	@Id
	private Long idcotizacioncompra;

	private int numero;
	@Column(length = 4)
	private String serie;

	private int dia;
	private int mes;
	private int anio;
	private int fecha;

	@ManyToOne
	@JoinColumn(name = "idmoneda", referencedColumnName = "idmoneda")
	private Moneda moneda;
	@Column(precision = 10, scale = 4)
	private float tcambio;
	@Column(precision = 10, scale = 4)
	private float tcmoneda;
	
	@ManyToOne
	@JoinColumn(name = "idresponsable", referencedColumnName = "idresponsable")
	private Responsable responsable;
	private String glosa;

	@ManyToOne
	@JoinColumn(name = "idclieprov", referencedColumnName = "idclieprov")
	private Clieprov clieprov;

	private static final long serialVersionUID = 1L;

	public CotizacionCompra() {
		super();
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

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public Clieprov getClieprov() {
		return clieprov;
	}

	public void setClieprov(Clieprov clieprov) {
		this.clieprov = clieprov;
	}

	public Long getIdcotizacioncompra() {
		return idcotizacioncompra;
	}

	public void setIdcotizacioncompra(Long idcotizacioncompra) {
		this.idcotizacioncompra = idcotizacioncompra;
	}

}
