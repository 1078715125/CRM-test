package com.atguigu.crm.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.OrderService;

@Controller
@RequestMapping("order/")
public class OrderHandler {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/details/{orderId}")
	public String showDetails(@PathVariable("orderId") long orderId,
			Map<String, Object> map) {
		Order order = orderService.getWithDetailById(orderId);
		map.put("order", order);
		return "order/details";
	}

	@RequestMapping("/list/{custId}")
	public String showList(@PathVariable("custId") long custId,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}
		Map<String, Object> reqParam = new HashMap<String, Object>();
		reqParam.put("EQL_custId", custId);
		Page<Order> page = orderService.getPageByCustId(pageNo, reqParam);
		map.put("page", page);
		map.put("custId", custId);
		return "order/list";
	}

}
