package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Moneda;
import entity.TCambio;
import entity.TCambioPK;

public class TCambioDAO extends AbstractDAO<TCambio> {

	public TCambioDAO() {
		super(TCambio.class);
	}

	public List<TCambio> getFechaMoneda(TCambioPK id) {
		CriteriaQuery<TCambio> q = cb.createQuery(TCambio.class);
		Root<TCambio> c = q.from(TCambio.class);
		Predicate condicion = cb.equal(c.get("id"), id);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();	
	}

	public List<TCambio> getMesMoneda(Moneda moneda, int anio, int mes) {
		CriteriaQuery<TCambio> q = cb.createQuery(TCambio.class);
		Root<TCambio> c = q.from(TCambio.class);
		Predicate condicion = cb.and(cb.equal(c.get("moneda"), moneda),
				cb.equal(c.get("id").get("anio"), anio),
				cb.equal(c.get("id").get("mes"), mes));
		q.select(c)
				.where(condicion)
				.orderBy(cb.asc(c.get("id").get("anio")),
						cb.asc(c.get("id").get("mes")),
						cb.asc(c.get("id").get("dia")));
		return getEntityManager().createQuery(q).getResultList();
	}
}