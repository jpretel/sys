package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the docingreso database table.
 * 
 */
@Entity
@Table(name="docingreso")
@NamedQuery(name="Docingreso.findAll", query="SELECT d FROM Docingreso d")
public class Docingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int iddocingreso;

	@Column(nullable=false, length=15)
	private String correlativo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false, length=500)
	private String glosa;

	@Column(nullable=false, length=4)
	private String idalmacen;

	@Column(nullable=false, length=3)
	private String idmotivo;

	@Column(nullable=false, length=3)
	private String idresponsable;

	@Column(nullable=false, length=4)
	private String idsucursal;

	@Column(nullable=false, length=10)
	private String numero;

	@Column(nullable=false, length=4)
	private String serie;

	public Docingreso() {
	}

	public int getIddocingreso() {
		return this.iddocingreso;
	}

	public void setIddocingreso(int iddocingreso) {
		this.iddocingreso = iddocingreso;
	}

	public String getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getGlosa() {
		return this.glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public String getIdalmacen() {
		return this.idalmacen;
	}

	public void setIdalmacen(String idalmacen) {
		this.idalmacen = idalmacen;
	}

	public String getIdmotivo() {
		return this.idmotivo;
	}

	public void setIdmotivo(String idmotivo) {
		this.idmotivo = idmotivo;
	}

	public String getIdresponsable() {
		return this.idresponsable;
	}

	public void setIdresponsable(String idresponsable) {
		this.idresponsable = idresponsable;
	}

	public String getIdsucursal() {
		return this.idsucursal;
	}

	public void setIdsucursal(String idsucursal) {
		this.idsucursal = idsucursal;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

}