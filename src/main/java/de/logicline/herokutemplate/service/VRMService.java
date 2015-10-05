package de.logicline.herokutemplate.service;


import java.util.List;
import de.logicline.herokutemplate.model.VRMEntity;

public interface VRMService {
	
	public void addVrm(VRMEntity vrm);
	
	public void addVrm(List<VRMEntity> vrmEntityList);
    
	public List<VRMEntity> getVrmList(String token, String contractInfoId);
	
	public List<VRMEntity> getVrmList();
	
	public void updateVrm(String token, String contractInfoIdFk, List<VRMEntity> vrmForUpdate);
	
	public void createVrm(String token, String contractInfoId, List<VRMEntity> newVrm);
	
	/*
    public void addVRM(VRMEntity vrm);
    
    public List<VRMEntity> getVRMs(Integer userId);
    */
  
}
