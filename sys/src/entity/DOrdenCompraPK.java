package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * ID class for entity: DOrdenCompra
 *
 */ 
@Embeddable
public class DOrdenCompraPK  implements Serializable {   
   
	         
	private Long idordencompra;         
	private int item;
	private static final long serialVersionUID = 1L;

	public DOrdenCompraPK() {}

	

	public Long getIdordencompra() {
		return this.idordencompra;
	}

	public void setIdordencompra(Long idordencompra) {
		this.idordencompra = idordencompra;
	}
	

	public int getItem() {
		return this.item;
	}

	public void setItem(int item) {
		this.item = item;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof DOrdenCompraPK)) {
			return false;
		}
		DOrdenCompraPK other = (DOrdenCompraPK) o;
		return true
			&& (getIdordencompra() == null ? other.getIdordencompra() == null : getIdordencompra().equals(other.getIdordencompra()))
			&& getItem() == other.getItem();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getIdordencompra() == null ? 0 : getIdordencompra().hashCode());
		result = prime * result + getItem();
		return result;
	}
   
   
}
