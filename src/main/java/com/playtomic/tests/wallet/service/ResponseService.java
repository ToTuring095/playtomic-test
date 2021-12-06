package com.playtomic.tests.wallet.service;

import org.springframework.stereotype.Service;

import com.playtomic.tests.wallet.dto.ResponseDTO;
import com.playtomic.tests.wallet.util.ReturnConstants;

@Service
public class ResponseService {

	public ResponseDTO processResponseBeforeSend(ResponseDTO response) {
		if(response == null) {
			response = new ResponseDTO();
			response.setCode(ReturnConstants.RETURN_CODE_KO);
		}
		else {
			response.setCode(ReturnConstants.RETURN_CODE_OK);
		}
		return response;
	}
}
