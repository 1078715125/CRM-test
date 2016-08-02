package com.atguigu.crm.service;

import java.util.Map;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.model.Page;

public interface OrderService {

	Page<Order> getPageByCustId(int pageNo, Map<String, Object> reqParam);

	Order getWithDetailById(long orderId);

}
