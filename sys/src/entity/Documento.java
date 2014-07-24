package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the documento database table.
 * 
 */
@Entity
@Table(name="documento")
@NamedQuery(name="Documento.findAll", query="SELECT d FROM Documento d")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=3)
	private String iddocumento;

	@Column(name="cod_sunat", length=4)
	private String codSunat;

	@Column(length=75)
	private String descripcion;

	//bi-directional many-to-one association to DocumentoNumero
	@OneToMany(mappedBy="documento")
	private List<DocumentoNumero> documentoNumeros;

	public Documento() {
	}

	public String getIddocumento() {
		return this.iddocumento;
	}

	public void setIddocumento(String iddocumento) {
		this.iddocumento = iddocumento;
	}

	public String getCodSunat() {
		return this.codSunat;
	}

	public void setCodSunat(String codSunat) {
		this.codSunat = codSunat;
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
		documentoNumero.setDocumento(this);

		return documentoNumero;
	}

	public DocumentoNumero removeDocumentoNumero(DocumentoNumero documentoNumero) {
		getDocumentoNumeros().remove(documentoNumero);
		documentoNumero.setDocumento(null);

		return documentoNumero;
	}

}