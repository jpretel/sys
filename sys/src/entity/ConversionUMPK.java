package entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ID class for entity: ConversionUM
 *
 */ 
@Embeddable
public class ConversionUMPK  implements Serializable {   
   
	private String idunimedida;
	private String idunimedida_equiv;
	private static final long serialVersionUID = 1L;

	public ConversionUMPK() {}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof ConversionUMPK)) {
			return false;
		}
		ConversionUMPK other = (ConversionUMPK) o;
		return true
			&& (getIdunimedida() == null ? other.getIdunimedida() == null : getIdunimedida().equals(other.getIdunimedida()))
			&& (getIdunimedida_equiv() == null ? other.getIdunimedida_equiv() == null : getIdunimedida_equiv().equals(other.getIdunimedida_equiv()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getIdunimedida() == null ? 0 : getIdunimedida().hashCode());
		result = prime * result + (getIdunimedida_equiv() == null ? 0 : getIdunimedida_equiv().hashCode());
		return result;
	}


	public String getIdunimedida() {
		return idunimedida;
	}


	public void setIdunimedida(String idunimedida) {
		this.idunimedida = idunimedida;
	}


	public String getIdunimedida_equiv() {
		return idunimedida_equiv;
	}


	public void setIdunimedida_equiv(String idunimedida_equiv) {
		this.idunimedida_equiv = idunimedida_equiv;
	}
   
   
}
