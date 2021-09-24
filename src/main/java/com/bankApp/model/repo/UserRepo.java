package com.bankApp.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankApp.web.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	public List<User> findByProfile(String profile);
	public User findByUsername(String username);
}