package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.CustomerActivity;

public interface CustomerActivityMapper {

	int getTotalElements(Map<String, Object> params);

	List<CustomerActivity> getContent(Map<String, Object> params);

	void create(CustomerActivity activity);

	CustomerActivity getById(@Param("id") long activityId);

	void update(CustomerActivity activity);

	void delete(@Param("id") long activityId);

}
