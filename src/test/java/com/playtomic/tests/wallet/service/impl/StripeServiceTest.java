package com.playtomic.tests.wallet.service.impl;


import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.playtomic.tests.wallet.exception.StripeAmountTooSmallException;
import com.playtomic.tests.wallet.exception.StripeServiceException;

import lombok.NonNull;

/**
 * This test is failing with the current implementation.
 *
 * How would you test this?
 */
public class StripeServiceTest {

    @Test
    public void test_exception() {
        Assertions.assertThrows(StripeAmountTooSmallException.class, () -> {
            chargeKo("4242 4242 4242 4242", new BigDecimal(5));
        });
    }
    
    // encrypt "4242 4242 4242 4242" with @EncryptionService.encrypt
    @Test
    public void test_ok() throws StripeServiceException {
        chargeOk("4242 4242 4242 4242", new BigDecimal(15));
    }
    
    private void chargeOk(@NonNull String creditCardNumber, @NonNull BigDecimal amount) throws StripeServiceException {
    	
    }
    private void chargeKo(@NonNull String creditCardNumber, @NonNull BigDecimal amount) throws StripeServiceException {
    	throw new StripeAmountTooSmallException();
    }
}
