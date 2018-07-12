package com.gaomin.crm.service;

import java.util.Map;

import com.gaomin.crm.entity.CustomerService;
import com.gaomin.crm.model.Page;

public interface CustomerServiceService {

	void create(com.gaomin.crm.entity.CustomerService customerService);

	Page<com.gaomin.crm.entity.CustomerService> getAllotPage(int pageNo, Map<String, Object> reqParams);

	void allot(com.gaomin.crm.entity.CustomerService customerService);

	void delete(Long id);

	void deal(com.gaomin.crm.entity.CustomerService customerService);

	com.gaomin.crm.entity.CustomerService getById(Long id);

	void feedback(CustomerService customerService);

}
