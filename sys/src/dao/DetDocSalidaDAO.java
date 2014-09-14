package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DetDocsalida;
import entity.Docsalida;

public class DetDocSalidaDAO extends AbstractDAO<DetDocsalida> {
	public DetDocSalidaDAO(){
		super(DetDocsalida.class);
	}
	
	public List<DetDocsalida> getPorIdSalida(Docsalida salida){
		CriteriaQuery<DetDocsalida> q = cb.createQuery(DetDocsalida.class);
		Root<DetDocsalida> c = q.from(DetDocsalida.class);
		Predicate condicion = cb.equal(c.get("docsalida"), salida);
		q.multiselect(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
}
