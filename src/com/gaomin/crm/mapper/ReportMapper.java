package com.gaomin.crm.mapper;

import java.util.List;
import java.util.Map;

public interface ReportMapper {

	int getPayTotalElements(Map<String, Object> params);

	List<Map<String, Object>> getPayContent(Map<String, Object> params);
	
	int getConsistTotalElements(Map<String, Object> params);
	
	List<Map<String, Object>> getConsistContent(Map<String, Object> params);
	
	int getServiceTotalElements(Map<String, Object> params);
	
	List<Map<String, Object>> getServiceContent(Map<String, Object> params);
	
	int getDrainTotalElements(Map<String, Object> params);
	
	List<Map<String, Object>> getDrainContent(Map<String, Object> params);

}
