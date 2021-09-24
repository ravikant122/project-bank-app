package com.bankApp.model.service;

import java.util.List;

import com.bankApp.web.entities.Account;

public interface AccountService {
	public List<Account> getAllAccounts();
	public Account getAccountById(Integer accountId);
	public Account addAccount(Account account);
	public Double getAccountBalance(Integer accountId);
	public Account updateAccount(Integer accountId, Double balance);

}
