package com.dream.filler.tracker.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dream.filler.tracker.DTO.CurrentUser;
import com.dream.filler.tracker.entity.Resources;
import com.dream.filler.tracker.entity.Role;
import com.dream.filler.tracker.entity.RoleResourceDetail;
import com.dream.filler.tracker.entity.UserInfo;
import com.dream.filler.tracker.exception.ResourceException;
import com.dream.filler.tracker.exception.RoleException;
import com.dream.filler.tracker.repository.ResourcesRespository;
import com.dream.filler.tracker.repository.RoleRepository;
import com.dream.filler.tracker.repository.RoleResourceDetailRepository;
import com.dream.filler.tracker.repository.UserInfoRepository;
import com.dream.filler.tracker.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ResourcesRespository resourcesRespository;
	
	@Autowired
	private RoleResourceDetailRepository roleResourceDetailRepository;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Override
	public CurrentUser loadUserByUsername(String emailId) throws UsernameNotFoundException{
		
		UserInfo userInfo = userInfoRepository.findByEmailIdAndStatus(emailId, Constants.ACTIVE);
		String requestedURI = httpServletRequest.getRequestURI();
		if(requestedURI.contains("swagger") || requestedURI.contains("api-docs")) {
		} 
		return new CurrentUser(userInfo);
	}


	public List<RoleResourceDetail> loadRoleResourcesDetails() throws RoleException{
		List<RoleResourceDetail> roleResourceDetails = roleResourceDetailRepository.findAll();
		if(roleResourceDetails == null || roleResourceDetails.isEmpty()) {
			log.error(Constants.ROLE_RESOURCE_MAPPING_NOT_FOUND);
			throw new RoleException(Constants.ROLE_RESOURCE_MAPPING_NOT_FOUND);
		}
		return roleResourceDetails;
	}

	public List<Role> loadRoles() throws RoleException{
		List<Role> roles = roleRepository.findAll();
		if(roles == null || roles.isEmpty()) {
			log.error(Constants.ROLE_NOT_FOUND);
			throw new RoleException(Constants.ROLE_NOT_FOUND);
		}
		return roles;

	}
	
	public List<Resources> loadResources() throws ResourceException{
		List<Resources> resources = resourcesRespository.findAll();
		if(resources == null || resources.isEmpty()) {
			String errorMsg = "No Resources found.";
			log.error(errorMsg);
			throw new ResourceException(errorMsg);
		}
		return resources;
	}

}
