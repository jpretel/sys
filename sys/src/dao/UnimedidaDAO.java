package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Unimedida;

public class UnimedidaDAO extends AbstractDAO<Unimedida> {
	public UnimedidaDAO() {
		super(Unimedida.class);
	}

	public List<Unimedida> getExcepto(String idmedida) {
		CriteriaQuery<Unimedida> q = cb.createQuery(Unimedida.class);
		Root<Unimedida> c = q.from(Unimedida.class);
		Predicate condicion = cb.notEqual(c.get("idmedida"), idmedida);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
}
