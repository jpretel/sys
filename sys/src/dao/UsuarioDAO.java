package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.GrupoUsuario;
import entity.Usuario;

public class UsuarioDAO extends AbstractDAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public List<Usuario> getPorGrupoUsuario(GrupoUsuario grupo) {
		CriteriaQuery<Usuario> q = cb.createQuery(Usuario.class);
		Root<Usuario> c = q.from(Usuario.class);
		Predicate condicion = cb.equal(c.get("grupoUsuario"), grupo);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

	public int getTotalPorGrupoUsuario(GrupoUsuario grupo) {
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<Usuario> c = q.from(Usuario.class);
		Predicate condicion = cb.equal(c.get("grupoUsuario"), grupo);
		q.select(cb.count(c)).where(condicion);
		return getEntityManager().createQuery(q).getSingleResult().intValue();
	}

	public Usuario getPorUsuarioClave(String usuario, String clave) {
		CriteriaQuery<Usuario> q = cb.createQuery(Usuario.class);
		Root<Usuario> c = q.from(Usuario.class);
		Predicate condicion = cb.and(cb.equal(c.get("idusuario"), usuario),
				cb.equal(c.get("clave"), clave));
		q.select(c).where(condicion);
		try {
			return getEntityManager().createQuery(q).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
