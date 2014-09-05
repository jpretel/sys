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
	@Column(nullable=false, length=3)
	private String iddocumento;

	@Column(name="nomenclatura", length=50)
	private String nomenclatura;
	
	@Column(name="cod_sunat", length=4)
	private String codSunat;

	@Column(name="descripcion", length=200)
	private String descripcion;
	
	@Column(name="formulario", length=200)
	private String formulario;

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
	
	public String getNomenclatura() {
		return nomenclatura;
	}

	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}
	
	public String getFormulario() {
		return formulario;
	}

	public void setFormulario(String formulario) {
		this.formulario = formulario;
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