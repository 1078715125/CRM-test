package com.gaomin.crm.handler;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.ReportService;
import com.gaomin.crm.utils.ParamsUtil;

@Controller
@RequestMapping("/report")
public class ReportHandler {

	@Autowired
	private ReportService reportService;

	@RequestMapping("/jFree")
	public String JFreeChart(Map<String, Object> map){
		List<Map<String,Object>> mapList = ParamsUtil.getMap();
		map.put("mapList", mapList);
		return "jFree";
	}
	
	@RequestMapping("/drain")
	public String drain(HttpServletRequest request, Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {
		try {
			basePage(request, map, pageNoStr, "getDrainPage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "report/drain";
	}

	@RequestMapping("/service")
	public String service(HttpServletRequest request, Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {
		try {
			basePage(request, map, pageNoStr, "getServicePage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "report/service";
	}

	@RequestMapping("/consist")
	public String consist(HttpServletRequest request, Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {
		try {
			basePage(request, map, pageNoStr, "getConsistPage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "report/consist";
	}

	@RequestMapping("/pay")
	public String pay(HttpServletRequest request, Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {
		// Map<String, Object> reqParams = WebUtils.getParametersStartingWith(
		// request, "search_");
		// int pageNo = 1;
		// try {
		// pageNo = Integer.parseInt(pageNoStr);
		// } catch (Exception e) {
		// }
		// Page<Map<String, Object>> page = reportService.getPayPage(pageNo,
		// reqParams);
		// map.put("page", page);
		// map.putAll(reqParams);
		try {
			basePage(request, map, pageNoStr, "getPayPage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "report/pay";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void basePage(HttpServletRequest request, Map<String, Object> map,
			String pageNoStr, String methodName) throws Exception {
		Map<String, Object> reqParams = WebUtils.getParametersStartingWith(
				request, "search_");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}
		Method method = ReportService.class.getDeclaredMethod(methodName,
				int.class, Map.class);
		Page page = (Page) method.invoke(reportService, pageNo, reqParams);
		ParamsUtil.setMap(page.getContent());
		map.put("page", page);
		map.putAll(reqParams);
	}
}
