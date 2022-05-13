package com.dream.filler.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dream.filler.tracker.entity.UserInfo;

@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	
	public UserInfo findByEmailId(String emailId);
	public UserInfo findByEmailIdAndStatus(String emailId, String status);

}
