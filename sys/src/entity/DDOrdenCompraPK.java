package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * ID class for entity: DDOrdenCompra
 *
 */
@Embeddable
public class DDOrdenCompraPK  implements Serializable {   
   
	         
	private long idordencompra;         
	private int item;
	private static final long serialVersionUID = 1L;

	public DDOrdenCompraPK() {}

	

	public long getIdordencompra() {
		return this.idordencompra;
	}

	public void setIdordencompra(long idordencompra) {
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
		if (!(o instanceof DDOrdenCompraPK)) {
			return false;
		}
		DDOrdenCompraPK other = (DDOrdenCompraPK) o;
		return true
			&& getIdordencompra() == other.getIdordencompra()
			&& getItem() == other.getItem();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((int) (getIdordencompra() ^ (getIdordencompra() >>> 32)));
		result = prime * result + getItem();
		return result;
	}
   
   
}
