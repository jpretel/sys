package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transportista database table.
 * 
 */
@Entity
@Table(name="transportista")
@NamedQuery(name="Transportista.findAll", query="SELECT t FROM Transportista t")
public class Transportista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique=true, nullable=false, length=8)
	private String dni;

	@Column(nullable=false, length=100)
	private String direccion;

	@Column(name="fech_venc", length=10)
	private String fechVenc;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(name="num_lic", length=20)
	private String numLic;

	@Column(name="tipo_brevete", nullable=false, length=10)
	private String tipoBrevete;

	public Transportista() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFechVenc() {
		return this.fechVenc;
	}

	public void setFechVenc(String fechVenc) {
		this.fechVenc = fechVenc;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumLic() {
		return this.numLic;
	}

	public void setNumLic(String numLic) {
		this.numLic = numLic;
	}

	public String getTipoBrevete() {
		return this.tipoBrevete;
	}

	public void setTipoBrevete(String tipoBrevete) {
		this.tipoBrevete = tipoBrevete;
	}

}