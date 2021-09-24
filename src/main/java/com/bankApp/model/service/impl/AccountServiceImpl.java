package com.bankApp.model.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankApp.model.exception.AccountNotFoundException;
import com.bankApp.model.repo.AccountRepo;
import com.bankApp.model.service.AccountService;
import com.bankApp.web.entities.Account;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private AccountRepo accountRepo;

	@Autowired
	public void setAccountDao(AccountRepo accountRepo) {
		this.accountRepo = accountRepo;
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepo.findAll();
	}

	@Override
	public Account getAccountById(Integer id) {
		return accountRepo.findById(id)
				.orElseThrow(() -> new AccountNotFoundException("account with id "+id+" is not found"));
	}

	@Override
	public Account addAccount(Account account) {
		return accountRepo.save(account);
	}

	@Override
	public Double getAccountBalance(Integer accountId) {
		Account account = getAccountById(accountId);
		return account.getAccountBalance();
	}

	@Override
	public Account updateAccount(Integer accountId, Double balance) {
		Account accountToBeUpdated = getAccountById(accountId);
		accountToBeUpdated.setAccountBalance(balance);
		accountRepo.save(accountToBeUpdated);
		return accountToBeUpdated;
	}

}
