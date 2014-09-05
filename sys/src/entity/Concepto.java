package entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="concepto")
public class Concepto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=3)
	private String idconcepto;

	@Column(nullable=false)
	private int centraliza;

	@Column(nullable=false, length=200)
	private String descripcion;

	@Column(name="es_transferencia", nullable=false)
	private int esTransferencia;

	@Column(name="nombre_corto", nullable=false, length=100)
	private String nombreCorto;

	@Column(name="solcita_compra", nullable=false)
	private int solcitaCompra;

	@Column(name="solicita_recepcion", nullable=false)
	private int solicitaRecepcion;

	@Column(nullable=false, length=1)
	private String tipo;

	public Concepto() {
	}

	public int getCentraliza() {
		return this.centraliza;
	}

	public void setCentraliza(int centraliza) {
		this.centraliza = centraliza;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEsTransferencia() {
		return this.esTransferencia;
	}

	public void setEsTransferencia(int esTransferencia) {
		this.esTransferencia = esTransferencia;
	}

	public String getNombreCorto() {
		return this.nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public int getSolcitaCompra() {
		return this.solcitaCompra;
	}

	public void setSolcitaCompra(int solcitaCompra) {
		this.solcitaCompra = solcitaCompra;
	}

	public int getSolicitaRecepcion() {
		return this.solicitaRecepcion;
	}

	public void setSolicitaRecepcion(int solicitaRecepcion) {
		this.solicitaRecepcion = solicitaRecepcion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String historial(){
        return "Codigo: "+this.idconcepto + " Descripcion: " + this.descripcion;
     }

	public String getIdconcepto() {
		return idconcepto;
	}

	public void setIdconcepto(String idconcepto) {
		this.idconcepto = idconcepto;
	}
}