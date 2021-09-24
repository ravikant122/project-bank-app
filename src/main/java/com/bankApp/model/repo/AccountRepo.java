package com.bankApp.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankApp.web.entities.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>{

}
