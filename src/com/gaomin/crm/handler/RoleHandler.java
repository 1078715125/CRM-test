package com.gaomin.crm.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gaomin.crm.entity.Authority;
import com.gaomin.crm.entity.Role;
import com.gaomin.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.AuthorityService;

@Controller
@RequestMapping("/role")
public class RoleHandler {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;

	@RequestMapping(value = "/assign", method = RequestMethod.PUT)
	public String assign(Role role) {
		
		roleService.updateRelationship(role);
		
		return "redirect:/role/list";
	}

	@RequestMapping(value = "/assign/{id}", method = RequestMethod.GET)
	public String toAssign(@PathVariable("id") Long id, Map<String, Object> map) {
		Role role = roleService.getWithAuthById(id);
		List<Authority> parentAuthorities = authorityService
				.getParentAuthorities();

		map.put("role", role);
		map.put("parentAuthorities", parentAuthorities);
		return "role/assign";
	}

	@RequestMapping(value = "/create/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		roleService.delete(id);
		return "redirect:/role/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String update(Role role) {
		roleService.update(role);
		return "redirect:/role/list";
	}

	@RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
	public String toEdit(@PathVariable("id") Long id, Map<String, Object> map) {
		Role role = roleService.getById(id);
		map.put("role", role);
		return "role/input";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Role role) {
		roleService.create(role);
		return "redirect:/role/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String toCreate(Map<String, Object> map) {
		Role role = new Role();
		map.put("role", role);
		return "role/input";
	}

	@RequestMapping("/list")
	public String showList(HttpServletRequest request, Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}
		Map<String, Object> reqParams = new HashMap<String, Object>();
		Page<Role> page = roleService.getPage(pageNo, reqParams);

		map.put("page", page);
		return "role/list";
	}

}
