package com.seatmanagement.test.config;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class TestErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		// conversion logic for decoding conversion
		String requestBody = IOUtils.toString(response.getBody());
		int httpStatusCode = response.getRawStatusCode();
		throw new RuntimeException("RequestBody : " + requestBody + "; HTTPStatusCode " + httpStatusCode);
	}
}