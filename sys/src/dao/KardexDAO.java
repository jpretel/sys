package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Almacen;
import entity.Kardex;
import entity.Producto;
import entity.Sucursal;

public class KardexDAO extends AbstractDAO<Kardex> {
	public KardexDAO() {
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

	public float getSaldoAntesDe(int fecha, Producto producto,
			Sucursal sucursal, Almacen almacen) {
		float saldo = 0.0F;
		CriteriaQuery<Kardex> q = cb.createQuery(Kardex.class);
		Root from = q.from(Kardex.class);
		// cb.equal(from.get("anio"), anio),
		Predicate condicion = cb.and(cb.equal(from.get("producto"), producto),
				cb.lessThan(from.get("fecha"), fecha));

		if (almacen == null) {
			if (sucursal != null)
				condicion = cb.and(condicion,
						cb.equal(from.get("sucursal"), sucursal));
		} else {
			condicion = cb.and(condicion,
					cb.equal(from.get("almacen"), almacen));
		}

		q.select(cb.sum(cb.prod(from.get("factor"), from.get("cantidad"))))
				.where(condicion);
		Query query = em.createQuery(q);
		try {
			saldo = (float) query.getSingleResult();
		} catch (Exception e) {
			saldo = 0;
		}
		return saldo;
	}

	public List<Kardex> getMovimientos(int fecha_d, int fecha_h,
			Producto producto, Sucursal sucursal, Almacen almacen) {

		CriteriaQuery<Kardex> q = cb.createQuery(Kardex.class);
		Root from = q.from(Kardex.class);

		Predicate condicion = cb.and(cb.equal(from.get("producto"), producto),
				cb.between(from.get("fecha"), fecha_d, fecha_h));

		if (almacen == null) {
			if (sucursal != null)
				condicion = cb.and(condicion,
						cb.equal(from.get("sucursal"), sucursal));
		} else {
			condicion = cb.and(condicion,
					cb.equal(from.get("almacen"), almacen));
		}

		q.select(from)
				.where(condicion)
				.orderBy(cb.asc(from.get("fecha")), cb.desc(from.get("factor")));
		Query query = em.createQuery(q);
		return query.getResultList();
	}

	public List<Tuple> getSaldosSucursalAlmacen(int fecha, Sucursal sucursal,
			Almacen almacen) {
		// CriteriaQuery<Kardex> q = cb.createQuery(Kardex.class);
		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		Root from = q.from(Kardex.class);
		// cb.equal(from.get("anio"), anio),
		Predicate condicion = cb.lessThanOrEqualTo(from.get("fecha"), fecha);

		if (almacen == null) {
			if (sucursal != null)
				condicion = cb.and(condicion,
						cb.equal(from.get("sucursal"), sucursal));
		} else {
			condicion = cb.and(condicion,
					cb.equal(from.get("almacen"), almacen));
		}

		q.multiselect(
				from.get("producto").alias("producto"),
				cb.sum(cb.prod(from.get("factor"), from.get("cantidad")))
						.alias("cantidad")).where(condicion);
		q.groupBy(from.get("producto"));

		Query query = em.createQuery(q);

		return query.getResultList();
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
