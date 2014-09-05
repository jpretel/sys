package entity;

import java.io.Serializable;

import javax.persistence.*;

import vista.formularios.EntidadLog;


/**
 * The persistent class for the area database table.
 * 
 */
@Entity
@Table(name="area")
@NamedQuery(name="Area.findAll", query="SELECT a FROM Area a")
public class Area implements Serializable, EntidadLog {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=3)
	private String idarea;

	@Column(nullable=false, length=75)
	private String descripcion;

	public Area() {
	}

	public String getIdarea() {
		return this.idarea;
	}

	public void setIdarea(String idarea) {
		this.idarea = idarea;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String historial(){
        return "Codigo: "+this.idarea + " Descripcion: " + this.descripcion;
     }
	
	

}