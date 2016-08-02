package com.atguigu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.mapper.OrderMapper;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.OrderService;
import com.atguigu.crm.utils.ParamsUtil;

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
