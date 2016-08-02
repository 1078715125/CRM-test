package com.atguigu.crm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.mapper.CustomerDrainMapper;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.CustomerDrainService;
import com.atguigu.crm.service.CustomerService;
import com.atguigu.crm.utils.ParamsUtil;

@Service("customerDrainService")
public class CustomerDrainServiceImpl implements CustomerDrainService {

	@Autowired
	private CustomerDrainMapper customerDrainMapper;
	@Autowired
	private CustomerService customerService;

	@Override
	public void callCustomerDrainCheckProcecure() {
		customerDrainMapper.checkDrain();
	}

	@Override
	@Transactional
	public Page<CustomerDrain> getPage(int pageNo, Map<String, Object> reqParams) {

		Map<String, Object> params = ParamsUtil
				.parserRequestParams2MyBatisParams(reqParams);

		int totalElements = customerDrainMapper.getTotalElements(params);
		int pageSize = 2;
		Page<CustomerDrain> page = new Page<CustomerDrain>(pageNo, pageSize,
				totalElements);
		pageNo = page.getPageNo();

		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);

		List<CustomerDrain> content = customerDrainMapper.getContent(params);
		page.setContent(content);

		return page;
	}

	@Override
	@Transactional
	public CustomerDrain getById(long id) {
		return customerDrainMapper.getById(id);
	}

	@Override
	@Transactional
	public void updateDelay(CustomerDrain drain) {
		customerDrainMapper.updateDelay(drain);
	}

	@Override
	@Transactional
	public void confirmDrain(CustomerDrain drain) {
		customerService.updateStatus(drain.getCustomer().getId(), "流失");

		drain.setDrainDate(new Date());
		drain.setStatus("流失");
		customerDrainMapper.drain(drain);
	}
}
