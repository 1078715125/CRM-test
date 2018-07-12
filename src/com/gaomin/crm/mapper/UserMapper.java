package com.gaomin.crm.mapper;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

	User getByName(@Param("name") String name);

	List<User> getAllUser();

	int getTotalElements(Map<String, Object> params);

	List<User> getContent(Map<String, Object> params);

	void create(User user);

	User getById(@Param("id") Long id);

	void update(User user);

	void delete(Long id);

}
