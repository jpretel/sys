package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prueba database table.
 * 
 */
@Entity
@Table(name="prueba")
@NamedQuery(name="Prueba.findAll", query="SELECT p FROM Prueba p")
public class Prueba implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idprueba;

	@Column(length=45)
	private String pruebacol;
	
	@Column
	private String pruebacol2;
	
	public String getPruebacol2() {
		return pruebacol2;
	}

	public void setPruebacol2(String pruebacol2) {
		this.pruebacol2 = pruebacol2;
	}

	public Prueba() {
	}

	public int getIdprueba() {
		return this.idprueba;
	}

	public void setIdprueba(int idprueba) {
		this.idprueba = idprueba;
	}

	public String getPruebacol() {
		return this.pruebacol;
	}

	public void setPruebacol(String pruebacol) {
		this.pruebacol = pruebacol;
	}

}