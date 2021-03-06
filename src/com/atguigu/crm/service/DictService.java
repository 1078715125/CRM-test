package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.model.Page;

public interface DictService {

	List<String> getRegions();

	List<String> getLevels();

	List<String> getSatisfies();

	List<String> getCredits();

	List<String> getServiceTypes();

	Page<Dict> getPage(int pageNo, Map<String, Object> reqParams);

	void create(Dict dict);

	Dict getById(Long dictId);

	void update(Dict dict);

	void delete(Long dictId);

}
