package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DOrdenCompra;
import entity.Docingreso;
import entity.KardexCompraRecepcion;
import entity.OrdenCompra;

public class KardexCompraRecepcionDAO extends AbstractDAO<KardexCompraRecepcion> {
	public KardexCompraRecepcionDAO() {
		super(KardexCompraRecepcion.class);
	}
	
	public List<KardexCompraRecepcion> getPorDOrdenCompra(DOrdenCompra doc) {
		CriteriaQuery<KardexCompraRecepcion> q = cb.createQuery(KardexCompraRecepcion.class);
		Root<KardexCompraRecepcion> c = q.from(KardexCompraRecepcion.class);
		Predicate condicion = cb.equal( c.get("dordencompra"), doc);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public void borrarPorRefCompraFactor(long idreferencia, int factor) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<KardexCompraRecepcion> delete = cb.createCriteriaDelete(KardexCompraRecepcion.class);
		Root<KardexCompraRecepcion> c = delete.from(KardexCompraRecepcion.class);
		Predicate condicion = cb.equal( c.get("idreferencia"), idreferencia);
		Predicate condicion1 = cb.equal( c.get("factor"), factor);
		delete.where(condicion,condicion1);
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Tuple> getSaldoOrdenCompra(OrdenCompra oCompra,
			Docingreso docingreso) {

		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		Root from = q.from(KardexCompraRecepcion.class);
		
		Predicate condicion = cb.and(
				cb.equal(from.get("idordencompra"), oCompra.getIdordencompra()),
				cb.and(cb.notEqual(from.get("iddocingreso"),
						docingreso.getIddocingreso())));
		q.multiselect(
				from.get("producto").alias("producto"),
				cb.sum(cb.prod(from.get("factor"), from.get("cantidad")))
						.alias("cantidad")).where(condicion);
		q.groupBy(from.get("producto"));
		q.having(cb.greaterThan(
				cb.sum(cb.prod(from.get("factor"), from.get("cantidad"))), 0));

		return em.createQuery(q).getResultList();
	}
}
