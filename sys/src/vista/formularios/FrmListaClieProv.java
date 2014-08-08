package vista.formularios;
import java.util.List;

import dao.ClieprovDAO;
import entity.Clieprov;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class FrmListaClieProv extends AbstractMaestroLista {
	private static final long serialVersionUID = 1L;
	
	private ClieprovDAO cdao = new ClieprovDAO();
	private Object[] obj;
	private List<Clieprov> clieprovL = cdao.findAll();
	private int nFilasP = clieprovL.size();
	private Clieprov clieprov;

	public FrmListaClieProv() {
		super();
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 607, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 257, Short.MAX_VALUE)
		);
		pnlContenido.setLayout(groupLayout);
		String columnas[] = { "Codigo", "Producto", "Grupo de Producto",
				"Subgrupo de Producto" };
		super.inicia_Lista(columnas, obj);
		llenar_lista();
	}
	
	@Override
	public void irFormulario(String opcion) {		
		if (RetornarPk() instanceof Object){
			clieprov = cdao.find(RetornarPk());
		}
		FrmClieprov frmclieprov = new FrmClieprov();
		super.init(frmclieprov, opcion, clieprov);
	}

	@Override
	public void llenar_lista() {
		for (int i = 0; i < nFilasP; i++) {
			clieprov = new Clieprov();
			obj = new Object[] {clieprov.getIdclieprov(),
					clieprov.getRazonSocial(),clieprov.getRuc(),
					clieprov.getDireccion() };
			modeloLista.addRow(obj);
		}
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void grabar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void llenar_datos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void llenar_tablas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vista_edicion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vista_noedicion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void llenarDesdeVista() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidaVista() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
}
