package dao;

import java.util.List;

import javax.persistence.StoredProcedureQuery;

import entity.StockExistenciasValorizadoEnt;

public class HojaTrabajoStockDAO extends AbstractDAO<StockExistenciasValorizadoEnt> {
	public HojaTrabajoStockDAO(){super(StockExistenciasValorizadoEnt.class);}
	
	public List<Object[]> ListarHojaTrabajo()
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarHojaTrabajoStock");
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return Resul;
	}

}
