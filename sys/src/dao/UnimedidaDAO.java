package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entity.Unimedida;

public class UnimedidaDAO extends AbstractDAO<Unimedida> {
	public UnimedidaDAO() {
		super(Unimedida.class);	
	}
	public List<Unimedida> findAllbyUnimedia() {
		CriteriaQuery<Unimedida> q = cb.createQuery(Unimedida.class);
		Root<Unimedida> m = q.from(Unimedida.class);
		q.select(m);
		List<Unimedida> unimedida = getEntityManager().createQuery(q).getResultList();
		return unimedida;
	}
}
