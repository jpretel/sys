package entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ID class for entity: CfgCentralizaAlm
 *
 */
@Embeddable
public class CfgCentralizaAlmPK implements Serializable {

	@Column(length = 3)
	private String idconcepto;
	@Column(length = 3)
	private String idgrupo;
	@Column(length = 3)
	private String idsubgrupo;

	private static final long serialVersionUID = 1L;

	public CfgCentralizaAlmPK() {
	}

	public String getIdgrupo() {
		return this.idgrupo;
	}

	public void setIdgrupo(String idgrupo) {
		this.idgrupo = idgrupo;
	}

	/*
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof CfgCentralizaAlmPK)) {
			return false;
		}
		CfgCentralizaAlmPK other = (CfgCentralizaAlmPK) o;
		return true
				&& (getIdconcepto() == null ? other.getIdconcepto() == null
						: getIdconcepto().equals(other.getIdconcepto()))
				&& (getIdgrupo() == null ? other.getIdgrupo() == null
						: getIdgrupo().equals(other.getIdgrupo())
								&& (getIdsubgrupo() == null ? other
										.getIdsubgrupo() == null
										: getIdsubgrupo().equals(
												other.getIdsubgrupo())));
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (getIdconcepto() == null ? 0 : getIdconcepto().hashCode());
		result = prime * result
				+ (getIdgrupo() == null ? 0 : getIdgrupo().hashCode());
		return result;
	}

	public String getIdsubgrupo() {
		return idsubgrupo;
	}

	public void setIdsubgrupo(String idsubgrupo) {
		this.idsubgrupo = idsubgrupo;
	}

	public String getIdconcepto() {
		return idconcepto;
	}

	public void setIdconcepto(String idconcepto) {
		this.idconcepto = idconcepto;
	}

}
