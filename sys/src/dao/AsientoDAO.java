package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.internal.jpa.JPAQuery;

import vista.utilitarios.StringUtils;
import entity.Asiento;
import entity.Consumidor;
import entity.Consumidor_;
import entity.Subdiario;

public class AsientoDAO extends AbstractDAO<Asiento> {
	private static final String _ceros = "0000000000";

	public AsientoDAO() {
		super(Asiento.class);
	}

	@SuppressWarnings("unchecked")
	public String getMaxNumerador(Subdiario subdiario, int anio, int mes) {
		CriteriaQuery<Integer> q = cb.createQuery(Integer.class);
		Root c = q.from(Asiento.class);
		q.select(cb.max(c.get("numerador"))).where(
				cb.and(cb.equal(c.get("subdiario"), subdiario),
						cb.equal(c.get("anio"), anio),
						cb.equal(c.get("mes"), mes)));
		int numerador = getEntityManager().createQuery(q).getSingleResult();

		return StringUtils._right(
				_ceros + String.valueOf(numerador + 1).trim(), 10);
	}

	public List<Asiento> getFiltro(int idesde, int ihasta, Subdiario subdiario,
			int numero) {

		CriteriaQuery<Asiento> q = cb.createQuery(Asiento.class);
		Root<Asiento> c = q.from(Asiento.class);
		
		Expression<Integer> expfecha = c.get("aniomesdia");
				
		Predicate ps = null;
		
		if (subdiario != null)
			ps = cb.equal(c.get("subdiario"), subdiario);
		
		if (numero != 0)
			if (ps == null)
				ps = cb.equal(c.get("numerador"), numero);
			else
				ps = cb.and(ps, cb.equal(c.get("numerador"), numero));
		
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
		
		if (ps != null){
			q.select(c).where(ps);
		} else 
			q.select(c);
		
		return getEntityManager().createQuery(q).getResultList();
	}
}
