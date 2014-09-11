package dao;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import entity.Docingreso;
import entity.Documento;

public class DocingresoDAO extends AbstractDAO<Docingreso> {
	public DocingresoDAO(){
		super(Docingreso.class);
	}
	
	public int getPorSerie(String serie){
		CriteriaQuery<Docingreso> q = cb.createQuery(Docingreso.class);
		Root c = q.from(Docingreso.class);
		Predicate condicion = cb.equal(c.get("serie"), serie);
		q.select(cb.max(c.get("numero"))).where(condicion);
		System.out.println(getEntityManager().createQuery(q).getSingleResult());
		int Numero = 0;
		if (getEntityManager().createQuery(q).getSingleResult() instanceof Object)
			Numero = Integer.parseInt(((Object)getEntityManager().createQuery(q).getSingleResult()).toString());
		return Numero;
	}
}
