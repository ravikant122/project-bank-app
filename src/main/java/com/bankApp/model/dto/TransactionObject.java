package com.bankApp.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionObject {
	
	@NotNull(message="Account number can't left blank")
	@Min(value=1, message="Min account number can be 1")
	private Integer fromAccountNumber;
	
	@NotNull(message="Account number can't left blank")
	@Min(value=1, message="Min account number can be 1")
	private Integer toAccountNumber;
	
	@NotNull(message="Amount can't left blank")
	@Min(value=100, message="Min amount 100")
	@Max(value=1000000, message="Max amount 1000000")
	private Double amount;
}
