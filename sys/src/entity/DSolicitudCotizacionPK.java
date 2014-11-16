package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * ID class for entity: DRequerimiento
 *
 */
@Embeddable
public class DSolicitudCotizacionPK implements Serializable {

	private long idsolicitudcotizacion;
	private int item;
	private static final long serialVersionUID = 1L;

	public DSolicitudCotizacionPK() {
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
		if (!(o instanceof DSolicitudCotizacionPK)) {
			return false;
		}
		DSolicitudCotizacionPK other = (DSolicitudCotizacionPK) o;
		return true
				&& getIdsolicitudcotizacion() == other
						.getIdsolicitudcotizacion()
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
				+ ((int) (getIdsolicitudcotizacion() ^ (getIdsolicitudcotizacion() >>> 32)));
		result = prime * result + getItem();
		return result;
	}

	public long getIdsolicitudcotizacion() {
		return idsolicitudcotizacion;
	}

	public void setIdsolicitudcotizacion(long idsolicitudcotizacion) {
		this.idsolicitudcotizacion = idsolicitudcotizacion;
	}

}
