package de.logicline.parkingportal.utils;

import de.logicline.parkingportal.model.ContractInfoEntity;
import de.logicline.parkingportal.model.UserInfoEntity;

public class CreateUserWithContract {
	UserInfoEntity userInfoEntity;
	ContractInfoEntity contractInfoEntity;
	
	
	public UserInfoEntity getUserInfoEntity() {
		return userInfoEntity;
	}
	public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
		this.userInfoEntity = userInfoEntity;
	}
	public ContractInfoEntity getContractInfoEntity() {
		return contractInfoEntity;
	}
	public void setContractInfoEntity(ContractInfoEntity contractInfoEntity) {
		this.contractInfoEntity = contractInfoEntity;
	}
	
	
}
