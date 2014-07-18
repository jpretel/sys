package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the almacen database table.
 * 
 */
@Entity
@Table(name="almacen")
@NamedQuery(name="Almacen.findAll", query="SELECT a FROM Almacen a")
public class Almacen implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlmacenPK id;

	@Column(length=75)
	private String descripcion;

	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="idsucursal", nullable=false, insertable=false, updatable=false)
	private Sucursal sucursal;

	public Almacen() {
	}

	public AlmacenPK getId() {
		return this.id;
	}

	public void setId(AlmacenPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}