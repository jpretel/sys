package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pto_emision database table.
 * 
 */
@Entity
@Table(name="pto_emision")
@NamedQuery(name="PtoEmision.findAll", query="SELECT p FROM PtoEmision p")
public class PtoEmision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=3)
	private String idptoemision;

	@Column(length=75)
	private String descripcion;

	//bi-directional many-to-one association to DocumentoNumero
	@OneToMany(mappedBy="ptoEmision")
	private List<DocumentoNumero> documentoNumeros;

	public PtoEmision() {
	}

	public String getIdptoemision() {
		return this.idptoemision;
	}

	public void setIdptoemision(String idptoemision) {
		this.idptoemision = idptoemision;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<DocumentoNumero> getDocumentoNumeros() {
		return this.documentoNumeros;
	}

	public void setDocumentoNumeros(List<DocumentoNumero> documentoNumeros) {
		this.documentoNumeros = documentoNumeros;
	}

	public DocumentoNumero addDocumentoNumero(DocumentoNumero documentoNumero) {
		getDocumentoNumeros().add(documentoNumero);
		documentoNumero.setPtoEmision(this);

		return documentoNumero;
	}

	public DocumentoNumero removeDocumentoNumero(DocumentoNumero documentoNumero) {
		getDocumentoNumeros().remove(documentoNumero);
		documentoNumero.setPtoEmision(null);

		return documentoNumero;
	}

}