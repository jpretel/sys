package entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Embeddable;

/**
 * ID class for entity: ProductoImpuesto
 *
 */ 
@Embeddable
public class ProductoImpuestoPK  implements Serializable {   
   
	         
	private String idproducto;         
	private String idimpuesto;
	private static final long serialVersionUID = 1L;

	public ProductoImpuestoPK() {}

	

	public String getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(String idproducto) {
		this.idproducto = idproducto;
	}
	

	public String getIdimpuesto() {
		return this.idimpuesto;
	}

	public void setIdimpuesto(String idimpuesto) {
		this.idimpuesto = idimpuesto;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof ProductoImpuestoPK)) {
			return false;
		}
		ProductoImpuestoPK other = (ProductoImpuestoPK) o;
		return true
			&& (getIdproducto() == null ? other.getIdproducto() == null : getIdproducto().equals(other.getIdproducto()))
			&& (getIdimpuesto() == null ? other.getIdimpuesto() == null : getIdimpuesto().equals(other.getIdimpuesto()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getIdproducto() == null ? 0 : getIdproducto().hashCode());
		result = prime * result + (getIdimpuesto() == null ? 0 : getIdimpuesto().hashCode());
		return result;
	}
   
   
}
