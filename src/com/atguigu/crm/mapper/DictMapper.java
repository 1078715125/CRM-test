package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Dict;

public interface DictMapper {

	List<String> getRegions();

	List<String> getLevels();

	List<String> getSatisfies();

	List<String> getCredits();

	List<String> getServiceTypes();

	int getTotalElements(Map<String, Object> params);

	List<Dict> getContent(Map<String, Object> params);

	void create(Dict dict);

	Dict getById(@Param("id") Long dictId);

	void update(Dict dict);

	void delete(@Param("id") Long dictId);

}
