package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.SysDoc;
import entity.SysDocDocser;

public class SysDocDocserDAO extends AbstractDAO<SysDocDocser> {

	public SysDocDocserDAO() {
		super(SysDocDocser.class);
	}
	
	public List<SysDocDocser> getPorDocumento(SysDoc documento){
		CriteriaQuery<SysDocDocser> q = cb.createQuery(SysDocDocser.class);
		Root<SysDocDocser> c = q.from(SysDocDocser.class);
		Predicate condicion = cb.equal(c.get("sysDocBean"), documento);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public void borrarPorDocumento (SysDoc documento) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<SysDocDocser> delete = cb.createCriteriaDelete(SysDocDocser.class);
		Root<SysDocDocser> c = delete.from(SysDocDocser.class);
		delete.where(cb.equal(c.get("sysDocBean"), documento));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
}
