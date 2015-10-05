package de.logicline.herokutemplate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import de.logicline.herokutemplate.model.ContractInfoEntity;
import de.logicline.herokutemplate.model.UserEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Service
public class ContractServiceImpl implements ContractService {

	private Logger log = Logger.getLogger(this.getClass().getName());
	
    @PersistenceContext
    EntityManager em;
     
   
    @Transactional
	public void addContract(ContractInfoEntity contractInfo) {
		em.persist(contractInfo);		
		 try {
		        em.flush();  
		    } catch (PersistenceException e) {  		        
		    	log.error("Adding Contract failed", e);  
		    }
	}
	
	@Transactional
	public void addContract(List<ContractInfoEntity> contractInfoList) {

		if (contractInfoList.size() > 1000) {
			return;
		}
		for (ContractInfoEntity ci : contractInfoList) {
			addContract(ci);
		}
	}
    
    
   
    public List<ContractInfoEntity> getContractList(String token){
    	Integer userId = getUserId(token);

    	List<ContractInfoEntity> contractList = getContractList(userId);	
		return contractList;
    	
    }
    
    public List<ContractInfoEntity> getContractList(Integer userId){
    
    	CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContractInfoEntity> cq = cb.createQuery(ContractInfoEntity.class);
		Root<ContractInfoEntity> contractInfo = cq.from(ContractInfoEntity.class);
		cq.select(contractInfo);
		cq.where(cb.equal(contractInfo.get("userIdFk"),userId));
		List<ContractInfoEntity> contractList = em.createQuery(cq).getResultList();
	
		return contractList;
    	
    }
    
    public List<ContractInfoEntity> getContractList(){
        
    	CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContractInfoEntity> cq = cb.createQuery(ContractInfoEntity.class);
		Root<ContractInfoEntity> contractInfo = cq.from(ContractInfoEntity.class);
		cq.select(contractInfo);
		List<ContractInfoEntity> contractList = em.createQuery(cq).getResultList();
	
		return contractList;
    	
    }
   
    
    public Map<String, Object> getContractInfo(String token, String contractInfoId){
    	Integer userId = getUserId(token);
    	CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContractInfoEntity> cq = cb.createQuery(ContractInfoEntity.class);
		Root<ContractInfoEntity> user = cq.from(ContractInfoEntity.class);
		cq.select(user);
		// TODO implement check for UserRole is admin
		//cq.where(cb.and(cb.equal(user.get("userIdFk"),userId), cb.equal(user.get("contractInfoId"),contractInfoId)));
		cq.where(cb.equal(user.get("contractInfoId"),contractInfoId));
		List<ContractInfoEntity> resultList = em.createQuery(cq).getResultList();
		
		ContractInfoEntity result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
		}
    	
		// Comment forr test iissues
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("contractInfo", result);
		
    	// TODO Fill in the missing attributes from mockup (Customer ID, Prename, Surename etc.)
    	
    	return map;
    	
    }
    
    @Transactional
    public void updateContractInfo(String contractInfoId, ContractInfoEntity contractInfo){
    	Integer cii = Integer.valueOf(contractInfoId);
    	ContractInfoEntity contractInfoForUpdate = em.find(ContractInfoEntity.class, cii);
    	contractInfoForUpdate.setFacilityName(contractInfo.getFacilityName());
    	contractInfoForUpdate.setParkingLotCount(contractInfo.getParkingLotCount());
    	em.persist(contractInfoForUpdate);
    	
    }

 
    private Integer getUserId(String token){
    	CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cq.from(UserEntity.class);
		cq.select(user);
		cq.where(cb.equal(user.get("token"),token));
		List<UserEntity> resultList = em.createQuery(cq).getResultList();
		
		UserEntity result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
		}
		
		return result.getUserId();
    }

	

}
