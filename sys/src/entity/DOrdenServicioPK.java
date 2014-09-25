package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * ID class for entity: DOrdenCompra
 *
 */
@Embeddable
public class DOrdenServicioPK implements Serializable {

	private Long idordenservicio;
	private int item;
	private static final long serialVersionUID = 1L;

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
		if (!(o instanceof DOrdenServicioPK)) {
			return false;
		}
		DOrdenServicioPK other = (DOrdenServicioPK) o;
		return true
				&& (getIdordenservicio() == null ? other.getIdordenservicio() == null
						: getIdordenservicio().equals(
								other.getIdordenservicio()))
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
				+ (getIdordenservicio() == null ? 0 : getIdordenservicio()
						.hashCode());
		result = prime * result + getItem();
		return result;
	}

	public Long getIdordenservicio() {
		return idordenservicio;
	}

	public void setIdordenservicio(Long idordenservicio) {
		this.idordenservicio = idordenservicio;
	}

}
