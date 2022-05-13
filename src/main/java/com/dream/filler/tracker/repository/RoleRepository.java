package com.dream.filler.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dream.filler.tracker.entity.Role;


@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findByRole(String role);
	public void deleteByRole(String role);

}
