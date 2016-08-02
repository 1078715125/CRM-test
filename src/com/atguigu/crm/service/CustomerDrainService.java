package com.atguigu.crm.service;

import java.util.Map;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.model.Page;

public interface CustomerDrainService {

	void callCustomerDrainCheckProcecure();
		
	Page<CustomerDrain> getPage(int pageNo, Map<String, Object> reqParams);

	CustomerDrain getById(long id);

	void updateDelay(CustomerDrain drain);

	void confirmDrain(CustomerDrain drain);

}
