package com.gaomin.crm.handler;

import com.gaomin.crm.entity.Authority;
import com.gaomin.crm.entity.Role;
import com.gaomin.crm.entity.User;
import com.gaomin.crm.model.Navigate;
import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.RoleService;
import com.gaomin.crm.service.UserService;
import com.gaomin.crm.utils.GlobalNames;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserHandler {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@ResponseBody
	@RequestMapping("/navigate")
	public List<Navigate> navigate(HttpServletRequest request) {
		List<Navigate> navigates = new ArrayList<Navigate>();
		Navigate root = new Navigate(Long.MAX_VALUE, "客户关系管理系统");

		String path = request.getContextPath();

		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getPrincipals().getPrimaryPrincipal();

		Map<Long, Navigate> authMap = new HashMap<Long, Navigate>();
		Navigate pn = null;
		List<Authority> subAuthorities = user.getRole().getAuthorities();
		for (Authority subAuth : subAuthorities) {
			Navigate sn = new Navigate(subAuth.getId(), subAuth.getDisplayName(), path +
					subAuth.getUrl());
			
			Authority pareAuth = subAuth.getParentAuthority();
			pn = authMap.get(pareAuth.getId());
			if (pn == null) {
				pn = new Navigate(pareAuth.getId(), pareAuth.getDisplayName());
				pn.setState("open");
				
				root.getChildren().add(pn);
				authMap.put(pareAuth.getId(), pn);
			}
			
			pn.getChildren().add(sn);
			
		}
		navigates.add(root);
		return navigates;
	}

	@RequestMapping(value = "/create/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {

		userService.delete(id);

		return "redirect:/user/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String update(User user) {

		userService.update(user);

		return "redirect:/user/list";
	}

	@RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
	public String toEdit(@PathVariable("id") Long id, Map<String, Object> map) {

		User user = userService.getById(id);
		List<Role> roles = roleService.getAllRoles();
		Map<String, Object> allStatus = new HashMap<String, Object>();
		allStatus.put("1", "有效");
		allStatus.put("0", "无效");

		map.put("user", user);
		map.put("roles", roles);
		map.put("allStatus", allStatus);
		return "user/input";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(User user) {

		userService.create(user);

		return "redirect:/user/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String toCreate(Map<String, Object> map) {

		User user = new User();
		user.setEnabled(1);
		List<Role> roles = roleService.getAllRoles();
		Map<String, Object> allStatus = new HashMap<String, Object>();
		allStatus.put("1", "有效");
		allStatus.put("0", "无效");

		map.put("user", user);
		map.put("roles", roles);
		map.put("allStatus", allStatus);
		return "user/input";
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

		Page<User> page = userService.getPage(pageNo, reqParams);
		map.put("page", page);
		map.putAll(reqParams);

		return "user/list";
	}

	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpSession session, HttpServletResponse response)
			throws IOException {

		session.invalidate();

		response.getWriter().write("操作成功");
	}

	@RequestMapping("/shiroLogout")
	@ResponseBody
	public void shiroLogout(HttpSession session, HttpServletResponse response)
			throws IOException {

		session.invalidate();

		response.getWriter().write("操作成功");
		SecurityUtils.getSubject().logout();
	}

	@RequestMapping(value = "/shiroLogin", method = RequestMethod.POST)
	public String shiroLogin(User user, HttpSession session,
			RedirectAttributes attributes, Locale locale) {

		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(
					user.getName(), user.getPassword());
			token.setRememberMe(true);

			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				e.printStackTrace();
				String code = "error.user.login";
				String[] args = null;
				String message = messageSource.getMessage(code, args, locale);
				attributes.addFlashAttribute("message", message);
				return "redirect:/index";
			}
		}

		user = (User) currentUser.getPrincipals().getPrimaryPrincipal();

		session.setAttribute(GlobalNames.LOGIN_USER, user);
		return "redirect:/success";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, HttpSession session,
			RedirectAttributes attributes, Locale locale) {

		user = userService.login(user);

		if (user != null) {
			session.setAttribute(GlobalNames.LOGIN_USER, user);
			return "redirect:/success";
		}

		String code = "error.user.login";
		String[] args = null;
		String message = messageSource.getMessage(code, args, locale);
		attributes.addFlashAttribute("message", message);
		return "redirect:/index";
	}

}
