package com.bankApp.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankApp.model.dto.DepositObject;
import com.bankApp.model.dto.TransactionObject;
import com.bankApp.model.dto.WithdrawObject;
import com.bankApp.model.service.TransactionService;
import com.bankApp.web.entities.Transaction;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping(value="showAllTransactions")
	public String getAllTransaction(Model model) {
		model.addAttribute("transactions", transactionService.getAllTransactions());
		return "showAllTransactions";
	}

	@GetMapping(value="pendingTransaction")
	public String getAllPendingTransaction(Model model) {
		model.addAttribute("transactions", transactionService.getTransactionListByStatus("PENDING"));
		return "pendingTransaction";
	}
	
	@GetMapping(value="approveTransaction")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String approveTransaction(@RequestParam (name="id")Integer transactionId) {
		transactionService.approvePendingTransaction(transactionId);
		return "redirect:showAllTransactions";
	}
	
	@GetMapping(value="rejectTransaction")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String rejectTransaction(@RequestParam (name="id")Integer transactionId) {
		transactionService.rejectPendingTransaction(transactionId);
		return "redirect:showAllTransactions";
	}
	
	@GetMapping(value="withdrawMoney")
	@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
	public String withdrawMoneyGet(Model model) {
		model.addAttribute("transactionObject", new TransactionObject());
		return "withdraw";
	}

	@PostMapping(value="withdrawMoney")
	public String withdrawMoneyPost(@Valid @ModelAttribute WithdrawObject tx, BindingResult result) {
		if(result.hasErrors()) {
			return "withdraw";
		}
		Integer accountNumber = tx.getAccountNumber();
		Double withdrawAmount = tx.getAmount();
		try {
			transactionService.withdrawMoney(accountNumber, withdrawAmount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:showAllTransactions";
	}

	@GetMapping(value="depositMoney")
	@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
	public String depositMoneyGet(Model model) {
		model.addAttribute("depositObject", new DepositObject());
		return "deposit";
	}

	@PostMapping(value="depositMoney")
	public String depositMoneyPost(@Valid @ModelAttribute DepositObject tx, BindingResult result) {
		if(result.hasErrors()) {
			return "deposit";
		}
		Integer accountNumber = tx.getAccountNumber();
		Double depositAmount = tx.getAmount();

		transactionService.depositMoney(accountNumber, depositAmount);

		return "redirect:showAllTransactions";
	}

	@GetMapping(value="transferMoney")
	@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
	public String transferMoneyGet(Model model) {
		model.addAttribute("transactionObject", new TransactionObject());
		return "transfer";
	}

	@PostMapping(value="transferMoney")
	public String transferMoneyPost(@Valid @ModelAttribute TransactionObject tx, BindingResult result) {
		if(result.hasErrors()) {
			return "transfer";
		}
		Integer fromAccountNumber = tx.getFromAccountNumber();
		Integer toAccountNumber = tx.getToAccountNumber();
		Double amount = tx.getAmount();

		transactionService.transferMoney(fromAccountNumber, toAccountNumber, amount);

		return "redirect:showAllTransactions";
	}

	@GetMapping(value="accountStatement")
	public String getAccountStatement(@RequestParam (name="id")int id,Model model) {
		List<Transaction> accountStatement = transactionService.accountStatement(id);
		model.addAttribute("transactions", accountStatement);
		return "showAllTransactions";
	}
}
