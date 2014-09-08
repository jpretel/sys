package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.CfgCentralizaAlm;
import entity.Concepto;
import entity.Grupo;
import entity.Subgrupo;

public class CfgCentralizaAlmDAO extends AbstractDAO<CfgCentralizaAlm> {

	public CfgCentralizaAlmDAO() {
		super(CfgCentralizaAlm.class);
	}

	public List<CfgCentralizaAlm> getPorConcepto(Concepto concepto) {
		factory.getCache().evictAll();
		CriteriaQuery<CfgCentralizaAlm> q = cb.createQuery(CfgCentralizaAlm.class);
		Root<CfgCentralizaAlm> c = q.from(CfgCentralizaAlm.class);
		Predicate condicion = cb.equal(c.get("concepto"), concepto);
		q.select(c).where(condicion);	
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public CfgCentralizaAlm getPorConceptoGrupoSubGrupo(Concepto concepto, Grupo grupo, Subgrupo subgrupo) {
		CriteriaQuery<CfgCentralizaAlm> q = cb.createQuery(CfgCentralizaAlm.class);
		Root<CfgCentralizaAlm> c = q.from(CfgCentralizaAlm.class);
		Predicate condicion = cb.and(cb.equal(c.get("concepto"), concepto), cb.equal(c.get("grupo"), grupo), cb.equal(c.get("subgrupo"), subgrupo));
		q.select(c).where(condicion);	
		return getEntityManager().createQuery(q).getSingleResult();
	}
	
	public List<CfgCentralizaAlm> aEliminar(Concepto concepto, List<CfgCentralizaAlm> centraliza) {
		List<CfgCentralizaAlm> eliminar = new ArrayList<CfgCentralizaAlm>();
		for (CfgCentralizaAlm o1 : getPorConcepto(concepto)) {
			boolean existe = false;
			salir: for (CfgCentralizaAlm o2 : centraliza) {
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