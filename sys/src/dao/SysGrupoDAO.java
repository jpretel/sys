package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.SysGrupo;
import entity.SysOpcion;
import entity.SysTitulo;
import entity.Usuario;

public class SysGrupoDAO extends AbstractDAO<SysGrupo> {

	public SysGrupoDAO() {
		super(SysGrupo.class);
	}
	
	public List<SysGrupo> getPorTitulo(SysTitulo titulo) {
		CriteriaQuery<SysGrupo> q = cb.createQuery(SysGrupo.class);
		Root<SysGrupo> c = q.from(SysGrupo.class);
		Predicate condicion = cb.equal(c.get("sysTitulo"), titulo);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public List<SysGrupo> getPorTitulo(SysTitulo titulo, Usuario usuario) {
		CriteriaQuery<SysGrupo> q = cb.createQuery(SysGrupo.class);
		Root<SysGrupo> c = q.from(SysGrupo.class);
		Predicate condicion = cb.equal(c.get("sysTitulo"), titulo);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

}
