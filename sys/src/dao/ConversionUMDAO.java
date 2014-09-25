package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.ConversionUM;
import entity.Unimedida;

public class ConversionUMDAO extends AbstractDAO<ConversionUM> {

	public ConversionUMDAO() {
		super(ConversionUM.class);
	}

	public List<ConversionUM> getPorUnimedida(Unimedida unimedida) {
		CriteriaQuery<ConversionUM> q = cb.createQuery(ConversionUM.class);
		Root<ConversionUM> c = q.from(ConversionUM.class);
		Predicate condicion = cb.equal(c.get("unimedida"), unimedida);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public List<ConversionUM> aEliminar(Unimedida unimedida, List<ConversionUM> conversiones) {
		List<ConversionUM> eliminar = new ArrayList<ConversionUM>();
		for (ConversionUM o1 : getPorUnimedida(unimedida)) {
			boolean existe = false;
			salir: for (ConversionUM o2 : conversiones) {
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
