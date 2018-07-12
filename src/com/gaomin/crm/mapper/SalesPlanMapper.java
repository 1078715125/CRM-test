package com.gaomin.crm.mapper;

import com.gaomin.crm.entity.SalesPlan;
import org.apache.ibatis.annotations.Param;

public interface SalesPlanMapper {

	long save(SalesPlan salesPlan);

	void updatePlan(SalesPlan salesPlan);

	void deletePlan(@Param("id") long id);

	void updateResult(SalesPlan salesPlan);

}
