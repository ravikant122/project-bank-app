package com.bankApp.model.service;

import java.util.List;

import com.bankApp.web.entities.Customer;

public interface CustomerService {
	public void addCustomer(Customer customer);
	public Customer getCustomer(Integer id);
	public List<Customer> getAllCustomers();
	public Customer updateCustomer(Integer id,Customer customer);
	public Customer deleteCustomer(Integer id);
}
