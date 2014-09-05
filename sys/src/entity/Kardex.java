package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Kardex
 *
 */
@Entity
@Table(name = "kardex")
public class Kardex implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)
	private Sucursal sucursal;
	@JoinColumns({
			@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false),
			@JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen", insertable = false, updatable = false) })
	private Almacen almacen;
	
	@Column
	private int dia;
	
	@Column
	private int mes;

	@Column
	private int anio;
	
	
	private Producto producto;

	private Unimedida unimedida;

	private int factor;

	@Column(precision = 17, scale = 8)
	private float cantidad;

	private static final long serialVersionUID = 1L;

	public Kardex() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Unimedida getUnimedida() {
		return unimedida;
	}

	public void setUnimedida(Unimedida unimedida) {
		this.unimedida = unimedida;
	}

	public int getFactor() {
		return factor;
	}

	public void setFactor(int factor) {
		this.factor = factor;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

}
