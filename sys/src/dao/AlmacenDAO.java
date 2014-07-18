package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Almacen;
import entity.Sucursal;

public class AlmacenDAO extends AbstractDAO<Almacen> {

	public AlmacenDAO() {
		super(Almacen.class);
	}

	public List<Almacen> getPorSucursal(Sucursal sucursal) {
		CriteriaQuery<Almacen> q = cb.createQuery(Almacen.class);
		Root<Almacen> c = q.from(Almacen.class);
		Predicate condicion = cb.equal(c.get("sucursal"), sucursal);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

	public void borrarPorSucursal(Sucursal sucursal) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<Almacen> delete = cb
				.createCriteriaDelete(Almacen.class);
		Root<Almacen> c = delete.from(Almacen.class);
		delete.where(cb.equal(c.get("sucursal"), sucursal));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}

}
