package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Grupo;
import entity.Subgrupo;

public class SubgrupoDAO extends AbstractDAO<Subgrupo> {
	public SubgrupoDAO(){
		super(Subgrupo.class);		
	}
	
	public List<Subgrupo> findAllbyGrupo(Grupo grupo) {
		CriteriaQuery<Subgrupo> q = cb.createQuery(Subgrupo.class);
		Root<Subgrupo> m = q.from(Subgrupo.class);
		Predicate condicion = cb.equal(m.get("grupo"), grupo);
		q.select(m).where(condicion);
		List<Subgrupo>  subgrupo = getEntityManager().createQuery(q).getResultList();
		return subgrupo;
	}
	
	public void borrarPorGrupo (Grupo grupo) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<Subgrupo> delete = cb.createCriteriaDelete(Subgrupo.class);
		Root<Subgrupo> c = delete.from(Subgrupo.class);
		delete.where(cb.equal(c.get("grupo"), grupo));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
}
