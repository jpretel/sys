package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.FlujoAprobacion;
import entity.SysFormulario;

public class FlujoAprobacionDAO extends
		AbstractDAO<FlujoAprobacion> {

	public FlujoAprobacionDAO() {
		super(FlujoAprobacion.class);
	}
	
	public List<FlujoAprobacion> getPorFormulario(SysFormulario formulario){
		CriteriaQuery<FlujoAprobacion> q = cb.createQuery(FlujoAprobacion.class);
		Root<FlujoAprobacion> from = q.from(FlujoAprobacion.class);
		Predicate condicion = cb.equal( from.get("sysFormulario"), formulario);
		q.select(from).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public void borrarPorFormulario(SysFormulario formulario) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<FlujoAprobacion> delete = cb.createCriteriaDelete(FlujoAprobacion.class);
		Root<FlujoAprobacion> c = delete.from(FlujoAprobacion.class);
		delete.where(cb.equal(c.get("sysFormulario"), formulario));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}

}
