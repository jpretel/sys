package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SysFormularioAprobacion
 *
 */
@Entity
@Table(name = "flujo_aprobacion")
public class FlujoAprobacion implements Serializable {

	@EmbeddedId
	private FlujoAprobacionPK id;

	@ManyToOne
	@JoinColumn(name = "idflujo", referencedColumnName = "idflujo")
	private Flujo flujo;

	@ManyToOne
	@JoinColumn(name = "idformulario", referencedColumnName = "idformulario", insertable = false, updatable = false)
	private SysFormulario sysFormulario;
	private static final long serialVersionUID = 1L;

	public FlujoAprobacion() {
		super();
	}

	public FlujoAprobacionPK getId() {
		return id;
	}

	public void setId(FlujoAprobacionPK id) {
		this.id = id;
	}

	public Flujo getFlujo() {
		return flujo;
	}

	public void setFlujo(Flujo flujo) {
		this.flujo = flujo;
	}

	public SysFormulario getSysFormulario() {
		return sysFormulario;
	}

	public void setSysFormulario(SysFormulario sysFormulario) {
		this.sysFormulario = sysFormulario;
	}
}
