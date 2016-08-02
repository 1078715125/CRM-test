package com.atguigu.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Contact;

public interface TestBatch {

	List<Contact> selectIn(@Param("list") List<Integer> list);

	void batchInsert(@Param("list") List<Contact> list);
	void batchInsert2(@Param("list") List<Contact> list);
}
