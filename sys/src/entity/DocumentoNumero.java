package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the documento_numero database table.
 * 
 */
@Entity
@Table(name="documento_numero")
@NamedQuery(name="DocumentoNumero.findAll", query="SELECT d FROM DocumentoNumero d")
public class DocumentoNumero implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DocumentoNumeroPK id;

	@Column(length=8)
	private String numero;

	//bi-directional many-to-one association to Documento
	@ManyToOne
	@JoinColumn(name="iddocumento", nullable=false, insertable=false, updatable=false)
	private Documento documento;

	//bi-directional many-to-one association to PtoEmision
	@ManyToOne
	@JoinColumn(name="idptoemision", nullable=false, insertable=false, updatable=false)
	private PtoEmision ptoEmision;

	public DocumentoNumero() {
	}

	public DocumentoNumeroPK getId() {
		return this.id;
	}

	public void setId(DocumentoNumeroPK id) {
		this.id = id;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public PtoEmision getPtoEmision() {
		return this.ptoEmision;
	}

	public void setPtoEmision(PtoEmision ptoEmision) {
		this.ptoEmision = ptoEmision;
	}

}