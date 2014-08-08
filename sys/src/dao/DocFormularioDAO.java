package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DocFormulario;
import entity.Documento;
import entity.DocumentoNumero;

public class DocFormularioDAO extends AbstractDAO<DocFormulario> {

	public DocFormularioDAO() {
		super(DocFormulario.class);
	}
	
	public List<DocFormulario> getPorDocumento(Documento documento){
		CriteriaQuery<DocFormulario> q = cb.createQuery(DocFormulario.class);
		Root<DocFormulario> c = q.from(DocFormulario.class);
		Predicate condicion = cb.equal(c.get("documento"), documento);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public void borrarPorDocumento (Documento documento) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<DocFormulario> delete = cb.createCriteriaDelete(DocFormulario.class);
		Root<DocFormulario> c = delete.from(DocFormulario.class);
		delete.where(cb.equal(c.get("documento"), documento));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
}
