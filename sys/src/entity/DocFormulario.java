package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the doc_formulario database table.
 * 
 */
@Entity
@Table(name="doc_formulario")
@NamedQuery(name="DocFormulario.findAll", query="SELECT d FROM DocFormulario d")
public class DocFormulario implements Serializable {
	@Override
	public String toString() {
		return "DocFormulario [iddocumento=" + iddocumento + ", estado="
				+ estado + ", idopcion=" + idopcion + ", documento="
				+ documento + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=3)
	private String iddocumento;

	@Column(nullable=false)
	private int estado;

	@Column(nullable=false, length=3)
	private String idopcion;

	//bi-directional many-to-one association to Documento
	@ManyToOne
	@JoinColumn(name="iddocumento", nullable=false, insertable=false, updatable=false)
	private Documento documento;

	public DocFormulario() {
	}

	public String getIddocumento() {
		return this.iddocumento;
	}

	public void setIddocumento(String iddocumento) {
		this.iddocumento = iddocumento;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getIdopcion() {
		return this.idopcion;
	}

	public void setIdopcion(String idopcion) {
		this.idopcion = idopcion;
	}

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}