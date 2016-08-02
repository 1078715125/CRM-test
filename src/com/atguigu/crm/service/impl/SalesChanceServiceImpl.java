package com.atguigu.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.ContactMapper;
import com.atguigu.crm.mapper.CustomerMapper;
import com.atguigu.crm.mapper.SalesChanceMapper;
import com.atguigu.crm.model.ChanceCondition;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.model.PropertyFilter;
import com.atguigu.crm.service.SalesChanceService;

@Service
public class SalesChanceServiceImpl implements SalesChanceService {

	@Autowired
	private SalesChanceMapper salesChanceMapper;

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private ContactMapper contactMapper;

	@Override
	@Transactional(readOnly = true)
	public Page<SalesChance> getPage(int pageNo, Map<String, Object> reqParams) {

		Map<String, Object> params = parseRequestParams2MybatisParams(reqParams);

		// 获取总记录数

		int totalElements = (int) salesChanceMapper.getTotalElements(params);
		int pageSize = Page.SMALL_PAGESIZE;
		// int pageSize = 3;

		Page<SalesChance> page = new Page<SalesChance>(pageNo, pageSize,
				totalElements);
		// 获取修正后的页码
		int pageNo2 = page.getPageNo();

		int firstIndex = ((pageNo2 - 1) * pageSize) + 1;
		int lastIndex = (pageNo2 * pageSize) + 1;

		params.put("firstIndex", firstIndex);
		params.put("lastIndex", lastIndex);

		List<SalesChance> content = salesChanceMapper.getContent(params);
		page.setContent(content);

		return page;
	}

	private Map<String, Object> parseRequestParams2MybatisParams(
			Map<String, Object> reqParams) {
		Map<String, Object> params = new HashMap<String, Object>();

		List<PropertyFilter> filters = PropertyFilter
				.parseRequestParam2PropertyFilter(reqParams);
		
		for (PropertyFilter filter : filters) {
			params.put(filter.getPropertyName(), filter.getPropertyVal());
		}
		return params;
	}

	private Object contectLike(String like) {
		StringBuilder sb = new StringBuilder();
		like = (like == null) ? "" : like.trim();
		sb.append('%').append(like).append('%');
		return sb.toString();
	}

	@Override
	@Transactional
	public void save(SalesChance salesChance) {
		salesChance.setStatus(1);// 1 才被创建
		salesChanceMapper.save(salesChance);
	}

	@Override
	@Transactional
	public void delete(long id) {
		salesChanceMapper.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public SalesChance getById(long id) {

		return salesChanceMapper.getById(id);
	}

	@Override
	@Transactional
	public void update(SalesChance salesChance) {

		salesChanceMapper.update(salesChance);
	}

	@Override
	@Transactional(readOnly = true)
	public SalesChance getWithUserById(long chanceId) {

		return salesChanceMapper.getWithUserById(chanceId);
	}

	@Override
	@Transactional
	public void dispatch(SalesChance salesChance) {
		salesChance.setStatus(2);// 2. 执行中
		salesChanceMapper.dispatch(salesChance);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SalesChance> getPageOfPlan(User designee, int pageNo,
			ChanceCondition condition) {
		// 获取总记录数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("designee", designee);
		params.put("status", 200);
		params.put("likeContact", contectLike(condition.getSearchLikeContact()));
		params.put("likeCustName",
				contectLike(condition.getSearchLikeCustName()));
		params.put("likeTitle", contectLike(condition.getSearchLikeTitle()));

		int totalElements = (int) salesChanceMapper.getTotalElements(params);
		// int pageSize = Page.SMALL_PAGESIZE;
		int pageSize = 2;

		Page<SalesChance> page = new Page<SalesChance>(pageNo, pageSize,
				totalElements);
		// 获取修正后的页码
		int pageNo2 = page.getPageNo();

		int firstIndex = ((pageNo2 - 1) * pageSize) + 1;
		int lastIndex = (pageNo2 * pageSize) + 1;

		params.put("firstIndex", firstIndex);
		params.put("lastIndex", lastIndex);

		List<SalesChance> content = salesChanceMapper.getContent(params);
		page.setContent(content);

		return page;

	}

	@Override
	@Transactional(readOnly = true)
	public SalesChance getWithPlanById(long chanceId) {

		return salesChanceMapper.getWithPlanById(chanceId);
	}

	@Override
	@Transactional
	public void updateStatusFinish(long chanceId, String status) {

		salesChanceMapper.updateStatusFinish(chanceId, status);
		SalesChance chance = salesChanceMapper.getById(chanceId);
		Customer customer = new Customer();
		customer.setName(chance.getCustName());
		customer.setNo(UUID.randomUUID().toString());
		customer.setState("正常");

		customerMapper.saveCustForFinish(customer);

		Contact contact = new Contact();
		contact.setName(chance.getContact());
		contact.setTel(chance.getContactTel());
		contact.setCustomer(customer);

		contactMapper.saveForFinish(contact);
	}

	@Override
	public void updateStatusStop(long chanceId, String status) {

		salesChanceMapper.updateStatusFinish(chanceId, status);
	}

}
