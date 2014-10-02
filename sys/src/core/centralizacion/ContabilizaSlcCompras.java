package core.centralizacion;

import java.util.ArrayList;
import java.util.List;

import dao.DOrdenCompraDAO;
import dao.DSolicitudCompraDAO;
import dao.KardexSlcCompraDAO;
import dao.OrdenCompraDAO;
import dao.SolicitudCompraDAO;
import entity.DOrdenCompra;
import entity.DSolicitudCompra;
import entity.DSolicitudCompraPK;
import entity.KardexSlcCompra;
import entity.OrdenCompra;
import entity.SolicitudCompra;

public class ContabilizaSlcCompras {
	public static boolean ContabilizaSolicitud(long id) {
		SolicitudCompraDAO slcDAO = new SolicitudCompraDAO();
		DSolicitudCompraDAO dslcDAO = new DSolicitudCompraDAO();
		KardexSlcCompraDAO kardexDAO = new KardexSlcCompraDAO();

		SolicitudCompra solicitud = slcDAO.find(id);

		if (solicitud == null) {
			return false;
		}

		List<DSolicitudCompra> dsolicitud;

		dsolicitud = dslcDAO.getPorSolicitudCompra(solicitud);
		kardexDAO.borrarPorIdSolicitudCompra(id);

		List<KardexSlcCompra> kardex_list = new ArrayList<KardexSlcCompra>();

		for (DSolicitudCompra ds : dsolicitud) {
			KardexSlcCompra kardex = new KardexSlcCompra();
			kardex.setDsolicitudcompra(ds);
			kardex.setProducto(ds.getProducto());
			kardex.setUnimedida(ds.getUnimedida());
			kardex.setFactor(1);
			kardex.setCantidad(ds.getCantidad());
			kardex_list.add(kardex);
		}

		kardexDAO.create(kardex_list);

		return true;
	}

	public static boolean ContabilizaOrdenCompra(long id) {
		OrdenCompraDAO ordDAO = new OrdenCompraDAO();
		DOrdenCompraDAO dordDAO = new DOrdenCompraDAO();
		DSolicitudCompraDAO dslcDAO = new DSolicitudCompraDAO();
		KardexSlcCompraDAO kardexDAO = new KardexSlcCompraDAO();

		OrdenCompra orden = ordDAO.find(id);

		if (orden == null) {
			return false;
		}

		List<DOrdenCompra> dorden;

		dorden = dordDAO.getPorOrdenCompra(orden);
		kardexDAO.borrarPorIdSolicitudCompra(id);

		List<KardexSlcCompra> kardex_list = new ArrayList<KardexSlcCompra>();

		for (DOrdenCompra doc : dorden) {

			if (doc.getTipo_referencia() == 'S') {
				DSolicitudCompra ds;

				DSolicitudCompraPK ids = new DSolicitudCompraPK();

				ids.setIdsolicitudcompra(doc.getIdreferencia());
				ids.setItem(doc.getItemreferencia());

				ds = dslcDAO.find(ids);

				if (ds != null) {
					KardexSlcCompra kardex = new KardexSlcCompra();
					kardex.setDsolicitudcompra(ds);
					kardex.setDordencompra(doc);
					kardex.setProducto(doc.getProducto());
					kardex.setUnimedida(doc.getUnimedida());
					kardex.setFactor(-1);
					kardex.setCantidad(doc.getCantidad());
					kardex_list.add(kardex);
				}
			}
		}

		kardexDAO.create(kardex_list);

		return true;
	}
}
