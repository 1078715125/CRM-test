package com.gaomin.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gaomin.crm.entity.CustomerDrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.CustomerDrainService;

@Controller
@RequestMapping("/drain")
public class CustomerDrainHandler {

	@Autowired
	private CustomerDrainService customerDrainService;

	@RequestMapping(value = "/confirm", method = RequestMethod.PUT)
	public String confirm(CustomerDrain drain) {
		
		customerDrainService.confirmDrain(drain);
		return "redirect:/drain/list";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String toConfirm(@RequestParam("id") long id, Map<String, Object> map) {
		
		CustomerDrain drain = customerDrainService.getById(id);
		map.put("drain", drain);
		return "drain/confirm";
	}

	@RequestMapping(value = "/delay", method = RequestMethod.PUT)
	@ResponseBody
	public String delay(CustomerDrain drain, @RequestParam("count") int count) {
		if (count > 1) {
			drain.setDelay("`" + drain.getDelay());
		}
		customerDrainService.updateDelay(drain);
		return "1";
	}

	@RequestMapping(value = "/delay", method = RequestMethod.GET)
	public String toDelay(@RequestParam("id") long id, Map<String, Object> map) {

		CustomerDrain drain = customerDrainService.getById(id);
		map.put("drain", drain);
		return "drain/delay";
	}

	@RequestMapping("/list")
	public String showList(Map<String, Object> map, HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {

		Map<String, Object> reqParams = WebUtils.getParametersStartingWith(
				request, "search_");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}
		Page<CustomerDrain> page = customerDrainService.getPage(pageNo,
				reqParams);

		map.put("page", page);
		map.putAll(reqParams);
		return "drain/list";
	}

}
