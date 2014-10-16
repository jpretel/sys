package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DOrdenCompra;
import entity.KardexCompraRecepcion;

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
}
