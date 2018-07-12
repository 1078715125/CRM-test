package com.gaomin.crm.service;

import com.gaomin.crm.entity.SalesPlan;

public interface SalesPlanService {

	void savePlan(SalesPlan salesPlan);

	void updatePlan(SalesPlan salesPlan);

	void deletePlan(long id);

	void updateResult(SalesPlan salesPlan);

}
