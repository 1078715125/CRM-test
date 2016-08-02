package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.CustomerDrain;

public interface CustomerDrainMapper {

	@Update("{call check_drain}")
	void checkDrain();

	int getTotalElements(Map<String, Object> params);

	List<CustomerDrain> getContent(Map<String, Object> params);

	CustomerDrain getById(@Param("id") long id);

	void updateDelay(CustomerDrain drain);

	void drain(CustomerDrain drain);

}
