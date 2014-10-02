package vista.utilitarios.renderers;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import dao.DSolicitudCompraDAO;
import dao.SolicitudCompraDAO;
import entity.DSolicitudCompra;
import entity.DSolicitudCompraPK;
import entity.SolicitudCompra;
import vista.utilitarios.StringUtils;

public class ReferenciaDOCRenderer extends DefaultTableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		
		SolicitudCompraDAO scDAO = new SolicitudCompraDAO();
		
		String valor = "N/A";
		ReferenciaDOC referencia;
		if (value instanceof ReferenciaDOC && value != null) {
			referencia = (ReferenciaDOC) value;
			switch (referencia.getTipo_referencia()) {
			case 'S':				
				SolicitudCompra s = scDAO.find(referencia.getIdreferencia());
				if (s!= null){
					valor = "SOL." + s.getSerie() + "-" + s.getNumero() + "/" + referencia.getItem_referencia();
				} 
				break;
			default:
				
				break;
			}
		}
		
		setText(valor);
		
		return this;
	}
}