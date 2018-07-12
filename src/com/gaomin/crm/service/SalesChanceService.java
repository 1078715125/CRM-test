package com.gaomin.crm.service;

import java.util.Map;

import com.gaomin.crm.entity.SalesChance;
import com.gaomin.crm.entity.User;
import com.gaomin.crm.model.ChanceCondition;
import com.gaomin.crm.model.Page;

public interface SalesChanceService {

	Page<SalesChance> getPage(int pageNo, Map<String, Object> reqParams);

	void save(SalesChance salesChance);

	void delete(long id);

	SalesChance getById(long id);

	void update(SalesChance salesChance);

	SalesChance getWithUserById(long chanceId);

	void dispatch(SalesChance salesChance);

	Page<SalesChance> getPageOfPlan(User designee, int pageNo,
                                    ChanceCondition condition);

	SalesChance getWithPlanById(long chanceId);

	void updateStatusFinish(long chanceId, String status);

	void updateStatusStop(long chanceId, String status);

}
