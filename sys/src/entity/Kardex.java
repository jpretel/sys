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

	private long idreferencia;
	
	@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false,  nullable = false)
	private Sucursal sucursal;
	
	@JoinColumns({
			@JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", nullable = false),
			@JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen", nullable = false) })
	private Almacen almacen;

	@ManyToOne
	@JoinColumn(name = "idproducto", referencedColumnName = "idproducto", nullable = false)
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "idmedida", referencedColumnName = "idunimedida", nullable = false)
	private Unimedida unimedida;

	@ManyToOne
	@JoinColumn(name = "idmoneda", referencedColumnName = "idmoneda", nullable = false)
	private Moneda moneda;

	@ManyToOne
	@JoinColumn(name = "idconcepto", referencedColumnName = "idconcepto", nullable = false)
	private Concepto concepto;
	
	@Column
	private int fecha;
	
	@Column
	private int dia;

	@Column
	private int mes;

	@Column
	private int anio;

	@Column(precision = 1, scale = 0)
	private int factor;

	@Column(precision = 17, scale = 8)
	private float cantidad;

	@Column(precision = 17, scale = 8)
	private float precio;

	@Column(precision = 17, scale = 8)
	private float importemof;

	@Column(precision = 17, scale = 8)
	private float importemex;
	
	@Column
	private char tipo;
	
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

	public long getIdreferencia() {
		return idreferencia;
	}

	public void setIdreferencia(long idreferencia) {
		this.idreferencia = idreferencia;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getImportemof() {
		return importemof;
	}

	public void setImportemof(float importemof) {
		this.importemof = importemof;
	}

	public float getImportemex() {
		return importemex;
	}

	public void setImportemex(float importemex) {
		this.importemex = importemex;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
}
