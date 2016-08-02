package com.atguigu.crm.service;

import java.util.Map;

import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.model.Page;

public interface CustomerServiceService {

	void create(CustomerService customerService);

	Page<CustomerService> getAllotPage(int pageNo, Map<String, Object> reqParams);

	void allot(CustomerService customerService);

	void delete(Long id);

	void deal(CustomerService customerService);

	CustomerService getById(Long id);

	void feedback(CustomerService customerService);

}
