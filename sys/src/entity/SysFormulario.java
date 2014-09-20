package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_formulario database table.
 * 
 */
@Entity
@Table(name="sys_formulario")
@NamedQuery(name="SysFormulario.findAll", query="SELECT s FROM SysFormulario s")
public class SysFormulario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=15)
	private String idformulario;

	@Column(length=75)
	private String descripcion;

	@Column(length=200)
	private String imagen;
	
	@Column(length=75)
	private String opcion;

	public SysFormulario() {
	}

	public String getIdformulario() {
		return this.idformulario;
	}

	public void setIdformulario(String idformulario) {
		this.idformulario = idformulario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

}