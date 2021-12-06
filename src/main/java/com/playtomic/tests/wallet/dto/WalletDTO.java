package com.playtomic.tests.wallet.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WalletDTO extends ResponseDTO{

	private Long id;
	private BigDecimal balance;
	
}
