package com.bankApp.model.service;

import java.util.List;

import com.bankApp.web.entities.User;

public interface UserService {
	public List<User> getAllEmployees();
	public User getEmployeeById(Integer employeeId);
	public User addEmployee(User user);
	public User deleteEmployee(Integer employeeId);
	public User updateEmployee(Integer employeeId,User user);
	public User findByUsername(String username);
}
