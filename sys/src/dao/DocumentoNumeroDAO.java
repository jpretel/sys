package dao;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Documento;
import entity.DocumentoNumero;
import entity.DocumentoNumeroPK;

public class DocumentoNumeroDAO extends AbstractDAO<DocumentoNumero> {

	public DocumentoNumeroDAO() {
		super(DocumentoNumero.class);
	}
	
	public DocumentoNumero getPorDocumento(Documento documento){
		CriteriaQuery<DocumentoNumero> q = cb.createQuery(DocumentoNumero.class);
		Root<DocumentoNumero> c = q.from(DocumentoNumero.class);
		Predicate condicion = cb.equal(c.get("documento"),documento);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public String getPorDocumento1(Documento documento,DocumentoNumeroPK id){
		CriteriaQuery<DocumentoNumero> q = cb.createQuery(DocumentoNumero.class);
		Root c1 = q.from(DocumentoNumero.class);
		Predicate condicion = cb.equal(c1.get("documento"),documento);
		Predicate condicion1 = cb.equal(c1.get("id"),id);
		q.select(cb.max(c1.get("numero"))).where(condicion,condicion1);
		System.out.println(getEntityManager().createQuery(q).getSingleResult());
		Object numero = getEntityManager().createQuery(q).getSingleResult();
		return numero.toString();
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
