package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Asiento;
import entity.DAsiento;

public class DAsientoDAO extends AbstractDAO<DAsiento> {

	public DAsientoDAO() {
		super(DAsiento.class);
	}
	
	public List<DAsiento> getPorAsiento(Asiento asiento){
		CriteriaQuery<DAsiento> q = cb.createQuery(DAsiento.class);
		Root<DAsiento> c = q.from(DAsiento.class);
		Predicate condicion = cb.equal( c.get("asiento"), asiento);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public void borrarPorAsiento(Asiento asiento) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<DAsiento> delete = cb.createCriteriaDelete(DAsiento.class);
		Root<DAsiento> c = delete.from(DAsiento.class);
		delete.where(cb.equal(c.get("asiento"), asiento));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}

}
