package com.cts.eauction.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.WebRequest;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EauctionExceptionHandler.class)
public class EauctionExceptionHandlerTest extends TestCase{
	
	@Autowired
	EauctionExceptionHandler handler;
	
	@Mock
	WebRequest webRequest;
	
	@Test
	public void testExceptionHandler() {
		com.cts.eauction.dto.Error error =new com.cts.eauction.dto.Error();
		error.setErrorCode(500);
		error.setErrorMessage("test");
		EauctionException ex = new EauctionException("exception", error);
		assertNotNull(handler.handleException(ex, webRequest));
	}
	

}
