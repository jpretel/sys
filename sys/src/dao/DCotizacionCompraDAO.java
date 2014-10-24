package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.CotizacionCompra;
import entity.DCotizacionCompra;

public class DCotizacionCompraDAO extends AbstractDAO<DCotizacionCompra> {

	public DCotizacionCompraDAO() {
		super(DCotizacionCompra.class);
	}

	public List<DCotizacionCompra> getPorCotizacionCompra(CotizacionCompra oc) {
		CriteriaQuery<DCotizacionCompra> q = cb
				.createQuery(DCotizacionCompra.class);
		Root<DCotizacionCompra> c = q.from(DCotizacionCompra.class);
		Predicate condicion = cb.equal(c.get("cotizacioncompra"), oc);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

	public List<DCotizacionCompra> aEliminar(CotizacionCompra oc,
			List<DCotizacionCompra> almacenes) {
		List<DCotizacionCompra> eliminar = new ArrayList<DCotizacionCompra>();
		for (DCotizacionCompra o1 : getPorCotizacionCompra(oc)) {
			boolean existe = false;
			salir: for (DCotizacionCompra o2 : almacenes) {
				if (o1.getId().equals(o2.getId())) {
					existe = true;
					break salir;
				}
			}
			if (!existe)
				eliminar.add(o1);
		}
		return eliminar;
	}

	public void borrarPorCotizacionCompra(CotizacionCompra oc) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<DCotizacionCompra> delete = cb
				.createCriteriaDelete(DCotizacionCompra.class);
		Root<DCotizacionCompra> c = delete.from(DCotizacionCompra.class);
		delete.where(cb.equal(c.get("id").get("idcotizacioncompra"),
				oc.getIdcotizacioncompra()));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}

}
