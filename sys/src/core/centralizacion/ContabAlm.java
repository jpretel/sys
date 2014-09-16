package core.centralizacion;

import java.util.List;

import dao.DetDocIngresoDAO;
import dao.DocingresoDAO;
import dao.KardexDAO;
import dao.UnimedidaDAO;
import entity.DetDocingreso;
import entity.Docingreso;
import entity.Kardex;
import entity.Unimedida;

public class ContabAlm {
	public static void ContabAlm(long id,Object entity){
		KardexDAO kardexdao = new KardexDAO();
		kardexdao.borrarPorIngresoSalida(id);
	
		float tcambio = 0;
		String idmoneda = "";
		float importemof = 0;
		float importemex = 0;
		if(entity instanceof Docingreso){
			DetDocIngresoDAO detIngDAO = new DetDocIngresoDAO();
			Docingreso ingreso = new DocingresoDAO().find(id);
			tcambio = ingreso.getTcambio();
			idmoneda = ingreso.getMoneda().getIdmoneda();			
			List<DetDocingreso> detIngL = detIngDAO.getPorIdIngreso(ingreso);		
			for(DetDocingreso det : detIngL){
				Kardex kardex = new Kardex();
				kardex.setIdreferencia(id);
				kardex.setDia(ingreso.getDia());
				kardex.setMes(ingreso.getMes());
				kardex.setAnio(ingreso.getAnio());
				kardex.setAlmacen(ingreso.getAlmacen());
				kardex.setProducto(det.getProducto());
				Unimedida unimedida = new UnimedidaDAO().find(det.getIdmedida());
				kardex.setUnimedida(unimedida);
				kardex.setCantidad(det.getCantidad());				
				kardex.setPrecio(det.getPrecio());
				kardex.setFactor(1);
				if(idmoneda == "01"){
					importemof = det.getPrecio() * det.getCantidad();
					importemex = (det.getPrecio() * det.getCantidad()) * tcambio;
				}else{
					importemof = (det.getPrecio() * det.getCantidad()) / tcambio;
					importemex = det.getPrecio() * det.getCantidad();
				}
				kardex.setImportemof(importemof);
				kardex.setImportemex(importemex);
				kardexdao.crear_editar(kardex);
			}			
		}
				
	}
}
