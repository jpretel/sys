package vista.utilitarios;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vista.controles.DSGTableModel;

public class JTableUtils {

	public static JList<String> buildRowHeader(final JTable table,
			final DSGTableModel model) {
		final Vector<String> headers = new Vector<String>();
		for (int i = 0; i < table.getRowCount(); i++) {
			headers.add(String.valueOf(i + 1).toUpperCase());
		}

		headers.add("+");
		ListModel<String> lm = new AbstractListModel<String>() {
			private static final long serialVersionUID = -401435697781157444L;

			public int getSize() {
				return headers.size();
			}

			public String getElementAt(int index) {
				return headers.get(index);
			}
		};

		final JList<String> rowHeader = new JList<String>(lm);
		rowHeader.setOpaque(false);
		rowHeader.setFixedCellWidth(30);

		MouseInputAdapter mouseAdapter = new MouseInputAdapter() {
			Cursor RESIZE_CURSOR = Cursor
					.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);

			private int getLocationToIndex(Point point) {
				int i = rowHeader.locationToIndex(point);
				if (!rowHeader.getCellBounds(i, i).contains(point)) {
					i = -1;
				}
				return i;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				int previ = getLocationToIndex(new Point(e.getX(), e.getY() - 3));
				if (model.getEditar()) {
					if (previ == table.getRowCount()) {
						model.addRow();
					}
				}

				if (previ > -1 && previ < table.getRowCount()) {
					if (e.getButton() != MouseEvent.BUTTON1) {
						JPopupMenu menu = getnNewPopup(previ, model);
						menu.show(rowHeader, e.getX(), e.getY());
					}
				}
				
			}
			
			public void mouseClicked(MouseEvent e){
				super.mouseClicked(e);
				int previ = getLocationToIndex(new Point(e.getX(), e.getY() - 3));
				System.out.println(previ);
				model.getTable().getSelectionModel().setSelectionInterval(previ, previ);
				rowHeader.setSelectionBackground(Color.YELLOW);
			}

			@SuppressWarnings("unused")
			private boolean isResizeCursor() {
				return rowHeader.getCursor() == RESIZE_CURSOR;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
			 */
			/*
			 * @Override public void mouseMoved(MouseEvent e) {
			 * super.mouseMoved(e); int previ = getLocationToIndex(new
			 * Point(e.getX(), e.getY() - 3)); int nexti =
			 * getLocationToIndex(new Point(e.getX(), e.getY() + 3)); if (previ
			 * != -1 && previ != nexti) { if (!isResizeCursor()) { oldCursor =
			 * rowHeader.getCursor(); rowHeader.setCursor(RESIZE_CURSOR); index
			 * = previ; } } else if (isResizeCursor()) {
			 * rowHeader.setCursor(oldCursor); } }
			 * 
			 * @Override public void mouseReleased(MouseEvent e) {
			 * super.mouseReleased(e); if (isResizeCursor()) {
			 * rowHeader.setCursor(oldCursor); index = -1; oldY = -1; } }
			 * 
			 * @Override public void mouseDragged(MouseEvent e) {
			 * super.mouseDragged(e); if (isResizeCursor() && index != -1) { int
			 * y = e.getY(); if (oldY != -1) { int inc = y - oldY; int
			 * oldRowHeight = table.getRowHeight(index);
			 * 
			 * if (oldRowHeight > 12 || inc > 0) { int rowHeight =
			 * Math.max(MIN_ROW_HEIGHT, oldRowHeight + inc);
			 * 
			 * table.setRowHeight(index, rowHeight);
			 * 
			 * if (rowHeader.getModel().getSize() > index + 1) { int rowHeight1
			 * = table.getRowHeight(index + 1) - inc; rowHeight1 = Math.max(12,
			 * rowHeight1); table.setRowHeight(index + 1, rowHeight1); } }
			 * 
			 * } oldY = y; } }
			 */

		};
		rowHeader.addMouseListener(mouseAdapter);
		rowHeader.addMouseMotionListener(mouseAdapter);
		rowHeader.addMouseWheelListener(mouseAdapter);
		rowHeader.setCellRenderer(new RowHeaderRenderer(table, model));
		rowHeader.setBackground(table.getBackground());
		rowHeader.setForeground(table.getForeground());
		return rowHeader;
	}

	static class RowHeaderRenderer extends JLabel implements
			ListCellRenderer<String> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JTable table;

		public RowHeaderRenderer(JTable table, DSGTableModel model) {
			this.table = table;
			JTableHeader header = this.table.getTableHeader();
			setOpaque(true);
			setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			setHorizontalAlignment(CENTER);
			setForeground(header.getForeground());
			setBackground(header.getBackground());
			setFont(header.getFont());
			setDoubleBuffered(true);
		}

