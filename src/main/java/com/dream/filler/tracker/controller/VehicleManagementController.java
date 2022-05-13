package com.dream.filler.tracker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.filler.tracker.DTO.CurrentUser;
import com.dream.filler.tracker.entity.UserInfo;
import com.dream.filler.tracker.entity.VehicleInfo;
import com.dream.filler.tracker.repository.UserInfoRepository;
import com.dream.filler.tracker.service.VehicleManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value="Truck Management", tags= "Truck Management", description="Operations pertaining to manage truck.")
@RestController
@Slf4j
@RequestMapping("/api/v1/tracker/truck")
public class VehicleManagementController {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private VehicleManagementService vehicleManagementService;
	
	@ApiOperation(value = "Add Truck Info",response = UserInfo.class)
	@PostMapping(value = "/user/addVehicleInfo" , produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> addVehicleInfo(@RequestBody VehicleInfo truckInfo, OAuth2Authentication oauthPrincipal){
			try {
				long userId = ((CurrentUser)oauthPrincipal.getPrincipal()).getUserId();
				
				return new ResponseEntity<>(vehicleManagementService.addVehicle(truckInfo, userId), HttpStatus.CREATED);
			} catch (Exception e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
			}
	}
	
	@ApiOperation(value = "Get Truck Info",response = UserInfo.class)
	@GetMapping(value = "/admin/getVehicleInfo" , produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> getVehicleInfo(){
			try {
					return new ResponseEntity<>(vehicleManagementService.getVehicle(), HttpStatus.OK);
			} catch (Exception e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
			}
	}
	
	
	
	
	
}
