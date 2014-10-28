package dao;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import entity.StockExistenciasValorizadoEnt;

public class HojaTrabajoStockDAO extends AbstractDAO<StockExistenciasValorizadoEnt> {
	public HojaTrabajoStockDAO(){super(StockExistenciasValorizadoEnt.class);}
	
	public List<Object[]> ListarHojaTrabajo(String prmsrtgrupo,String prmstrmarca)
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarHojaTrabajoStock");
			query.registerStoredProcedureParameter("prmsrtgrupo",String.class,ParameterMode.IN);
			query.registerStoredProcedureParameter("prmstrmarca",String.class,ParameterMode.IN);
			query.setParameter("prmsrtgrupo",prmsrtgrupo);
			query.setParameter("prmstrmarca",prmstrmarca);
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return Resul;
	}

	public List<Object[]> ListarGrupo()
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarGrupo");
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return Resul;
	}

	public List<Object[]> ListarMarca()
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarMarca");
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return Resul;
	}

}
