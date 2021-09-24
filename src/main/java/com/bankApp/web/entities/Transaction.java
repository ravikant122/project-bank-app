package com.bankApp.web.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "account" })
@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;
	private Integer fromAccountNumber;
	private Integer toAccountNumber;
	private String transactionType;
	private Double amount;
	private String status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_fk")
	private Account account;

	public Transaction(Integer fromAccountNumber, Integer toAccountNumber, String transactionType,
			Double amount, String status) {
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.transactionType = transactionType;
		this.amount = amount;
		this.status = status;
	}

	

}
