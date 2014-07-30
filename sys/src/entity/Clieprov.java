package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the clieprov database table.
 * 
 */
@Entity
@Table(name="clieprov")
@NamedQuery(name="Clieprov.findAll", query="SELECT c FROM Clieprov c")
public class Clieprov implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=11)
	private String idclieprov;

	@Column(nullable=false, length=200)
	private String direccion;

	@Column(name="razon_social", nullable=false, length=200)
	private String razonSocial;

	@Column(nullable=false, length=11)
	private String ruc;

	@Column(nullable=false, length=1)
	private String tipo;

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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}