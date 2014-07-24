package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_doc_docser database table.
 * 
 */
@Entity
@Table(name="sys_doc_docser")
@NamedQuery(name="SysDocDocser.findAll", query="SELECT s FROM SysDocDocser s")
public class SysDocDocser implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysDocDocserPK id;

	//bi-directional many-to-one association to SysDoc
	@ManyToOne
	@JoinColumn(name="sys_doc", insertable=false, updatable=false)
	private SysDoc sysDocBean;

	public SysDocDocser() {
	}
	
	public SysDocDocserPK getId() {
		return this.id;
	}

	public void setId(SysDocDocserPK id) {
		this.id = id;
	}

	public SysDoc getSysDocBean() {
		return this.sysDocBean;
	}

	public void setSysDocBean(SysDoc sysDocBean) {
		this.sysDocBean = sysDocBean;
	}

}