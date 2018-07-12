package com.gaomin.crm.service;

import java.util.Map;

import com.gaomin.crm.entity.Storage;
import com.gaomin.crm.model.Page;

public interface StorageService {

	Page<Storage> getPage(int pageNo, Map<String, Object> reqParams);

	void create(Storage storage);

	Storage getById(Long id);

	void update(Storage storage);

	void delete(Long id);

}
