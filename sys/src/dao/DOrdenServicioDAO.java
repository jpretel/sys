package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DOrdenServicio;
import entity.OrdenServicio;

public class DOrdenServicioDAO extends AbstractDAO<DOrdenServicio> {

	public DOrdenServicioDAO() {
		super(DOrdenServicio.class);
	}
	
	public List<DOrdenServicio> getPorOrdenServicio(OrdenServicio oc) {
		CriteriaQuery<DOrdenServicio> q = cb.createQuery(DOrdenServicio.class);
		Root<DOrdenServicio> c = q.from(DOrdenServicio.class);
		Predicate condicion = cb.equal( c.get("ordenservicio"), oc);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public List<DOrdenServicio> aEliminar(OrdenServicio oc, List<DOrdenServicio> ordenes) {
		List<DOrdenServicio> eliminar = new ArrayList<DOrdenServicio>();
		for (DOrdenServicio o1 : getPorOrdenServicio(oc)) {
			boolean existe = false;
			salir: for (DOrdenServicio o2 : ordenes) {
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

}
