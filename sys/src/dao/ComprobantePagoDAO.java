package dao;

import java.util.List;

import javax.persistence.NoResultException;
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
	
	public List<Object[]> ListarMoneda()
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarMoneda");
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return Resul;
	}
	
	public List<Object[]> ListarNotaIngreso(String prmstridclieprov,String prmstridalmacen)
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarNotaIngreso");
			query.registerStoredProcedureParameter("prmstridclieprov",String.class,ParameterMode.IN);
			query.registerStoredProcedureParameter("prmstridalmacen",String.class,ParameterMode.IN);//prmstridalmacen
			query.setParameter("prmstridclieprov",prmstridclieprov);
			query.setParameter("prmstridalmacen",prmstridalmacen);
			Resul=  query.getResultList();
			
			
		} catch (Exception e) {
			System.out.println("Datos");
			e.printStackTrace();
		}
		
		return Resul;
	}
	
	public int InsertComprobantePago(String prmxml)
	{
		int Resul=0;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_InsertComprobantePago");
			query.registerStoredProcedureParameter("prmxml",String.class,ParameterMode.IN);
			query.setParameter("prmxml",prmxml);
			query.executeUpdate();
			getEntityManager().getTransaction().commit();
			
			
		} catch (Exception e) {
			System.out.println("Datos");
			e.printStackTrace();
		}
		
		return Resul;
	}
	
	public Object DevolverComprobantePago(String prmstrdocumento)
	{
		Object comprobantepago=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_DevuelveComprobantePago");
			query.registerStoredProcedureParameter("prmstrdocumento",String.class,ParameterMode.IN);
			query.setParameter("prmstrdocumento",prmstrdocumento);
			
			comprobantepago= query.getSingleResult();
			
		} catch(NoResultException ex) {
	        return null;
	    }catch (Exception e) {
			e.printStackTrace();
		}
		
		return comprobantepago;
	}
	
	public List<Object[]> DevolverComprobantePagoDetalle(int prmintcomprobante)
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("DevuelveComprobantePagoDetalle");
			query.registerStoredProcedureParameter("prmintcomprobante",Integer.class,ParameterMode.IN);
			query.setParameter("prmintcomprobante",prmintcomprobante);
			Resul=  query.getResultList();
			
			
		} catch (Exception e) {
			System.out.println("Datos");
			e.printStackTrace();
		}
		
		return Resul;
	}
	
}
