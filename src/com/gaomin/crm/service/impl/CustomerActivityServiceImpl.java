package com.gaomin.crm.service.impl;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.CustomerActivity;
import com.gaomin.crm.service.CustomerActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaomin.crm.mapper.CustomerActivityMapper;
import com.gaomin.crm.model.Page;
import com.gaomin.crm.utils.ParamsUtil;

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
		int pageSize = Page.NORMAL_PAGESIZE;
		Page<CustomerActivity> page = new Page<CustomerActivity>(pageNo,
				pageSize, totalElements);
		pageNo = page.getPageNo();

		int fromIndex = (pageNo - 1) * pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", pageSize);

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
