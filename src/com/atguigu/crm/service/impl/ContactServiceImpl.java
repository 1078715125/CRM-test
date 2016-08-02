package com.atguigu.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.mapper.ContactMapper;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.model.PropertyFilter;
import com.atguigu.crm.service.ContactService;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactMapper contactMapper;

	@Override
	public List<Contact> getContactsByCustId(long custId) {
		return contactMapper.getContactsByCustId(custId);
	}

	@Override
	public Page<Contact> getPageByCustId(int pageNo,
			Map<String, Object> reqParam) {
		Map<String, Object> params = parserRequestParams2MyBatisParams(reqParam);

		int totalElements = contactMapper.getTotalElements(params);

		int pageSize = 3;
		Page<Contact> page = new Page<Contact>(pageNo, pageSize, totalElements);
		pageNo = page.getPageNo();// 修正页码
		
		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<Contact> content = contactMapper.getContent(params);
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
	public void create(Contact contact) {
		contactMapper.create(contact);
	}

	@Override
	public Contact getWithCustById(long contactId) {
		return contactMapper.getWithCustById(contactId);
	}

	@Override
	public void update(Contact contact) {
		contactMapper.update(contact);
	}

	@Override
	public void delete(long contactId) {
		contactMapper.delete(contactId);
	}
}
