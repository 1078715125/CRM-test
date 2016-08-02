package com.atguigu.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.DictService;

@Controller
@RequestMapping("/dict")
public class DictHandler {

	@Autowired
	private DictService dictService;

	@RequestMapping(value = "/create/{dictId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("dictId") Long dictId) {
		dictService.delete(dictId);

		return "redirect:/dict/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String update(Dict dict) {
		dictService.update(dict);

		return "redirect:/dict/list";
	}

	@RequestMapping(value = "/create/{dictId}", method = RequestMethod.GET)
	public String toEdit(@PathVariable("dictId") Long dictId,
			Map<String, Object> map) {
		Dict dict = dictService.getById(dictId);
		map.put("dict", dict);
		return "dict/input";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Dict dict) {
		dictService.create(dict);

		return "redirect:/dict/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String toCreate(Map<String, Object> map) {

		Dict dict = new Dict();
		map.put("dict", dict);
		return "dict/input";
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

		Page<Dict> page = dictService.getPage(pageNo, reqParams);
		map.put("page", page);
		map.putAll(reqParams);
		return "dict/list";
	}

}
