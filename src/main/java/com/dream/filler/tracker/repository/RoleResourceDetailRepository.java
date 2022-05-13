package com.dream.filler.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dream.filler.tracker.entity.RoleResourceDetail;
import com.dream.filler.tracker.entity.RoleResourceId;

@Repository
@Transactional
public interface RoleResourceDetailRepository extends JpaRepository<RoleResourceDetail, RoleResourceId> {

	public RoleResourceDetail findByRoleResourceIdRoleRoleId(Long roleId);
	public RoleResourceDetail findByRoleResourceIdResourceResourceId(Long resourceId);

}
