package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.SysModulo;
import entity.SysTitulo;
import entity.Usuario;

public class SysTituloDAO extends AbstractDAO<SysTitulo> {

	public SysTituloDAO() {
		super(SysTitulo.class);
	}
	public List<SysTitulo> getPorModulo(SysModulo modulo) {
		CriteriaQuery<SysTitulo> q = cb.createQuery(SysTitulo.class);
		Root<SysTitulo> c = q.from(SysTitulo.class);
		Predicate condicion = cb.equal(c.get("sysModulo"), modulo);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

	public List<SysTitulo> aEliminar(SysModulo modulo, List<SysTitulo> titulos) {
		List<SysTitulo> eliminar = new ArrayList<SysTitulo>();
		for (SysTitulo o1 : getPorModulo(modulo)) {
			boolean existe = false;
			salir: for (SysTitulo o2 : titulos) {
				if (o1.getId().equals(o2.getId())) {
					existe = true;
					break salir;
				}
			}
			if (!existe)
				eliminar.add(o1);
		}
		return eliminar;
	}
	
	public void borrarPorModulo(SysModulo modulo) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<SysTitulo> delete = cb
				.createCriteriaDelete(SysTitulo.class);
		Root<SysTitulo> c = delete.from(SysTitulo.class);
		delete.where(cb.equal(c.get("sysModulo"), modulo));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	

	
	public List<SysTitulo> getPorModuloUsuario(SysModulo modulo, Usuario usuario) {
		CriteriaQuery<SysTitulo> q = cb.createQuery(SysTitulo.class);
		Root<SysTitulo> c = q.from(SysTitulo.class);
		Predicate condicion = cb.equal(c.get("sysModulo"), modulo);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
}
