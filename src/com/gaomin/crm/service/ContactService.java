package com.gaomin.crm.service;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.Contact;
import com.gaomin.crm.model.Page;

public interface ContactService {

	List<Contact> getContactsByCustId(long custId);

	Page<Contact> getPageByCustId(int pageNo, Map<String, Object> reqParam);

	void create(Contact contact);

	Contact getWithCustById(long contactId);

	void update(Contact contact);

	void delete(long contactId);

}
