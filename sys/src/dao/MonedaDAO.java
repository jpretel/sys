package dao;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Moneda;

public class MonedaDAO extends AbstractDAO<Moneda> {

	public MonedaDAO() {
		super(Moneda.class);
	}
	
	public Moneda getPorTipo(int tipo) {
		CriteriaQuery<Moneda> q = cb.createQuery(Moneda.class);
		Root<Moneda> c = q.from(Moneda.class);
		
		Predicate condicion = cb.equal(c.get("tipo"), tipo);
		q.select(c).where(condicion);
		try {
			return getEntityManager().createQuery(q).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
