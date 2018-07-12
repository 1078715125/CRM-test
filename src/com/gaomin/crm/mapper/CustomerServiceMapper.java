package com.gaomin.crm.mapper;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.CustomerService;
import org.apache.ibatis.annotations.Param;

public interface CustomerServiceMapper {

	void create(CustomerService customerService);

	int getTotalElements(Map<String, Object> params);

	List<CustomerService> getContent(Map<String, Object> params);

	void update(CustomerService customerService);

	void delete(@Param("id") Long id);

	CustomerService getById(@Param("id") Long id);

}
