package com.playtomic.tests.wallet.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/stripe")
public class TestStripeController {

	@PostMapping(path = "/v01/ok/charges")
	public ResponseEntity<Void> testOkCharge() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
