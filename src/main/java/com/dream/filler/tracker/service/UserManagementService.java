package com.dream.filler.tracker.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.dream.filler.tracker.entity.Role;
import com.dream.filler.tracker.entity.UserInfo;
import com.dream.filler.tracker.entity.UserPasscode;
import com.dream.filler.tracker.exception.RoleException;
import com.dream.filler.tracker.exception.UserException;
import com.dream.filler.tracker.repository.RoleRepository;
import com.dream.filler.tracker.repository.UserInfoRepository;
import com.dream.filler.tracker.repository.UserPasscodeRepository;
import com.dream.filler.tracker.util.Constants;
import com.dream.filler.tracker.util.TrackerUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import antlr.collections.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserManagementService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserPasscodeRepository userPasscodeRepository;

	public UserInfo registerUserByRegistrationType(UserInfo userInfo) throws RoleException, UserException,
	MessagingException, JsonParseException, JsonMappingException, IOException {
		String role=userInfo.getRegistrationType();
		
		if (null == role) {
			throw new RoleException("please select appropriate role");
		}
		
		Role checkRole=roleRepository.findByRole(role);

		if (checkRole == null) {
			throw new RoleException(Constants.ROLE_NOT_FOUND);
		}else {
			userInfo.setRole(roleRepository.findByRole(role));
			return this.registerUser(userInfo);
		}

	}
	public UserInfo registerUser(@Valid UserInfo userInfo) throws RoleException, UserException, MessagingException,
			com.fasterxml.jackson.core.JsonParseException, JsonMappingException, IOException { 
		String userEmailId = userInfo.getEmailId();

		String passcode = "";

		String fAndlName = TrackerUtils.firstLetterCapital(userInfo.getFirstName()) + " "
				+ TrackerUtils.firstLetterCapital(userInfo.getLastName());

		UserInfo userInfoDB = userInfoRepository.findByEmailId(userEmailId);
		if (null != userInfoDB && userInfoDB.getStatus().equalsIgnoreCase(Constants.ACTIVE)) {
			throw new UserException(Constants.USER_ALREADY_REGISTERED);
		}

//		if (null != userInfoDB && userInfoDB.getStatus().equalsIgnoreCase(Constants.INACTIVE)) {
//
//			userInfoDB.setStatus(Constants.INACTIVE);
//			userInfoDB.setRole(role);
//			String password = passwordEncoder.encode(userInfo.getPassword());
//			userInfoDB.setPassword(password);
//			Date createdOn = new Date();
//			if (userInfo.getTncAccepted() != null && userInfo.getTncAccepted()) {
//				userInfoDB.setTncAccepted(userInfo.getTncAccepted());
//				userInfoDB.setTncTimestamp(createdOn);
//			} else {
//				throw new UserException(Constants.TERMS_AND_CONDITIONS_NOT_ACCEPTED);
//			}
//			userInfoDB.setCreatedOn(createdOn);
//			userInfoDB.setCreatedBy(userEmailId);
//			userInfoDB.setUpdatedOn(createdOn);
//			userInfoDB.setUpdatedBy(userEmailId);
//
//			userInfoDB.setFirstName(userInfo.getFirstName());
//			userInfoDB.setLastName(userInfo.getLastName());
//			userInfoDB.setEmailId(userInfo.getEmailId());
//
//			userInfo = userInfoRepository.save(userInfoDB);
//			log.info(Constants.USER_REGISTERED_SUCCESSFULLY);
//
//			UserPasscode userPasscode = new UserPasscode();
//			Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.MINUTE, 30);
//			Random random = new Random();
//			passcode = String.format("%04d", random.nextInt(10000));
//			userPasscode.setExpiresOn(calendar.getTime());
//			userPasscode.setPasscode(passcode);
//			userPasscode.setUserId(userEmailId);
//			userPasscodeRepository.save(userPasscode);
//			log.info(Constants.USER_PASSCODE_FOR_USER_REGISTRATION_SAVED_SUCCESSFULLY);
//		} else {

		userInfo.setStatus(Constants.ACTIVE);

		String password = passwordEncoder.encode(userInfo.getPassword());
		userInfo.setPassword(password);
		Date createdOn = new Date();

		if (userInfo.getTncAccepted() != null && userInfo.getTncAccepted()) {
			userInfo.setTncTimestamp(createdOn);
		} else {
			throw new UserException(Constants.TERMS_AND_CONDITIONS_NOT_ACCEPTED);
		}
		userInfo.setCreatedOn(createdOn);
		userInfo.setCreatedBy(userEmailId);
		userInfo.setUpdatedOn(createdOn);
		userInfo.setUpdatedBy(userEmailId);

		userInfo = userInfoRepository.save(userInfo);
		log.info(Constants.USER_REGISTERED_SUCCESSFULLY);

//			UserPasscode userPasscode = new UserPasscode();
//			Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.MINUTE, 30);
//			Random random = new Random();
//			passcode = String.format("%04d", random.nextInt(10000));
//			userPasscode.setExpiresOn(calendar.getTime());
//			userPasscode.setPasscode(passcode);
//			userPasscode.setUserId(userEmailId);
//			userPasscodeRepository.save(userPasscode);
//			log.info(Constants.USER_PASSCODE_FOR_USER_REGISTRATION_SAVED_SUCCESSFULLY);

//		}

		return userInfo;
	}

	public UserInfo storeUserAppDeviceToken(String token, long userId, String loginType) throws UserException {
		Optional<UserInfo> optional = userInfoRepository.findById(userId);
		if (!optional.isPresent()) {
			throw new UserException(Constants.USER_NOT_FOUND);
		}
		if (isUserInactive(optional.get().getEmailId())) {
			throw new UserException(Constants.USER_INACTIVE);
		}
//		if (optional != null && !optional.get().getRegistrationType().equals(loginType)
//				&& !loginType.equals(Constants.LOGINTYPE_ALEXA) && !loginType.equals(Constants.LOGINTYPE_GOOGLE)) {
//			throw new UserException(Constants.INVALID_LOGIN_TYPE);
//		}
		UserInfo userInfo = optional.get();
		userInfo.setAppDeviceToken(token);

		String data[] = token.split(",");
		if (data.length == 3) {
			userInfo.setAppDeviceToken(data[0]);
			userInfo.setDob(data[1]);
			userInfo.setGender(data[2]);
		}
		userInfo = userInfoRepository.save(userInfo);
		log.info(Constants.USER_APP_TOKEN_UPDATED_SUCCESSFULLY);
		return userInfo;
	}

	private boolean isUserInactive(String userEmailId) {
		UserInfo userExists = userInfoRepository.findByEmailIdAndStatus(userEmailId, Constants.INACTIVE);
		return (userExists == null ? false : true);
	}

}
