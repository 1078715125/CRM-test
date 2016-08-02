package com.atguigu.crm.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atguigu.crm.model.PropertyFilter;

public class ParamsUtil {

	public static ThreadLocal<List<Map<String, Object>>> local = new ThreadLocal<List<Map<String, Object>>>();
	
	
	public static List<Map<String, Object>> getMap(){
		return local.get();
	}
	
	public static void setMap(List<Map<String, Object>> params) {
		local.set(params);
	}
	
	public static Map<String, Object> parserRequestParams2MyBatisParams(
			Map<String, Object> reqParams) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (reqParams == null || reqParams.size() == 0) {
			return params;
		}
		List<PropertyFilter> filters = PropertyFilter
				.parseRequestParam2PropertyFilter(reqParams);
		for (PropertyFilter filter : filters) {
			params.put(filter.getPropertyName(), filter.getPropertyVal());
		}
		return params;
	}
}
