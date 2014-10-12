package core.centralizacion;
import dao.DOrdenCompraDAO;
import dao.DetDocIngresoDAO;
import dao.DocingresoDAO;
import dao.KardexCompraRecepcionDAO;
import dao.OrdenCompraDAO;
import entity.DOrdenCompra;
import entity.DetDocingreso;
import entity.Docingreso;
import entity.KardexCompraRecepcion;
import entity.OrdenCompra;

public class ContabilizaComprasRecepcion {
	public static boolean ContabilizaComprasRecepcion(long id,int factor,String tabla){
		OrdenCompraDAO ordenCompraDAO = new OrdenCompraDAO();
		DocingresoDAO docingresoDAO = new DocingresoDAO();
		DOrdenCompraDAO dordenCompraDAO = new DOrdenCompraDAO();
		DetDocIngresoDAO detdocingresoDAO = new DetDocIngresoDAO();
		OrdenCompra ordenCompra = null;
		Docingreso docingreso = null;
		KardexCompraRecepcion kardexCompraRecepcion = new KardexCompraRecepcion();
		KardexCompraRecepcionDAO kardexCompraRecepcionDAO = new KardexCompraRecepcionDAO();
		kardexCompraRecepcionDAO.borrarPorRefCompraFactor(id,factor);
		if(tabla == "Compra"){			
			ordenCompra = ordenCompraDAO.find(id);
			for(DOrdenCompra dordenCompra : dordenCompraDAO.getPorOrdenCompra(ordenCompra)){
				kardexCompraRecepcion.setDordencompra(dordenCompra);
				kardexCompraRecepcion.setFactor(factor);
				kardexCompraRecepcion.setCantidad(dordenCompra.getCantidad());
				kardexCompraRecepcion.setProducto(dordenCompra.getProducto());
				kardexCompraRecepcion.setUnimedida(dordenCompra.getUnimedida());
			}
		}else{
			docingreso = docingresoDAO.find(id);
			for(DetDocingreso detdocingreso: detdocingresoDAO.getPorIdIngreso(docingreso)){
				kardexCompraRecepcion.setDordencompra(detdocingreso.getDordencompra());
				kardexCompraRecepcion.setCantidad(detdocingreso.getCantidad());
				kardexCompraRecepcion.setFactor(factor);
				kardexCompraRecepcion.setProducto(detdocingreso.getProducto());
				kardexCompraRecepcion.setUnimedida(detdocingreso.getUnimedida());
				kardexCompraRecepcion.setDetdocingreso(detdocingreso);
			}
		}
		kardexCompraRecepcion.setIdreferencia(id);
		kardexCompraRecepcionDAO.crear_editar(kardexCompraRecepcion);		
		return true;
	}
}
