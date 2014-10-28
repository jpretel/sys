package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Almacen;
import entity.CotizacionCompra;
import entity.SolicitudCompra;
import entity.Sucursal;

public class CotizacionCompraDAO extends AbstractDAO<CotizacionCompra> {

	public CotizacionCompraDAO() {
		super(CotizacionCompra.class);
	}

	@SuppressWarnings("unchecked")
	public int getPorSerie(String serie) {
		CriteriaQuery<CotizacionCompra> q = cb
				.createQuery(CotizacionCompra.class);
		Root c = q.from(CotizacionCompra.class);
		Predicate condicion = cb.equal(c.get("serie"), serie);
		q.select(cb.max(c.get("numero"))).where(condicion);

		int Numero = 0;
		if (getEntityManager().createQuery(q).getSingleResult() instanceof Object)
			Numero = Integer.parseInt(((Object) getEntityManager().createQuery(
					q).getSingleResult()).toString());
		return Numero;
	}

	public List<CotizacionCompra> getFiltro(int idesde, int ihasta,
			String serie, int numero) {

		CriteriaQuery<CotizacionCompra> q = cb
				.createQuery(CotizacionCompra.class);
		Root<CotizacionCompra> c = q.from(CotizacionCompra.class);

		Expression<Integer> expfecha = c.get("fecha");

		Predicate ps = null;

		if (serie.trim().length() > 0)
			ps = cb.equal(c.get("serie"), serie);

		if (numero != 0)
			if (ps == null)
				ps = cb.equal(c.get("numero"), numero);
			else
				ps = cb.and(ps, cb.equal(c.get("numero"), numero));

		if (idesde != 0)
			if (ps == null) {
				ps = cb.greaterThanOrEqualTo(expfecha, idesde);
			} else {
				ps = cb.and(ps, cb.greaterThanOrEqualTo(expfecha, idesde));
			}

		if (ihasta != 0)
			if (ps == null) {
				ps = cb.lessThanOrEqualTo(expfecha, ihasta);
			} else {
				ps = cb.and(ps, cb.lessThanOrEqualTo(expfecha, ihasta));
			}

		if (ps != null) {
			q.select(c).where(ps);
		} else
			q.select(c);

		return getEntityManager().createQuery(q).getResultList();
	}

	public CotizacionCompra getPorSerieNumero(String serie, String numero) {
		CriteriaQuery<CotizacionCompra> q = cb
				.createQuery(CotizacionCompra.class);
		Root<CotizacionCompra> c = q.from(CotizacionCompra.class);
		Predicate ps = null;
		ps = cb.equal(c.get("serie"), serie);
		ps = cb.and(ps, cb.equal(c.get("numero"), numero));
		q.select(c).where(ps);

		return getEntityManager().createQuery(q).getSingleResult();
	}
	
	
	public CotizacionCompra getPorSerieNumeroSucursalAlmacen(String serie,
			int numero, Sucursal sucursal, Almacen almacen) {
		CriteriaQuery<CotizacionCompra> q = cb
				.createQuery(CotizacionCompra.class);
		Root<CotizacionCompra> from = q.from(CotizacionCompra.class);
		Predicate ps = cb.and(cb.equal(from.get("sucursal"), sucursal),
				cb.equal(from.get("almacen"), almacen),
				cb.equal(from.get("serie"), serie),
				cb.equal(from.get("numero"), numero));

		q.select(from).where(ps);
		try {
			return getEntityManager().createQuery(q).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
