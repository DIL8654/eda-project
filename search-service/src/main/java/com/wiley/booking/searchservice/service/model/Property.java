package com.wiley.booking.searchservice.service.model;

import java.time.Instant;

import com.wiley.booking.property.Address;

import lombok.Builder;
import lombok.Data;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
@Data
@Builder
public class Property {

  private String name;
  private Address address;
  private double price;
  private double discount;
  private double discountedPrice;
  private String email;
  private String hotlineMobile;
  private boolean available;
  private Instant fromDate;
  private Instant toDate;
}
