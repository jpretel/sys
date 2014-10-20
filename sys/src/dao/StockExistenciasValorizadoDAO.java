package dao;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Tuple;

import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.queries.StoredProcedureCall;

import vista.Sys;
import entity.Almacen;
import entity.StockExistenciasValorizadoEnt;

public class StockExistenciasValorizadoDAO extends AbstractDAO<StockExistenciasValorizadoEnt> {
	


	public StockExistenciasValorizadoDAO() {
		super(StockExistenciasValorizadoEnt.class);
		
}
	
		
	
	public  List<StockExistenciasValorizadoEnt> ReporteStockExistenciaValorizado(String prmstrsucursal,String prmstralmacen)
			throws Exception
	{
		
		//List<StockExistenciasValorizadoEnt>stock=new Vector<StockExistenciasValorizadoEnt>();
		Vector<StockExistenciasValorizadoEnt>stock=new Vector<StockExistenciasValorizadoEnt>();
		try {
			List<Object[]> Resul=null;
			
			getEntityManager().getTransaction().begin();

			StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("sp_ReporteStockExisteciaValor");
			query.registerStoredProcedureParameter("prmstridsucursal",String.class,ParameterMode.IN);
			query.registerStoredProcedureParameter("prmstridalamcen",String.class,ParameterMode.IN);
			query.setParameter("prmstridsucursal",prmstrsucursal);
			query.setParameter("prmstridalamcen", prmstralmacen);
			
			Resul=  query.getResultList();
			
			//
			
			for(int i=0;i<Resul.size();i++)
			{
				StockExistenciasValorizadoEnt s = new StockExistenciasValorizadoEnt();
				
				s.setCodigo(Resul.get(i)[0].toString());
				s.setDescripcion(Resul.get(i)[1].toString());
				s.setMarca(Resul.get(i)[2].toString());
				s.setStock((BigDecimal)Resul.get(i)[3]);
				s.setCosto((BigDecimal)Resul.get(i)[4]);
				stock.add(s);
				
				System.out.println(Resul.get(i)[1]);	
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//
		return stock;
		
		
		
	}
	
	
	
}
