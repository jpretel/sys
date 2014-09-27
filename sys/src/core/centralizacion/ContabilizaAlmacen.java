package core.centralizacion;

import java.util.List;

import dao.DetDocIngresoDAO;
import dao.DetDocSalidaDAO;
import dao.DocingresoDAO;
import dao.KardexDAO;
import dao.ProductoDAO;
import dao.UnimedidaDAO;
import entity.DetDocingreso;
import entity.DetDocsalida;
import entity.Docingreso;
import entity.Docsalida;
import entity.Kardex;
import entity.Producto;
import entity.Unimedida;

public class ContabilizaAlmacen {
	public static void ContabAlm(long id, Object entity) {
		KardexDAO kardexdao = new KardexDAO();
		kardexdao.borrarPorIngresoSalida(id);

		float tcambio = 0;
		String idmoneda = "";
		float importemof = 0;
		float importemex = 0;
		if (entity instanceof Docingreso) {
			DetDocIngresoDAO detIngDAO = new DetDocIngresoDAO();
			Docingreso ingreso = new DocingresoDAO().find(id);
			tcambio = ingreso.getTcambio();
			idmoneda = ingreso.getMoneda().getIdmoneda();
			List<DetDocingreso> detIngL = detIngDAO.getPorIdIngreso(ingreso);
			for (DetDocingreso det : detIngL) {
				Kardex kardex = new Kardex();
				kardex.setIdreferencia(id);
				kardex.setMoneda(ingreso.getMoneda());
				kardex.setConcepto(ingreso.getConcepto());
				kardex.setDia(ingreso.getDia());
				kardex.setMes(ingreso.getMes());
				kardex.setAnio(ingreso.getAnio());
				kardex.setFecha(ingreso.getAnio() * 10000 + ingreso.getMes()
						* 100 + ingreso.getDia());
				kardex.setAlmacen(ingreso.getAlmacen());
				Producto producto = new ProductoDAO().find(det.getId()
						.getIdproducto());
				kardex.setProducto(producto);
				Unimedida unimedida = new UnimedidaDAO()
						.find(det.getIdmedida());
				kardex.setUnimedida(unimedida);
				kardex.setCantidad(det.getCantidad());
				kardex.setPrecio(det.getPrecio());
				kardex.setFactor(1);
				if (idmoneda == "01") {
					importemof = det.getPrecio() * det.getCantidad();
					importemex = (det.getPrecio() * det.getCantidad())
							* tcambio;
				} else {
					importemof = (det.getPrecio() * det.getCantidad())
							/ tcambio;
					importemex = det.getPrecio() * det.getCantidad();
				}
				kardex.setImportemof(importemof);
				kardex.setImportemex(importemex);
				kardexdao.crear_editar(kardex);
			}
		}

	}

	public static void ContabilizarIngreso(Docingreso ingreso) {
		long id = ingreso.getIddocingreso();
		KardexDAO kardexdao = new KardexDAO();
		kardexdao.borrarPorIngresoSalida(id);

		float tcambio = 0;
		String idmoneda = "";
		float importemof = 0;
		float importemex = 0;
		DetDocIngresoDAO detIngDAO = new DetDocIngresoDAO();
		tcambio = ingreso.getTcambio();
		idmoneda = ingreso.getMoneda().getIdmoneda();
		List<DetDocingreso> detIngL = detIngDAO.getPorIdIngreso(ingreso);
		for (DetDocingreso det : detIngL) {
			Kardex kardex = new Kardex();
			kardex.setIdreferencia(id);
			kardex.setMoneda(ingreso.getMoneda());
			kardex.setConcepto(ingreso.getConcepto());
			kardex.setDia(ingreso.getDia());
			kardex.setMes(ingreso.getMes());
			kardex.setAnio(ingreso.getAnio());
			kardex.setFecha(ingreso.getAnio() * 10000 + ingreso.getMes() * 100
					+ ingreso.getDia());
			kardex.setAlmacen(ingreso.getAlmacen());
			Producto producto = new ProductoDAO().find(det.getId()
					.getIdproducto());
			kardex.setProducto(producto);
			Unimedida unimedida = new UnimedidaDAO().find(det.getIdmedida());
			kardex.setUnimedida(unimedida);
			kardex.setCantidad(det.getCantidad());
			kardex.setPrecio(det.getPrecio());
			kardex.setFactor(1);
			if (idmoneda == "01") {
				importemof = det.getPrecio() * det.getCantidad();
				importemex = (det.getPrecio() * det.getCantidad()) * tcambio;
			} else {
				importemof = (det.getPrecio() * det.getCantidad()) / tcambio;
				importemex = det.getPrecio() * det.getCantidad();
			}
			kardex.setImportemof(importemof);
			kardex.setImportemex(importemex);
			kardexdao.crear_editar(kardex);
		}
	}
	
	public static void ContabilizarSalida(Docsalida salida) {
		long id = salida.getIddocsalida();
		KardexDAO kardexdao = new KardexDAO();
		kardexdao.borrarPorIngresoSalida(id);

		float tcambio = 0;
		String idmoneda = "";
		float importemof = 0;
		float importemex = 0;
		DetDocSalidaDAO detSalDAO = new DetDocSalidaDAO();
		tcambio = salida.getTcambio();
		idmoneda = salida.getMoneda().getIdmoneda();
		List<DetDocsalida> detSal= detSalDAO.getPorIdSalida(salida);
		for (DetDocsalida det : detSal) {
			Kardex kardex = new Kardex();
			kardex.setIdreferencia(id);
			kardex.setMoneda(salida.getMoneda());
			kardex.setConcepto(salida.getConcepto());
			kardex.setDia(salida.getDia());
			kardex.setMes(salida.getMes());
			kardex.setAnio(salida.getAnio());
			kardex.setFecha(salida.getAnio() * 10000 + salida.getMes() * 100
					+ salida.getDia());
			kardex.setAlmacen(salida.getAlmacen());
			Producto producto = new ProductoDAO().find(det.getId()
					.getIdproducto());
			kardex.setProducto(producto);
			Unimedida unimedida = new UnimedidaDAO().find(det.getIdmedida());
			kardex.setUnimedida(unimedida);
			kardex.setCantidad(det.getCantidad());
			kardex.setPrecio(det.getPrecio());
			kardex.setFactor(-1);
			if (idmoneda == "01") {
				importemof = det.getPrecio() * det.getCantidad();
				importemex = (det.getPrecio() * det.getCantidad()) * tcambio;
			} else {
				importemof = (det.getPrecio() * det.getCantidad()) / tcambio;
				importemex = det.getPrecio() * det.getCantidad();
			}
			kardex.setImportemof(importemof);
			kardex.setImportemex(importemex);
			kardexdao.crear_editar(kardex);
		}

	}
}
