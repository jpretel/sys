package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sys_modulo database table.
 * 
 */
@Entity
@Table(name="sys_modulo")
@NamedQuery(name="SysModulo.findAll", query="SELECT s FROM SysModulo s")
public class SysModulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=5)
	private String idmodulo;

	@Column(length=75)
	private String descripcion;

	@Column(length=250)
	private String imagen;

	//bi-directional many-to-one association to SysTitulo
	@OneToMany(mappedBy="sysModulo")
	private List<SysTitulo> sysTitulos;

	public SysModulo() {
	}

	public String getIdmodulo() {
		return this.idmodulo;
	}

	public void setIdmodulo(String idmodulo) {
		this.idmodulo = idmodulo;
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

	public List<SysTitulo> getSysTitulos() {
		return this.sysTitulos;
	}

	public void setSysTitulos(List<SysTitulo> sysTitulos) {
		this.sysTitulos = sysTitulos;
	}

	public SysTitulo addSysTitulo(SysTitulo sysTitulo) {
		getSysTitulos().add(sysTitulo);
		sysTitulo.setSysModulo(this);

		return sysTitulo;
	}

	public SysTitulo removeSysTitulo(SysTitulo sysTitulo) {
		getSysTitulos().remove(sysTitulo);
		sysTitulo.setSysModulo(null);

		return sysTitulo;
	}

}