package com.atguigu.crm.service;

import com.atguigu.crm.entity.SalesPlan;

public interface SalesPlanService {

	void savePlan(SalesPlan salesPlan);

	void updatePlan(SalesPlan salesPlan);

	void deletePlan(long id);

	void updateResult(SalesPlan salesPlan);

}
