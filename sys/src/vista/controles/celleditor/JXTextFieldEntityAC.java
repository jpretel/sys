package vista.controles.celleditor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.jdesktop.swingx.JXTextField;

import vista.Sys;

public abstract class JXTextFieldEntityAC<T> extends JXTextField implements
		FocusListener {
	private static final long serialVersionUID = 1L;
	private List<T> data;
	private List<T> sugerencias = new ArrayList<T>();
	private JWindow autoSuggestionPopUpWindow;
	private JTable table;
	private JScrollPane scrollPane;
	private int indice = -1;
	private int iSeleccionado;
	private int[] anchos;
	private String[] cabeceras;
	private T seleccionado;
	private DocumentListener documentListener = new DocumentListener() {

		@Override
		public void insertUpdate(DocumentEvent de) {
			checkForAndShowSuggestions();
		}

		@Override
		public void removeUpdate(DocumentEvent de) {
			checkForAndShowSuggestions();
		}

		@Override
		public void changedUpdate(DocumentEvent de) {
			checkForAndShowSuggestions();
		}
	};

	public JXTextFieldEntityAC(String[] cabeceras,
			int[] anchos, JTable tabla, int ubicacion) {
		this.anchos = anchos;
		this.cabeceras = cabeceras;

		indice = -1;
		autoSuggestionPopUpWindow = new JWindow(getFormulario());
		autoSuggestionPopUpWindow.setOpacity(0.95f);

		scrollPane = new JScrollPane();
		autoSuggestionPopUpWindow.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addFocusListener(this);
		addFocusListener(this);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ev) {
				// Tecla hacia abajo
				if (ev.getKeyCode() == 40) {

					if (autoSuggestionPopUpWindow.isVisible()) {
						if (table.getRowCount() > 0
								&& table.getSelectedRow() < table.getRowCount() - 1) {
							table.setRowSelectionInterval(
									table.getSelectedRow() + 1,
									table.getSelectedRow() + 1);
						}
					} else {
						checkForAndShowSuggestions();
					}
					return;
				}

				// Tecla hacia arriba
				if (ev.getKeyCode() == 38) {
					if (autoSuggestionPopUpWindow.isVisible()) {
						if (table.getRowCount() > 0
								&& table.getSelectedRow() > 0) {
							table.setRowSelectionInterval(
									table.getSelectedRow() - 1,
									table.getSelectedRow() - 1);
						}
					}
					return;
				}

				// Tecla Enter
				if (ev.getKeyCode() == 10) {
					if (autoSuggestionPopUpWindow.isVisible()) {
						indice = table.getSelectedRow();
						if (validarDatos())
							autoSuggestionPopUpWindow.setVisible(false);
					}
					return;
				}

				// Tecla Escape
				if (ev.getKeyCode() == 27) {
					autoSuggestionPopUpWindow.setVisible(false);
					return;
				}

				// Control + F
				if (ev.getModifiers() == 2 && ev.getKeyCode() == 70) {
					checkForAndShowSuggestions();
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					indice = table.getSelectedRow();
					if (validarDatos())
						autoSuggestionPopUpWindow.setVisible(false);
				}
			}
		});

		getDocument().addDocumentListener(documentListener);
	}

	public void checkForAndShowSuggestions() {
		if (!this.isEditable() || !this.isEnabled()) {
			autoSuggestionPopUpWindow.setVisible(false);
			return;
		}

		String typedWord = getText();
		indice = -1;
		boolean added = wordTyped(typedWord);
		if (!added) {
			indice = -1;
			autoSuggestionPopUpWindow.setVisible(false);
		} else {
			showPopUpWindow();
		}
	}

	public void checkForAndShowSuggestions(String dato) {
		if (!this.isEditable() || !this.isEnabled()) {
			autoSuggestionPopUpWindow.setVisible(false);
			return;
		}

		String typedWord = dato;
		indice = -1;
		boolean added = wordTyped(typedWord);
		if (!added) {
			indice = -1;
			autoSuggestionPopUpWindow.setVisible(false);
		} else {
			showPopUpWindow();
		}
	}

	public boolean wordTyped(String typedWord) {
		typedWord = typedWord.trim();
		sugerencias = new ArrayList<T>();
		if (typedWord.isEmpty() || typedWord.length() < getMinimoBusqueda()) {
			return false;
		}
		boolean hayCoincidencias = false;
		for (T dato : data) {
			if (coincideBusqueda(dato, typedWord)) {
				sugerencias.add(dato);
				hayCoincidencias = true;
			}
		}
		return hayCoincidencias;
	}

	public int getMinimoBusqueda() {
		return 1;
	}

	private void showPopUpWindow() {
		int anchoTotal = 0;
		Object[][] data = new Object[sugerencias.size()][2];

		int ind = 0;
		for (T s : sugerencias) {
			data[ind] = entity2Object(s);
			ind++;
		}

		DefaultTableModel model = new DefaultTableModel(data, cabeceras) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};

		table.setModel(model);

		for (int i = 0; i < table.getColumnCount(); i++) {
			anchoTotal += anchos[i];
			table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
		}

		int windowX = getLocationOnScreen().x;
		int windowY = getLocationOnScreen().y + getHeight();

		autoSuggestionPopUpWindow.setLocation(windowX, windowY);
		autoSuggestionPopUpWindow.setMinimumSize(new Dimension(this.getParent()
				.getWidth(), anchoTotal));
		autoSuggestionPopUpWindow.setAutoRequestFocus(false);
		autoSuggestionPopUpWindow.setVisible(true);

	}

	private boolean validarDatos() {
		iSeleccionado = -1;
		if (indice > -1 && sugerencias.size() > 0
				&& indice <= sugerencias.size()) {
			seleccionado = sugerencias.get(indice);
			cargaDatos();
			return true;
		} else {
			return false;
		}
	}

	public abstract void cargaDatos();

	public int getiSeleccionado() {
		return iSeleccionado;
	}

	public void setiSeleccionado(int iSeleccionado) {
		this.iSeleccionado = iSeleccionado;
	}

	@Override
	public void focusGained(FocusEvent e) {
		revisaFoco('G', e);
	}

	@Override
	public void focusLost(FocusEvent e) {
		revisaFoco('L', e);
	}

	private void revisaFoco(char tipo, FocusEvent e) {
		boolean band = false;
		if (tipo == 'G') {
			if (e.getComponent() == table) {
				band = true;
			}
		} else {
			if ((e.getComponent() == table && e.getOppositeComponent() == this)
					|| (e.getComponent() == this && e.getOppositeComponent() == table)) {
				band = true;
			}
			if (e.getComponent() == this && !band) {
				cargaDatos();
			}
		}
		if (!band) {
			autoSuggestionPopUpWindow.setVisible(false);
		}
	}

	public T getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(T seleccionado) {
		this.seleccionado = seleccionado;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public void setText(String t) {
		this.getDocument().removeDocumentListener(documentListener);
		super.setText(t);
		this.getDocument().addDocumentListener(documentListener);
	}
	
	protected void buscarPorCodigo(){
		
	}

	public static Window getFormulario() {
		return (Window) Sys.mainF;
	}
	
	public JTextCellEditorAC getCellEditor() {
		return new JTextCellEditorAC(this);
	}
	
	public abstract boolean coincideBusqueda(T entity, String cadena);

	public abstract Object[] entity2Object(T entity);
}

class JTextCellEditorAC extends AbstractCellEditor implements TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComponent component = new JTextField();
	
	public JTextCellEditorAC(JXTextFieldEntityAC<?> text) {
		this.component = text;
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int rowIndex, int vColIndex) {

		((JXTextFieldEntityAC<?>) component).setText((String) value);
		return component;
	}

	public Object getCellEditorValue() {
		return ((JXTextFieldEntityAC<?>) component).getText();
	}
}