package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entity.Marca;

public class MarcaDAO extends AbstractDAO<Marca> {
	public MarcaDAO(){
		super(Marca.class);
	}
	public List<Marca> findAllbyMarca() {
		CriteriaQuery<Marca> q = cb.createQuery(Marca.class);
		Root<Marca> m = q.from(Marca.class);
		q.select(m);
		List<Marca>  marca = getEntityManager().createQuery(q).getResultList();
		return marca;
	}
}
