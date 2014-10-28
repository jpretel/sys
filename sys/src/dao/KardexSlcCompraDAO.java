package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.KardexSlcCompra;
import entity.OrdenCompra;
import entity.SolicitudCompra;

public class KardexSlcCompraDAO extends AbstractDAO<KardexSlcCompra> {

	public KardexSlcCompraDAO() {
		super(KardexSlcCompra.class);
	}

	public List<Tuple> getSaldoSolicitudCompra(SolicitudCompra solicitudCompra,
			OrdenCompra ordenCompra) {

		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		Root from = q.from(KardexSlcCompra.class);

		// Join p = from.join("dordencompra", JoinType.LEFT);

		Predicate condicion = cb.and(
				cb.equal(from.get("solicitudcompra"), solicitudCompra),
				cb.and(cb.notEqual(from.get("id_referencia"),
						ordenCompra.getIdordencompra())));

		// q.multiselect(from,
		// p.get("id").get("idordencompra").alias("idordencompra"));

		q.multiselect(
				from.get("producto").alias("producto"),
				from.get("unimedida").alias("unimedida"),
				cb.sum(cb.prod(from.get("factor"), from.get("cantidad")))
						.alias("cantidad")).where(condicion);
		q.groupBy(from.get("producto"), from.get("unimedida"));
		q.having(cb.greaterThan(
				cb.sum(cb.prod(from.get("factor"), from.get("cantidad"))), 0));

		return em.createQuery(q).getResultList();
	}

	public void borrarPorIdSolicitudCompra(long idsolicitudcompra) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<KardexSlcCompra> delete = cb
				.createCriteriaDelete(KardexSlcCompra.class);
		Root<KardexSlcCompra> c = delete.from(KardexSlcCompra.class);
		delete.where(cb.equal(
				c.get("solicitudcompra").get("idsolicitudcompra"),
				idsolicitudcompra));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
	public void borrarPorIdCotizacionCompra(long idcotizacioncompra) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<KardexSlcCompra> delete = cb
				.createCriteriaDelete(KardexSlcCompra.class);
		Root<KardexSlcCompra> c = delete.from(KardexSlcCompra.class);
		delete.where(cb.equal(
				c.get("cotizacioncompra").get("idcotizacioncompra"),
				idcotizacioncompra));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}

	public void borrarPorIdOrdenCompra(long idordencompra) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<KardexSlcCompra> delete = cb
				.createCriteriaDelete(KardexSlcCompra.class);
		Root<KardexSlcCompra> c = delete.from(KardexSlcCompra.class);
		delete.where(cb.and(cb.equal(c.get("id_referencia"), idordencompra),
				cb.equal(c.get("tipo_referencia"), "ORD_COMPRA")));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}

}
