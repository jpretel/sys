package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * ID class for entity: DOrdenCompra
 *
 */
@Embeddable
public class DSolicitudCompraPK implements Serializable {

	private Long idsolicitudcompra;
	private int item;
	private static final long serialVersionUID = 1L;

	public DSolicitudCompraPK() {
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
		if (!(o instanceof DSolicitudCompraPK)) {
			return false;
		}
		DSolicitudCompraPK other = (DSolicitudCompraPK) o;
		return true
				&& (getIdsolicitudcompra() == null ? other
						.getIdsolicitudcompra() == null
						: getIdsolicitudcompra().equals(
								other.getIdsolicitudcompra()))
				&& getItem() == other.getItem();
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (getIdsolicitudcompra() == null ? 0 : getIdsolicitudcompra()
						.hashCode());
		result = prime * result + getItem();
		return result;
	}

	public Long getIdsolicitudcompra() {
		return idsolicitudcompra;
	}

	public void setIdsolicitudcompra(Long idsolicitudcompra) {
		this.idsolicitudcompra = idsolicitudcompra;
	}

}
