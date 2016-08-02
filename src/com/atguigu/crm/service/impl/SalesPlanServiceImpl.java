package com.atguigu.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.mapper.SalesPlanMapper;
import com.atguigu.crm.service.SalesPlanService;

@Service
public class SalesPlanServiceImpl implements SalesPlanService{

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
