package de.logicline.herokutemplate.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import de.logicline.herokutemplate.model.ContactEntity;

@Repository
public class ContactDaoImpl extends AbstractDaoImpl<ContactEntity> implements
		ContactDao {

	public ContactDaoImpl() {
		super(ContactEntity.class);
	}

	public List<ContactEntity> findByCustomerId(String id) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ContactEntity> cq = cb.createQuery(ContactEntity.class);
		Root<ContactEntity> userInfo = cq.from(ContactEntity.class);
		cq.select(userInfo);
		cq.where(cb.like(userInfo.<String> get("customeridC"), "%" + id + "%"));
		List<ContactEntity> result = getEntityManager().createQuery(cq)
				.getResultList();
		return result;
	}

	public List<ContactEntity> findByCustomerId(String id, String countrySfid) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ContactEntity> cq = cb.createQuery(ContactEntity.class);
		Root<ContactEntity> userInfo = cq.from(ContactEntity.class);
		cq.select(userInfo);
		cq.where(cb.and(
				cb.like(userInfo.<String> get("customeridC"), "%" + id + "%"),
				(cb.equal(userInfo.get("countryC"), countrySfid))));

		List<ContactEntity> result = getEntityManager().createQuery(cq)
				.getResultList();
		return result;
	}
}
