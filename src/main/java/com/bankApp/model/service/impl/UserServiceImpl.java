package com.bankApp.model.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankApp.model.exception.EmployeeNotFoundException;
import com.bankApp.model.repo.UserRepo;
import com.bankApp.model.service.UserService;
import com.bankApp.web.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepo userRepo ;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> getAllEmployees() {
		return userRepo.findByProfile("ROLE_EMPLOYEE");
	}

	@Override
	public User getEmployeeById(Integer employeeId) {
		return userRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("employee with id : " + employeeId + " not found"));
	}

	@Override
	public User addEmployee(User user) {
		user.setProfile("ROLE_EMPLOYEE");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public User deleteEmployee(Integer employeeId) {
		User employeeToBeDeleted = getEmployeeById(employeeId);
		userRepo.delete(employeeToBeDeleted);
		return employeeToBeDeleted;
	}

	@Override
	public User updateEmployee(Integer employeeId,User user) {
		User employeeToBeUpdated = getEmployeeById(employeeId);
		employeeToBeUpdated.setEmail(user.getEmail());
		employeeToBeUpdated.setPassword(user.getPassword());
		employeeToBeUpdated.setPhone(user.getPhone());
		employeeToBeUpdated.setSalary(user.getSalary());
		userRepo.save(employeeToBeUpdated);
		return employeeToBeUpdated;
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
}
