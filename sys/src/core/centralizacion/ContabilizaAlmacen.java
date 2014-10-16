package core.centralizacion;

import java.util.ArrayList;
import java.util.List;

import dao.DetDocIngresoDAO;
import dao.DetDocSalidaDAO;
import dao.KardexDAO;
import dao.TCambioDAO;
import entity.DetDocingreso;
import entity.DetDocsalida;
import entity.Docingreso;
import entity.Docsalida;
import entity.Kardex;
import entity.TCambioPK;

public class ContabilizaAlmacen {

	public static void ContabilizarIngreso(Docingreso ingreso) {
		long id = ingreso.getIddocingreso();
		KardexDAO kardexdao = new KardexDAO();
		TCambioDAO tCampoDAO = new TCambioDAO();
		kardexdao.borrarPorIngresoSalida(id);
		float tcambio = 0;
		String idmoneda = "";
		float importemof = 0;
		float importemex = 0;
		DetDocIngresoDAO detIngDAO = new DetDocIngresoDAO();
		
		// El tipo de Cambio debe de retornar según configuración (Revisar donde)
		/*TCambioPK idtc = new TCambioPK();  
		idtc.setIdmoneda(ingreso.getMoneda().getIdmoneda());
		idtc.setAnio(ingreso.getAnio());
		idtc.setMes(ingreso.getMes());
		idtc.setDia(ingreso.getDia());*/
		tcambio = 2.8F;
				//tCampoDAO.getFechaMoneda(idtc).get(0).getCompra();					
		
		idmoneda = ingreso.getMoneda().getIdmoneda();
		List<DetDocingreso> detIngL = detIngDAO.getPorIdIngreso(ingreso);
		List<Kardex> kardex_list = new ArrayList<Kardex>();

		for (DetDocingreso det : detIngL) {
			Kardex kardex = new Kardex();
			kardex.setIdreferencia(id);
			kardex.setTipo('I');
			kardex.setMoneda(ingreso.getMoneda());
			kardex.setConcepto(ingreso.getConcepto());
			kardex.setDia(ingreso.getDia());
			kardex.setMes(ingreso.getMes());
			kardex.setAnio(ingreso.getAnio());
			kardex.setFecha(ingreso.getAnio() * 10000 + ingreso.getMes() * 100
					+ ingreso.getDia());
			kardex.setSucursal(ingreso.getSucursal());
			kardex.setAlmacen(ingreso.getAlmacen());
			kardex.setProducto(det.getProducto());
			kardex.setUnimedida(det.getUnimedida());
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
			kardex_list.add(kardex);
		}
		kardexdao.create(kardex_list);
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
		tcambio = (float) 2.8; 
		//tCampoDAO.getFechaMoneda(ingreso.getMoneda(),
		//	ingreso.getAnio(), ingreso.getMes(), ingreso.getAnio()).getCompra();
		
		idmoneda = salida.getMoneda().getIdmoneda();
		List<DetDocsalida> detSal = detSalDAO.getPorIdSalida(salida);
		List<Kardex> kardex_list = new ArrayList<Kardex>();
		for (DetDocsalida det : detSal) {
			Kardex kardex = new Kardex();
			kardex.setIdreferencia(id);
			kardex.setTipo('S');
			kardex.setMoneda(salida.getMoneda());
			kardex.setConcepto(salida.getConcepto());
			kardex.setDia(salida.getDia());
			kardex.setMes(salida.getMes());
			kardex.setAnio(salida.getAnio());
			kardex.setFecha(salida.getAnio() * 10000 + salida.getMes() * 100
					+ salida.getDia());
			kardex.setSucursal(salida.getSucursal());
			kardex.setAlmacen(salida.getAlmacen());
			kardex.setProducto(det.getProducto());
			kardex.setUnimedida(det.getUnimedida());
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
			kardex_list.add(kardex);
		}
		kardexdao.create(kardex_list);
	}
}
