package com.gaomin.crm.service;

import java.util.Map;

import com.gaomin.crm.entity.Order;
import com.gaomin.crm.model.Page;

public interface OrderService {

	Page<Order> getPageByCustId(int pageNo, Map<String, Object> reqParam);

	Order getWithDetailById(long orderId);

}
