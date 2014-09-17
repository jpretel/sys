package core.centralizacion;
import java.util.ArrayList;
import java.util.List;

import dao.CfgCentralizaAlmDAO;
import dao.KardexDAO;
import entity.Asiento;
import entity.CfgCentralizaAlm;
import entity.DAsiento;
import entity.DAsientoPK;
import entity.Kardex;
import entity.Moneda;
import entity.Producto;

public class CentralizaAlm {
	
	public static String CentralizaAlm(Long id) {		
		
		CfgCentralizaAlmDAO cfgDAO = new CfgCentralizaAlmDAO();		
		KardexDAO kardexDAO = new KardexDAO();
		Kardex kardex = kardexDAO.getPorIngresoSalidaC(id);
		Asiento asiento = new Asiento();
		List<DAsiento> dasiento = new ArrayList<DAsiento>();

		if (kardex == null) {
			return "No hay Documento";
		}
		
		Long ida;
		ida = System.nanoTime();
		asiento.setIdasiento(ida);

		asiento.setAnio(kardex.getAnio());
		asiento.setMes(kardex.getMes());
		asiento.setDia(kardex.getDia());
		asiento.setEstado(1);
		asiento.setMoneda(kardex.getMoneda());

		asiento.setNumerador(0);
		asiento.setTipo('A');
		
		int i = 1;

		float tcambio = 2.8F, tcmoneda = 1.3F;
		
		Moneda moneda = kardex.getMoneda();
		
		for (Kardex det : kardexDAO.getPorIngresoSalidaL(id)) {
			float precio = det.getPrecio();
			float cantidad = det.getCantidad();
			float total = 0;
			Producto prod = det.getProducto();
			total = precio * cantidad;

			CfgCentralizaAlm cfg = cfgDAO.getPorConceptoGrupoSubGrupo(
					kardex.getConcepto(), prod.getSubgrupo().getGrupo(), prod.getSubgrupo());

			if (cfg == null) {
				return "No tiene configuracion contable";
			}

			// Insertar cuenta de Debe
			DAsiento da = new DAsiento();
			DAsientoPK pk = new DAsientoPK();

			pk.setIdasiento(ida);
			pk.setItem(i);
			da.setId(pk);
			da.setCuenta(cfg.getCta_debe());

			LlenarDebeHaber(moneda.getTipo(), da, total, tcambio, tcmoneda, 'D');
			// Solo si tiene analisis por producto
			da.setProducto(prod);
			da.setCantidad(cantidad);

			dasiento.add(da);

			i++;

			da = new DAsiento();
			pk = new DAsientoPK();

			pk.setIdasiento(ida);
			pk.setItem(i);
			da.setId(pk);
			da.setCuenta(cfg.getCta_haber());
			
			LlenarDebeHaber(moneda.getTipo(), da, total, tcambio, tcmoneda, 'H');

			dasiento.add(da);

			i++;
		}
		
		return "";
	}

	public static void LlenarDebeHaber(int tipo_moneda, DAsiento da,
			float importe, float tcambio, float tcmoneda, char columna) {
		switch (columna) {
		case 'D':
			if (importe < 0) {
				da.setDebe(0);
				da.setDebe_of(0);
				da.setDebe_ex(0);

				da.setHaber(Math.abs(importe));

				if (tipo_moneda == 0)
					da.setHaber_of(Math.abs(importe));
				else if (tipo_moneda == 1)
					da.setHaber_of(Math.abs(importe) / tcambio);
				else
					da.setHaber_of(Math.abs(importe) / tcambio / tcmoneda);

				if (tipo_moneda == 0)
					da.setHaber_ex(Math.abs(importe) * tcambio);
				else if (tipo_moneda == 1)
					da.setHaber_ex(Math.abs(importe));
				else
					da.setHaber_ex(Math.abs(importe) / tcmoneda);

			} else {
				da.setHaber(0);
				da.setHaber_of(0);
				da.setHaber_ex(0);

				da.setDebe(Math.abs(importe));

				if (tipo_moneda == 0)
					da.setDebe_of(importe);
				else if (tipo_moneda == 1)
					da.setDebe_of(importe / tcambio);
				else
					da.setDebe_of(importe / tcambio / tcmoneda);

				if (tipo_moneda == 0)
					da.setDebe_ex(importe * tcambio);
				else if (tipo_moneda == 1)
					da.setDebe_ex(importe);
				else
					da.setDebe_ex(importe / tcmoneda);
			}
			break;

		default:

			if (importe < 0) {
				da.setHaber(0);
				da.setHaber_of(0);
				da.setHaber_ex(0);

				da.setDebe(Math.abs(importe));

				if (tipo_moneda == 0)
					da.setDebe_of(Math.abs(importe));
				else if (tipo_moneda == 1)
					da.setDebe_of(Math.abs(importe) / tcambio);
				else
					da.setDebe_of(Math.abs(importe) / tcambio / tcmoneda);

				if (tipo_moneda == 0)
					da.setDebe_ex(Math.abs(importe) * tcambio);
				else if (tipo_moneda == 1)
					da.setDebe_ex(Math.abs(importe));
				else
					da.setDebe_ex(Math.abs(importe) / tcmoneda);

			} else {
				da.setDebe(0);
				da.setDebe_of(0);
				da.setDebe_ex(0);

				da.setHaber(Math.abs(importe));

				if (tipo_moneda == 0)
					da.setHaber_of(importe);
				else if (tipo_moneda == 1)
					da.setHaber_of(importe / tcambio);
				else
					da.setHaber_of(importe / tcambio / tcmoneda);

				if (tipo_moneda == 0)
					da.setHaber_ex(importe * tcambio);
				else if (tipo_moneda == 1)
					da.setHaber_ex(importe);
				else
					da.setHaber_ex(importe / tcmoneda);
			}
			break;
		}
	}
}
