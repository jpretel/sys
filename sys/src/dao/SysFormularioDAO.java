package dao;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.SysFormulario;

public class SysFormularioDAO extends AbstractDAO<SysFormulario> {

	public SysFormularioDAO() {
		super(SysFormulario.class);
	}

	public SysFormulario getPorOpcion(String opcion) {
		CriteriaQuery<SysFormulario> q = cb.createQuery(SysFormulario.class);
		Root<SysFormulario> c = q.from(SysFormulario.class);
		Predicate condicion = cb.equal(c.get("opcion"), opcion);
		q.select(c).where(condicion);
		try {
			return getEntityManager().createQuery(q).getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

}
