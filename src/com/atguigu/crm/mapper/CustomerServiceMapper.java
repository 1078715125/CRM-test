package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.CustomerService;

public interface CustomerServiceMapper {

	void create(CustomerService customerService);

	int getTotalElements(Map<String, Object> params);

	List<CustomerService> getContent(Map<String, Object> params);

	void update(CustomerService customerService);

	void delete(@Param("id") Long id);

	CustomerService getById(@Param("id") Long id);

}
