package com.gaomin.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gaomin.crm.mapper.ContactMapper;
import com.gaomin.crm.model.PropertyFilter;
import com.gaomin.crm.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaomin.crm.entity.Contact;
import com.gaomin.crm.model.Page;

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

		int pageSize = Page.NORMAL_PAGESIZE;
		Page<Contact> page = new Page<Contact>(pageNo, pageSize, totalElements);
		pageNo = page.getPageNo();// 修正页码
		
		int fromIndex = (pageNo - 1) * pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", pageSize);
		
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
