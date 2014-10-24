package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * ID class for entity: DOrdenCompra
 *
 */ 
@Embeddable
public class DCotizacionCompraPK  implements Serializable {   
   
	         
	private Long idcotizacioncompra;         
	private int item;
	private static final long serialVersionUID = 1L;

	public DCotizacionCompraPK() {}	

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
		if (!(o instanceof DCotizacionCompraPK)) {
			return false;
		}
		DCotizacionCompraPK other = (DCotizacionCompraPK) o;
		return true
			&& (getIdcotizacioncompra() == null ? other.getIdcotizacioncompra() == null : getIdcotizacioncompra().equals(other.getIdcotizacioncompra()))
			&& getItem() == other.getItem();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getIdcotizacioncompra() == null ? 0 : getIdcotizacioncompra().hashCode());
		result = prime * result + getItem();
		return result;
	}



	public Long getIdcotizacioncompra() {
		return idcotizacioncompra;
	}



	public void setIdcotizacioncompra(Long idcotizacioncompra) {
		this.idcotizacioncompra = idcotizacioncompra;
	}
   
   
}
