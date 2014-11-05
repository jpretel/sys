package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Requerimiento
 *
 */
@Entity
public class Requerimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long idrequerimiento;

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
	@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")
	private Sucursal sucursal;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false),
			@JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen") })
	private Almacen almacen;

	@ManyToOne
	@JoinColumn(name = "idresponsable", referencedColumnName = "idresponsable")
	private Responsable responsable;

	@ManyToOne
	@JoinColumn(name = "idflujo", referencedColumnName = "idflujo")
	private Flujo flujo;

	public Requerimiento() {
		super();
	}

	public long getIdrequerimiento() {
		return idrequerimiento;
	}

	public void setIdrequerimiento(long idrequerimiento) {
		this.idrequerimiento = idrequerimiento;
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

	public Flujo getFlujo() {
		return flujo;
	}

	public void setFlujo(Flujo flujo) {
		this.flujo = flujo;
	}

}
