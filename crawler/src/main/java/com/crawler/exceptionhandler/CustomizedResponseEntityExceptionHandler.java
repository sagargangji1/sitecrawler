package com.crawler.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.crawler.pojo.ResponseWrapper;
import com.crawler.utils.ResponseConstantUtility;
import com.fasterxml.jackson.core.JsonParseException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NullPointerException.class)
  public final ResponseEntity<ResponseWrapper> handleCustomException(Exception ex, WebRequest request) {
	  ResponseWrapper responseWrapper = new ResponseWrapper();
	  responseWrapper.setStatus(ResponseConstantUtility.getGeneralExceptionMessage());
    return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
  }
  
  @ExceptionHandler(JsonParseException.class)
  public final ResponseEntity<ResponseWrapper> handleJsonParseException(JsonParseException ex, WebRequest request) {
	  ResponseWrapper responseWrapper = new ResponseWrapper();
	  responseWrapper.setStatus(ResponseConstantUtility.getJsonParseException());
    return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
  }
  
}