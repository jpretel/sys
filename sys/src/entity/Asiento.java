package entity;

import java.io.Serializable;
import java.lang.Long;

import javax.persistence.*;

@Entity
@Table(name = "asiento")
public class Asiento implements Serializable {

	@Id
	private Long idasiento;

	@JoinColumn(name = "idsubdiario", referencedColumnName = "idsubdiario")
	private Subdiario subdiario;

	@JoinColumn(name = "idmoneda", referencedColumnName = "idmoneda")
	private Moneda moneda;

	@Column(scale = 3)
	private int dia;

	@Column(scale = 2)
	private int mes;

	@Column(scale = 4)
	private int anio;

	@Column
	private int aniomesdia;

	@Column
	private int numerador;
	
	@Column(scale = 1, precision = 0)
	private int estado;
	
	@Column(scale = 17, precision = 4)
	private float tcambio;
	
	@Column(scale = 17, precision = 6)
	private float tcmoneda;
	
	@Column(scale = 17, precision = 2)
	private float debe;

	@Column(scale = 17, precision = 2)
	private float haber;

	@Column(scale = 17, precision = 2)
	private float debe_of;

	@Column(scale = 17, precision = 2)
	private float haber_of;

	@Column(scale = 17, precision = 2)
	private float debe_ex;

	@Column(scale = 17, precision = 2)
	private float haber_ex;

	@Column
	private char tipo;

	private static final long serialVersionUID = 1L;

	public Asiento() {
		super();
	}

	public Long getIdasiento() {
		return this.idasiento;
	}

	public void setIdasiento(Long idasiento) {
		this.idasiento = idasiento;
	}

	public Subdiario getSubdiario() {
		return subdiario;
	}

	public void setSubdiario(Subdiario subdiario) {
		this.subdiario = subdiario;
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

	public int getNumerador() {
		return numerador;
	}

	public void setNumerador(int numerador) {
		this.numerador = numerador;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public int getAniomesdia() {
		return aniomesdia;
	}

	public void setAniomesdia(int aniomesdia) {
		this.aniomesdia = aniomesdia;
	}

	public float getDebe() {
		return debe;
	}

	public void setDebe(float debe) {
		this.debe = debe;
	}

	public float getHaber() {
		return haber;
	}

	public void setHaber(float haber) {
		this.haber = haber;
	}

	public float getDebe_of() {
		return debe_of;
	}

	public void setDebe_of(float debe_of) {
		this.debe_of = debe_of;
	}

	public float getHaber_of() {
		return haber_of;
	}

	public void setHaber_of(float haber_of) {
		this.haber_of = haber_of;
	}

	public float getDebe_ex() {
		return debe_ex;
	}

	public void setDebe_ex(float debe_ex) {
		this.debe_ex = debe_ex;
	}

	public float getHaber_ex() {
		return haber_ex;
	}

	public void setHaber_ex(float haber_ex) {
		this.haber_ex = haber_ex;
	}
	
	@Override
	public String toString() {
		return idasiento + " " + numerador;
	}

	public float getTcambio() {
		return tcambio;
	}

	public void setTcambio(float tcambio) {
		this.tcambio = tcambio;
	}

	public float getTcmoneda() {
		return tcmoneda;
	}

	public void setTcmoneda(float tcmoneda) {
		this.tcmoneda = tcmoneda;
	}
}