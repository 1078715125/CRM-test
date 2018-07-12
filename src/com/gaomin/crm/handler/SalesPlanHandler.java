package com.gaomin.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gaomin.crm.entity.SalesPlan;
import com.gaomin.crm.entity.User;
import com.gaomin.crm.service.SalesPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.gaomin.crm.entity.SalesChance;
import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.SalesChanceService;
import com.gaomin.crm.utils.GlobalNames;

@Controller
@RequestMapping("/plan")
public class SalesPlanHandler {

	@Autowired
	private SalesPlanService salesPlanService;
	@Autowired
	private SalesChanceService salesChanceService;

	@RequestMapping(value = "/details/{chanceId}", method = RequestMethod.GET)
	public String detail(@PathVariable("chanceId") long chanceId,
			Map<String, Object> map) {

		SalesChance chance = salesChanceService.getWithPlanById(chanceId);
		map.put("chance", chance);

		return "plan/detail";
	}

	@RequestMapping(value = "/chance/stop/{chanceId}", method = RequestMethod.PUT)
	public String stop(@PathVariable("chanceId") long chanceId) {

		salesChanceService.updateStatusStop(chanceId, "4");
		return "redirect:/plan/chance/list";
	}

	@RequestMapping(value = "/chance/finish/{chanceId}", method = RequestMethod.PUT)
	public String finish(@PathVariable("chanceId") long chanceId) {

		salesChanceService.updateStatusFinish(chanceId, "3");

		return "redirect:/plan/chance/list";
	}

	@ResponseBody
	@RequestMapping(value = "/execute", method = RequestMethod.PUT)
	public String updateResult(SalesPlan salesPlan) {

		salesPlanService.updateResult(salesPlan);

		return "1";
	}

	@RequestMapping(value = "/execute/{chanceId}", method = RequestMethod.GET)
	public String execute(@PathVariable("chanceId") int chanceId,
			Map<String, Object> map) {
		SalesChance chance = salesChanceService.getWithPlanById(chanceId);

		map.put("chance", chance);
		return "plan/exec";
	}

	@ResponseBody
	@RequestMapping(value = "/make", method = RequestMethod.DELETE)
	public String deletePlan(@RequestParam("id") long id) {

		salesPlanService.deletePlan(id);

		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "/make", method = RequestMethod.PUT)
	public String updatePlan(SalesPlan salesPlan) {

		salesPlanService.updatePlan(salesPlan);

		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "/make", method = RequestMethod.POST)
	public long addPlan(SalesPlan salesPlan) {
		salesPlanService.savePlan(salesPlan);

		return salesPlan.getId();
	}

	@RequestMapping(value = "/make/{chanceId}", method = RequestMethod.GET)
	public String makePlan(@PathVariable("chanceId") long chanceId,
			Map<String, Object> map) {

		SalesChance chance = salesChanceService.getWithPlanById(chanceId);

		map.put("chance", chance);
		return "plan/make";
	}

	@RequestMapping(value = "/chance/list")
	public String showList(HttpSession session,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request) {

		Map<String, Object> reqParams = WebUtils.getParametersStartingWith(
				request, "search_");
		User loginUser = (User) session.getAttribute(GlobalNames.LOGIN_USER);
		reqParams.put("EQL_designeeId", loginUser.getId());
		reqParams.put("EQI_status", 200);

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		Page<SalesChance> page = salesChanceService.getPage(pageNo, reqParams);

		map.put(GlobalNames.PAGE, page);

		for (Map.Entry<String, Object> entry : reqParams.entrySet()) {
			Object value = entry.getValue();
			if (value == null || value.toString().trim().equals("")) {
				continue;
			}
			request.setAttribute(entry.getKey(), value);
		}
		return "plan/list";
	}

}
