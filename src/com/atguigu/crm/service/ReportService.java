package com.atguigu.crm.service;

import java.util.Map;

import com.atguigu.crm.model.Page;

public interface ReportService {

	Page<Map<String, Object>> getPayPage(int pageNo,
			Map<String, Object> reqParams);

	Page<Map<String, Object>> getConsistPage(int pageNo,
			Map<String, Object> reqParams);

	Page<Map<String, Object>> getServicePage(int pageNo,
			Map<String, Object> reqParams);

	Page<Map<String, Object>> getDrainPage(int pageNo,
			Map<String, Object> reqParams);

}
