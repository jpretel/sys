package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import entity.DetDocingreso;
import entity.Docingreso;

public class DetDocIngresoDAO extends AbstractDAO<DetDocingreso> {
	public DetDocIngresoDAO(){
		super(DetDocingreso.class);
	}
	
	public List<DetDocingreso> getPorIdIngreso(Docingreso ingreso){
		CriteriaQuery<DetDocingreso> q = cb.createQuery(DetDocingreso.class);
		Root<DetDocingreso> c = q.from(DetDocingreso.class);
		Predicate condicion = cb.equal(c.get("docingreso"), ingreso);
		q.multiselect(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
}
