package com.gaomin.crm.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gaomin.crm.entity.User;
import com.gaomin.crm.service.SalesChanceService;
import com.gaomin.crm.service.UserService;
import com.gaomin.crm.utils.GlobalNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.gaomin.crm.entity.SalesChance;
import com.gaomin.crm.model.Page;

@RequestMapping("/chance")
@Controller
public class SalesChanceHandler {

	@Autowired
	private SalesChanceService salesChanceService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/dispatch", method = RequestMethod.PUT)
	public String doDispatch(SalesChance salesChance) {

		salesChanceService.dispatch(salesChance);

		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/dispatch/{chanceId}", method = RequestMethod.GET)
	public String toDispatch(@PathVariable("chanceId") long chanceId,
			Map<String, Object> map) {

		SalesChance chance = salesChanceService.getWithUserById(chanceId);
		chance.setDesigneeDate(new Date());

		List<User> users = userService.getAllUser();

		map.put("chance", chance);
		map.put("users", users);

		return "chance/dispatch";
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public String edit(SalesChance salesChance, RedirectAttributes attributes) {

		salesChanceService.update(salesChance);

		attributes.addFlashAttribute("message", "修改成功！");
		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String toEditUI(@PathVariable("id") long id, Map<String, Object> map) {

		SalesChance chance = salesChanceService.getById(id);
		map.put("chance", chance);

		return "chance/input";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") long id,
			RedirectAttributes attributes) {

		salesChanceService.delete(id);

		attributes.addFlashAttribute("message", "删除成功！");
		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String add(SalesChance salesChance, HttpSession session,
			RedirectAttributes attributes) {

		User createBy = (User) session.getAttribute(GlobalNames.LOGIN_USER);
		salesChance.setCreateBy(createBy);
		salesChanceService.save(salesChance);

		attributes.addFlashAttribute("message", "添加成功！");
		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String toAddUI(Map<String, Object> map) {

		SalesChance chance = new SalesChance();
		chance.setCreateDate(new Date());
		map.put("chance", chance);

		return "chance/input";
	}

	@RequestMapping("/list")
	public String showList(HttpSession session,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request) {

		// // holdOn为空说明条件需要更新
		// if (pageNoStr == null && condition.getHoldOn() == null
		// && condition.getSearchLikeContact() != null) {
		// session.setAttribute(GlobalNames.CONDITION, condition);
		// System.out.println("set");
		// }
		// // 若holdOn不为空,从session取出保持的condition
		// condition = (ChanceCondition) session
		// .getAttribute(GlobalNames.CONDITION);
		// // 若取出的condition为空，则new一个并放入session
		// if (condition == null) {
		// condition = new ChanceCondition();
		// session.setAttribute(GlobalNames.CONDITION, condition);
		// System.out.println("new");
		// }
		Map<String, Object> reqParams = WebUtils.getParametersStartingWith(
				request, "search_");
		User loginUser = (User) session.getAttribute(GlobalNames.LOGIN_USER);
		reqParams.put("EQL_createById", loginUser.getId());
		reqParams.put("EQI_status", 1);

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
		return "chance/list";
	}

}
