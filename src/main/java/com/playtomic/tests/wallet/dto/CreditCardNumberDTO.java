package com.playtomic.tests.wallet.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class CreditCardNumberDTO{

	@NotNull
	@Pattern(regexp="^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|", message = "Credit card number not valid")
	@Pattern(regexp="(?<mastercard>5[1-5][0-9]{14})|", message = "Credit card number not valid")
	private String number;
	
}
