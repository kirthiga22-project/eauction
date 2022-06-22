package com.cts.eauction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cts.eauction.dto.EauctionPayload;
import com.cts.eauction.model.Product;

@RestControllerAdvice
public class EauctionExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EauctionException.class)
	public ResponseEntity<EauctionPayload<Product>> handleException(EauctionException ex, WebRequest request){
		EauctionPayload<Product> payload = new EauctionPayload<>();
		payload.setError(ex.getError());
		payload.setStatus("Error");
		ex.getError().setPath(request.getDescription(false));
		return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
	}
}
