package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;

public interface SalesChanceMapper {

	long getTotalElements(Map<String, Object> params);

	List<SalesChance> getContent(Map<String, Object> params);

	void save(SalesChance salesChance);

	void delete(@Param("id") long id);

	SalesChance getById(long id);

	void update(SalesChance salesChance);

	SalesChance getWithUserById(@Param("id") long chanceId);

	void dispatch(SalesChance salesChance);

	SalesChance getWithPlanById(@Param("id") long chanceId);

	void updateStatusFinish(@Param("id") long chanceId, @Param("status")String status);

}
