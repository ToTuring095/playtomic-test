package com.playtomic.tests.wallet.api;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playtomic.tests.wallet.service.EncryptionService;

@RestController
@RequestMapping("/api/encryption")
public class EncryptionController {

	@GetMapping(path="/v01/encrypt")
	public ResponseEntity<String> encrypt(@RequestBody String creditCardNumber){
		String ret;
		try {
			ret = EncryptionService.encrypt(creditCardNumber);
			return new ResponseEntity<>(ret, HttpStatus.OK);
		} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
				| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(path="/v01/decrypt")
	public ResponseEntity<String> decrypt(@RequestBody String creditCardNumber){
		String ret;
		try {
			ret = EncryptionService.decrypt(creditCardNumber);
			return new ResponseEntity<>(ret, HttpStatus.OK);
		} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
				| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
