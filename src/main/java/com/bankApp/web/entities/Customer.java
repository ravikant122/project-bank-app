package com.bankApp.web.entities;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "account")
@Entity
@Table(name="customer")
public class Customer {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	@NotEmpty(message="amount can't left blank")
	private String customerName;
	@NotEmpty(message="amount can't left blank")
	private String customerPassword;
	@NotEmpty(message="amount can't left blank")
	private String customerEmail;
	@NotEmpty(message="amount can't left blank")
	private String customerPhone;
	@NotEmpty(message="amount can't left blank")
	private String customerAddress;
	
	@JoinColumn(name="accountNo")
	@OneToOne(cascade = CascadeType.ALL)
	private Account account;
	
	public Customer(String customerName, String customerPassword, String customerEmail,
			String customerPhone,String customerAddress) {
		this.customerPassword = customerPassword;
		this.customerEmail = customerEmail;
		this.customerName=customerName;
		this.customerPhone=customerPhone;
		this.customerAddress=customerAddress;
	}
	
}
