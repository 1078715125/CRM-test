package com.atguigu.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.mapper.CustomerMapper;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.model.PropertyFilter;
import com.atguigu.crm.service.ContactService;
import com.atguigu.crm.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private ContactService contactService;

	@Override
	@Transactional(readOnly = true)
	public Page<Customer> getPage(int pageNo, Map<String, Object> reqParams) {

		Map<String, Object> params = parserRequestParams2MyBatisParams(reqParams);
		// 查询总记录数
		int totalElements = customerMapper.getTotalElements(params);

		int pageSize = 3;
		Page<Customer> page = new Page<Customer>(pageNo, pageSize,
				totalElements);
		pageNo = page.getPageNo();// 修正页码

		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);

		List<Customer> content = customerMapper.getContent(params);
		page.setContent(content);

		return page;
	}

	private Map<String, Object> parserRequestParams2MyBatisParams(
			Map<String, Object> reqParams) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (reqParams == null || reqParams.size() == 0) {
			return params;
		}
		List<PropertyFilter> filters = PropertyFilter
				.parseRequestParam2PropertyFilter(reqParams);
		for (PropertyFilter filter : filters) {
			params.put(filter.getPropertyName(), filter.getPropertyVal());
		}
		return params;
	}

	@Override
	@Transactional(readOnly = true)
	public Customer getCustById(long custId) {

		return customerMapper.getCustById(custId);
	}

	@Override
	public void update(Customer customer) {
		customerMapper.update(customer);
	}

	@Override
	public void updateStatus(long id, String status) {
		customerMapper.updateStatus(id, status);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerMapper.getAllCustomers();
	}

}
