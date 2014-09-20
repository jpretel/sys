package dao;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;

import entity.GuardarUsuario;

public class GuardarUsuarioDAO extends AbstractDAO<GuardarUsuario>{

	public GuardarUsuarioDAO() {
		super(GuardarUsuario.class);
	}
	
	public void borrar() {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<GuardarUsuario> delete = cb.createCriteriaDelete(GuardarUsuario.class);
		
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
}
