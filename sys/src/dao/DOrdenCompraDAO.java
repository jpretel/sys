package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DOrdenCompra;
import entity.OrdenCompra;

public class DOrdenCompraDAO extends AbstractDAO<DOrdenCompra> {

	public DOrdenCompraDAO() {
		super(DOrdenCompra.class);
	}
	
	public List<DOrdenCompra> getPorOrdenCompra(OrdenCompra oc) {
		CriteriaQuery<DOrdenCompra> q = cb.createQuery(DOrdenCompra.class);
		Root<DOrdenCompra> c = q.from(DOrdenCompra.class);
		Predicate condicion = cb.equal( c.get("ordencompra"), oc);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public List<DOrdenCompra> aEliminar(OrdenCompra oc, List<DOrdenCompra> almacenes) {
		List<DOrdenCompra> eliminar = new ArrayList<DOrdenCompra>();
		for (DOrdenCompra o1 : getPorOrdenCompra(oc)) {
			boolean existe = false;
			salir: for (DOrdenCompra o2 : almacenes) {
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
