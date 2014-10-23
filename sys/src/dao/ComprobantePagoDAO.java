package dao;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import entity.StockExistenciasValorizadoEnt;

public class ComprobantePagoDAO extends AbstractDAO<StockExistenciasValorizadoEnt>{
	
	public ComprobantePagoDAO(){super(StockExistenciasValorizadoEnt.class);}

	public List<Object[]> ListarTipoDoc()
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarTipoDoc");
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return Resul;
	}
	
	public List<Object[]> ListarProveedor()
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarProveedor");
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return Resul;
	}
	
	public List<Object[]> ListarAlmacen(String prmstrsucursal)
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarAlmacen");
			query.registerStoredProcedureParameter("prmstrsucursal",String.class,ParameterMode.IN);
			query.setParameter("prmstrsucursal",prmstrsucursal);
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return Resul;
	}
	
	public List<Object[]> ListarNotaIngreso(int prmintsucursal)
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarNotaIngreso");
			/*query.registerStoredProcedureParameter("prmstrsucursal",String.class,ParameterMode.IN);
			query.setParameter("prmstrsucursal",prmintsucursal);*/
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			System.out.println("Datos");
			e.printStackTrace();
		}
		
		return Resul;
	}
	
}
