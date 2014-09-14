package core.centralizacion;

import dao.KardexDAO;

public class ContabAlm {
	public static void ContabAlm(long id,Object entity){
		KardexDAO kardexdao = new KardexDAO();
		kardexdao.borrarPorIngresoSalida(id);
		
	}
}
