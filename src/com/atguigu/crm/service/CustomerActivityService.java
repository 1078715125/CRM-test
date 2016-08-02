package com.atguigu.crm.service;

import java.util.Map;

import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.model.Page;

public interface CustomerActivityService {

	Page<CustomerActivity> getPageByCustId(int pageNo, Map<String, Object> reqParam);

	void create(CustomerActivity activity);

	CustomerActivity getById(long activityId);

	void update(CustomerActivity activity);

	void delete(long activityId);

}
