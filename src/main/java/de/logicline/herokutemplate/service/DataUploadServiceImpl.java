package de.logicline.herokutemplate.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import de.logicline.herokutemplate.model.ContractInfoEntity;
import de.logicline.herokutemplate.model.UserEntity;
import de.logicline.herokutemplate.model.UserInfoEntity;
import de.logicline.herokutemplate.model.VRMEntity;
import de.logicline.herokutemplate.utils.Enums;
import de.logicline.herokutemplate.utils.PasswordGenerator;
import de.logicline.herokutemplate.utils.StringUtilMethods;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
public class DataUploadServiceImpl implements DataUploadService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@PersistenceContext
	EntityManager em;

	@Autowired
	private UserService userService;

	@Autowired
	private ContractService contractService;
	
	@Autowired
	private VRMService vrmService;

	public void generate(MultipartFile file) {

		try {

			InputStream inputStream = file.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));

			String zeile = bufferedReader.readLine();
			String[] s = zeile.split(";");
			String typeIndicator = s[0];
			if (typeIndicator.equals("Företagsnamn")) {
				generateUserData(file);	
			} else if (typeIndicator.equals("Customer ID")) {
				generateContractData(file);
			} else if (typeIndicator.equals("Customer Contract  ID")) {
				generateVrmData(file);
			} else {
				log.warn("Incorect format of upload file. Upload aborted");
			}
			bufferedReader.close();

			/**
			 * Store the file in Tomcat directory // Creating the directory to
			 * store file byte[] bytes = file.getBytes(); String rootPath =
			 * System.getProperty("catalina.home"); File dir = new File(rootPath
			 * + File.separator + "tmpFiles"); if (!dir.exists()) dir.mkdirs();
			 * 
			 * // Create the file on server File serverFile = new
			 * File(dir.getAbsolutePath() + File.separator + name);
			 * BufferedOutputStream stream = new BufferedOutputStream( new
			 * FileOutputStream(serverFile)); stream.write(bytes);
			 * stream.close(); return "You successfully uploaded file=" + name +
			 * " at location: " + serverFile.getAbsolutePath();
			 **/

		} catch (IOException e) {

			log.error("Data upload failed", e);
			return;
		}
	}



	public List<UserInfoEntity> generateUserData(MultipartFile file) throws IOException {

		InputStream inputStream = file.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

		String zeile = bufferedReader.readLine();
		int counter = 1;
		
		List<UserInfoEntity> currentUserInfoList = userService.getUserInfoList();
		List<UserInfoEntity> userInfoList = new LinkedList<UserInfoEntity>();
		while (null != (zeile = bufferedReader.readLine())) {
			counter++;
			zeile = zeile.replaceAll("Saknas", "");
			String[] split = zeile.split(";");
			if (split.length == 0){
				continue;
			}
			
			List<String> splitList = new LinkedList<String>(
					Arrays.asList(split));

			for (int i = 0; i < (22 - split.length); i++) {
				splitList.add(new String(""));
			}
			
			
			
			UserInfoEntity uie = new UserInfoEntity();

			String company = splitList.get(0);
			uie.setCompany(company);

			String orgNr = splitList.get(1);
			if (orgNr.isEmpty()) {
				log.warn("Entry row: " + counter
						+ " Organization ID missing. Upload aborted");
				continue;
			}
			
			uie.setOrgNr(orgNr);

			
			String customerId = splitList.get(2);
			if (customerId.isEmpty()) {
				log.warn("Entry row: " + counter
						+ " Customer ID missing. Upload aborted");
				continue;
			}
			
			boolean userExists = false;
			for(UserInfoEntity cuie : currentUserInfoList){
				if (cuie.getCustomerId().equals(customerId)){
					userExists = true;
					break;
				}
			}
			
			if(userExists){
				log.warn("Entry row: " + counter
						+ " User with customerID: " + customerId +" already exists. Upload aborted");
				continue;
			}
	
			uie.setCustomerId(customerId);
			
			
			UserEntity ue = new UserEntity();
			ue.setUsername(orgNr);
			String password = new PasswordGenerator().generatePswd(10,10,2,2,2);
			ue.setPassword(password);
			String token = DigestUtils.md5Hex(orgNr + ue.getPassword());
			ue.setToken(token);
			ue.setRole(Enums.UserRole.ROLE_CUSTOMER.name());
			userService.addUser(ue);
			
			
			uie.setUserIdFk(ue.getUserId());

			
			
			String typ = splitList.get(3);
			uie.setTyp(typ);

			String identityNr = splitList.get(4);
			uie.setIdentityNr(identityNr);

			String invoiceLab = splitList.get(5);
			uie.setInvoiceLab(invoiceLab);

			String facilityNr = splitList.get(6);
			uie.setFacilityNr(facilityNr);

			String contactPersNr = splitList.get(7);
			uie.setContactPersNr(contactPersNr);

			String email = splitList.get(8);
			uie.setEmail(email);

			String mainPoPox = splitList.get(9);
			uie.setMainPoPox(mainPoPox);

			String mainStreet = splitList.get(10);
			uie.setMainStreet(mainStreet);

			String mainZipcode = splitList.get(11);
			uie.setMainZipcode(mainZipcode);

			String mainCity = splitList.get(12);
			uie.setMainCity(mainCity);

			String mainName = splitList.get(13);
			uie.setMainName(mainName);

			String mainSurname = splitList.get(14);
			uie.setMainSurname(mainSurname);

			String billingPoPox = splitList.get(15);
			uie.setBillingPoPox(billingPoPox);

			String billingStreet = splitList.get(16);
			uie.setBillingStreet(billingStreet);

			String billingZipcode = splitList.get(17);
			uie.setBillingZipcode(billingZipcode);

			String billingCity = splitList.get(18);
			uie.setBillingCity(billingCity);

			String billingName = splitList.get(19);
			uie.setBillingName(billingName);

			String billingSurname = splitList.get(21);
			uie.setBillingSurname(billingSurname);


			userInfoList.add(uie);

		}

		userService.addUserInfo(userInfoList);
		return userInfoList;

	}

	private void generateContractData(MultipartFile file) throws IOException {
		List<UserInfoEntity> userInfoList = userService.getUserInfoList();

		InputStream inputStream = file.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

		String zeile = bufferedReader.readLine();
		int counter = 1;
		
		List<ContractInfoEntity> currentContractInfoList = contractService.getContractList();
		List<ContractInfoEntity> contractInfoList = new LinkedList<ContractInfoEntity>();

		while (null != (zeile = bufferedReader.readLine())) {
			counter++;
			zeile = zeile.replaceAll("Saknas", "");
			String[] split = zeile.split(";");

			List<String> splitList = new LinkedList<String>(
					Arrays.asList(split));

			for (int i = 0; i < (4 - split.length); i++) {
				splitList.add(new String(""));
			}

			ContractInfoEntity contractInfo = new ContractInfoEntity();

			String customerId = splitList.get(0);
			if (customerId.isEmpty()) {
				log.warn("Entry row: " + counter
						+ " Customer ID missing. Upload aborted");
				continue;
			}

			for (UserInfoEntity uie : userInfoList) {
				if (uie.getCustomerId().equals(customerId)) {
					contractInfo.setUserIdFk(uie.getUserIdFk());
				}
			}

			if (contractInfo.getUserIdFk() == null) {
				log.warn("Entry row: " + counter
						+ " No user for this Contract. Upload aborted");
				continue;
			}

			String contractId = splitList.get(1);
			
			boolean contractExist = false;
			for(ContractInfoEntity ccie : currentContractInfoList){
				if (ccie.getContractId().equals(contractId)){
					contractExist = true;
					break;
				}
			}
			
			if(contractExist){
				log.warn("Entry row: " + counter
						+ " Contract with contractID: " + contractId +" already exists. Upload aborted");
				continue;
			}
			
			contractInfo.setContractId(contractId);

			String tmp = splitList.get(2);
			Integer parkingLotCount = 0;
			if(StringUtilMethods.isNumeric(tmp)){
				parkingLotCount = Integer.valueOf(tmp);
			}
			
			contractInfo.setParkingLotCount(parkingLotCount);

			// hardcoded till csv Data Sheets will be extended
			contractInfo.setFacilityName("Globen");

			contractInfoList.add(contractInfo);

		}

		contractService.addContract(contractInfoList);
		bufferedReader.close();
		return;

	}

	private void generateVrmData(MultipartFile file) throws IOException {
		List<ContractInfoEntity> contractList = contractService.getContractList();
		List<VRMEntity> currentVrmList = vrmService.getVrmList();
		
		InputStream inputStream = file.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

		String zeile = bufferedReader.readLine();
		int counter = 1;

		while (null != (zeile = bufferedReader.readLine())) {
			counter++;
			zeile = zeile.replaceAll("Saknas", "");
			String[] split = zeile.split(";");

			List<String> splitList = new LinkedList<String>(
					Arrays.asList(split));

			for (int i = 0; i < (2 - split.length); i++) {
				splitList.add(new String(""));
			}

			String contractId = splitList.get(0);
			if (contractId.isEmpty()) {
				log.warn("Entry row: " + counter
						+ " Contract ID missing. Upload aborted");
				continue;
			}
			
			
			

			Integer userIdFk = null;
			Integer contractInfoIdFk = null;
			Integer parkingLotCount = null;
			
			
			for (ContractInfoEntity cie : contractList) {
				if (cie.getContractId().equals(contractId)) {
					userIdFk = cie.getUserIdFk();
					contractInfoIdFk = cie.getContractInfoId();
					parkingLotCount = cie.getParkingLotCount();
				}
			}
			

			if (userIdFk == null) {
				log.warn("Entry row: " + counter
						+ " No Contract. Upload aborted");
				continue;
			}
			
			boolean vrmExist = false;
			for(VRMEntity cvrm :  currentVrmList){
				if (cvrm.getContractInfoIdFk().equals(contractInfoIdFk)){
					vrmExist = true;
					break;
				}
			}
			if(vrmExist){
				log.warn("Entry row: " + counter
						+ " For Contract: " + contractId +" already VRMs attached. Upload aborted");
				continue;
			}
			

			
			String vrms = splitList.get(1);
			String[] vrmSplit = vrms.split("\\|");
			if(vrmSplit.length > (parkingLotCount * 5)){
				log.warn("Entry row: " + counter
						+ " There are " +  vrmSplit.length  + " VRM names in File. Maximum Allowed: " + (parkingLotCount * 5));
			}
			
			List<VRMEntity> vrmList = new LinkedList<VRMEntity>();
			for(int i = 0; i < (parkingLotCount * 5) && i < vrmSplit.length; i++){
				if(!vrmSplit[i].isEmpty()){
					VRMEntity vrm = new VRMEntity();
					vrm.setUserIdFk(userIdFk);
					vrm.setContractInfoIdFk(contractInfoIdFk);
					vrm.setVrmName(vrmSplit[i]);
					vrmList.add(vrm);
				}
				
			}

			vrmService.addVrm(vrmList);
		}

		
		bufferedReader.close();
		return;

	}

}
