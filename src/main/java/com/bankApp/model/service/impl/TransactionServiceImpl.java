package com.bankApp.model.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankApp.model.exception.AccountNotFoundException;
import com.bankApp.model.exception.NotSufficientBalanceException;
import com.bankApp.model.exception.TransactionNotFoundException;
import com.bankApp.model.repo.TransactionRepo;
import com.bankApp.model.service.AccountService;
import com.bankApp.model.service.TransactionService;
import com.bankApp.web.entities.Account;
import com.bankApp.web.entities.Transaction;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	private AccountService accountService;
	private TransactionRepo transactionRepo;

	@Autowired
	public TransactionServiceImpl(AccountService accountService,TransactionRepo transactionRepo) {
		this.accountService = accountService;
		this.transactionRepo = transactionRepo;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return transactionRepo.findAll();
	}

	@Override
	public Transaction getTransactionById(int transactionId) {
		return transactionRepo.findById(transactionId).orElseThrow(
				() -> new TransactionNotFoundException("transaction with id : " + transactionId + " not found"));
	}

	@Override
	public Transaction addTransaction(Transaction transaction) {
		return transactionRepo.save(transaction);
	}

	@Override
	public Transaction updateTransaction(int transactionId, String status) {
		Transaction transactionToBeUpdated = getTransactionById(transactionId);
		transactionToBeUpdated.setStatus(status);
		transactionRepo.save(transactionToBeUpdated);
		return transactionToBeUpdated;
	}

	@Override
	@Transactional(rollbackOn = { AccountNotFoundException.class, NotSufficientBalanceException.class })
	public void depositMoney(Integer accountNumber, Double depositAmount) {
		Account account = accountService.getAccountById(accountNumber);
		Transaction transaction = new Transaction(null, accountNumber, 
										"DEPOSIT", depositAmount, "COMPLETED");
		account.getTransactions().add(transaction);
		transaction.setAccount(account);
		accountService.updateAccount(accountNumber, account.getAccountBalance() + depositAmount);
	}

	@Override
	@Transactional(rollbackOn = { AccountNotFoundException.class, NotSufficientBalanceException.class })
	public void withdrawMoney(Integer accountNumber, Double withdrawAmount) {

		Account account = accountService.getAccountById(accountNumber);
		if (account.getAccountBalance() >= withdrawAmount) {
			Transaction transaction = new Transaction(accountNumber, null,
											"WITHDRAW", withdrawAmount, "COMPLETED");
			transaction.setAccount(account);
			account.getTransactions().add(transaction);
			accountService.updateAccount(accountNumber, account.getAccountBalance() - withdrawAmount);
		} else {
			throw new NotSufficientBalanceException("accountNumber: " + accountNumber + " has not sufficient balance");
		}

	}

	@Override
	@Transactional(rollbackOn = { AccountNotFoundException.class, NotSufficientBalanceException.class })
	public void transferMoney(Integer fromAccountNumber, Integer toAccountNumber, Double amount) {

		Account fromAccount = accountService.getAccountById(fromAccountNumber);
		if (fromAccount.getAccountBalance() >= amount) {

			Account toAccount = accountService.getAccountById(toAccountNumber);

			if (amount >= 200000) {
				Transaction transaction = new Transaction(fromAccountNumber, toAccountNumber,
											"TRANSFER", amount, "PENDING");
				transaction.setAccount(fromAccount);
				addTransaction(transaction);
			} else {
				Transaction transaction = new Transaction(fromAccountNumber, toAccountNumber,
						"TRANSFER", amount, "COMPLETED");
				fromAccount.getTransactions().add(transaction);
				accountService.updateAccount(fromAccountNumber, fromAccount.getAccountBalance() - amount);
				accountService.updateAccount(toAccountNumber, toAccount.getAccountBalance() + amount);
			}

		} else {
			throw new NotSufficientBalanceException(
					"accountNumber: " + fromAccountNumber + " has not sufficient balance");
		}

	}

	@Override
	public List<Transaction> getTransactionListByStatus(String status) {
		return transactionRepo.findByStatus(status);
	}

	@Override
	@Transactional(rollbackOn = { AccountNotFoundException.class, NotSufficientBalanceException.class,TransactionNotFoundException.class })
	public void approvePendingTransaction(Integer transactionId) {
		Transaction transaction = getTransactionById(transactionId);
		Double amount = transaction.getAmount();

		Integer fromAccountNumber = transaction.getFromAccountNumber();
		Integer toAccountNumber = transaction.getToAccountNumber();

		Account fromAccount = accountService.getAccountById(fromAccountNumber);
		Account toAccount = accountService.getAccountById(toAccountNumber);

		if (fromAccount.getAccountBalance() >= amount) {
			accountService.updateAccount(fromAccountNumber, fromAccount.getAccountBalance() - amount);
			accountService.updateAccount(toAccountNumber, toAccount.getAccountBalance() + amount);
			transaction.setStatus("COMPLETED");
			addTransaction(transaction);
		} else {
			throw new NotSufficientBalanceException(
					"account with id : " + fromAccountNumber + " don't have sufficient balance");
		}
	}

	@Override
	public void rejectPendingTransaction(Integer transactionId) {
		Transaction transaction = getTransactionById(transactionId);
		transaction.setStatus("REJECTED");
		addTransaction(transaction);
	}

	@Override
	public List<Transaction> accountStatement(Integer accountNumber) {
		List<Transaction> list1=transactionRepo.findByFromAccountNumber(accountNumber);
		List<Transaction> list2=transactionRepo.findByToAccountNumber(accountNumber);
		
		list1.addAll(list2);
		return list1;
	}

}
