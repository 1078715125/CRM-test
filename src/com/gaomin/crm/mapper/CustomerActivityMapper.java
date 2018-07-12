package com.gaomin.crm.mapper;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.CustomerActivity;
import org.apache.ibatis.annotations.Param;

public interface CustomerActivityMapper {

	int getTotalElements(Map<String, Object> params);

	List<CustomerActivity> getContent(Map<String, Object> params);

	void create(CustomerActivity activity);

	CustomerActivity getById(@Param("id") long activityId);

	void update(CustomerActivity activity);

	void delete(@Param("id") long activityId);

}
