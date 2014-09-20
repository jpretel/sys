package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.GrupoUsuario;
import entity.GrupoUsuarioPrivilegio;
import entity.SysFormulario;

public class GrupoUsuarioPrivilegioDAO extends
		AbstractDAO<GrupoUsuarioPrivilegio> {

	public GrupoUsuarioPrivilegioDAO() {
		super(GrupoUsuarioPrivilegio.class);
	}

	public List<GrupoUsuarioPrivilegio> getPorGrupoUsuario(
			GrupoUsuario grupousuario) {
		CriteriaQuery<GrupoUsuarioPrivilegio> q = cb
				.createQuery(GrupoUsuarioPrivilegio.class);
		Root<GrupoUsuarioPrivilegio> c = q.from(GrupoUsuarioPrivilegio.class);
		Predicate condicion = cb.equal(c.get("grupoUsuario"), grupousuario);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

	public GrupoUsuarioPrivilegio getPorOpcion(String opcion, GrupoUsuario grupo) {
		CriteriaQuery<GrupoUsuarioPrivilegio> q = cb
				.createQuery(GrupoUsuarioPrivilegio.class);
		Root<GrupoUsuarioPrivilegio> from = q
				.from(GrupoUsuarioPrivilegio.class);
		Path<SysFormulario> path = from.join("sysFormulario").get("opcion");

		Predicate condicion = cb.equal(from.get("grupoUsuario"), grupo);
		q.select(from).where(cb.and(condicion, cb.equal(path, opcion)));

		List<GrupoUsuarioPrivilegio> lista = getEntityManager().createQuery(q)
				.getResultList();

		if (lista == null || lista.size() < 1) {
			return null;
		}

		return lista.get(0);
	}

}
