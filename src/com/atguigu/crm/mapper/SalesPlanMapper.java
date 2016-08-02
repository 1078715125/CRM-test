package com.atguigu.crm.mapper;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesPlan;

public interface SalesPlanMapper {

	long save(SalesPlan salesPlan);

	void updatePlan(SalesPlan salesPlan);

	void deletePlan(@Param("id") long id);

	void updateResult(SalesPlan salesPlan);

}
