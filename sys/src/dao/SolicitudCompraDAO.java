package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.SolicitudCompra;
import entity.SolicitudCompra;

public class SolicitudCompraDAO extends AbstractDAO<SolicitudCompra>{

	public SolicitudCompraDAO() {
		super(SolicitudCompra.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public int getPorSerie(String serie){
		CriteriaQuery<SolicitudCompra> q = cb.createQuery(SolicitudCompra.class);
		Root c = q.from(SolicitudCompra.class);
		Predicate condicion = cb.equal(c.get("serie"), serie);
		q.select(cb.max(c.get("numero"))).where(condicion);
		
		int Numero = 0;
		if (getEntityManager().createQuery(q).getSingleResult() instanceof Object)
			Numero = Integer.parseInt(((Object)getEntityManager().createQuery(q).getSingleResult()).toString());
		return Numero;
	}
	
	public List<SolicitudCompra> getFiltro(int idesde, int ihasta, String serie,
			int numero) {

		CriteriaQuery<SolicitudCompra> q = cb.createQuery(SolicitudCompra.class);
		Root<SolicitudCompra> c = q.from(SolicitudCompra.class);
		
		Expression<Integer> expfecha = c.get("aniomesdia");
				
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
		
		if (ps != null){
			q.select(c).where(ps);
		} else 
			q.select(c);
		
		return getEntityManager().createQuery(q).getResultList();
	}

}

