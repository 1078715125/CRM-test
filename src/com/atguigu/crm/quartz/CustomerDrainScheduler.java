package com.atguigu.crm.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mapper.CustomerDrainMapper;

@Component
public class CustomerDrainScheduler {
	@Autowired
	private CustomerDrainMapper customerDrainMapper;

	public void callCustomerDrainCheckProcecure() {
		customerDrainMapper.checkDrain();
	}
	
	@Transactional
	public void test(){
		
	}
}
