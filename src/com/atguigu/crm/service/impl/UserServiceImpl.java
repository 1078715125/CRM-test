package com.atguigu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.UserService;
import com.atguigu.crm.utils.ParamsUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(User user) {
		String name = user.getName();
		String password = user.getPassword();
		user = userMapper.getByName(name);

		if (user != null && user.getEnabled() == 1
				&& user.getPassword().equals(password)) {
			return user;
		}

		return null;
	}

	@Override
	public List<User> getAllUser() {

		return userMapper.getAllUser();
	}

	@Override
	public Page<User> getPage(int pageNo, Map<String, Object> reqParams) {
		Map<String, Object> params = ParamsUtil
				.parserRequestParams2MyBatisParams(reqParams);

		int totalElements = userMapper.getTotalElements(params);
		int pageSize = 3;
		Page<User> page = new Page<User>(pageNo, pageSize, totalElements);
		pageNo = page.getPageNo();

		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<User> content =  userMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	@Override
	public void create(User user) {
		userMapper.create(user);
	}

	@Override
	public User getById(Long id) {
		return userMapper.getById(id);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public void delete(Long id) {
		userMapper.delete(id);
	}

}
