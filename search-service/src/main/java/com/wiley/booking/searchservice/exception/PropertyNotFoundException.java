package com.wiley.booking.searchservice.exception;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
public class PropertyNotFoundException extends RuntimeException {

  public PropertyNotFoundException(final String s) {
    super(s);
  }
}
