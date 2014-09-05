package vista.formularios;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import dao.DocingresoDAO;
import vista.formularios.listas.AbstractDocList;

public class FrmListaRecepcion extends AbstractDocList {
	private DocingresoDAO docIngresoDAO = new DocingresoDAO();
	public FrmListaRecepcion() {
		super("Lista de Notas de Ingreso");
		cboDocumento.setVisible(false);
		lblDocumento.setVisible(false);
		llenarLista();
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void nuevo() {
		FrmDocRecepcion docRecepcion = new FrmDocRecepcion();
		init(docRecepcion,"NUEVO",null);
	}

	@Override
	public void llenarLista() {
		modelo_lista = new DefaultTableModel();
		cabeceras = new String[] {"Documento", "Serie","Numero"};
		for (String cabecera : cabeceras) {
			modelo_lista.addColumn(cabecera);
		}
	}

}
