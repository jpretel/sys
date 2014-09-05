package entity;

import java.io.Serializable;
import java.lang.Long;

import javax.persistence.Embeddable;

/**
 * ID class for entity: DAsiento
 *
 */ 
@Embeddable
public class DAsientoPK  implements Serializable {   
   
	         
	private Long idasiento;         
	private int item;
	private static final long serialVersionUID = 1L;

	public DAsientoPK() {}

	

	public Long getIdasiento() {
		return this.idasiento;
	}

	public void setIdasiento(Long idasiento) {
		this.idasiento = idasiento;
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
		if (!(o instanceof DAsientoPK)) {
			return false;
		}
		DAsientoPK other = (DAsientoPK) o;
		return true
			&& (getIdasiento() == null ? other.getIdasiento() == null : getIdasiento().equals(other.getIdasiento()))
			&& getItem() == other.getItem();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getIdasiento() == null ? 0 : getIdasiento().hashCode());
		result = prime * result + getItem();
		return result;
	}
   
   
}
