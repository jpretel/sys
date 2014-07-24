package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.SysDoc;
import entity.SysDocFlujo;

public class SysDocFlujoDAO extends AbstractDAO<SysDocFlujo> {

	public SysDocFlujoDAO() {
		super(SysDocFlujo.class);
	}
	
	public List<SysDocFlujo> getPorDocumento(SysDoc documento){
		CriteriaQuery<SysDocFlujo> q = cb.createQuery(SysDocFlujo.class);
		Root<SysDocFlujo> c = q.from(SysDocFlujo.class);
		Predicate condicion = cb.equal(c.get("sysDocBean"), documento);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public void borrarPorDocumento (SysDoc documento) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<SysDocFlujo> delete = cb.createCriteriaDelete(SysDocFlujo.class);
		Root<SysDocFlujo> c = delete.from(SysDocFlujo.class);
		delete.where(cb.equal(c.get("sysDocBean"), documento));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
}
