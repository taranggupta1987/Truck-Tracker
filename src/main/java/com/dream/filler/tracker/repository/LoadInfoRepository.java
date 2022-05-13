package com.dream.filler.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dream.filler.tracker.entity.LoadInfo;

@Repository
@Transactional
public interface LoadInfoRepository extends JpaRepository<LoadInfo, Long> {
	

}
