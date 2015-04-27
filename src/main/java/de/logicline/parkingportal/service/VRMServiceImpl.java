package de.logicline.parkingportal.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import de.logicline.parkingportal.model.ContractInfoEntity;
import de.logicline.parkingportal.model.UserEntity;
import de.logicline.parkingportal.model.VRMEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class VRMServiceImpl implements VRMService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@PersistenceContext
	EntityManager em;

	@Transactional
	public void addVrm(VRMEntity vrmEntity) {
		em.persist(vrmEntity);
		try {
			em.flush();
		} catch (PersistenceException e) {
			log.error("Creating UserEntity failed", e);
		}
	}

	@Transactional
	public void addVrm(List<VRMEntity> vrmEntityList) {

		if (vrmEntityList.size() > 1000) {
			return;
		}
		for (VRMEntity vrm : vrmEntityList) {
			addVrm(vrm);
		}
	}

	public List<VRMEntity> getVrmList(String token, String contractInfoIdFk) {

		Integer userId = getUserId(token);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<VRMEntity> cq = cb.createQuery(VRMEntity.class);
		Root<VRMEntity> vrm = cq.from(VRMEntity.class);
		cq.select(vrm);
		// TODO implement check for UserRole is admin
		// cq.where(cb.and(cb.equal(vrm.get("userIdFk"), userId),
		// cb.equal(vrm.get("contractInfoIdFk"), contractInfoIdFk)));
		cq.where(cb.equal(vrm.get("contractInfoIdFk"), contractInfoIdFk));
		List<VRMEntity> resultList = em.createQuery(cq).getResultList();

		return resultList;

	}

	public List<VRMEntity> getVrmList() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<VRMEntity> cq = cb.createQuery(VRMEntity.class);
		Root<VRMEntity> vrmInfo = cq.from(VRMEntity.class);
		cq.select(vrmInfo);
		List<VRMEntity> vrmList = em.createQuery(cq).getResultList();
		return vrmList;
	}

	@Transactional
	public void updateVrm(String token, String contractInfoIdFk,
			List<VRMEntity> vrmForUpdate) {
		List<VRMEntity> vrmList = getVrmList(token, contractInfoIdFk);
		List<Integer> vrmToDelete = new ArrayList<Integer>();

		for (VRMEntity vrmEntity : vrmList) {
			for (VRMEntity vrmEntityForUpdate : vrmForUpdate) {
				if (vrmEntity.getVrmId().equals(vrmEntityForUpdate.getVrmId())) {
					if (vrmEntityForUpdate.getVrmName().isEmpty()) {
						vrmToDelete.add(vrmEntity.getVrmId());
					} else {
						vrmEntity.setVrmName(vrmEntityForUpdate.getVrmName());
						vrmEntity.setDescription(vrmEntityForUpdate
								.getDescription());
					}
				}
			}
		}

		em.flush();
		if (vrmToDelete.size() > 0) {
			Query mquery = em
					.createQuery("delete from VRMEntity v where v.vrmId in (:vrmToDelete)");
			mquery.setParameter("vrmToDelete", vrmToDelete);
			mquery.executeUpdate();
		}
	}

	@Transactional
	public void createVrm(String token, String contractInfoId,
			List<VRMEntity> newVrm) {

		// TODO implement check for UserRole is admin
		// Integer userIdFk = getUserId(token);

		Integer contractInfoIdInt = Integer.valueOf(contractInfoId);
		Integer userIdFk = getUserIdFk(contractInfoIdInt);

		for (VRMEntity n : newVrm) {
			n.setUserIdFk(userIdFk);
			n.setContractInfoIdFk(contractInfoIdInt);
			em.persist(n);
		}

	};

	private Integer getUserId(String token) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cq.from(UserEntity.class);
		cq.select(user);
		cq.where(cb.equal(user.get("token"), token));
		List<UserEntity> resultList = em.createQuery(cq).getResultList();

		UserEntity result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
		}

		return result.getUserId();
	}

	private Integer getUserIdFk(Integer contractInfoId) {
		ContractInfoEntity contractInfo = em.find(ContractInfoEntity.class,
				contractInfoId);
		return contractInfo.getUserIdFk();
	}

	/**
	 * @Transactional public void addVRM(VRMEntity vrm) { em.persist(vrm); }
	 * 
	 *                public List<VRMEntity> getVRMs(Integer userId){
	 *                CriteriaBuilder cb = em.getCriteriaBuilder();
	 *                CriteriaQuery<VRMEntity> cq =
	 *                cb.createQuery(VRMEntity.class); Root<VRMEntity> vrm =
	 *                cq.from(VRMEntity.class); cq.select(vrm);
	 *                cq.where(cb.equal(vrm.get("userIdFk"),userId));
	 *                List<VRMEntity> resultList =
	 *                em.createQuery(cq).getResultList();
	 * 
	 *                return resultList;
	 * 
	 *                }
	 * @Transactional public List<Person> listPeople() { CriteriaQuery<Person> c
	 *                = em.getCriteriaBuilder().createQuery(Person.class);
	 *                c.from(Person.class); return
	 *                em.createQuery(c).getResultList(); }
	 * @Transactional public void removePerson(Integer id) { Person person =
	 *                em.find(Person.class, id); if (null != person) {
	 *                em.remove(person); } }
	 **/

}
