package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: KardexRequerimiento
 *
 */
@Entity
@Table(name="kardex_requerimiento")

public class KardexRequerimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="idrequerimiento", referencedColumnName = "idrequerimiento")
	private Requerimiento requerimiento;
	
	@ManyToOne
	@JoinColumn(name="idproducto", referencedColumnName = "idproducto")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name="idunimedida", referencedColumnName = "idunimedida")
	private Unimedida unimedida;
	
	
	private int factor;
	
	@Column(length=10)
	private String tipo_referencia;
	
	private long id_referencia;
	
	@Column(precision = 17, scale = 6)
	private float cantidad;
	
	public KardexRequerimiento() {
		super();
	}  
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Requerimiento getRequerimiento() {
		return requerimiento;
	}

	public void setRequerimiento(Requerimiento requerimiento) {
		this.requerimiento = requerimiento;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
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

	public String getTipo_referencia() {
		return tipo_referencia;
	}

	public void setTipo_referencia(String tipo_referencia) {
		this.tipo_referencia = tipo_referencia;
	}

	public long getId_referencia() {
		return id_referencia;
	}

	public void setId_referencia(long id_referencia) {
		this.id_referencia = id_referencia;
	}

	public Unimedida getUnimedida() {
		return unimedida;
	}

	public void setUnimedida(Unimedida unimedida) {
		this.unimedida = unimedida;
	}
   
}
