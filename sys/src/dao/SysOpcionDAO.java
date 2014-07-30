package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.SysGrupo;
import entity.SysOpcion;
import entity.Usuario;

public class SysOpcionDAO extends AbstractDAO<SysOpcion> {

	public SysOpcionDAO() {
		super(SysOpcion.class);
	}
	
	public void borrarPorGrupo(SysGrupo grupo) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<SysOpcion> delete = cb
				.createCriteriaDelete(SysOpcion.class);
		Root<SysOpcion> c = delete.from(SysOpcion.class);
		delete.where(cb.equal(c.get("sysGrupo"), grupo));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
	public List<SysOpcion> getPorGrupo(SysGrupo grupo) {
		CriteriaQuery<SysOpcion> q = cb.createQuery(SysOpcion.class);
		Root<SysOpcion> c = q.from(SysOpcion.class);
		Predicate condicion = cb.equal(c.get("sysGrupo"), grupo);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public List<SysOpcion> getPorGrupo(SysGrupo grupo, Usuario usuario) {
		CriteriaQuery<SysOpcion> q = cb.createQuery(SysOpcion.class);
		Root<SysOpcion> c = q.from(SysOpcion.class);
		Predicate condicion = cb.equal(c.get("sysGrupo"), grupo);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

}
