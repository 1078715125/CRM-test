package com.gaomin.crm.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gaomin.crm.entity.Contact;
import com.gaomin.crm.entity.Customer;
import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.ContactService;
import com.gaomin.crm.service.CustomerService;

@Controller
@RequestMapping("/contact")
public class ContactHandler {

	@Autowired
	private ContactService contactService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/delete/{contactId}/{customerId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("contactId") long contactId,
			@PathVariable("customerId") long customerId) {

		contactService.delete(contactId);
		return "redirect:/contact/list/" + customerId;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String update(Contact contact) {

		contactService.update(contact);

		return "redirect:/contact/list/" + contact.getCustomer().getId();
	}

	@RequestMapping("/toEdit/{contactId}")
	public String toEdit(@PathVariable("contactId") long contactId,
			Map<String, Object> map) {

		Contact contact = contactService.getWithCustById(contactId);
		map.put("contact", contact);
		return "contact/input";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Contact contact) {

		contactService.create(contact);

		return "redirect:/contact/list/" + contact.getCustomer().getId();
	}

	@RequestMapping("/toCreate/{custId}")
	public String toCreate(@PathVariable("custId") long custId,
			Map<String, Object> map) {

		Contact contact = new Contact();
		Customer customer = new Customer();
		customer.setId(custId);
		contact.setCustomer(customer);

		map.put("contact", contact);
		return "contact/input";
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
		Page<Contact> page = contactService.getPageByCustId(pageNo, reqParam);

		map.put("page", page);
		map.put("customer", customer);
		return "contact/list";
	}
}
