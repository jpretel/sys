package vista.controles;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vista.utilitarios.JTableUtils;
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

	private JScrollPane scrollPane;
	private JTable table;

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
								getNombre_detalle().toUpperCase(),
								this.cabeceras[columna].toUpperCase(), String
										.valueOf(i + 1).toString());
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
						UtilMensajes.mensaje_alterta(
								(repetidos.length == 1) ? "DETALLE_NOIGUAL"
										: "DETALLE_NOIGUALES",
								getNombre_detalle().toUpperCase(),
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
			cadena = cabeceras[columnas[0]].toUpperCase();
		} else
			for (int i = 0; i < columnas.length; i++) {
				cadena = cadena.concat(((i == 0) ? ""
						: (i == columnas.length - 1) ? " y " : ", ")
						.concat(cabeceras[i].toUpperCase()));
			}
		return cadena;
	}

	@Override
	public void addRow(Object[] rowData) {
		super.addRow(rowData);
		refrescarRowHeader();
	}

	@Override
	public void removeRow(int row) {
		super.removeRow(row);
		refrescarRowHeader();
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

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setScrollAndTable(JScrollPane scrollPane, JTable table) {
		this.scrollPane = scrollPane;
		this.table = table;
		refrescarRowHeader();
	}
	
	private void refrescarRowHeader() {
		if (getScrollPane() != null && getTable() != null) {
			getScrollPane().setRowHeaderView(
					JTableUtils.buildRowHeader(getTable(), this));
		}
	}
	
	public abstract void addRow();
}