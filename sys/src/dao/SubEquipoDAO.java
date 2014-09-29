package dao;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import entity.Equipo;
import entity.Subequipo;

public class SubEquipoDAO extends AbstractDAO<Subequipo>{

	public SubEquipoDAO() {
		super(Subequipo.class);
		// TODO Auto-generated constructor stub
	}
	
	public void borrarPorEquipo (Equipo equipo) {
		getEntityManager().getTransaction().begin();
		CriteriaDelete<Subequipo> delete = cb.createCriteriaDelete(Subequipo.class);
		Root<Subequipo> c = delete.from(Subequipo.class);
		delete.where(cb.equal(c.get("equipo"), equipo));
		Query query = getEntityManager().createQuery(delete);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}

}
