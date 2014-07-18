package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the subdiario database table.
 * 
 */
@Entity
@NamedQuery(name="Subdiario.findAll", query="SELECT s FROM Subdiario s")
public class Subdiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idsubdiario;

	private String descripcion;

	@Column(name="es_declarable")
	private int esDeclarable;

	public Subdiario() {
	}

	public String getIdsubdiario() {
		return this.idsubdiario;
	}

	public void setIdsubdiario(String idsubdiario) {
		this.idsubdiario = idsubdiario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEsDeclarable() {
		return this.esDeclarable;
	}

	public void setEsDeclarable(int esDeclarable) {
		this.esDeclarable = esDeclarable;
	}

}