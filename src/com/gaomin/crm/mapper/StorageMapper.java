package com.gaomin.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gaomin.crm.entity.Storage;

public interface StorageMapper {

	int getTotalElements(Map<String, Object> params);

	List<Storage> getContent(Map<String, Object> params);

	void create(Storage storage);

	Storage getById(@Param("id") Long id);

	void update(Storage storage);

	void delete(@Param("id") Long id);

}
