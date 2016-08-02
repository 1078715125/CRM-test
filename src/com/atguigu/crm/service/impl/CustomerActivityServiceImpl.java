package com.atguigu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.mapper.CustomerActivityMapper;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.CustomerActivityService;
import com.atguigu.crm.utils.ParamsUtil;

@Service
@Transactional
public class CustomerActivityServiceImpl implements CustomerActivityService {

	@Autowired
	private CustomerActivityMapper customerActivityMapper;

	@Override
	public Page<CustomerActivity> getPageByCustId(int pageNo,
			Map<String, Object> reqParam) {
		Map<String, Object> params = ParamsUtil
				.parserRequestParams2MyBatisParams(reqParam);

		int totalElements = customerActivityMapper.getTotalElements(params);
		int pageSize = 3;
		Page<CustomerActivity> page = new Page<CustomerActivity>(pageNo,
				pageSize, totalElements);
		pageNo = page.getPageNo();

		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);

		List<CustomerActivity> content = customerActivityMapper.getContent(params);
		page.setContent(content);

		return page;
	}

	@Override
	public void create(CustomerActivity activity) {
		customerActivityMapper.create(activity);
	}

	@Override
	public CustomerActivity getById(long activityId) {
		return customerActivityMapper.getById(activityId);
	}

	@Override
	public void update(CustomerActivity activity) {
		customerActivityMapper.update(activity);
	}

	@Override
	public void delete(long activityId) {
		customerActivityMapper.delete(activityId);
	}

}
