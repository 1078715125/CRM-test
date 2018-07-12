package com.gaomin.crm.service.impl;

import java.util.List;

import com.gaomin.crm.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaomin.crm.mapper.AuthorityMapper;
import com.gaomin.crm.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityMapper authorityMapper;

	@Override
	public List<Authority> getParentAuthorities() {
		return authorityMapper.getParentAuthorities();
	}
}
 