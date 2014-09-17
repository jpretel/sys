package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Kardex;

public class KardexDAO extends AbstractDAO<Kardex> {
	public KardexDAO(){
		super(Kardex.class);
	}
	
	public void borrarPorIngresoSalida(long idreferencia) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<Kardex> delete = cb.createCriteriaDelete(Kardex.class);
		Root<Kardex> c = delete.from(Kardex.class);
		delete.where(cb.equal(c.get("idreferencia"), idreferencia));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
	public Kardex getPorIngresoSalidaC(long idreferencia) {
		getEntityManager().getTransaction().begin();
		CriteriaQuery<Kardex> q = cb.createQuery(Kardex.class);
		Root<Kardex> c = q.from(Kardex.class);
		Predicate condicion = cb.equal(c.get("idreferencia"), idreferencia);
		q.multiselect(c.get("idreferencia")).distinct(true);
		return getEntityManager().createQuery(q).getSingleResult();
	}
	
	public List<Kardex> getPorIngresoSalidaL(long idreferencia) {
		getEntityManager().getTransaction().begin();
		CriteriaQuery<Kardex> q = cb.createQuery(Kardex.class);
		Root<Kardex> c = q.from(Kardex.class);
		Predicate condicion = cb.equal(c.get("idreferencia"), idreferencia);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

}
