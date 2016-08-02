package com.atguigu.crm.service;

import java.util.Map;

import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.model.Page;

public interface StorageService {

	Page<Storage> getPage(int pageNo, Map<String, Object> reqParams);

	void create(Storage storage);

	Storage getById(Long id);

	void update(Storage storage);

	void delete(Long id);

}
