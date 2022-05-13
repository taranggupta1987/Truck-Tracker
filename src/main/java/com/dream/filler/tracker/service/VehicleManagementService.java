package com.dream.filler.tracker.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.policy.Principal;
import com.dream.filler.tracker.entity.UserInfo;
import com.dream.filler.tracker.entity.VehicleInfo;
import com.dream.filler.tracker.exception.TruckException;
import com.dream.filler.tracker.exception.UserException;
import com.dream.filler.tracker.repository.UserInfoRepository;
import com.dream.filler.tracker.repository.VehicleInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VehicleManagementService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private VehicleInfoRepository truckInforRepository;
	
	public Boolean addVehicle(VehicleInfo vehicleInfo, Long userId) {

		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
		
		if(userInfo.get() == null) {
			new UserException("User Not Found");
		}
		
		log.info(vehicleInfo.toString());
		if(vehicleInfo.getTruckNumber() == null || vehicleInfo.getTruckNumber() == "") {
			new TruckException("Not able to find vehicle number.");
		}
		
		Double grossAmount = vehicleInfo.getRate()*vehicleInfo.getGrossWeight();
		Double balanceFrieght = grossAmount - vehicleInfo.getAdvanceCash() - vehicleInfo.getAdvanceDiesel();
		
		vehicleInfo.setGrossAmount(grossAmount);
		vehicleInfo.setBalanceFrieght(balanceFrieght);
		
		Date date = new Date();
		vehicleInfo.setCreatedBy(userInfo.get().getEmailId());
		vehicleInfo.setCreatedOn(date);
		vehicleInfo.setUpdatedBy(userInfo.get().getEmailId());
		vehicleInfo.setUpdatedOn(date);
		
		truckInforRepository.save(vehicleInfo);
		
		return true;
	}
	
	public List<VehicleInfo> getVehicle(){
		
		List<VehicleInfo> vehicleInfo = truckInforRepository.findAll();
		
		return vehicleInfo;
		
	}

	
	
}
