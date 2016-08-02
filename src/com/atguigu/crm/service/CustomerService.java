package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.model.Page;

public interface CustomerService {

	Page<Customer> getPage(int pageNo, Map<String, Object> reqParams);

	Customer getCustById(long custId);

	void update(Customer customer);

	void updateStatus(long id, String status);

	List<Customer> getAllCustomers();

}
