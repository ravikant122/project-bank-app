package com.bankApp.web.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankApp.model.service.UserService;
import com.bankApp.web.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value="getAllEmployees")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String getAllEmployees(ModelMap map) {
		map.addAttribute("employees", userService.getAllEmployees());
		return "showAllEmployees";
	}

	@GetMapping(value="addEmployee")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String addEmployeeGet(ModelMap map) {
		map.addAttribute("employee", new User());
		return "addEmployee";
	}
	
	@PostMapping(value="addEmployee")
	public String addEmployeePost(@Valid @ModelAttribute User user, BindingResult result, ModelMap map) {
		if(result.hasErrors()) {
			return "addEmployee";
		}

		Integer userId=user.getUserId();
		if(userId==null) {
			userService.addEmployee(user);
		}else {
			userService.updateEmployee(userId, user);
		}
		return "redirect:getAllEmployees";
	}
	
	@GetMapping(value="deleteEmployee")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteEmployee(@RequestParam(name="id") int id) {
		userService.deleteEmployee(id);
		return "redirect:getAllEmployees";
	}
	
	@GetMapping(value="updateEmployee")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String updateEmployee(@RequestParam(name="id") int id,ModelMap map) {
		map.addAttribute("employee", userService.getEmployeeById(id));
		return "updateEmployee";
	}
}
