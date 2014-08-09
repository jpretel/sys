package vista.formularios;

import java.util.List;

import dao.DocFormularioDAO;
import vista.controles.ComboObject;
import vista.formularios.listas.AbstractDocList;
import entity.DocFormulario;
import entity.DocIngreso;
import entity.Documento;

public class FrmListaRecepcion extends AbstractDocList {
	private DocIngreso docIngreso;
	private DocFormularioDAO docformularioDAO = new DocFormularioDAO();
	private List<DocFormulario> docformularioL = docformularioDAO.getPorFormulario("FrmListaRecepcion");
	public FrmListaRecepcion() {
		super("Lista de Guias de Recepcion");
		for(int i = 0 ; i < docformularioL.size() ; i++){
			ComboObject<Documento> documento = new ComboObject<Documento>(docformularioL.get(i).getDocumento(),docformularioL.get(i).getIddocumento());
			cboDocumento.addItem(documento);
		}		
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void nuevo() {
		FrmDocRecepcion docRecepcion = new FrmDocRecepcion();
		docIngreso = new DocIngreso();
		init(docRecepcion,"NUEVO",docIngreso);		
	}

}
