package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.DOrdenCompra;
import entity.DetDocingreso;
import entity.Docingreso;
import entity.OrdenCompra;

public class DetDocIngresoDAO extends AbstractDAO<DetDocingreso> {
	public DetDocIngresoDAO(){
		super(DetDocingreso.class);
	}
	
	public List<DetDocingreso> getPorIdIngreso(Docingreso ingreso){
		CriteriaQuery<DetDocingreso> q = cb.createQuery(DetDocingreso.class);
		Root<DetDocingreso> c = q.from(DetDocingreso.class);
		Predicate condicion = cb.equal(c.get("docingreso"), ingreso);
		q.multiselect(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public List<DetDocingreso> aEliminar(Docingreso oc, List<DetDocingreso> detalle) {
		List<DetDocingreso> eliminar = new ArrayList<DetDocingreso>();
		for (DetDocingreso o1 : getPorIdIngreso(oc)) {
			boolean existe = false;
			salir: for (DetDocingreso o2 : detalle) {
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
