package com.gaomin.crm.mapper;

import java.util.List;
import java.util.Map;

import com.gaomin.crm.entity.Dict;
import org.apache.ibatis.annotations.Param;

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
