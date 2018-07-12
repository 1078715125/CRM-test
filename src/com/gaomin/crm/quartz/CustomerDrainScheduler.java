package com.gaomin.crm.quartz;

import com.gaomin.crm.mapper.CustomerDrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
