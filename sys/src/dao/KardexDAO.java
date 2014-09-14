package dao;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import entity.Kardex;

public class KardexDAO extends AbstractDAO<Kardex> {
	public KardexDAO(){
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

}