		@Override
		public Component getListCellRendererComponent(
				JList<? extends String> list, String value, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText((value == null) ? "" : value.toString());
			setPreferredSize(null);
			setPreferredSize(new Dimension((int) getPreferredSize().getWidth(),
					table.getRowHeight(index)));
			// trick to force repaint on JList (set updateLayoutStateNeeded =
			// true) on BasicListUI
			list.firePropertyChange("cellRenderer", 0, 1);
			return this;
		}
	}

	static JPopupMenu getnNewPopup(final int row, final DSGTableModel model) {
		JPopupMenu menu = new JPopupMenu();
		JMenuItem item;
		menu.add(item = new JMenuItem("Copiar"));
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Clipboard clipboar = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				String data = "";
				for (int i = 0; i < model.getColumnCount(); i++) {
					data = data.concat((i == 0) ? "" : "\t").concat(
							(model.getValueAt(row, i) == null) ? "" : model
									.getValueAt(row, i).toString().trim());
				}
				StringSelection dataClip = new StringSelection(data);
				
				clipboar.setContents(dataClip, dataClip);
			}
		});

		menu.add(item = new JMenuItem("Pegar"));
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				int maxColumn = model.getColumnCount();
				int iColumn = 0;
				try {
					String result = (String) clipboard
							.getData(DataFlavor.stringFlavor);
					result = result.trim();
					String dato = "";
					int i;
					salir: for (i = 0; i < result.length(); i++) {
						char cr = result.charAt(i);
						if (cr == '\n') {
							break salir;
						}
						if (cr == '\t') {
							model.setValueAt(dato, row, iColumn);
							iColumn++;
							dato = "";
							if (iColumn == maxColumn) {
								break salir;
							}
						}
						dato = dato.concat(String.valueOf(cr));
					}
					if (!dato.isEmpty())
						model.setValueAt(dato, row, iColumn);

				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		if (!model.getEditar()) {
			item.setEnabled(false);
		}
		menu.addSeparator();

		menu.add(item = new JMenuItem("Eliminar"));
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int seleccion = JOptionPane.showOptionDialog(null,
						"Desea Eliminar el Registro Seleccionado",
						"Informacion del Sistema", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] {
								"Si", "No" }, "Si");
				if (seleccion == 0) {
					model.removeRow(row);
				}
			}
		});

		if (!model.getEditar()) {
			item.setEnabled(false);
		}

		return menu;
	}

	class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JTable table;
		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, null, 1);
		JSpinner spinner = new JSpinner(model);
		int clickCountToStart = 1;

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			spinner.setValue(((Integer) value).intValue());
			return spinner;
		}

		public Object getCellEditorValue() {
			return (Integer) spinner.getValue();
		}

		public boolean isCellEditable(EventObject anEvent) {
			if (anEvent instanceof MouseEvent) {
				return ((MouseEvent) anEvent).getClickCount() >= clickCountToStart;
			}
			return true;
		}

		public boolean shouldSelectCell(EventObject anEvent) {
			return true;
		}

		public boolean stopCellEditing() {
			return super.stopCellEditing();
		}

		public void cancelCellEditing() {
			super.cancelCellEditing();
		}
	}

	class ButtonEditor extends AbstractCellEditor implements TableCellEditor,
			ActionListener {
		JTable table;
		JButton button = new JButton();
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		int clickCountToStart = 1;

		public ButtonEditor(JTable table) {
			this.table = table;
			button.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			StringBuilder sb = new StringBuilder();
			int row = table.getEditingRow();
			int col = table.getEditingColumn();
			// System.out.printf("row = %d  col = %d%n", row, col);
			sb.append((String) table.getValueAt(row, 6));
			sb.append(" has ");
			sb.append(((col == 4) ? "bought " : "sold "));
			sb.append(((Integer) table.getValueAt(row, 3)).toString());
			sb.append(" shares of " + (String) table.getValueAt(row, 0));
			sb.append(" at "
					+ nf.format(((Double) table.getValueAt(row, 1))
							.doubleValue()));
			stopCellEditing();
			System.out.println(sb.toString());
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			button.setText(value.toString());
			return button;
		}

		public Object getCellEditorValue() {
			return button.getText();
		}

		public boolean isCellEditable(EventObject anEvent) {
			if (anEvent instanceof MouseEvent) {
				return ((MouseEvent) anEvent).getClickCount() >= clickCountToStart;
			}
			return true;
		}

		public boolean shouldSelectCell(EventObject anEvent) {
			return true;
		}

		public boolean stopCellEditing() {
			return super.stopCellEditing();
		}

		public void cancelCellEditing() {
			super.cancelCellEditing();
		}
	}

	class SpinnerRenderer implements TableCellRenderer {
		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, null, 1);
		JSpinner spinner = new JSpinner(model);

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			spinner.setValue(((Integer) value).intValue());
			return spinner;
		}
	}

	class ButtonRenderer implements TableCellRenderer {
		JButton button = new JButton();

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			button.setText(value.toString());
			return button;
		}
	}

	public class DoubleRenderer extends DefaultTableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		NumberFormat nf = NumberFormat.getCurrencyInstance();

		public DoubleRenderer() {
			setHorizontalAlignment(RIGHT);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			super.getTableCellRendererComponent(table, value, isSelected,
					hasFocus, row, column);
			setText(nf.format(((Double) value).doubleValue()));
			return this;
		}
	}
}