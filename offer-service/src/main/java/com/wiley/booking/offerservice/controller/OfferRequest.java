package com.wiley.booking.offerservice.controller;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author Dilanka
 * @create at 3/8/2021
 */
@Data
public class OfferRequest implements Serializable {

  private List<String> propertyIds;
  private Long from;
  private Long to;

}
