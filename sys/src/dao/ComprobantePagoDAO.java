package dao;
//...........................
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
		}
		
		return Resul;
	}
	
	public int InsertComprobantePago(String prmxml)
	{
		int Resul=0;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_InsertComprobantePago");
			query.registerStoredProcedureParameter("prmstrCadXML",String.class,ParameterMode.IN);
			query.setParameter("prmstrCadXML",prmxml);
			query.executeUpdate();
			getEntityManager().getTransaction().commit();
			
			
		} catch (Exception e) {
			throw e;
		}
		
		return Resul;
	}
	
	public Object DevolverComprobantePago(int prmintIdComproPago)
	{
		Object comprobantepago=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_DevuelveComprobantePago");
			query.registerStoredProcedureParameter("prmintIdComproPago",Integer.class,ParameterMode.IN);
			query.setParameter("prmintIdComproPago",prmintIdComproPago);
			
			comprobantepago= query.getSingleResult();
			
		} catch(NoResultException ex) {
	        return null;
	    }catch (Exception e) {
	    	throw e;
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
			throw e;
		}
		
		return Resul;
	}
	
	public List<Object[]> ListarProducto(String prmsrtgrupo,String prmstrmarca)
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarProductoxGrupoxMarca");
			query.registerStoredProcedureParameter("prmsrtgrupo",String.class,ParameterMode.IN);
			query.registerStoredProcedureParameter("prmstrmarca",String.class,ParameterMode.IN);
			query.setParameter("prmsrtgrupo",prmsrtgrupo);
			query.setParameter("prmstrmarca",prmstrmarca);
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			throw e;
		}
		
		return Resul;
	}
	
	public List<Object[]> ListarComprobantePago(String prmsrtdocumento,String prmstrserie)
	{
		List<Object[]> Resul=null;
		try {
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ListarComprobantePago");
			query.registerStoredProcedureParameter("prmsrtdocumento",String.class,ParameterMode.IN);
			query.registerStoredProcedureParameter("prmstrserie",String.class,ParameterMode.IN);
			query.setParameter("prmsrtdocumento",prmsrtdocumento);
			query.setParameter("prmstrserie",prmstrserie);
			Resul=  query.getResultList();
			
		} catch (Exception e) {
			throw e;
		}
		
		return Resul;
	}
	
}
