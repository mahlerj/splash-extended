package de.logicline.splash.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import de.logicline.splash.model.ContactEntity;

@Repository
public class ContactDaoImpl extends AbstractDaoImpl<ContactEntity> implements
		ContactDao {

	public ContactDaoImpl() {
		super(ContactEntity.class);
	}

	public ContactEntity getContactByUserId(String userId) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ContactEntity> cq = cb.createQuery(ContactEntity.class);
		Root<ContactEntity> userInfo = cq.from(ContactEntity.class);
		cq.select(userInfo);
		cq.where(cb.equal(userInfo.get("userIdFk"), userId));
		List<ContactEntity> resultList = em.createQuery(cq).getResultList();

		ContactEntity result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
		}

		return result;
	}

	public List<ContactEntity> findByName(String name) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ContactEntity> cq = cb.createQuery(ContactEntity.class);
		Root<ContactEntity> userInfo = cq.from(ContactEntity.class);
		cq.select(userInfo);
		cq.where(cb.like(userInfo.<String> get("lastName"), "%" + name + "%"));
		List<ContactEntity> result = getEntityManager().createQuery(cq)
				.getResultList();
		return result;
	}

}
