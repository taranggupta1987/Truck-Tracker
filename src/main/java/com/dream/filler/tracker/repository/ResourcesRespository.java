package com.dream.filler.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dream.filler.tracker.entity.Resources;

@Repository
@Transactional
public interface ResourcesRespository extends JpaRepository<Resources, Long> {
	
	public Resources findByResource(String resource);

}
