package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.model.Page;

public interface UserService {

	User login(User user);

	List<User> getAllUser();

	Page<User> getPage(int pageNo, Map<String, Object> reqParams);

	void create(User user);

	User getById(Long id);

	void update(User user);

	void delete(Long id);

}
