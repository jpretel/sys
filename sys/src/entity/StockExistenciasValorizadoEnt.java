package entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


public class StockExistenciasValorizadoEnt {
	
	

	String codigo;
	String descripcion;
	String Marca;
	BigDecimal stock;
	BigDecimal Costo;
	
	public StockExistenciasValorizadoEnt(){}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public BigDecimal getStock() {
		return stock;
	}
	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}
	public BigDecimal getCosto() {
		return Costo;
	}
	public void setCosto(BigDecimal costo) {
		Costo = costo;
	}
	
	
}
