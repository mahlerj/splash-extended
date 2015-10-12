package de.logicline.herokutemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Generic Abstract Superclass for all DAOs encapsulates basic DB Operations in
 * order to omit boilerplate code
 * 
 * @author mulrich
 *
 * @param <T>
 */
public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {

	@PersistenceContext
	EntityManager em;

	private Class<T> entityClass;

	public AbstractDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.logicline.parkingportal.dao.AbstractDao#findAll()
	 */
	@Override
	public List<T> findAll() {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.logicline.parkingportal.dao.AbstractDao#find(java.lang.Object)
	 */
	@Override
	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.logicline.parkingportal.dao.AbstractDao#findBySfid(java.lang.String)
	 */
	// @Override
	// public T findBySfid(String id) {
	// CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	// CriteriaQuery<T> cq = cb.createQuery(entityClass);
	// Root<T> r = cq.from(entityClass);
	// cq.select(r);
	// cq.where(cb.equal(r.get("sfid"), id));
	// List<T> resultList = getEntityManager().createQuery(cq).getResultList();
	//
	// T result = null;
	// if (resultList != null && resultList.size() > 0) {
	// result = resultList.get(0);
	// }
	//
	// return result;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.logicline.parkingportal.dao.AbstractDao#remove(T)
	 */
	@Override
	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.logicline.parkingportal.dao.AbstractDao#remove(T)
	 */

	public void remove(List<T> entityList) {
		for (T entity : entityList) {
			getEntityManager().remove(getEntityManager().merge(entity));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.logicline.parkingportal.dao.AbstractDao#edit(T)
	 */

	public T edit(T entity) {
		return getEntityManager().merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.logicline.parkingportal.dao.AbstractDao#create(T)
	 */
	@Override
	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public void create(List<T> entityList) {
		for (T entity : entityList) {
			getEntityManager().persist(entity);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.logicline.parkingportal.dao.AbstractDao#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.logicline.parkingportal.dao.AbstractDao#setEntityManager(javax.persistence
	 * .EntityManager)
	 */
	@Override
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

}
