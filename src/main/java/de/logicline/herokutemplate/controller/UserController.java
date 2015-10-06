package de.logicline.herokutemplate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.logicline.herokutemplate.dto.UserInfoEntity;
import de.logicline.herokutemplate.model.ContactEntity;
import de.logicline.herokutemplate.model.UserEntity;
import de.logicline.herokutemplate.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

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

		ContactEntity contactEntity = userService.getContact(token);
		if (contactEntity != null) {
			responseMap.put("firstName", contactEntity.getFirstName());
			responseMap.put("lastName", contactEntity.getLastName());
		}

		return responseMap;
	}

	@RequestMapping(value = "/user/clogin", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> userLoginWithCaptcha(
			@RequestBody Map<String, String> userInput,
			HttpServletRequest request, HttpServletResponse response) {

		String username = userInput.get("username");
		String password = userInput.get("password");
		String challenge = userInput.get("recaptcha_challenge_field");
		String uresponse = userInput.get("recaptcha_response_field");

		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(
				request.getRemoteAddr(), challenge, uresponse);

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

		// TODO merge with normal login
		Map<String, String> responseMap = new HashMap<String, String>();
		String token = userEntityTmp.getToken();
		responseMap.put("token", token);
		responseMap.put("userId", String.valueOf(userEntityTmp.getUserId()));
		responseMap.put("role", userEntityTmp.getRole());

		ContactEntity contactEntity = userService.getContact(token);
		if (contactEntity != null) {
			responseMap.put("firstName", contactEntity.getFirstName());
			responseMap.put("lastName", contactEntity.getLastName());
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
	public @ResponseBody UserInfoEntity getContact(HttpServletRequest request,
			HttpServletResponse response) {

		String token = request.getHeader("token");
		ContactEntity contactEntity = userService.getContact(token);
		if (contactEntity == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}

		// TODO replace userInfoEntity
		return contactEntity.toDto();

	}

	@RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
	public @ResponseBody UserInfoEntity getUserInfoByUserId(
			@PathVariable("userId") Integer userId, HttpServletRequest request,
			HttpServletResponse response) {

		// TODO implement check for UserRole is admin
		String token = request.getHeader("token");

		ContactEntity contactEntity = userService.getContactByUserId(userId);
		if (contactEntity == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}

		return contactEntity.toDto();
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public @ResponseBody void createUserInfoById(
			@RequestBody final UserInfoEntity userInfoEntity,
			HttpServletRequest request, HttpServletResponse response) {

		Integer userIdFk = userService.createUser(userInfoEntity);

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

		Map<Integer, String> customerIdMap = userService.getCustomerIdMap();

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

}
