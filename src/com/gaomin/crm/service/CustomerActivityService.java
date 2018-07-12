package com.gaomin.crm.service;

import java.util.Map;

import com.gaomin.crm.entity.CustomerActivity;
import com.gaomin.crm.model.Page;

public interface CustomerActivityService {

	Page<CustomerActivity> getPageByCustId(int pageNo, Map<String, Object> reqParam);

	void create(CustomerActivity activity);

	CustomerActivity getById(long activityId);

	void update(CustomerActivity activity);

	void delete(long activityId);

}
