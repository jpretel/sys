package vista.formularios;
import vista.formularios.listas.AbstractDocList;
import entity.DocIngreso;

public class FrmListaRecepcion extends AbstractDocList {
	private DocIngreso docIngreso;
	/*private DocFormularioDAO docformularioDAO = new DocFormularioDAO();
	private List<DocFormulario> docformularioL = docformularioDAO.getPorFormulario("FrmListaRecepcion");
	ComboObject<Documento> documento;*/
	public FrmListaRecepcion() {
		super("Lista de Notas de Ingreso");
		cboDocumento.setVisible(false);
		lblDocumento.setVisible(false);
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void nuevo() {
		FrmDocRecepcion docRecepcion = new FrmDocRecepcion();
		docIngreso = new DocIngreso();
		init(docRecepcion,"NUEVO",docIngreso);		
	}

}
