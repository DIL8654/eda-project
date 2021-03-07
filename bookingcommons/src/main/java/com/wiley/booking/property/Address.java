package com.wiley.booking.property;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */

@Data
public class Address implements Serializable {

  private String street;
  private String city;
  private String country;
  private String postcode;
}
