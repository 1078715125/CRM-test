package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.model.Page;

public interface RoleService {

	List<Role> getAllRoles();

	Page<Role> getPage(int pageNo, Map<String, Object> reqParams);

	void create(Role role);

	Role getById(Long id);

	void update(Role role);

	void delete(Long id);

	Role getWithAuthById(Long id);

	void updateRelationship(Role role);

	List<Authority> getParentAuthoritiesByUserId(Long userId);

}
