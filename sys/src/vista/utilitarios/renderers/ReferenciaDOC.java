package vista.utilitarios.renderers;

public class ReferenciaDOC {
	private char tipo_referencia;
	private long idreferencia;
	private int item_referencia;

	public ReferenciaDOC() {
	}

	public ReferenciaDOC(char tipo_referencia, long idreferencia,
			int item_referencia) {
		this.tipo_referencia = tipo_referencia;
		this.idreferencia = idreferencia;
		this.item_referencia = item_referencia;
	}

	public char getTipo_referencia() {
		return tipo_referencia;
	}

	public void setTipo_referencia(char tipo_referencia) {
		this.tipo_referencia = tipo_referencia;
	}

	public long getIdreferencia() {
		return idreferencia;
	}

	public void setIdreferencia(long idreferencia) {
		this.idreferencia = idreferencia;
	}

	public int getItem_referencia() {
		return item_referencia;
	}

	public void setItem_referencia(int item_referencia) {
		this.item_referencia = item_referencia;
	}

}
