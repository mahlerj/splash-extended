package de.logicline.herokutemplate.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.logicline.herokutemplate.model.ContactEntity;

@Entity
public class ContactDto {

	@Id
	@GeneratedValue
	private Integer userInfoId;

	private Integer userIdFk;

	private String customerId;
	private String typ;
	private String identityNr;
	private String orgNr;
	private String company;
	private String invoiceLab;
	private String facilityNr;
	private String contactPersNr;
	private String email;
	private String mainPoPox;
	private String mainStreet;
	private String mainZipcode;
	private String mainCity;
	private String mainName;
	private String mainSurname;

	public Integer getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	public Integer getUserIdFk() {
		return userIdFk;
	}

	public void setUserIdFk(Integer userIdFk) {
		this.userIdFk = userIdFk;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getIdentityNr() {
		return identityNr;
	}

	public void setIdentityNr(String identityNr) {
		this.identityNr = identityNr;
	}

	public String getOrgNr() {
		return orgNr;
	}

	public void setOrgNr(String orgNr) {
		this.orgNr = orgNr;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getInvoiceLab() {
		return invoiceLab;
	}

	public void setInvoiceLab(String invoiceLab) {
		this.invoiceLab = invoiceLab;
	}

	public String getFacilityNr() {
		return facilityNr;
	}

	public void setFacilityNr(String facilityNr) {
		this.facilityNr = facilityNr;
	}

	public String getContactPersNr() {
		return contactPersNr;
	}

	public void setContactPersNr(String contactPersNr) {
		this.contactPersNr = contactPersNr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMainPoPox() {
		return mainPoPox;
	}

	public void setMainPoPox(String mainPoPox) {
		this.mainPoPox = mainPoPox;
	}

	public String getMainStreet() {
		return mainStreet;
	}

	public void setMainStreet(String mainStreet) {
		this.mainStreet = mainStreet;
	}

	public String getMainZipcode() {
		return mainZipcode;
	}

	public void setMainZipcode(String mainZipcode) {
		this.mainZipcode = mainZipcode;
	}

	public String getMainCity() {
		return mainCity;
	}

	public void setMainCity(String mainCity) {
		this.mainCity = mainCity;
	}

	public String getMainName() {
		return mainName;
	}

	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

	public String getMainSurname() {
		return mainSurname;
	}

	public void setMainSurname(String mainSurname) {
		this.mainSurname = mainSurname;
	}

	public ContactEntity toEntity(ContactEntity contactEntity) {

		// not changeable
		// contactEntity.setId(getUserInfoId());
		// contactEntity.setSfid(getUserIdFk());
		contactEntity.setCustomerId(getCustomerId());
		contactEntity.setEmail(getEmail());
		contactEntity.setFirstName(getMainName());
		contactEntity.setLastName(getMainSurname());
		contactEntity.setMailingCity(getMainCity());
		contactEntity.setMailingPostalCode(getMainZipcode());
		contactEntity.setMailingStreet(getMainStreet());

		return contactEntity;
	}

}
