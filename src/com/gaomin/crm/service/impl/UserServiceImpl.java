package com.gaomin.crm.service.impl;

import com.gaomin.crm.entity.User;
import com.gaomin.crm.mapper.UserMapper;
import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.UserService;
import com.gaomin.crm.utils.ParamsUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
		int pageSize = Page.NORMAL_PAGESIZE;
		Page<User> page = new Page<User>(pageNo, pageSize, totalElements);
		pageNo = page.getPageNo();

		int fromIndex = (pageNo - 1) * pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", pageSize);
		
		List<User> content =  userMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	@Override
	public void create(User user) {
		// 盐值加密密码
        String salt  = UUID.randomUUID().toString().replaceAll("-","");
        user.setSalt(salt);
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), salt, 1024);
        user.setPassword(simpleHash.toString());
		userMapper.create(user);
	}

	@Override
	public User getById(Long id) {
		return userMapper.getById(id);
	}

	@Override
	public void update(User user) {
        // 盐值加密密码
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), user.getSalt(), 1024);
        user.setPassword(simpleHash.toString());
		userMapper.update(user);
	}

	@Override
	public void delete(Long id) {
		userMapper.delete(id);
	}

}
