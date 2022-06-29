package com.dream.filler.tracker.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.auth.policy.Principal;
import com.dream.filler.tracker.DTO.CurrentUser;
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

	public Boolean addVehicle(VehicleInfo vehicleInfo, Long userId) throws TruckException {

		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);

		if (userInfo.get() == null) {
			new UserException("User Not Found");
		}

		log.info(vehicleInfo.toString());

		if (vehicleInfo.getTruckNumber() == null || vehicleInfo.getTruckNumber() == " ") {
			throw new TruckException("Not able to find vehicle number.");
		}

		Double grossAmount = vehicleInfo.getRate() * vehicleInfo.getGrossWeight();
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

	public List<VehicleInfo> getVehicle() {

		List<VehicleInfo> vehicleInfo = truckInforRepository.findAll();

		return vehicleInfo;

	}

	public List<VehicleInfo> getVehiclebytrucknumber(String truckno) {

		List<VehicleInfo> vehicleInfo = truckInforRepository.findByTruckNumber(truckno);

		return vehicleInfo;

	}

	public VehicleInfo getVehiclebytruckId(Long truckId) {

		VehicleInfo vehicleInfo = truckInforRepository.findByTruckId(truckId);
		if (vehicleInfo==null) {
			new UserException("truck Not Found");
		}

		return vehicleInfo;

	}

	public List<VehicleInfo> getVehiclebyemailId(String createdBy) {

		List<VehicleInfo> vehicleInfo = truckInforRepository.findByCreatedBy(createdBy);

		return vehicleInfo;

	}

	public List<VehicleInfo> getVehiclebyTruckNo(String truckNo) {

		List<VehicleInfo> vehicleInfo = truckInforRepository.findByTruckNumber(truckNo);

		return vehicleInfo;

	}


	

	public VehicleInfo updatevechile(VehicleInfo vehicleInfo, Long userId, Long truckId) throws TruckException {
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);

		if (userInfo.get() == null) {
			new UserException("User Not Found");
		}
		VehicleInfo editvechile = truckInforRepository.findByTruckId(truckId);
		log.info(vehicleInfo.toString());
		if (vehicleInfo.getTruckNumber() == null || vehicleInfo.getTruckNumber() == " ") {
			throw new TruckException("Not able to find vehicle number.");
		}
		editvechile.setTruckNumber(vehicleInfo.getTruckNumber());
		editvechile.setEntryDate(vehicleInfo.getEntryDate());
		editvechile.setTokenNumber(vehicleInfo.getTokenNumber());
		editvechile.setRate(vehicleInfo.getRate());

		Double grossAmount = vehicleInfo.getRate() * vehicleInfo.getGrossWeight();
		Double balanceFrieght = grossAmount - vehicleInfo.getAdvanceCash() - vehicleInfo.getAdvanceDiesel();
		editvechile.setGrossAmount(grossAmount);
		editvechile.setBalanceFrieght(balanceFrieght);
		editvechile.setAdvanceCash(vehicleInfo.getAdvanceCash());
		editvechile.setAdvanceDiesel(vehicleInfo.getAdvanceDiesel());
		editvechile.setGrossWeight(vehicleInfo.getGrossWeight());
		editvechile.setNetWeight(vehicleInfo.getNetAmount());
		editvechile.setGrNumber(vehicleInfo.getGrNumber());

		Date date = new Date();

		editvechile.setUpdatedBy(userInfo.get().getEmailId());
		editvechile.setUpdatedOn(date);

		return truckInforRepository.save(editvechile);
	}

	public boolean deletebyId(Long truckId) throws TruckException {

		VehicleInfo deleteV = truckInforRepository.findByTruckId(truckId);

		if (truckInforRepository.findByTruckId(truckId) == null) {
			throw new TruckException("truck id is invalid");
		}
		truckInforRepository.deleteById(truckId);
		return true;

	}

}
