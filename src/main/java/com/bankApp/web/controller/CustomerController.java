package com.bankApp.web.controller;

import javax.servlet.http.HttpServletRequest;
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

import com.bankApp.model.service.CustomerService;
import com.bankApp.web.entities.Account;
import com.bankApp.web.entities.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}
	
	@GetMapping(value = "getCustomer")
	public String getCustomerJsp() {
		return "getCustomer";
	}
	
	@PostMapping(value = "getCustomerController")
	public String getCustomer(@ModelAttribute(name="customerId") Integer customerId, ModelMap map) {
		map.addAttribute("customer",service.getCustomer(customerId));
		return "showCustomer";
	}
	
	@GetMapping(value = "getAllCustomers")
	public String getAllCustomers(ModelMap map) {
		System.out.println("h5");
		map.addAttribute("customers",service.getAllCustomers());
		System.out.println("h6");
		return "showAllCustomers";
	}
	
	@GetMapping(value = "addCustomer")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String addCustomerJsp(ModelMap map) {
		map.addAttribute("customer",new Customer());
		return "addCustomer";
	}
	
	@PostMapping(value = "addCustomerController")
	public String addCustomer(HttpServletRequest request,@Valid @ModelAttribute(name = "customer") Customer customer,
			BindingResult result) {
		if(result.hasFieldErrors()) {
			return "addCustomer";
		}
		Integer customerId = customer.getCustomerId();
		if (customerId == null) {
			System.out.println("h1");
			Double balance = Double.parseDouble(request.getParameter("accountBalance"));
			
			Account account = new Account(balance);
			customer.setAccount(account);
			account.setCustomer(customer);

			System.out.println("h1");
			service.addCustomer(customer);
			System.out.println("h4");
		}else {
			service.updateCustomer(customerId, customer);
		}

		return "redirect:getAllCustomers";
	}
	
	@GetMapping(value="updateCustomer")
	@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
	public String updateCustomer(@RequestParam(name = "id") int customerId, ModelMap map) {
		map.addAttribute("customer", service.getCustomer(customerId));
		return "updateCustomer";
	}
	
	@GetMapping(value="deleteCustomer")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteCustomer(@RequestParam(name = "id") int customerId) {
		service.deleteCustomer(customerId);
		return "redirect:getAllCustomers";
	}
	
}
