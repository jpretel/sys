package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DSolicitudCompra;
import entity.SolicitudCompra;

public class DSolicitudCompraDAO extends AbstractDAO<DSolicitudCompra>{

	public DSolicitudCompraDAO() {
		super(DSolicitudCompra.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<DSolicitudCompra> getPorSolicitudCompra(SolicitudCompra oc) {
		CriteriaQuery<DSolicitudCompra> q = cb.createQuery(DSolicitudCompra.class);
		Root<DSolicitudCompra> c = q.from(DSolicitudCompra.class);
		Predicate condicion = cb.equal( c.get("solicitudcompra"), oc);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public List<DSolicitudCompra> aEliminar(SolicitudCompra oc, List<DSolicitudCompra> almacenes) {
		List<DSolicitudCompra> eliminar = new ArrayList<DSolicitudCompra>();
		for (DSolicitudCompra o1 : getPorSolicitudCompra(oc)) {
			boolean existe = false;
			salir: for (DSolicitudCompra o2 : almacenes) {
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
