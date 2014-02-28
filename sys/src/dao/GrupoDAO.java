package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entity.Grupo;

public class GrupoDAO extends AbstractDAO<Grupo> {
	public GrupoDAO(){
		super(Grupo.class);
	}
	public List<Grupo> findAllbyGrupo() {
		CriteriaQuery<Grupo> q = cb.createQuery(Grupo.class);
		Root<Grupo> m = q.from(Grupo.class);
		q.select(m);
		List<Grupo>  Grupo = getEntityManager().createQuery(q).getResultList();
		return Grupo;
	}

}
