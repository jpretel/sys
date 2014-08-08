package vista.formularios;

import vista.formularios.listas.AbstractDocList;
import entity.DocIngreso;

public class FrmListaRecepcion extends AbstractDocList {
	private DocIngreso docIngreso;
	public FrmListaRecepcion() {
		super("Lista de Recepciones");
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void nuevo() {
		/*FrmDocRecepcion docRecepcion = new FrmDocRecepcion();
		docIngreso = new DocIngreso();
		init(docRecepcion,"NUEVO",docIngreso);*/		
	}

}
