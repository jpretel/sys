package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Empresa
 *
 */
@Entity
public class Empresa implements Serializable {

	@Id
	private char id;
	
	@Column(length = 200)
	private String razon_social;
	
	@Column(length = 12)
	private String ruc;
	
	@Column(length = 200)
	private String direccion;
	
	@Column(length = 250)
	private String ruta_reportes;
	
	@Column(length = 250)
	private String ruta_exportar;

	private static final long serialVersionUID = 1L;

	public Empresa() {
		super();
	}

	public char getId() {
		return id;
	}

	public void setId(char id) {
		this.id = id;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRuta_reportes() {
		return ruta_reportes;
	}

	public void setRuta_reportes(String ruta_reportes) {
		this.ruta_reportes = ruta_reportes;
	}

	public String getRuta_exportar() {
		return ruta_exportar;
	}

	public void setRuta_exportar(String ruta_exportar) {
		this.ruta_exportar = ruta_exportar;
	}

}
