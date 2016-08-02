package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.ProductService;
import com.atguigu.crm.service.StorageService;

@Controller
@RequestMapping("/storage")
public class StorageHandler {
	@Autowired
	private StorageService storageService;
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/create/{id}", method = RequestMethod.DELETE)
	public String update(@PathVariable("id") Long id) {

		storageService.delete(id);

		return "redirect:/storage/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String update(Storage storage,
			@RequestParam("incrementCount") Integer iCount) {
		iCount = (iCount == null) ? 0 : iCount;
		storage.setStockCount(storage.getStockCount() + iCount);
		storageService.update(storage);

		return "redirect:/storage/list";
	}

	@RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
	public String toEdit(@PathVariable("id") Long id, Map<String, Object> map) {
		Storage storage = storageService.getById(id);
		map.put("storage", storage);
		return "storage/input";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Storage storage) {

		storageService.create(storage);

		return "redirect:/storage/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String toCreate(Map<String, Object> map) {
		Storage storage = new Storage();

		List<Product> products = productService.getAllProducts();
		map.put("storage", storage);
		map.put("products", products);
		return "storage/input";
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

		Page<Storage> page = storageService.getPage(pageNo, reqParams);
		map.put("page", page);
		map.putAll(reqParams);
		return "storage/list";
	}
}
