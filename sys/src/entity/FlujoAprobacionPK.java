package entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Embeddable;

/**
 * ID class for entity: SysFormularioAprobacion
 *
 */ 
@Embeddable
public class FlujoAprobacionPK  implements Serializable {   
   
	         
	private String idformulario;         
	private int orden;
	private static final long serialVersionUID = 1L;

	public FlujoAprobacionPK() {}

	

	public String getIdformulario() {
		return this.idformulario;
	}

	public void setIdformulario(String idformulario) {
		this.idformulario = idformulario;
	}
	

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof FlujoAprobacionPK)) {
			return false;
		}
		FlujoAprobacionPK other = (FlujoAprobacionPK) o;
		return true
			&& (getIdformulario() == null ? other.getIdformulario() == null : getIdformulario().equals(other.getIdformulario()))
			&& getOrden() == other.getOrden();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getIdformulario() == null ? 0 : getIdformulario().hashCode());
		result = prime * result + getOrden();
		return result;
	}
   
   
}
