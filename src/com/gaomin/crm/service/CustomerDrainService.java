package com.gaomin.crm.service;

import java.util.Map;

import com.gaomin.crm.entity.CustomerDrain;
import com.gaomin.crm.model.Page;

public interface CustomerDrainService {

	void callCustomerDrainCheckProcecure();
		
	Page<CustomerDrain> getPage(int pageNo, Map<String, Object> reqParams);

	CustomerDrain getById(long id);

	void updateDelay(CustomerDrain drain);

	void confirmDrain(CustomerDrain drain);

}
