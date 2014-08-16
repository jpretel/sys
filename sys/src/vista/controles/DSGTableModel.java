package vista.controles;

import javax.swing.table.DefaultTableModel;

import vista.utilitarios.UtilMensajes;

public abstract class DSGTableModel extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean editar = false;

	private String nombre_detalle = "";

	private int[] obligatorios;
	private int[] repetidos;

	private String[] cabeceras;

	public DSGTableModel() {
		this("Campo 1", "Campo 2");
	}

	public DSGTableModel(String... cabeceras) {
		this.cabeceras = cabeceras;
		for (String c : cabeceras) {
			addColumn(c);
		}

	}

	public boolean isCellEditable(int row, int column) {
		return evaluaEdicion(row, column);
	}

	public abstract boolean evaluaEdicion(int row, int column);

	public void limpiar() {
		while (getRowCount() != 0) {
			removeRow(0);
		}
	}

	public boolean esValido() {
		if (obligatorios != null) {
			for (int i = 0; i < getRowCount(); i++) {
				for (int columna : obligatorios) {
					if (getValueAt(i, columna) == null
							|| getValueAt(i, columna).toString().isEmpty()) {
						UtilMensajes.mensaje_alterta("DETALLE_REQUERIDO",
								getNombre_detalle(), this.cabeceras[columna],
								String.valueOf(i + 1).toString());
						return false;
					}
				}
			}
		}

		if (repetidos != null) {
			for (int i = 0; i < getRowCount(); i++) {
				String cad1 = getValoresUnidos(i, repetidos);
				for (int j = i + 1; j < getRowCount(); j++) {
					String cad2 = getValoresUnidos(j, repetidos);
					if (cad1.equals(cad2)) {
						UtilMensajes.mensaje_alterta("DETALLE_NOIGUALES",
								getNombre_detalle(),
								getConcatenaCabeceras(this.repetidos), String
										.valueOf(i + 1).toString(), String
										.valueOf(j + 1).toString());
						return false;
					}
				}
			}
		}
		return true;
	}

	private String getValoresUnidos(int i, int[] columnas) {
		String cadena = "";
		for (int columna : columnas) {
			String valor = "";
			valor = (getValueAt(i, columna) == null) ? "" : getValueAt(i,
					columna).toString().trim();
			cadena = cadena.concat(valor);
		}
		return cadena;
	}

	private String getConcatenaCabeceras(int[] columnas) {
		String cadena = "";
		if (columnas.length == 1) {
			cadena = cabeceras[columnas[0]];
		} else
			for (int i = 0; i < columnas.length; i++) {
				cadena = cadena.concat(((i == 0) ? ""
						: (i == columnas.length - 1) ? " y " : ", ")
						.concat(cabeceras[i].toUpperCase()));
			}
		return cadena;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public boolean getEditar() {
		return this.editar;
	}

	public String getNombre_detalle() {
		return nombre_detalle;
	}

	public void setNombre_detalle(String nombre_detalle) {
		this.nombre_detalle = nombre_detalle;
	}

	public void setObligatorios(int... obligatorios) {
		this.obligatorios = obligatorios;
	}

	public void setRepetidos(int... repetidos) {
		this.repetidos = repetidos;
	}
}