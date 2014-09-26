package entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: DAsiento
 *
 */
@Entity
@Table(name = "dasiento")
public class DAsiento implements Serializable {

	@EmbeddedId
	private DAsientoPK id;

	@JoinColumn(name = "idcuenta", referencedColumnName = "idcuenta", insertable = false, updatable = false)
	private Cuenta cuenta;

	private float debe;

	private float debe_of;

	private float debe_ex;

	private float haber;

	private float haber_of;

	private float haber_ex;
	
	// Analisis por Producto
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto", insertable = false, updatable = false)
	private Producto producto;
	
	@Column(scale = 17, precision = 8)
	private float cantidad;
	
	// Analisis por Centro de Costo
	@JoinColumn(name = "idconsumidor", referencedColumnName = "idconsumidor", insertable = false, updatable = false)
	private Consumidor consumidor;
	
	@ManyToOne
	@JoinColumn(name = "idasiento", referencedColumnName ="idasiento", insertable = false, updatable = false)
	private Asiento asiento;
	
	private static final long serialVersionUID = 1L;
	
	public DAsiento() {
		super();
	}
	
	public DAsientoPK getId() {
		return id;
	}

	public void setId(DAsientoPK id) {
		this.id = id;
	}

	public float getDebe() {
		return debe;
	}

	public void setDebe(float debe) {
		this.debe = debe;
	}

	public float getDebe_of() {
		return debe_of;
	}

	public void setDebe_of(float debe_of) {
		this.debe_of = debe_of;
	}

	public float getDebe_ex() {
		return debe_ex;
	}

	public void setDebe_ex(float debe_ex) {
		this.debe_ex = debe_ex;
	}

	public float getHaber() {
		return haber;
	}

	public void setHaber(float haber) {
		this.haber = haber;
	}

	public float getHaber_of() {
		return haber_of;
	}

	public void setHaber_of(float haber_of) {
		this.haber_of = haber_of;
	}

	public float getHaber_ex() {
		return haber_ex;
	}

	public void setHaber_ex(float haber_ex) {
		this.haber_ex = haber_ex;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}
}
