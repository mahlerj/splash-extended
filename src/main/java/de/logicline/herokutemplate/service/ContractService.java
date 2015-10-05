package de.logicline.herokutemplate.service;

import java.util.List;
import java.util.Map;
import de.logicline.herokutemplate.model.ContractInfoEntity;


public interface ContractService {
    
	 public void addContract(ContractInfoEntity contractInfo);
	 public void addContract(List<ContractInfoEntity> contractInfoList); 
	
	 public List<ContractInfoEntity> getContractList(String token);
	 public List<ContractInfoEntity> getContractList(Integer userId);
	 public List<ContractInfoEntity> getContractList();
	  
	  public Map<String, Object> getContractInfo(String token, String contractId);
	  
	  public void updateContractInfo(String contractInfoId, ContractInfoEntity contractInfoValues);
	  
	  
  
}
