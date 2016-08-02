package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Contact;

public interface ContactMapper {

	void saveForFinish(Contact contact);

	List<Contact> getContactsByCustId(@Param("custId") long custId);

	int getTotalElements(Map<String, Object> params);

	List<Contact> getContent(Map<String, Object> params);

	void create(Contact contact);

	Contact getWithCustById(@Param("id") long contactId);

	void update(Contact contact);

	void delete(@Param("id") long contactId);

}
