package com.gaomin.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.gaomin.crm.entity.Product;
import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductHandler {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/create/{id}", method = RequestMethod.DELETE)
	public String update(@PathVariable("id") Long id) {
		
		productService.delete(id);
		
		return "redirect:/product/list";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String update(Product product) {

		productService.update(product);

		return "redirect:/product/list";
	}
	
	@RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
	public String toEdit(@PathVariable("id") Long id, Map<String, Object> map) {
		Product product = productService.getById(id);
		map.put("product", product);
		return "product/input";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Product product) {

		productService.create(product);

		return "redirect:/product/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String toCreate(Map<String, Object> map) {
		Product product = new Product();
		map.put("product", product);
		return "product/input";
	}

	@RequestMapping("/list")
	public String showList(HttpServletRequest request, Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {
		Map<String, Object> reqParams = WebUtils.getParametersStartingWith(
				request, "search_");

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<Product> page = productService.getPage(pageNo, reqParams);
		map.put("page", page);
		map.putAll(reqParams);
		return "product/list";
	}

}
