package com.gaomin.crm.service;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.Authority;
import com.gaomin.crm.entity.Role;
import com.gaomin.crm.model.Page;

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
