package com.wiley.booking.property;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */

@Data
@Document(collection = "PROPERTY")
public class Property implements Serializable {

  private String name;
  private Address address;
  private double price;
  private double discountRate;
  private String email;
  private String hotlineMobile;
  private boolean available;
  private Instant fromDate;
  private Instant toDate;
}
