package vista.utilitarios;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.JTableHeader;

import vista.controles.DSGTableModel;

public class JTableUtils {
	private static final int MIN_ROW_HEIGHT = 12;

	public static JList buildRowHeader(final JTable table,
			final DSGTableModel model) {
		final Vector<String> headers = new Vector<String>();
		for (int i = 0; i < table.getRowCount(); i++) {
			// headers.add(String.valueOf((char) (i + 65)).toUpperCase());
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
			Cursor oldCursor;
			Cursor RESIZE_CURSOR = Cursor
					.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
			int index = -1;
			int oldY = -1;

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
					} else if (previ > -1 && previ < table.getRowCount()) {
						if (e.getButton() != MouseEvent.BUTTON1) {
							JPopupMenu menu = getnNewPopup(previ, model);
							menu.show(rowHeader, e.getX(), e.getY());
						}

					}
					// if (e.getClickCount() == 2) {
					//
					// int seleccion = JOptionPane.showOptionDialog(null,
					// "Desea Eliminar el Registro Seleccionado",
					// "Informacion del Sistema",
					// JOptionPane.YES_NO_OPTION,
					// JOptionPane.QUESTION_MESSAGE, null,
					// new Object[] { "Si", "No" }, "Si");
					// if (seleccion == 0) {
					// model.removeRow(previ);
					// }
					// } else if (e.getButton() == MouseEvent.BUTTON2)
				}
			}

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

	static class RowHeaderRenderer extends JLabel implements ListCellRenderer {

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

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
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

	static JPopupMenu getnNewPopup(int row, DSGTableModel model) {
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
		return menu;
	}
}
