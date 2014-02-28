package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Grupo;
import entity.Subgrupo;

public class SubgrupoDAO extends AbstractDAO<Subgrupo> {
	public SubgrupoDAO(){
		super(Subgrupo.class);		
	}
	public List<Subgrupo> findAllbyGrupo() {
		CriteriaQuery<Subgrupo> q = cb.createQuery(Subgrupo.class);
		Root<Subgrupo> m = q.from(Subgrupo.class);
		q.select(m);
		List<Subgrupo>  subgrupo = getEntityManager().createQuery(q).getResultList();
		return subgrupo;
	}
	
	public List<Subgrupo> findAllbyGrupo1(Grupo grupo) {
		CriteriaQuery<Subgrupo> q = cb.createQuery(Subgrupo.class);
		Root<Subgrupo> m = q.from(Subgrupo.class);
		Predicate condicion = cb.equal(m.get("grupo"), grupo);
		q.select(m).where(condicion);
		List<Subgrupo>  subgrupo = getEntityManager().createQuery(q).getResultList();
		return subgrupo;
	}
}
