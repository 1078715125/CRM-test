package com.gaomin.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gaomin.crm.entity.Contact;
import com.gaomin.crm.entity.Customer;
import com.gaomin.crm.service.ContactService;
import com.gaomin.crm.service.CustomerService;
import com.gaomin.crm.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.gaomin.crm.model.Page;

@Controller
@RequestMapping("/customer")
public class CustomerHandler {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private DictService dictService;
	
	@ResponseBody
	@RequestMapping(value="/delete/{custId}",method=RequestMethod.DELETE)
	public String deleteStatus(@PathVariable("custId") long custId){
		
		customerService.updateStatus(custId, "删除");
		
		return "1";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(Customer customer){
		
		customerService.update(customer);
		
		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String toEdit(@PathVariable("id") long custId,
			Map<String, Object> map) {

		Customer customer = customerService.getCustById(custId);
		map.put("customer", customer);
		
		List<Contact> managers =  contactService.getContactsByCustId(custId);
		map.put("managers", managers);
		
		List<String> regions = dictService.getRegions();
		List<String> levels = dictService.getLevels();
		List<String> satisfies = dictService.getSatisfies();
		List<String> credits = dictService.getCredits();
		map.put("regions", regions);
		map.put("levels", levels);
		map.put("satisfies", satisfies);
		map.put("credits", credits);
		
		return "customer/input";
	}

	@RequestMapping(value = "/list")
	public String showList(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request) {

		Map<String, Object> reqParams = WebUtils.getParametersStartingWith(
				request, "search_");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<Customer> page = customerService.getPage(pageNo, reqParams);
		map.put("page", page);

		List<String> regions = dictService.getRegions();
		List<String> levels = dictService.getLevels();
		map.put("regions", regions);
		map.put("levels", levels);

		map.putAll(reqParams);
		for (Map.Entry<String, Object> entry : reqParams.entrySet()) {
			Object value = entry.getValue();
			if (value == null || value.toString().trim().equals("")) {
				continue;
			}
			map.put(entry.getKey(), value);
		}
		return "customer/list";
	}

}
