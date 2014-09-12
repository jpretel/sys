package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ConversionUM
 *
 */
@Entity
@Table(name = "conversion_um")
public class ConversionUM implements Serializable {

	@EmbeddedId
	private ConversionUMPK id;
	private float factor;
	@ManyToOne
	@JoinColumn(name = "idunimedida", referencedColumnName = "idunimedida", insertable = false, updatable = false, nullable = false)
	private Unimedida unimedida;
	@ManyToOne
	@JoinColumn(name = "idunimedida_equiv", referencedColumnName = "idunimedida", insertable = false, updatable = false, nullable = false)
	private Unimedida unimedida_equiv;
	private static final long serialVersionUID = 1L;

	public ConversionUM() {
		super();
	}

	public float getFactor() {
		return this.factor;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}

	public ConversionUMPK getId() {
		return id;
	}

	public void setId(ConversionUMPK id) {
		this.id = id;
	}

	public Unimedida getUnimedida() {
		return unimedida;
	}

	public void setUnimedida(Unimedida unimedida) {
		this.unimedida = unimedida;
	}

	public Unimedida getUnimedida_equiv() {
		return unimedida_equiv;
	}

	public void setUnimedida_equiv(Unimedida unimedida_equiv) {
		this.unimedida_equiv = unimedida_equiv;
	}

}
