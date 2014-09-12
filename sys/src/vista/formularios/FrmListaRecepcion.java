package vista.formularios;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import dao.DocingresoDAO;
import entity.Docingreso;
import vista.formularios.listas.AbstractDocList;
import vista.formularios.listas.DSGTableList;
import vista.formularios.listas.DSGTableModelList;
import vista.utilitarios.StringUtils;


/*Falta Aplicar los filtros y verificar los botones edicion y impresion*/
public class FrmListaRecepcion extends AbstractDocList {
	private DocingresoDAO docIngresoDAO = new DocingresoDAO();
	private List<Docingreso> lista = new ArrayList<Docingreso>();
	private Object[][] data;	
	public FrmListaRecepcion() {
		super("Lista de Notas de Ingreso");
		cboDocumento.setVisible(false);
		lblDocumento.setVisible(false);
		cabeceras = new String[] {"Id","Fecha","Serie", "Numero","Responsable","Sucursal","Almacen"};
		tblDocumentos = new DSGTableList(6) {			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void DoDobleClick(int row) {
				abrirFormulario("VISTA");			
			}
		};

		modelo_lista = new DSGTableModelList(cabeceras);
		tblDocumentos.setModel(modelo_lista);
	
		pnlDocumentos.setViewportView(tblDocumentos);
		llenarLista();
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void nuevo() {	
		FrmDocRecepcion docRecepcion = new FrmDocRecepcion();
		init(docRecepcion,"NUEVO",null);
	}
	
	@Override
	public void abrirFormulario(String estado){
		FrmDocRecepcion docRecepcion = new FrmDocRecepcion();
		Docingreso docingreso = null;
		if (RetornarPk() instanceof Object) {
			docingreso = docIngresoDAO.find(RetornarPk());
		}
		init(docRecepcion,estado,docingreso);
	}
	
	@Override
	public void editar() {
		abrirFormulario("EDICION");
	}
	
	@Override
	public Object[][] getData(int idesde, int ihasta,String serie, int numero) {
		lista = docIngresoDAO.getFiltro(idesde, ihasta, serie, numero);
		data = new Object[lista.size()][3];
		int i = 0;
		for (Docingreso ingreso : lista) {
			Calendar c = GregorianCalendar.getInstance();
			c.set(ingreso.getAnio(), ingreso.getMes() - 1, ingreso.getDia());
			String cnumero = StringUtils._padl(ingreso.getNumero(), 8, '0');
			data[i] = new Object[] {ingreso.getIddocingreso(),c.getTime(),ingreso.getSerie(),cnumero,ingreso.getResponsable().getNombre(),
					ingreso.getAlmacen().getSucursal().getDescripcion(),ingreso.getAlmacen().getDescripcion()};
			i++;
		}
		return data;
	}
}
