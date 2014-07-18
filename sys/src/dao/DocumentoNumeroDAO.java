package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Documento;
import entity.DocumentoNumero;

public class DocumentoNumeroDAO extends AbstractDAO<DocumentoNumero> {

	public DocumentoNumeroDAO() {
		super(DocumentoNumero.class);
	}
	
	public List<DocumentoNumero> getPorDocumento(Documento documento){
		CriteriaQuery<DocumentoNumero> q = cb.createQuery(DocumentoNumero.class);
		Root<DocumentoNumero> c = q.from(DocumentoNumero.class);
		Predicate condicion = cb.equal(c.get("documento"), documento);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public void borrarPorDocumento (Documento documento) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<DocumentoNumero> delete = cb.createCriteriaDelete(DocumentoNumero.class);
		Root<DocumentoNumero> c = delete.from(DocumentoNumero.class);
		delete.where(cb.equal(c.get("documento"), documento));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
}
