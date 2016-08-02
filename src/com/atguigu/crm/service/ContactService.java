package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.model.Page;

public interface ContactService {

	List<Contact> getContactsByCustId(long custId);

	Page<Contact> getPageByCustId(int pageNo, Map<String, Object> reqParam);

	void create(Contact contact);

	Contact getWithCustById(long contactId);

	void update(Contact contact);

	void delete(long contactId);

}
