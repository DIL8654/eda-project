package com.wiley.booking.offer;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

/**
 * @author Dilanka
 * @create at 3/8/2021
 */

@ToString
@Data
@Document(collection = "OFFERS")
public class Offer {

  private String id;
  private String propertyId;
  private Instant from;
  private Instant to;
  private double discount;

}
