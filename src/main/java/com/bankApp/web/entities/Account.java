package com.bankApp.web.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"customer","transactions"})
@Entity
@Table(name="account")
public class Account {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountNo;
	private Double accountBalance;
	
	@OneToOne(mappedBy = "account")
	private Customer customer;
	
	@OneToMany(mappedBy = "account",fetch = FetchType.EAGER,cascade =CascadeType.MERGE)
	private List<Transaction> transactions=new ArrayList<>();
	
	public Account(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
}
