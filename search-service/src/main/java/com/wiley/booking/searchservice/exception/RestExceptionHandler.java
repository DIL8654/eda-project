package com.wiley.booking.searchservice.exception;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(PropertyNotFoundException.class)
  protected ResponseEntity<Object> handlePropertyNotFoundException(PropertyNotFoundException ex) {
    ApiError apiError = new ApiError(EXPECTATION_FAILED);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }
}
