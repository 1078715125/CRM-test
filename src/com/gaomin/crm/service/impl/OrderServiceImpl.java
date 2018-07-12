package com.gaomin.crm.service.impl;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaomin.crm.mapper.OrderMapper;
import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.OrderService;
import com.gaomin.crm.utils.ParamsUtil;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public Page<Order> getPageByCustId(int pageNo, Map<String, Object> reqParams) {
		Map<String, Object> params = ParamsUtil.parserRequestParams2MyBatisParams(reqParams);
		
		int totalElements = orderMapper.getTotalElements(params);
		int pageSize = 3;
		Page<Order> page = new Page<Order>(pageNo, pageSize, totalElements);
		pageNo = page.getPageNo();

		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);

		List<Order> content = orderMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	@Override
	public Order getWithDetailById(long orderId) {
		return orderMapper.getWithDetailById(orderId);
	} 
}
