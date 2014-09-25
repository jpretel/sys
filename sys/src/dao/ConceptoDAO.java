package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Concepto;

public class ConceptoDAO extends AbstractDAO<Concepto> {
	public ConceptoDAO(){
		super(Concepto.class);
	}
	
	public List<Concepto> getPorTipo(String tipo) {
		CriteriaQuery<Concepto> q = cb.createQuery(Concepto.class);
		Root<Concepto> c = q.from(Concepto.class);
		Predicate condicion = cb.equal(c.get("tipo"), tipo);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
}
