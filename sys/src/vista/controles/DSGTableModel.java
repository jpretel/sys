package vista.controles;

import javax.swing.table.DefaultTableModel;

public abstract class DSGTableModel extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean editar = false;

	public DSGTableModel() {
		addColumn("Campo 1");
		addColumn("Campo 2");
	}
	
	public DSGTableModel(String [] cabeceras) {
		if (cabeceras == null) {
			addColumn("Campo 1");
			addColumn("Campo 2");
		} else {
			for (String c : cabeceras){
				addColumn(c);
			}
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

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public boolean getEditar() {
		return this.editar;
	}
}
