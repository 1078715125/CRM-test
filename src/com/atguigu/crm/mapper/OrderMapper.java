package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Order;

public interface OrderMapper {

	int getTotalElements(Map<String, Object> params);

	List<Order> getContent(Map<String, Object> params);

	Order getWithDetailById(@Param("id") long orderId);

}
