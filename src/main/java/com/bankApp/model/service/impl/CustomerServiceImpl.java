package com.bankApp.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankApp.model.exception.CustomerNotFoundException;
import com.bankApp.model.repo.CustomerRepo;
import com.bankApp.model.service.CustomerService;
import com.bankApp.web.entities.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepo customerRepo;

	@Autowired
	public CustomerServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	public Customer getCustomer(Integer id) {
		return customerRepo.findById(id).orElseThrow
				(()-> new CustomerNotFoundException("Customer with id "+ id +" is not found"));
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}
	
	@Override
	public void addCustomer(Customer customer) {
		System.out.println("h2");
		customerRepo.save(customer);
		System.out.println("h3");
	}

	@Override
	public Customer updateCustomer(Integer id,Customer customer) {
		Customer customerToUpdate=getCustomer(id);
		customerToUpdate.setCustomerEmail(customer.getCustomerEmail());
		customerToUpdate.setCustomerName(customer.getCustomerName());
		customerToUpdate.setCustomerPhone(customer.getCustomerPhone());
		customerToUpdate.setCustomerPassword(customer.getCustomerPassword());
		customerToUpdate.setCustomerAddress(customer.getCustomerAddress());
		customerRepo.save(customerToUpdate);
		return customerToUpdate;
	}

	@Override
	public Customer deleteCustomer(Integer id) {
		Customer customerToDelete=getCustomer(id);
		customerRepo.delete(customerToDelete);
		return customerToDelete;
	}

}
