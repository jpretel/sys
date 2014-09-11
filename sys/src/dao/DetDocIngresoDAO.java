package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DetDocingreso;
import entity.DetDocingresoPK;
import entity.Docingreso;

public class DetDocIngresoDAO extends AbstractDAO<DetDocingreso> {
	public DetDocIngresoDAO(){
		super(DetDocingreso.class);
	}
	
	public List<DetDocingreso> getPorIdIngreso(long idingreso){
		CriteriaQuery<DetDocingreso> q = cb.createQuery(DetDocingreso.class);
		Root<DetDocingresoPK> c = q.from(DetDocingresoPK.class);
		Predicate condicion = cb.equal(c.get("idingreso"), idingreso);
		q.multiselect(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
}
