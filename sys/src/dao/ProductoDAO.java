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
	public List<Producto> findAllbyProducto() {
		CriteriaQuery<Producto> q = cb.createQuery(Producto.class);
		Root<Producto> m = q.from(Producto.class);
		q.select(m);
		List<Producto>  producto = getEntityManager().createQuery(q).getResultList();
		return producto;
	}
	public Producto RetornarxIdProducto(String IdProducto) {
		CriteriaQuery<Producto> q = cb.createQuery(Producto.class);
		Root<Producto> m = q.from(Producto.class);
		Predicate condicion = cb.equal(m.get("idproductos"), IdProducto);
		q.select(m).where(condicion);
		Producto producto = getEntityManager().createQuery(q).getSingleResult();
		return producto;
	}

}
