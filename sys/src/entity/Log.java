package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the log database table.
 * 
 */
@Entity
@NamedQuery(name="Log.findAll", query="SELECT l FROM Log l")
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String idlog;

	private Timestamp date;

	@Lob
	private String log;

	@Column(name="nom_formulario")
	private String nomFormulario;

	private String tipo;

	private String usuario;

	public Log() {
	}

	public String getIdlog() {
		return this.idlog;
	}

	public void setIdlog(String idlog) {
		this.idlog = idlog;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getLog() {
		return this.log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getNomFormulario() {
		return this.nomFormulario;
	}

	public void setNomFormulario(String nomFormulario) {
		this.nomFormulario = nomFormulario;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}