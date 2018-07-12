package com.gaomin.crm.service.impl;

import com.gaomin.crm.entity.SalesPlan;
import com.gaomin.crm.mapper.SalesPlanMapper;
import com.gaomin.crm.service.SalesPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalesPlanServiceImpl implements SalesPlanService {

	@Autowired
	private SalesPlanMapper salesPlanMapper;

	@Override
	@Transactional
	public void savePlan(SalesPlan salesPlan) {
		
		salesPlanMapper.save(salesPlan);
	}

	@Override
	@Transactional
	public void updatePlan(SalesPlan salesPlan) {
		
		salesPlanMapper.updatePlan(salesPlan);
	}

	@Override
	@Transactional
	public void deletePlan(long id) {
		
		salesPlanMapper.deletePlan(id);
	}

	@Override
	@Transactional
	public void updateResult(SalesPlan salesPlan) {
		
		salesPlanMapper.updateResult(salesPlan);
	}
	
}
