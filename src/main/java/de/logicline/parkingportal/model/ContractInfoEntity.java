package de.logicline.parkingportal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ContractInfoEntity {

    @Id
    @GeneratedValue
    private Integer contractInfoId;

    private Integer userIdFk;
    
    private String contractId;
    
    private String facilityName;
    
    private Integer parkingLotCount;

	public Integer getContractInfoId() {
		return contractInfoId;
	}

	public void setContractInfoId(Integer contractInfoId) {
		this.contractInfoId = contractInfoId;
	}

	public Integer getUserIdFk() {
		return userIdFk;
	}

	public void setUserIdFk(Integer userIdFk) {
		this.userIdFk = userIdFk;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public Integer getParkingLotCount() {
		return parkingLotCount;
	}

	public void setParkingLotCount(Integer parkingLotCount) {
		this.parkingLotCount = parkingLotCount;
	}
    
    
    
}