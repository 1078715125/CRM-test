package com.atguigu.crm.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.CustomerServiceService;
import com.atguigu.crm.service.DictService;
import com.atguigu.crm.service.UserService;
import com.atguigu.crm.utils.GlobalNames;

@Controller
@RequestMapping("/service")
public class CustomerServiceHandler {

	@Autowired
	private CustomerServiceService customerServiceService;
	@Autowired
	private DictService dictService;
	@Autowired
	private com.atguigu.crm.service.CustomerService customerService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/archive/{id}", method = RequestMethod.GET)
	public String toArchive(@PathVariable("id") Long id, Map<String, Object> map) {

		CustomerService service = customerServiceService.getById(id);
		map.put("service", service);
		return "service/archive/archive";
	}

	@RequestMapping("/archive")
	public String archivePage(HttpServletRequest request,
			Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQS_type", "archive");
		basePage(request, map, pageNoStr, params);
		return "service/archive/list";
	}

	@RequestMapping(value = "/feedback", method = RequestMethod.PUT)
	public String feedback(CustomerService customerService,
			RedirectAttributes attributes) {
		customerServiceService.feedback(customerService);
		attributes.addFlashAttribute("message", "保存成功!");
		return "redirect:/service/feedback";
	}

	@RequestMapping(value = "/feedback/{id}", method = RequestMethod.GET)
	public String toFeedback(@PathVariable("id") Long id,
			Map<String, Object> map) {

		CustomerService service = customerServiceService.getById(id);
		map.put("service", service);
		return "service/feedback/feedback";
	}

	@RequestMapping("/feedback")
	public String feedbackPage(HttpServletRequest request,
			Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQS_type", "feedback");
		basePage(request, map, pageNoStr, params);
		return "service/feedback/list";
	}

	@RequestMapping(value = "/deal", method = RequestMethod.PUT)
	public String deal(CustomerService customerService,
			RedirectAttributes attributes) {
		customerServiceService.deal(customerService);
		attributes.addFlashAttribute("message", "保存成功!");
		return "redirect:/service/deal";
	}

	@RequestMapping(value = "/deal/{id}", method = RequestMethod.GET)
	public String toDeal(@PathVariable("id") Long id, Map<String, Object> map) {

		CustomerService service = customerServiceService.getById(id);
		String dealDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		map.put("service", service);
		map.put("dealDate", dealDate);
		return "service/deal/deal";
	}

	@RequestMapping("/deal")
	public String dealPage(HttpServletRequest request, Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQS_type", "deal");
		basePage(request, map, pageNoStr, params);
		return "service/deal/list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		customerServiceService.delete(id);
		return "redirect:/service/allot";
	}

	@ResponseBody
	@RequestMapping(value = "/allot", method = RequestMethod.PUT)
	public String allot(CustomerService customerService) {
		customerService.setAllotDate(new Date());
		customerServiceService.allot(customerService);

		return "1";
	}

	@RequestMapping("/allot")
	public String allotPage(HttpServletRequest request,
			Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQS_type", "allot");
		basePage(request, map, pageNoStr, params);
		List<User> users = userService.getAllUser();
		map.put("users", users);
		return "service/allot/list";
	}

	private void basePage(HttpServletRequest request, Map<String, Object> map,
			String pageNoStr, Map<String, Object> params) {
		Map<String, Object> reqParams = WebUtils.getParametersStartingWith(
				request, "search_");
		reqParams.putAll(params);
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<CustomerService> page = customerServiceService.getAllotPage(
				pageNo, reqParams);
		map.put("page", page);
		map.putAll(reqParams);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(CustomerService customerService, HttpSession session) {
		User user = (User) session.getAttribute(GlobalNames.LOGIN_USER);
		customerService.setCreatedby(user);
		customerServiceService.create(customerService);

		return "redirect:/service/allot";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String toCreate(Map<String, Object> map) {

		List<Customer> customers = customerService.getAllCustomers();
		List<String> serviceTypes = dictService.getServiceTypes();
		String createDate = new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date());

		map.put("serviceTypes", serviceTypes);
		map.put("customers", customers);
		map.put("createDate", createDate);
		return "service/input";
	}
}
