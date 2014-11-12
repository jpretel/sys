package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Docsalida;

public class DocsalidaDAO extends AbstractDAO<Docsalida> {
	public DocsalidaDAO() {
		super(Docsalida.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getPorSerie(String serie) {
		CriteriaQuery<Docsalida> q = cb.createQuery(Docsalida.class);
		Root c = q.from(Docsalida.class);
		Predicate condicion = cb.equal(c.get("serie"), serie);
		q.select(cb.max(c.get("numero"))).where(condicion);

		int Numero = 0;
		if (getEntityManager().createQuery(q).getSingleResult() instanceof Object)
			Numero = Integer.parseInt(((Object) getEntityManager().createQuery(
					q).getSingleResult()).toString());
		return Numero;
	}

	public List<Docsalida> getFiltro(int idesde, int ihasta, String serie,
			int numero) {

		CriteriaQuery<Docsalida> q = cb.createQuery(Docsalida.class);
		Root<Docsalida> c = q.from(Docsalida.class);

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
}
