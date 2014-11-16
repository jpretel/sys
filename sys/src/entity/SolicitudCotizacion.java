package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Requerimiento
 *
 */
@Entity
@Table(name = "solicitudcotizacion")
public class SolicitudCotizacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long idsolicitudcotizacion;

	@Column(length = 4)
	private String serie;

	@Column
	private int numero;

	@Column
	private int fecha;

	@Column
	private int anio;

	@Column
	private int mes;

	@Column
	private int dia;

	@Column(length = 200)
	private String glosa;

	@ManyToOne
	@JoinColumn(name="idclieprov", referencedColumnName = "idclieprov")
	private Clieprov clieprov;
	
	@ManyToOne
	@JoinColumn(name="idsolicitudcompra", referencedColumnName = "idsolicitudcompra")
	private SolicitudCompra solicitudcompra;
	
	public SolicitudCotizacion() {
		super();
	}
	
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public long getIdsolicitudcotizacion() {
		return idsolicitudcotizacion;
	}

	public void setIdsolicitudcotizacion(long idsolicitudcotizacion) {
		this.idsolicitudcotizacion = idsolicitudcotizacion;
	}

	public Clieprov getClieprov() {
		return clieprov;
	}

	public void setClieprov(Clieprov clieprov) {
		this.clieprov = clieprov;
	}

	public SolicitudCompra getSolicitudcompra() {
		return solicitudcompra;
	}

	public void setSolicitudcompra(SolicitudCompra solicitudcompra) {
		this.solicitudcompra = solicitudcompra;
	}

}
