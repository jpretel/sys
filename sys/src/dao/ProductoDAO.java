package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Producto;

public class ProductoDAO extends AbstractDAO<Producto> {
	public ProductoDAO() {
		super(Producto.class);
	}
	
	public List<Producto> getServicios() {
		CriteriaQuery<Producto> q = cb.createQuery(Producto.class);
		Root<Producto> from = q.from(Producto.class);
		
		Predicate condicion = cb.equal( from.get("esServicio"), 1);
		q.select(from).where(condicion);
		
		return getEntityManager().createQuery(q).getResultList();
	}
	

}
