package vista.formularios;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import dao.AlmacenDAO;
import dao.DocingresoDAO;
import dao.ResponsableDAO;
import entity.Almacen;
import entity.AlmacenPK;
import entity.Docingreso;
import entity.Responsable;
import vista.formularios.listas.AbstractDocList;
import vista.formularios.listas.DSGTableList;
import vista.formularios.listas.DSGTableModelList;

public class FrmListaRecepcion extends AbstractDocList {
	private DocingresoDAO docIngresoDAO = new DocingresoDAO();
	private List<Docingreso> lista = new ArrayList<Docingreso>();
	private Object[][] data;	
	public FrmListaRecepcion() {
		super("Lista de Notas de Ingreso");
		cboDocumento.setVisible(false);
		lblDocumento.setVisible(false);
		Docingreso docingreso = new Docingreso();
		cabeceras = new String[] {"Id","Serie", "Numero","Responsable","Sucursal","Almacen"};
		tblDocumentos = new DSGTableList(5) {			
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
		llenarLista(docingreso);
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
	public Object[][] getData(int idesde, int ihasta, int numero,
			Object xingreso) {
		lista = docIngresoDAO.findAll();
		data = new Object[lista.size()][3];
		int i = 0;
		for (Docingreso ingreso : lista) {
			Calendar c = GregorianCalendar.getInstance();
			c.set(ingreso.getAnio(), ingreso.getMes() - 1, ingreso.getDia());
			String cnumero = String.valueOf(ingreso.getNumero());
			String xnumero = org.codehaus.plexus.util.StringUtils.repeat("0",8 - cnumero.length())+cnumero;	
			data[i] = new Object[] {ingreso.getIddocingreso(),ingreso.getSerie(),xnumero,ingreso.getResponsable().getNombre(),
					ingreso.getAlmacen().getSucursal().getDescripcion(),ingreso.getAlmacen().getDescripcion()};
			i++;
		}
		return data;
	}
}
