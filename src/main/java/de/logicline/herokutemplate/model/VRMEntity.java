package de.logicline.herokutemplate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VRMEntity {

    @Id
    @GeneratedValue
    private Integer vrmId;

    private Integer userIdFk;
    
    private Integer contractInfoIdFk;
    
    private String vrmName;

    private String description;

	public Integer getVrmId() {
        return vrmId;
    }

    public void setVrmId(Integer vrmId) {
        this.vrmId= vrmId;
    }
    
    public Integer getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(Integer userIdFk) {
        this.userIdFk = userIdFk;
    }

    public Integer getContractInfoIdFk() {
		return contractInfoIdFk;
	}

	public void setContractInfoIdFk(Integer contractInfoIdFk) {
		this.contractInfoIdFk = contractInfoIdFk;
	}

	public String getVrmName() {
        return vrmName;
    }

    public void setVrmName(String vrmName) {
        this.vrmName = vrmName;
    }
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
