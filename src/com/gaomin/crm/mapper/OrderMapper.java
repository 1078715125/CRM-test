package com.gaomin.crm.mapper;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

	int getTotalElements(Map<String, Object> params);

	List<Order> getContent(Map<String, Object> params);

	Order getWithDetailById(@Param("id") long orderId);

}
