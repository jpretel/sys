package vista.formularios;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import dao.OrdenCompraDAO;
import entity.OrdenCompra;
import vista.Sys;
import vista.formularios.listas.AbstractDocList;
import vista.formularios.listas.DSGTableList;
import vista.formularios.listas.DSGTableModelList;
import vista.utilitarios.StringUtils;


/*Falta Aplicar los filtros y verificar los botones edicion y impresion*/
public class FrmListaOrdenCompra extends AbstractDocList {
	private OrdenCompraDAO ordencompraDAO = new OrdenCompraDAO();
	private List<OrdenCompra> lista = new ArrayList<OrdenCompra>();
	private Object[][] data;	
	public FrmListaOrdenCompra() {
		super("Ordenes de Compra");
		cboDocumento.setVisible(false);
		lblDocumento.setVisible(false);
		cabeceras = new String[] {"Fecha","Serie", "Numero","Responsable","Sucursal","Almacen"};
		tblDocumentos = new DSGTableList(5) {			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void DoDobleClick(int row) {
				AbrirFormulario(row, "VISTA");			
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
		//frmo docRecepcion = new FrmDocRecepcion();
		//init(docRecepcion,"NUEVO",null);
	}
	
	public void AbrirFormulario(int row, String opcion) {
		FrmDocOrdenCompra frm = new FrmDocOrdenCompra();
		frm.actualiza_objeto(data[row][data[0].length-1], opcion);
		Sys.desktoppane.add(frm);
		frm.moveToFront();
	}
	
	@Override
	public void editar() {
		int row = tblDocumentos.getSelectedRow();
		if (row > -1) {
			row = tblDocumentos.convertRowIndexToModel(row);
			AbrirFormulario(row, "EDICION");
		}
		
	}
	
	@Override
	public Object[][] getData(int idesde, int ihasta,String serie, int numero) {
		lista = ordencompraDAO.getFiltro(idesde, ihasta, serie, numero);
		data = new Object[lista.size()][3];
		int i = 0;
		for (OrdenCompra oc : lista) {
			Calendar c = GregorianCalendar.getInstance();
			c.set(oc.getAnio(), oc.getMes() - 1, oc.getDia());
			String cnumero = StringUtils._padl(oc.getNumero(), 8, '0');
			
			data[i] = new Object[] {c.getTime(),oc.getSerie(),cnumero,oc.getResponsable().getNombre(),
					oc.getSucursal().getDescripcion(),oc.getAlmacen().getDescripcion(), oc.getIdordencompra()};
			i++;
		}
		return data;
	}

	@Override
	public void abrirFormulario(String estado) {
		// TODO Auto-generated method stub
		
	}
}
