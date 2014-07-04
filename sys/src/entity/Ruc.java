package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ruc database table.
 * 
 */
@Entity
@Table(name="ruc")
@NamedQuery(name="Ruc.findAll", query="SELECT r FROM Ruc r")
public class Ruc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique=true, nullable=false)
	private int ruc;

	@Column(length=30)
	private String actComerExt;

	@Column(length=200)
	private String actEcon;

	@Column(length=50)
	private String comprPago;

	@Column(length=25)
	private String condicion;

	@Column(length=200)
	private String direccion;

	@Column(length=30)
	private String emisionCompr;

	private byte estado;

	@Temporal(TemporalType.DATE)
	private Date fechaInicAct;

	@Temporal(TemporalType.DATE)
	private Date fechaInsc;

	@Temporal(TemporalType.DATE)
	private Date fechaPLE;

	@Column(length=45)
	private String nomComercial;

	@Column(length=50)
	private String padrones;

	@Column(length=50)
	private String profesion;

	@Column(length=45)
	private String razonSocial;

	@Column(length=30)
	private String sistCont;

	@Column(length=50)
	private String sistEmiElec;

	@Column(length=45)
	private String tipoContribuyente;

	private int tipoDocumento;

	public Ruc() {
	}

	public int getRuc() {
		return this.ruc;
	}

	public void setRuc(int ruc) {
		this.ruc = ruc;
	}

	public String getActComerExt() {
		return this.actComerExt;
	}

	public void setActComerExt(String actComerExt) {
		this.actComerExt = actComerExt;
	}

	public String getActEcon() {
		return this.actEcon;
	}

	public void setActEcon(String actEcon) {
		this.actEcon = actEcon;
	}

	public String getComprPago() {
		return this.comprPago;
	}

	public void setComprPago(String comprPago) {
		this.comprPago = comprPago;
	}

	public String getCondicion() {
		return this.condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmisionCompr() {
		return this.emisionCompr;
	}

	public void setEmisionCompr(String emisionCompr) {
		this.emisionCompr = emisionCompr;
	}

	public byte getEstado() {
		return this.estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public Date getFechaInicAct() {
		return this.fechaInicAct;
	}

	public void setFechaInicAct(Date fechaInicAct) {
		this.fechaInicAct = fechaInicAct;
	}

	public Date getFechaInsc() {
		return this.fechaInsc;
	}

	public void setFechaInsc(Date fechaInsc) {
		this.fechaInsc = fechaInsc;
	}

	public Date getFechaPLE() {
		return this.fechaPLE;
	}

	public void setFechaPLE(Date fechaPLE) {
		this.fechaPLE = fechaPLE;
	}

	public String getNomComercial() {
		return this.nomComercial;
	}

	public void setNomComercial(String nomComercial) {
		this.nomComercial = nomComercial;
	}

	public String getPadrones() {
		return this.padrones;
	}

	public void setPadrones(String padrones) {
		this.padrones = padrones;
	}

	public String getProfesion() {
		return this.profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getSistCont() {
		return this.sistCont;
	}

	public void setSistCont(String sistCont) {
		this.sistCont = sistCont;
	}

	public String getSistEmiElec() {
		return this.sistEmiElec;
	}

	public void setSistEmiElec(String sistEmiElec) {
		this.sistEmiElec = sistEmiElec;
	}

	public String getTipoContribuyente() {
		return this.tipoContribuyente;
	}

	public void setTipoContribuyente(String tipoContribuyente) {
		this.tipoContribuyente = tipoContribuyente;
	}

	public int getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}