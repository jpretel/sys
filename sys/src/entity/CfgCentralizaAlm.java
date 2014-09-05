package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CfgCentralizaAlm
 *
 */
@Entity
@Table(name = "cfg_centraliza_alm")
public class CfgCentralizaAlm implements Serializable {

	@EmbeddedId
	private CfgCentralizaAlmPK id;
	@JoinColumn(name = "idcuenta_debe", referencedColumnName = "idcuenta")
	private Cuenta cta_debe;
	@JoinColumn(name = "idcuenta_haber", referencedColumnName = "idcuenta")
	private Cuenta cta_haber;

	@ManyToOne
	@JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo", insertable = false, updatable = false)
	private Grupo grupo;

	@ManyToOne
	@JoinColumn(name = "idconcepto", referencedColumnName = "idconcepto", insertable = false, updatable = false)
	private Concepto concepto;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo", insertable = false, updatable = false),
			@JoinColumn(name = "idsubgrupo", referencedColumnName = "idsubgrupo", insertable = false, updatable = false) })
	private Subgrupo subgrupo;

	public CfgCentralizaAlmPK getId() {
		return id;
	}

	public void setId(CfgCentralizaAlmPK id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;

	public CfgCentralizaAlm() {
		super();
	}

	public Cuenta getCta_debe() {
		return cta_debe;
	}

	public void setCta_debe(Cuenta cta_debe) {
		this.cta_debe = cta_debe;
	}

	public Cuenta getCta_haber() {
		return cta_haber;
	}

	public void setCta_haber(Cuenta cta_haber) {
		this.cta_haber = cta_haber;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Subgrupo getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(Subgrupo subgrupo) {
		this.subgrupo = subgrupo;
	}

	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

}
