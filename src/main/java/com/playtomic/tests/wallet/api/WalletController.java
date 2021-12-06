package com.playtomic.tests.wallet.api;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playtomic.tests.wallet.dto.ResponseDTO;
import com.playtomic.tests.wallet.service.EncryptionService;
import com.playtomic.tests.wallet.service.ResponseService;
import com.playtomic.tests.wallet.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private Logger log = LoggerFactory.getLogger(WalletController.class);
    
    @Autowired
    private WalletService walletService;
    @Autowired
    private ResponseService responseService;
    
    
    
    // security checks take for granted (example: in a dedicated microservice with spring security)
    @GetMapping(path="/v01/{id}")
    public ResponseEntity<ResponseDTO> getWallet(@PathVariable Long id) {
        log.info("WalletController.getWallet START");
    	ResponseDTO wallet = walletService.getWallet(id);
    	log.info("WalletController.getWallet wallet [{}]", wallet);
    	wallet = responseService.processResponseBeforeSend(wallet);
    	return new ResponseEntity<>(wallet, HttpStatus.OK);
    }
    
    // sanitized (without '-' delimiter) and encrypted creditCardNumber from FE side take for granted
    @PutMapping(path="/v01/charge")
    public ResponseEntity<ResponseDTO> chargeWalletByCcn(@RequestBody String creditCardNumber) {
		log.info("WalletController.chargeWalletByCcn START");
		ResponseDTO ret = null;
    	try {
    		log.info("WalletController.chargeWalletByCcn creditCardNumber: [{}]",creditCardNumber);
    		creditCardNumber = EncryptionService.decrypt2(creditCardNumber);
    		log.info("WalletController.chargeWalletByCcn creditCardNumber after decrypt: [{}]",creditCardNumber);
    		boolean isCharged = walletService.chargeWalletByCcn(creditCardNumber);
    		if(isCharged)
    			ret = new ResponseDTO();
			ret = responseService.processResponseBeforeSend(ret);
			return new ResponseEntity<>(ret, HttpStatus.OK);
		} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
				| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ret, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
}
