package dao;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import entity.KardexSlcCompra;

public class KardexSlcCompraDAO extends AbstractDAO<KardexSlcCompra> {

	public KardexSlcCompraDAO() {
		super(KardexSlcCompra.class);
	}
	
	public void borrarPorIdSolicitudCompra(long idsolicitudcompra) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<KardexSlcCompra> delete = cb.createCriteriaDelete(KardexSlcCompra.class);
		Root<KardexSlcCompra> c = delete.from(KardexSlcCompra.class);
		delete.where(cb.equal(c.get("dsolicitudcompra").get("id").get("idsolicitudcompra"), idsolicitudcompra));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
	public void borrarPorIdOrdenCompra(long idordencompra) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<KardexSlcCompra> delete = cb.createCriteriaDelete(KardexSlcCompra.class);
		Root<KardexSlcCompra> c = delete.from(KardexSlcCompra.class);
		delete.where(cb.equal(c.get("dordencompra").get("id").get("idordencompra"), idordencompra));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
}
