package dao;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Menu;

public class MenuDAO extends AbstractDAO<Menu> {
	public MenuDAO(){
		super(Menu.class);
	}
	public Menu RetornarIndex(String pos) {
		CriteriaQuery<Menu> q = cb.createQuery(Menu.class);
		Root<Menu> m = q.from(Menu.class);
		Predicate condicion = cb.equal(m.get("posicion"), pos);
		q.select(m).where(condicion);
		Menu menu = getEntityManager().createQuery(q).getSingleResult();
		return menu;
	}
}
