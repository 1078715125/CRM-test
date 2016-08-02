package com.atguigu.crm.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.CustomerActivityService;
import com.atguigu.crm.service.CustomerService;

@Controller
@RequestMapping("/activity")
public class CustomerActivityHandler {

	@Autowired
	private CustomerActivityService customerActivityService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/delete/{activityId}/{custId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("activityId") long activityId,
			@PathVariable("custId") long custId) {

		customerActivityService.delete(activityId);
		
		return "redirect:/activity/list/" + custId;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String update(CustomerActivity activity) {

		customerActivityService.update(activity);

		return "redirect:/activity/list/" + activity.getCustomer().getId();
	}

	@RequestMapping(value = "/toEdit/{activityId}", method = RequestMethod.GET)
	public String toEdit(@PathVariable("activityId") long activityId,
			Map<String, Object> map) {

		CustomerActivity activity = customerActivityService.getById(activityId);
		map.put("activity", activity);
		return "activity/input";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(CustomerActivity activity) {

		customerActivityService.create(activity);

		return "redirect:/activity/list/" + activity.getCustomer().getId();
	}

	@RequestMapping(value = "/toCreate/{custId}", method = RequestMethod.GET)
	public String toCreate(@PathVariable("custId") long custId,
			Map<String, Object> map) {
		CustomerActivity activity = new CustomerActivity();
		Customer customer = new Customer();
		customer.setId(custId);
		activity.setCustomer(customer);
		map.put("activity", activity);
		return "activity/input";
	}

	@RequestMapping("/list/{custId}")
	public String showList(@PathVariable("custId") long custId,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map) {
		Customer customer = customerService.getCustById(custId);
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}
		Map<String, Object> reqParam = new HashMap<String, Object>();
		reqParam.put("EQL_custId", custId);
		Page<CustomerActivity> page = customerActivityService.getPageByCustId(
				pageNo, reqParam);

		map.put("page", page);
		map.put("customer", customer);
		return "activity/list";
	}

}
