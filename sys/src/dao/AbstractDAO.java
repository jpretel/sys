package dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractDAO<T> {
	private Class<T> entityClass;
		
	private EntityManagerFactory factory;

	private EntityManager em;
	
	protected CriteriaBuilder cb;
	
	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
		factory = Persistence.createEntityManagerFactory("sys");
		em = factory.createEntityManager();
		cb = getEntityManager().getCriteriaBuilder();
	}

	public EntityManager getEntityManager() {
		return this.em;
	}

	public void create(T entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
		getEntityManager().getTransaction().commit();
	}

	public void edit(T entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
	}
	
	public void crear_editar (T entity){		
			getEntityManager().getTransaction().begin();
			getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
	}

	public void remove(T entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(getEntityManager().merge(entity));
		getEntityManager().getTransaction().commit();
	}

	public T find(Object id) {
		return getEntityManager().find(entityClass, id) ;
	}

	// Lista todos los objetos
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	// Lista los objetos por rango de busqueda
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] range) {
		CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() {
		CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
	
	public List<T> rangeOfList (List<T> array, int[] range) {
		List<T> lista = new ArrayList<T>();
		for(int i = range[0] ; i<range[1] ; i++){
			if(i>=array.size()) break;
			lista.add(array.get(i));
		}
		return lista;
	}
}