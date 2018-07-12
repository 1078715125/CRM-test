package com.gaomin.crm.mapper;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.Authority;
import org.apache.ibatis.annotations.Param;

import com.gaomin.crm.entity.Role;

public interface RoleMapper {

	List<Role> getAllRoles();

	int getTotalElements(Map<String, Object> params);

	List<Role> getContent(Map<String, Object> params);

	void create(Role role);

	Role getById(@Param("id") Long id);

	void update(Role role);

	void delete(@Param("id") Long id);

	Role getWithAuthById(Long id);

	void deleteOldRelationship(@Param("id") Long id);

	void updateRelationship(Role role);

	List<Authority> getParentAuthoritiesByUserId(@Param("id") Long userId);

}
