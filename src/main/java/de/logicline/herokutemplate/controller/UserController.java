package de.logicline.herokutemplate.controller;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import de.logicline.herokutemplate.model.ContractInfoEntity;
import de.logicline.herokutemplate.model.UserEntity;
import de.logicline.herokutemplate.model.UserInfoEntity;
import de.logicline.herokutemplate.service.ContractService;
import de.logicline.herokutemplate.service.UserService;
import de.logicline.herokutemplate.utils.CreateUserWithContract;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ContractService contractService;

	@Autowired
	private ReCaptchaImpl reCaptcha;
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> userLogin(
			@RequestBody final UserEntity userEntity,
			HttpServletResponse response) {
		UserEntity userEntityTmp = userService.getUserByNameAndPassword(
				userEntity.getUsername(), userEntity.getPassword());
		if (userEntityTmp == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		Map<String, String> responseMap = new HashMap<String, String>();
		String token = userEntityTmp.getToken();
		responseMap.put("token", token);
		responseMap.put("userId", String.valueOf(userEntityTmp.getUserId()));
		responseMap.put("role", userEntityTmp.getRole());

		UserInfoEntity userInfoEntity = userService.getUserInfo(token);
		if (userInfoEntity != null) {
			responseMap.put("firstName", userInfoEntity.getMainName());
			responseMap.put("lastName", userInfoEntity.getMainSurname());
		}

		return responseMap;
	}
	
	@RequestMapping(value = "/user/clogin", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> userLoginWithCaptcha(
			@RequestBody Map <String, String> userInput,
			HttpServletRequest request, HttpServletResponse response) {
		
		String username =  userInput.get("username");
		String password = userInput.get("password");
		String challenge = userInput.get("recaptcha_challenge_field");
        String uresponse = userInput.get("recaptcha_response_field");
		
        
        ReCaptchaResponse reCaptchaResponse =
        		reCaptcha.checkAnswer(request.getRemoteAddr(),
                challenge, uresponse
            );
 
        if (reCaptchaResponse.isValid()) {
        	response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
        	return null;
        }
        
        
		UserEntity userEntityTmp = userService.getUserByNameAndPassword(
				username, password);
		if (userEntityTmp == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		Map<String, String> responseMap = new HashMap<String, String>();
		String token = userEntityTmp.getToken();
		responseMap.put("token", token);
		responseMap.put("userId", String.valueOf(userEntityTmp.getUserId()));
		responseMap.put("role", userEntityTmp.getRole());

		UserInfoEntity userInfoEntity = userService.getUserInfo(token);
		if (userInfoEntity != null) {
			responseMap.put("firstName", userInfoEntity.getMainName());
			responseMap.put("lastName", userInfoEntity.getMainSurname());
		}

		return responseMap;
	}
	
	

	@RequestMapping(value = "/user/edit/password/{userId}", method = RequestMethod.PUT)
	public void updateUserPassword(@PathVariable("userId") Integer userId,
			@RequestBody final Map<String, String> passwordMap,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO implement check for UserRole is admin
		String token = request.getHeader("token");

		userService.updatePassword(userId);
		
		String oldPassword = passwordMap.get("oldPassword");
		String newPassword = passwordMap.get("newPassword");

		return;
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public @ResponseBody UserInfoEntity getUserInfo(HttpServletRequest request,
			HttpServletResponse response) {

		String token = request.getHeader("token");
		UserInfoEntity userInfoEntity = userService.getUserInfo(token);
		if (userInfoEntity == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}
		return userInfoEntity;

	}

	@RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
	public @ResponseBody UserInfoEntity getUserInfoByUserId(
			@PathVariable("userId") Integer userId, HttpServletRequest request,
			HttpServletResponse response) {

		// TODO implement check for UserRole is admin
		String token = request.getHeader("token");

		UserInfoEntity userInfoEntity = userService.getUserInfoByUserId(userId);
		if (userInfoEntity == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}
		return userInfoEntity;
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public @ResponseBody void createUserInfoById(@RequestBody final CreateUserWithContract createUserWithContract,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO implement check for UserRole is admin
		String token = request.getHeader("token");
		
		UserInfoEntity userInfoEntity = createUserWithContract.getUserInfoEntity();
		
		Integer userIdFk = userService.createUser(userInfoEntity);
		ContractInfoEntity contractInfoEntity = createUserWithContract.getContractInfoEntity();
		contractInfoEntity.setUserIdFk(userIdFk);
		contractService.addContract(contractInfoEntity);
		
		return;
	}	
	
	@RequestMapping(value = "/user/edit", method = RequestMethod.PUT)
	public void updateUserInfo(
			@RequestBody final UserInfoEntity userInfoEntity,
			HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader("token");
		userService.updateUserInfo(token, userInfoEntity);
		return;
	}
	
	@RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.PUT)
	public void updateUserInfoById(@PathVariable("userId") Integer userId,
			@RequestBody final UserInfoEntity userInfoEntity,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO implement check for UserRole is admin
		String token = request.getHeader("token");
		userService.updateUserInfoByUserId(userId, userInfoEntity);
		return;
	}
	
	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	public @ResponseBody Map<Integer, String> getAllCustomer(
			HttpServletRequest request, HttpServletResponse response) {

		// TODO implement check for UserRole is admin
		String token = request.getHeader("token");

		Map<Integer, String> customerIdMap = userService
				.getAllCustomer();

		return customerIdMap;
	}

	@RequestMapping(value = "/user/search/{customerId}", method = RequestMethod.GET)
	public @ResponseBody Map<Integer, String> searchUserByCustomerId(
			@PathVariable("customerId") String customerId,
			HttpServletRequest request, HttpServletResponse response) {

		// TODO implement check for UserRole is admin
		String token = request.getHeader("token");

		Map<Integer, String> customerIdMap = userService
				.searchUserByCustomerId(customerId);

		return customerIdMap;
	}

	
	
	@RequestMapping("/captcha")
	public String showUploadPage(Map<String, Object> map) {
		return "captcha";
	}
	

	
	@RequestMapping(value = "/captchaLoginJsp", method = RequestMethod.POST)
	public @ResponseBody String userCaptchaLogin(ModelMap model,
            HttpServletRequest request) {
		String challenge = request.getParameter("recaptcha_challenge_field");
        String uresponse = request.getParameter("recaptcha_response_field");
        
        ReCaptchaResponse reCaptchaResponse =
        		reCaptcha.checkAnswer(request.getRemoteAddr(),
                challenge, uresponse
            );
 
        if (reCaptchaResponse.isValid()) {
        	return "succsess";
        } else {
        	return "failed";
        }      
		
	}
	
	/**
	 * @RequestMapping("/loginPageJsp") public String showLoginPage(Map<String,
	 *                                  Object> map) { return "loginUser"; }
	 * @RequestMapping(value = "/userInfoJsp", method = RequestMethod.POST)
	 *                       public String
	 *                       getUserInfoJsp(@RequestParam("username")String
	 *                       user, @RequestParam("password")String pass,
	 *                       Map<String, Object> map, HttpServletResponse
	 *                       response) { // Get User Info Object UserEntity
	 *                       userEntity =
	 *                       userService.getUserByNameAndPassword(user, pass);
	 *                       if (userEntity == null){
	 *                       response.setStatus(HttpServletResponse
	 *                       .SC_BAD_REQUEST ); return "loginUser"; }
	 *                       map.put("userEntity", userEntity);
	 * 
	 *                       UserInfoEntity userInfoEntity =
	 *                       userService.getUserInfo(userEntity.getUserId());
	 *                       map.put("userInfoEntity", userInfoEntity);
	 * 
	 *                       return "editUserInfo"; }
	 * @RequestMapping("/updateUserInfoJsp") public String
	 *                                       updateUserInfo(UserInfoEntity
	 *                                       userInfoEntity, BindingResult
	 *                                       bindingReult) {
	 * 
	 * 
	 *                                       if(bindingReult.hasErrors()){
	 *                                       return Error }
	 * 
	 * 
	 *                                       userService.updateUserInfo(
	 *                                       userInfoEntity); return
	 *                                       "editUserInfo"; }
	 * @RequestMapping("/editUserJsp") public String listUser(Map<String,
	 *                                 Object> map) {
	 * 
	 *                                 map.put("user", new UserEntity()); //
	 *                                 map.put("peopleList",
	 *                                 personService.listPeople());
	 * 
	 *                                 return "editUser"; }
	 * @RequestMapping(value = "/saveUserJsp", method = RequestMethod.POST)
	 *                       public String addUser(@ModelAttribute("userEntity")
	 *                       UserEntity userEntity, BindingResult result) {
	 * 
	 *                       userEntity.setRole("ROLE_CUSTOMER");
	 *                       userService.addUser(userEntity);
	 * 
	 *                       return "redirect:/editUserJsp"; }
	 **/
}
