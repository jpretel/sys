package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.OrdenCompra;

public class OrdenCompraDAO extends AbstractDAO<OrdenCompra> {

	public OrdenCompraDAO() {
		super(OrdenCompra.class);
	}
	
	@SuppressWarnings("unchecked")
	public int getPorSerie(String serie){
		CriteriaQuery<OrdenCompra> q = cb.createQuery(OrdenCompra.class);
		Root c = q.from(OrdenCompra.class);
		Predicate condicion = cb.equal(c.get("serie"), serie);
		q.select(cb.max(c.get("numero"))).where(condicion);
		
		int Numero = 0;
		if (getEntityManager().createQuery(q).getSingleResult() instanceof Object)
			Numero = Integer.parseInt(((Object)getEntityManager().createQuery(q).getSingleResult()).toString());
		return Numero;
	}
	
	public List<OrdenCompra> getFiltro(int idesde, int ihasta, String serie,
			int numero) {

		CriteriaQuery<OrdenCompra> q = cb.createQuery(OrdenCompra.class);
		Root<OrdenCompra> c = q.from(OrdenCompra.class);
		
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
	
	public OrdenCompra getPorSerieNumero(String serie,String numero){
		CriteriaQuery<OrdenCompra> q = cb.createQuery(OrdenCompra.class);
		Root<OrdenCompra> c = q.from(OrdenCompra.class);
		Predicate ps = null;
		ps = cb.equal(c.get("serie"), serie);
		ps = cb.and(ps,cb.equal(c.get("numero"), numero));
		q.select(c).where(ps);
		
		return getEntityManager().createQuery(q).getSingleResult();
	}
}
