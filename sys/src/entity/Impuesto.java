package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Impuesto
 *
 */
@Entity
@Table(name = "impuesto")
public class Impuesto implements Serializable {

	@Id
	@Column(length = 3)
	private String idimpuesto;
	@Column(length = 75)
	private String descripcion;
	@Column(precision = 6, scale = 3)
	private float tasa;
	private static final long serialVersionUID = 1L;

	public Impuesto() {
		super();
	}

	public String getIdimpuesto() {
		return this.idimpuesto;
	}

	public void setIdimpuesto(String idimpuesto) {
		this.idimpuesto = idimpuesto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getTasa() {
		return tasa;
	}

	public void setTasa(float tasa) {
		this.tasa = tasa;
	}

}
