package vista.formularios;
import vista.formularios.listas.AbstractDocList;
//import entity.Docingreso;

public class FrmListaRecepcion extends AbstractDocList {	
	public FrmListaRecepcion() {
		super("Lista de Notas de Ingreso");
		cboDocumento.setVisible(false);
		lblDocumento.setVisible(false);
		txtNumero.setText(txtNumero.getCorrelativo("FrmListaRecepcion"));
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void nuevo() {
		FrmDocRecepcion docRecepcion = new FrmDocRecepcion();
		init(docRecepcion,"NUEVO",null);
	}

}
