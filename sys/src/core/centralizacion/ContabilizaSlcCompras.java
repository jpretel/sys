package core.centralizacion;

import java.util.ArrayList;
import java.util.List;

import dao.DSolicitudCompraDAO;
import dao.KardexSlcCompraDAO;
import dao.SolicitudCompraDAO;
import entity.DSolicitudCompra;
import entity.KardexSlcCompra;
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
}
