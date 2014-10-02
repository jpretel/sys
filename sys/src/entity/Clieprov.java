package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the clieprov database table.
 * 
 */
@Entity
@Table(name = "clieprov")
@NamedQuery(name = "Clieprov.findAll", query = "SELECT c FROM Clieprov c")
public class Clieprov implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = 11)
	private String idclieprov;

	@Column(nullable = false, length = 200)
	private String direccion;

	@Column(name = "razon_social", nullable = false, length = 200)
	private String razonSocial;

	@Override
	public String toString() {
		return "Clieprov [idclieprov=" + idclieprov + ", direccion="
				+ direccion + ", razonSocial=" + razonSocial + ", ruc=" + ruc
				+ "]";
	}

	@Column(nullable = false, length = 11)
	private String ruc;

	@Column
	private int es_cliente;

	@Column
	private int es_proveedor;

	@Column
	private int agente_retencion;

	@Column
	private int agente_percepcion;

	@Column
	private int buen_contribuyente;

	public Clieprov() {
	}

	public String getIdclieprov() {
		return this.idclieprov;
	}

	public void setIdclieprov(String idclieprov) {
		this.idclieprov = idclieprov;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String historial() {
		String tipoCambio = null;
		
		return "Código: " + idclieprov + ", dirección: " + direccion
				+ ", razonSocial: " + razonSocial + ", ruc: " + ruc + ", tipo="
				+ tipoCambio;
	}

	public int getAgente_retencion() {
		return agente_retencion;
	}

	public void setAgente_retencion(int agente_retencion) {
		this.agente_retencion = agente_retencion;
	}

	public int getAgente_percepcion() {
		return agente_percepcion;
	}

	public void setAgente_percepcion(int agente_percepcion) {
		this.agente_percepcion = agente_percepcion;
	}

	public int getBuen_contribuyente() {
		return buen_contribuyente;
	}

	public void setBuen_contribuyente(int buen_contribuyente) {
		this.buen_contribuyente = buen_contribuyente;
	}

	public int getEs_cliente() {
		return es_cliente;
	}

	public void setEs_cliente(int es_cliente) {
		this.es_cliente = es_cliente;
	}

	public int getEs_proveedor() {
		return es_proveedor;
	}

	public void setEs_proveedor(int es_proveedor) {
		this.es_proveedor = es_proveedor;
	}
}