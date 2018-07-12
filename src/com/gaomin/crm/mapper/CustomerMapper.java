package com.gaomin.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gaomin.crm.entity.Customer;

public interface CustomerMapper {

	void saveCustForFinish(Customer customer);

	int getTotalElements(Map<String, Object> params);

	List<Customer> getContent(Map<String, Object> params);

	Customer getCustById(@Param("id") long custId);

	void update(Customer customer);

	void updateStatus(@Param("id") long id, @Param("state") String state);

	List<Customer> getAllCustomers();

}
