package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * ID class for entity: DRequerimiento
 *
 */ 
@Embeddable
public class DRequerimientoPK  implements Serializable {   
   
	         
	private long idrequerimiento;         
	private int item;
	private static final long serialVersionUID = 1L;

	public DRequerimientoPK() {}

	

	public long getIdrequerimiento() {
		return this.idrequerimiento;
	}

	public void setIdrequerimiento(long idrequerimiento) {
		this.idrequerimiento = idrequerimiento;
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
		if (!(o instanceof DRequerimientoPK)) {
			return false;
		}
		DRequerimientoPK other = (DRequerimientoPK) o;
		return true
			&& getIdrequerimiento() == other.getIdrequerimiento()
			&& getItem() == other.getItem();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((int) (getIdrequerimiento() ^ (getIdrequerimiento() >>> 32)));
		result = prime * result + getItem();
		return result;
	}
   
   
}
