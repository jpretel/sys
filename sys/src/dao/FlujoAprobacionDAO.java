package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Flujo;
import entity.FlujoAprobacion;
import entity.SysFormulario;

public class FlujoAprobacionDAO extends AbstractDAO<FlujoAprobacion> {

	public FlujoAprobacionDAO() {
		super(FlujoAprobacion.class);
	}

	public List<FlujoAprobacion> getPorFormulario(SysFormulario formulario) {
		CriteriaQuery<FlujoAprobacion> q = cb
				.createQuery(FlujoAprobacion.class);
		Root<FlujoAprobacion> from = q.from(FlujoAprobacion.class);
		Predicate condicion = cb.equal(from.get("sysFormulario"), formulario);
		q.select(from).where(condicion)
				.orderBy(cb.asc(from.get("id").get("orden")));
		return getEntityManager().createQuery(q).getResultList();
	}

	public void borrarPorFormulario(SysFormulario formulario) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<FlujoAprobacion> delete = cb
				.createCriteriaDelete(FlujoAprobacion.class);
		Root<FlujoAprobacion> c = delete.from(FlujoAprobacion.class);
		delete.where(cb.equal(c.get("sysFormulario"), formulario));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}

	public Flujo getUltimoEstado(SysFormulario formulario) {
		List<FlujoAprobacion> flujoAprobacion = getPorFormulario(formulario);
		if (flujoAprobacion == null) {
			return null;
		}
		if (flujoAprobacion.size() == 0) {
			return null;
		}
		return flujoAprobacion.get(flujoAprobacion.size() - 1).getFlujo();
	}

	public Flujo getPrimerEstado(SysFormulario formulario) {
		List<FlujoAprobacion> flujoAprobacion = getPorFormulario(formulario);
		if (flujoAprobacion == null) {
			return null;
		}
		if (flujoAprobacion.size() == 0) {
			return null;
		}
		return flujoAprobacion.get(0).getFlujo();
	}

	public Flujo getEstadoAnterior(SysFormulario formulario, Flujo flujo) {
		List<FlujoAprobacion> flujoAprobacion = getPorFormulario(formulario);
		if (flujoAprobacion == null) {
			return null;
		}
		if (flujoAprobacion.size() == 0) {
			return null;
		}

		int index = -1;
		salir: for (int i = 0; i < flujoAprobacion.size(); i++) {
			Flujo f = flujoAprobacion.get(i).getFlujo();
			if (f.getIdflujo().equals(flujo.getIdflujo())) {
				index = i;
				break salir;
			}
		}

		if (index > 0) {
			return flujoAprobacion.get(index - 1).getFlujo();
		} else {
			return null;
		}
	}

	public Flujo getEstadoSiguiente(SysFormulario formulario, Flujo flujo) {
		List<FlujoAprobacion> flujoAprobacion = getPorFormulario(formulario);
		if (flujoAprobacion == null) {
			return null;
		}
		if (flujoAprobacion.size() == 0) {
			return null;
		}

		int index = -1;
		salir: for (int i = 0; i < flujoAprobacion.size(); i++) {
			Flujo f = flujoAprobacion.get(i).getFlujo();
			if (f.getIdflujo().equals(flujo.getIdflujo())) {
				index = i;
				break salir;
			}
		}

		if (index > -1 && index < flujoAprobacion.size()) {
			return flujoAprobacion.get(index + 1).getFlujo();
		} else {
			return null;
		}
	}
}
