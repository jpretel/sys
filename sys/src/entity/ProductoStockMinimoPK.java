package entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Embeddable;

/**
 * ID class for entity: ProductoStockMinimo
 *
 */
@Embeddable
public class ProductoStockMinimoPK  implements Serializable {   
   
	         
	private String idproducto;         
	private String idsucursal;         
	private String idalmacen;
	private static final long serialVersionUID = 1L;

	public ProductoStockMinimoPK() {}

	

	public String getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(String idproducto) {
		this.idproducto = idproducto;
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
		if (!(o instanceof ProductoStockMinimoPK)) {
			return false;
		}
		ProductoStockMinimoPK other = (ProductoStockMinimoPK) o;
		return true
			&& (getIdproducto() == null ? other.getIdproducto() == null : getIdproducto().equals(other.getIdproducto()))
			&& (getIdsucursal() == null ? other.getIdsucursal() == null : getIdsucursal().equals(other.getIdsucursal()))
			&& (getIdalmacen() == null ? other.getIdalmacen() == null : getIdalmacen().equals(other.getIdalmacen()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getIdproducto() == null ? 0 : getIdproducto().hashCode());
		result = prime * result + (getIdsucursal() == null ? 0 : getIdsucursal().hashCode());
		result = prime * result + (getIdalmacen() == null ? 0 : getIdalmacen().hashCode());
		return result;
	}
   
   
}
