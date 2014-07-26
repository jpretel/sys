package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entity.Consumidor;
import entity.Consumidor_;

public class ConsumidorDAO extends AbstractDAO<Consumidor> {

	public ConsumidorDAO() {
		super(Consumidor.class);
	}

	public List<Consumidor> findAllOrderByJerarquia() {
		CriteriaQuery<Consumidor> q = cb.createQuery(Consumidor.class);
		Root<Consumidor> c = q.from(Consumidor.class);
		q.select(c);
		q.orderBy(cb.asc(c.get(Consumidor_.jerarquia)));
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public List<Consumidor> borrarByJerarquia() {
		CriteriaQuery<Consumidor> q = cb.createQuery(Consumidor.class);
		Root<Consumidor> c = q.from(Consumidor.class);
		q.select(c);
		q.orderBy(cb.asc(c.get(Consumidor_.jerarquia)));
		return getEntityManager().createQuery(q).getResultList();
	}
}
