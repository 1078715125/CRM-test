package com.gaomin.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaomin.crm.entity.CustomerService;
import com.gaomin.crm.mapper.CustomerServiceMapper;
import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.CustomerServiceService;
import com.gaomin.crm.utils.ParamsUtil;

@Service
public class CustomerServiceServiceImpl implements CustomerServiceService {

	@Autowired
	private CustomerServiceMapper customerServiceMapper;

	@Override
	@Transactional
	public void create(CustomerService customerService) {
		customerServiceMapper.create(customerService);
	}

	@Override
	public Page<CustomerService> getAllotPage(int pageNo,
			Map<String, Object> reqParams) {
		
		Map<String, Object> params = ParamsUtil.parserRequestParams2MyBatisParams(reqParams);
		
		int totalElements = customerServiceMapper.getTotalElements(params);
		int pageSize = 3;
		Page<CustomerService> page = new Page<CustomerService>(pageNo, pageSize, totalElements);
		pageNo = page.getPageNo();// 修正页码
		
		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<CustomerService> content = customerServiceMapper.getContent(params);
		page.setContent(content);

		return page;
	}

	@Override
	public void allot(CustomerService customerService) {
		customerService.setServiceState("已分配");
		customerServiceMapper.update(customerService);
	}

	@Override
	public void delete(Long id) {
		customerServiceMapper.delete(id);
	}

	@Override
	public void deal(CustomerService customerService) {
		customerService.setServiceState("已处理");
		customerServiceMapper.update(customerService);
	}

	@Override
	public CustomerService getById(Long id) {
		return customerServiceMapper.getById(id);
	}

	@Override
	public void feedback(CustomerService customerService) {
		customerService.setServiceState("已归档");
		customerServiceMapper.update(customerService);
	}
}
