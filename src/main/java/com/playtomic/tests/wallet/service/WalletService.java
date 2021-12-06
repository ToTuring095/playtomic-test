package com.playtomic.tests.wallet.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.playtomic.tests.wallet.dto.WalletDTO;
import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.exception.StripeServiceException;
import com.playtomic.tests.wallet.repository.WalletRepository;

@Service
public class WalletService {
	private Logger log = LoggerFactory.getLogger(WalletService.class);
	
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private Mapper mapper; 
    @Autowired
    private StripeService stripeService;
    
	@Value("${spring.application.max-amount-chargable}")
    private BigDecimal maxAmountChargable;
	
	public WalletDTO getWallet(Long id) {
		WalletDTO ret;
		Wallet wallet;
		Optional<Wallet> optWallet = walletRepository.findById(id);
		if(!optWallet.isPresent()) {
			return null;
		}
		wallet = optWallet.get();
		ret = mapper.map(wallet, WalletDTO.class);
		log.info("WalletController.getWallet OK");
		return ret;
	}

	public boolean chargeWalletByCcn(String ccn) {
		try {
			stripeService.charge(ccn, maxAmountChargable);
			log.info("WalletController.chargeWalletByCcn OK");
			return true;
		}
		catch(StripeServiceException e) {
			log.error("WalletService.chargeWalletByCcn Wallet's charge for credit card number: [{}] failed", ccn);
			return false;
		}
	}
}
