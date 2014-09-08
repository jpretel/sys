package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GrupoCentralizacion
 *
 */
@Entity
@Table(name = "grupo_centralizacion")
public class GrupoCentralizacion implements Serializable {

	@Id
	@Column(nullable = false, length = 3)
	private String idgcentralizacion;
	@Column(length = 75)
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "idsubdiario", referencedColumnName = "idsubdiario")
	private Subdiario subdiario;
	private static final long serialVersionUID = 1L;

	public GrupoCentralizacion() {
		super();
	}

	public String getIdgcentralizacion() {
		return this.idgcentralizacion;
	}

	public void setIdgcentralizacion(String idgcentralizacion) {
		this.idgcentralizacion = idgcentralizacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Subdiario getSubdiario() {
		return subdiario;
	}

	public void setSubdiario(Subdiario subdiario) {
		this.subdiario = subdiario;
	}

}
