package com.atguigu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.mapper.AuthorityMapper;
import com.atguigu.crm.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityMapper authorityMapper;

	@Override
	public List<Authority> getParentAuthorities() {
		return authorityMapper.getParentAuthorities();
	}
}
 