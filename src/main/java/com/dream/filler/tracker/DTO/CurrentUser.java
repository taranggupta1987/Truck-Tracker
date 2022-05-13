package com.dream.filler.tracker.DTO;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dream.filler.tracker.entity.UserInfo;

public class CurrentUser implements UserDetails {
	private static final long serialVersionUID = 250971346474953251L;
	
	private UserInfo userInfo;
	
	public CurrentUser(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+userInfo.getRole().getRole()));
	}

	@Override
	public String getPassword() {
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		return userInfo.getEmailId();
	}
	
	public String getUserFristName() {
		return userInfo.getFirstName();
	}
	
	public String getUserEmailId() {
		return userInfo.getEmailId();
	}
	
	public String getUserLastName() {
		return userInfo.getLastName();
	}
	
	public Long getUserId() {
		return userInfo.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
