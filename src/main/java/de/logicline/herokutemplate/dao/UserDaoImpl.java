package de.logicline.herokutemplate.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import de.logicline.herokutemplate.model.UserEntity;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<UserEntity> implements UserDao {

	private Logger log = Logger.getLogger(this.getClass().getName());

	public UserDaoImpl() {
		super(UserEntity.class);
	}

	public Integer getUserId(String token) {

		UserEntity result = getUser(token);
		if (result != null) {
			return result.getUserId();
		}
		return null;
	}

	public UserEntity getUser(String token) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cq.from(UserEntity.class);
		cq.select(user);
		cq.where(cb.equal(user.get("token"), token));
		List<UserEntity> resultList = getEntityManager().createQuery(cq)
				.getResultList();

		UserEntity result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
			return result;
		}

		return null;
	}

	public UserEntity getUserByNameAndPassword(String username, String password) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cq.from(UserEntity.class);
		cq.select(user);
		cq.where(cb.and(cb.equal(user.get("username"), username),
				cb.equal(user.get("password"), password)));
		List<UserEntity> resultList = getEntityManager().createQuery(cq)
				.getResultList();

		if (resultList == null || resultList.size() == 0) {
			log.debug("UserEntity not found");
			return null;
		} else if (resultList.size() > 1) {
			log.warn("Multiple Users found for User with Username: " + username);
			return null;
		}

		return resultList.get(0);
	}

}
