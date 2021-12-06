package com.playtomic.tests.wallet.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


import com.playtomic.tests.wallet.util.EncryptionConstants;

public class EncryptionService {

	private static SecretKey KEY = null;
	private static IvParameterSpec VECTOR = null;
	
	public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
		if(KEY != null)
			return KEY;
	    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	    keyGenerator.init(n);
	    SecretKey key = keyGenerator.generateKey();
	    return key;
	}
	public static IvParameterSpec generateIv() {
		if(VECTOR != null)
			return VECTOR;
	    byte[] iv = new byte[16];
	    new SecureRandom().nextBytes(iv);
	    return new IvParameterSpec(iv);
	}
	public static String decrypt(String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException,
	    InvalidAlgorithmParameterException, InvalidKeyException,
	    BadPaddingException, IllegalBlockSizeException {
	    KEY = generateKey(128);
	    VECTOR = generateIv();
	    Cipher cipher = Cipher.getInstance(EncryptionConstants.ALGORITHM);
	    cipher.init(Cipher.DECRYPT_MODE, KEY, VECTOR);
	    byte[] plainText = cipher.doFinal(Base64.getDecoder()
	        .decode(cipherText));
	    return new String(plainText);
	}
	public static String encrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException,
	    InvalidAlgorithmParameterException, InvalidKeyException,
	    BadPaddingException, IllegalBlockSizeException {
		KEY = generateKey(128);
	    VECTOR = generateIv();
	    Cipher cipher = Cipher.getInstance(EncryptionConstants.ALGORITHM);
	    cipher.init(Cipher.ENCRYPT_MODE, KEY, VECTOR);
	    byte[] cipherText = cipher.doFinal(input.getBytes());
	    return Base64.getEncoder()
	        .encodeToString(cipherText);
	}
	public static String decrypt2(String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException,
    InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException {
    return new String(cipherText);
}
}
