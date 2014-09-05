package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.CfgCentralizaAlm;
import entity.Concepto;

public class CfgCentralizaAlmDAO extends AbstractDAO<CfgCentralizaAlm> {

	public CfgCentralizaAlmDAO() {
		super(CfgCentralizaAlm.class);
	}

	public List<CfgCentralizaAlm> getPorConcepto(Concepto concepto) {
		factory.getCache().evictAll();
		CriteriaQuery<CfgCentralizaAlm> q = cb.createQuery(CfgCentralizaAlm.class);
		Root<CfgCentralizaAlm> c = q.from(CfgCentralizaAlm.class);
		Predicate condicion = cb.equal(c.get("concepto"), concepto);
		q.select(c).where(condicion);	
		return getEntityManager().createQuery(q).getResultList();
	}
}