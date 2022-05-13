package com.dream.filler.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dream.filler.tracker.entity.UserPasscode;

@Repository
@Transactional
public interface UserPasscodeRepository extends JpaRepository<UserPasscode, String> {

}
