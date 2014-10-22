package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Docingreso;
import entity.Docsalida;
import entity.KardexRequerimiento;
import entity.Requerimiento;

public class KardexRequerimientoDAO extends AbstractDAO<KardexRequerimiento> {

	public KardexRequerimientoDAO() {
		super(KardexRequerimiento.class);
	}

	public void borrarPorRequerimiento(Requerimiento requerimiento) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<KardexRequerimiento> delete = cb
				.createCriteriaDelete(KardexRequerimiento.class);
		Root<KardexRequerimiento> c = delete.from(KardexRequerimiento.class);
		delete.where(cb.equal(c.get("requerimiento"), requerimiento));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Tuple> getSaldoRequerimiento(Requerimiento requerimiento,
			Docsalida docsalida) {

		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		Root from = q.from(KardexRequerimiento.class);
		
		Predicate condicion = cb.and(
				cb.equal(from.get("requerimiento"), requerimiento),
				cb.and(cb.notEqual(from.get("id_referencia"),
						docsalida.getIddocsalida())));
		q.multiselect(
				from.get("producto").alias("producto"),
				cb.sum(cb.prod(from.get("factor"), from.get("cantidad")))
						.alias("cantidad")).where(condicion);
		q.groupBy(from.get("producto"));
		q.having(cb.greaterThan(
				cb.sum(cb.prod(from.get("factor"), from.get("cantidad"))), 0));

		return em.createQuery(q).getResultList();
	}

	public void borrarPorIdReferencia(long id_referencia) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<KardexRequerimiento> delete = cb
				.createCriteriaDelete(KardexRequerimiento.class);
		Root<KardexRequerimiento> c = delete.from(KardexRequerimiento.class);
		delete.where(cb.equal(c.get("id_referencia"), id_referencia));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}

}
