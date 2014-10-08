package entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Embeddable;

/**
 * ID class for entity: PrivUsuarioAlmacen
 *
 */ 
@Embeddable
public class PrivUsuarioAlmacenPK  implements Serializable {   
   
	         
	private String idusuario;         
	private String idsucursal;         
	private String idalmacen;
	private static final long serialVersionUID = 1L;

	public PrivUsuarioAlmacenPK() {}

	public String getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}
	

	public String getIdsucursal() {
		return this.idsucursal;
	}

	public void setIdsucursal(String idsucursal) {
		this.idsucursal = idsucursal;
	}
	

	public String getIdalmacen() {
		return this.idalmacen;
	}

	public void setIdalmacen(String idalmacen) {
		this.idalmacen = idalmacen;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof PrivUsuarioAlmacenPK)) {
			return false;
		}
		PrivUsuarioAlmacenPK other = (PrivUsuarioAlmacenPK) o;
		return true
			&& (getIdusuario() == null ? other.getIdusuario() == null : getIdusuario().equals(other.getIdusuario()))
			&& (getIdsucursal() == null ? other.getIdsucursal() == null : getIdsucursal().equals(other.getIdsucursal()))
			&& (getIdalmacen() == null ? other.getIdalmacen() == null : getIdalmacen().equals(other.getIdalmacen()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getIdusuario() == null ? 0 : getIdusuario().hashCode());
		result = prime * result + (getIdsucursal() == null ? 0 : getIdsucursal().hashCode());
		result = prime * result + (getIdalmacen() == null ? 0 : getIdalmacen().hashCode());
		return result;
	}
   
   
}
