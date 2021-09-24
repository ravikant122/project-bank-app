package com.bankApp.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawObject {
	
	@NotNull(message="Account number can't left blank")
	@Min(value=1, message="Min account number can be 1")
	private Integer accountNumber;
	
	@NotNull(message="amount can't left blank")
	@Min(value=100, message="min amount 100")
	@Max(value=100000, message="max amount 100000")
	private Double amount;
}
