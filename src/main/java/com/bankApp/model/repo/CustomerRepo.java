package com.bankApp.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankApp.web.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
