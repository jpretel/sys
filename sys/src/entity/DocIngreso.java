package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the doc_ingreso database table.
 * 
 */
@Entity
@Table(name="doc_ingreso")
@NamedQuery(name="DocIngreso.findAll", query="SELECT d FROM DocIngreso d")
public class DocIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idingreso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false, length=2)
	private String idestado;

	@Column(nullable=false, length=2)
	private String idmoneda;

	@Column(nullable=false, length=7)
	private String numero;

	@Column(nullable=false, length=6)
	private String periodo;

	@Column(nullable=false, length=4)
	private String serie;

	//bi-directional many-to-one association to Almacen
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="idalmacen", referencedColumnName="IDALMACEN", nullable=false),
		@JoinColumn(name="idsucursal", referencedColumnName="IDSUCURSAL", nullable=false)
		})
	private Almacen almacen;

	//bi-directional many-to-one association to Documento
	@ManyToOne
	@JoinColumn(name="iddocumento", nullable=false)
	private Documento documento;

	//bi-directional many-to-one association to Clieprov
	@ManyToOne
	@JoinColumn(name="idclieprov", nullable=false)
	private Clieprov clieprov;

	//bi-directional many-to-one association to Motivo
	@ManyToOne
	@JoinColumn(name="idmotivo", nullable=false)
	private Motivo motivo;

	//bi-directional many-to-one association to Responsable
	@ManyToOne
	@JoinColumn(name="idresponsable", nullable=false)
	private Responsable responsable;

	public DocIngreso() {
	}

	public int getIdingreso() {
		return this.idingreso;
	}

	public void setIdingreso(int idingreso) {
		this.idingreso = idingreso;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getIdestado() {
		return this.idestado;
	}

	public void setIdestado(String idestado) {
		this.idestado = idestado;
	}

	public String getIdmoneda() {
		return this.idmoneda;
	}

	public void setIdmoneda(String idmoneda) {
		this.idmoneda = idmoneda;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Clieprov getClieprov() {
		return this.clieprov;
	}

	public void setClieprov(Clieprov clieprov) {
		this.clieprov = clieprov;
	}

	public Motivo getMotivo() {
		return this.motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public Responsable getResponsable() {
		return this.responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

}