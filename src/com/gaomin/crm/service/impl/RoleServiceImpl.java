package com.gaomin.crm.service.impl;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.Authority;
import com.gaomin.crm.mapper.RoleMapper;
import com.gaomin.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaomin.crm.entity.Role;
import com.gaomin.crm.model.Page;
import com.gaomin.crm.utils.ParamsUtil;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	@Transactional(readOnly=true)
	public List<Role> getAllRoles() {
		return roleMapper.getAllRoles();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Role> getPage(int pageNo, Map<String, Object> reqParams) {
		Map<String, Object> params = ParamsUtil
				.parserRequestParams2MyBatisParams(reqParams);

		int totalElements = roleMapper.getTotalElements(params);
		int pageSize = Page.NORMAL_PAGESIZE;
		Page<Role> page = new Page<Role>(pageNo, pageSize, totalElements);
		pageNo = page.getPageNo();
		int fromIndex = (pageNo - 1) * pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", pageSize);
		List<Role> content = roleMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	@Override
	@Transactional
	public void create(Role role) {
		roleMapper.create(role);
	}

	@Override
	@Transactional(readOnly=true)
	public Role getById(Long id) {
		return roleMapper.getById(id);
	}

	@Override
	@Transactional
	public void update(Role role) {
		roleMapper.update(role);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		roleMapper.delete(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Role getWithAuthById(Long id) {
		return roleMapper.getWithAuthById(id);
	}

	@Override
	@Transactional
	public void updateRelationship(Role role) {
		// 先删除旧的关联
		roleMapper.deleteOldRelationship(role.getId());
		// 更新关联
		if (role.getAuthorities().size() > 0) {
			roleMapper.updateRelationship(role);
		}
	}

	@Override
	public List<Authority> getParentAuthoritiesByUserId(Long userId) {
		return roleMapper.getParentAuthoritiesByUserId(userId);
	}
}
