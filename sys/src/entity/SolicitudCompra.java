package entity;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: OrdenCompra
 *
 */
@Entity
public class SolicitudCompra implements Serializable {

	@Id
	private Long idsolicitudcompra;

	private int numero;
	@Column(length = 4)
	private String serie;

	private int dia;
	private int mes;
	private int anio;
	private int fecha;

	@ManyToOne
	@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")
	private Sucursal sucursal;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false, nullable = false),
			@JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen") })
	private Almacen almacen;
	@ManyToOne
	@JoinColumn(name = "idresponsable", referencedColumnName = "idresponsable")
	private Responsable responsable;
	private String glosa;

	private static final long serialVersionUID = 1L;

	public SolicitudCompra() {
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

	public Long getIdsolicitudcompra() {
		return idsolicitudcompra;
	}

	public void setIdsolicitudcompra(Long idsolicitudcompra) {
		this.idsolicitudcompra = idsolicitudcompra;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

}
