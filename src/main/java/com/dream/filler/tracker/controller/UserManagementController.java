package com.dream.filler.tracker.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.filler.tracker.DTO.CurrentUser;
import com.dream.filler.tracker.entity.VehicleInfo;
import com.dream.filler.tracker.entity.UserInfo;
import com.dream.filler.tracker.exception.RoleException;
import com.dream.filler.tracker.exception.UserException;
import com.dream.filler.tracker.service.UserManagementService;
import com.google.gson.JsonParseException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value="User Management", tags= "User Management", description="Operations pertaining to manage user onboarding and login.")
@RestController
@Slf4j
@RequestMapping("/api/v1/tracker")
public class UserManagementController {
	
	@Autowired
	private UserManagementService userManagementService;
	
	@ApiOperation(value = "User authentication",response = UserInfo.class)
	@PostMapping(value = "/user/login" , produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> authenticate(OAuth2Authentication principal, @RequestBody String token, @RequestHeader String loginType){
		try {
			CurrentUser currentUser = (CurrentUser)principal.getPrincipal();
			return new ResponseEntity<>(userManagementService.storeUserAppDeviceToken(token.replaceAll("\"", ""),currentUser.getUserId(), loginType),HttpStatus.OK) ;
		} catch (UserException e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "User registration",response = UserInfo.class)
	@PostMapping(value = "/register" , produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> register(@RequestBody UserInfo userInfo){
			try {
					return new ResponseEntity<>(userManagementService.registerUserByRegistrationType(userInfo),HttpStatus.CREATED);
			} catch (RoleException | UserException | MessagingException | JsonParseException | IOException e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
			}
		
	}
	
}
