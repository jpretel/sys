package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DDOrdenCompra;
import entity.OrdenCompra;

public class DDOrdenCompraDAO extends AbstractDAO<DDOrdenCompra> {

	public DDOrdenCompraDAO() {
		super(DDOrdenCompra.class);
	}

	public List<DDOrdenCompra> getPorOrdenCompra(OrdenCompra oc) {
		CriteriaQuery<DDOrdenCompra> q = cb.createQuery(DDOrdenCompra.class);
		Root<DDOrdenCompra> c = q.from(DDOrdenCompra.class);
		Predicate condicion = cb.equal(c.get("id").get("idordencompra"),
				oc.getIdordencompra());
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

	public void borrarPorOrdenCompra(OrdenCompra oc) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<DDOrdenCompra> delete = cb
				.createCriteriaDelete(DDOrdenCompra.class);
		Root<DDOrdenCompra> c = delete.from(DDOrdenCompra.class);
		delete.where(cb.equal(c.get("id").get("idordencompra"),
				oc.getIdordencompra()));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
}
