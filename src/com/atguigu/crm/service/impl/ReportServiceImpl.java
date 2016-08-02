package com.atguigu.crm.service.impl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.mapper.ReportMapper;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.ReportService;
import com.atguigu.crm.utils.ParamsUtil;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportMapper reportMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Page<Map<String, Object>> basePage(int pageNo,
			Map<String, Object> reqParams, String type) throws Exception {
		Map<String, Object> params = ParamsUtil
				.parserRequestParams2MyBatisParams(reqParams);

		String methodCountName = "get" + type + "TotalElements";
		String methodContentName = "get" + type + "Content";

		Method methodCount = ReportMapper.class.getDeclaredMethod(
				methodCountName, Map.class);
		Method methodContent = ReportMapper.class.getDeclaredMethod(
				methodContentName, Map.class);

		int totalElements = (int) methodCount.invoke(reportMapper, params);
		int pageSize = 3;

		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo,
				pageSize, totalElements);
		pageNo = page.getPageNo();
		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);

		List content = (List) methodContent.invoke(reportMapper, params);
		page.setContent(content);
		return page;
	}

	@Override
	public Page<Map<String, Object>> getPayPage(int pageNo,
			Map<String, Object> reqParams) {
		// Map<String, Object> params = ParamsUtil
		// .parserRequestParams2MyBatisParams(reqParams);
		//
		// int totalElements = reportMapper.getPayTotalElements(params);
		// int pageSize = 3;
		// Page<Map<String, Object>> page = new Page<Map<String,
		// Object>>(pageNo,
		// pageSize, totalElements);
		// pageNo = page.getPageNo();
		// int fromIndex = (pageNo - 1) * pageSize + 1;
		// int endIndex = fromIndex + pageSize;
		// params.put("fromIndex", fromIndex);
		// params.put("endIndex", endIndex);
		//
		// List<Map<String, Object>> content =
		// reportMapper.getPayContent(params);
		// page.setContent(content);
		// return page;
		Page<Map<String, Object>> page = null;
		try {
			page = basePage(pageNo, reqParams, "Pay");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Page<Map<String, Object>> getConsistPage(int pageNo,
			Map<String, Object> reqParams) {

		Page<Map<String, Object>> page = null;
		try {
			page = basePage(pageNo, reqParams, "Consist");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Page<Map<String, Object>> getServicePage(int pageNo,
			Map<String, Object> reqParams) {
		Page<Map<String, Object>> page = null;
		try {
			page = basePage(pageNo, reqParams, "Service");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Page<Map<String, Object>> getDrainPage(int pageNo,
			Map<String, Object> reqParams) {
		Page<Map<String, Object>> page = null;
		try {
			page = basePage(pageNo, reqParams, "Drain");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
}
