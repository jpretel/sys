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
	
	@Column(length = 10)
	private String numerador;
	
	@Column(scale = 1, precision = 0)
	private int estado;
	
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
	
	public String getNumerador() {
		return numerador;
	}
	
	public void setNumerador(String numerador) {
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
}