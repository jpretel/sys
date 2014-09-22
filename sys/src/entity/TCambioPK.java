package entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ID class for entity: TCambio
 *
 */ 
@Embeddable
public class TCambioPK  implements Serializable {   
   
	@Column
	private int dia;         
	@Column
	private int mes;         
	@Column
	private int anio;         
	private String idmoneda;
	private static final long serialVersionUID = 1L;

	public TCambioPK() {}

	

	public int getDia() {
		return this.dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}
	

	public int getMes() {
		return this.mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}
	

	public int getAnio() {
		return this.anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}
	

	public String getIdmoneda() {
		return this.idmoneda;
	}

	public void setIdmoneda(String idmoneda) {
		this.idmoneda = idmoneda;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof TCambioPK)) {
			return false;
		}
		TCambioPK other = (TCambioPK) o;
		return true
			&& getDia() == other.getDia()
			&& getMes() == other.getMes()
			&& getAnio() == other.getAnio()
			&& (getIdmoneda() == null ? other.getIdmoneda() == null : getIdmoneda().equals(other.getIdmoneda()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getDia();
		result = prime * result + getMes();
		result = prime * result + getAnio();
		result = prime * result + (getIdmoneda() == null ? 0 : getIdmoneda().hashCode());
		return result;
	}
   
   
}
