package vista.utilitarios;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 1L;
	private String label;
	private JButton button= new JButton("");
	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
	}
	public Component getTableCellEditorComponent(JTable table, Object value,
	boolean isSelected, int row, int column) {
		label = "Borrar";//(value == null) ? "" : value.toString();
		getButton().setText(label);		
		return getButton();
	}
	public Object getCellEditorValue() {
		return new String(label);
	}
	
	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}
}