package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DSolicitudCotizacion;
import entity.SolicitudCotizacion;


public class DSolicitudCotizacionDAO extends AbstractDAO<DSolicitudCotizacion> {

	public DSolicitudCotizacionDAO() {
		super(DSolicitudCotizacion.class);
	}

	public List<DSolicitudCotizacion> getPorSolicitud(SolicitudCotizacion solicitud) {
		CriteriaQuery<DSolicitudCotizacion> q = cb.createQuery(DSolicitudCotizacion.class);
		Root<DSolicitudCotizacion> c = q.from(DSolicitudCotizacion.class);
		Predicate condicion = cb.equal(c.get("solicitud"), solicitud);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

	public List<DSolicitudCotizacion> aEliminar(SolicitudCotizacion solicitud,
			List<DSolicitudCotizacion> detalle) {
		List<DSolicitudCotizacion> eliminar = new ArrayList<DSolicitudCotizacion>();
		for (DSolicitudCotizacion o1 : getPorSolicitud(solicitud)) {
			boolean existe = false;
			salir: for (DSolicitudCotizacion o2 : detalle) {
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
