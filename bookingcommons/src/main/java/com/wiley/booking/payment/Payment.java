package com.wiley.booking.payment;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
@Data
@Document(collection = "PAYMENT")
public class Payment implements Serializable {

  private String userRef;
  private String last4Digits;
  private String bookingRef;
  private double amount;
  private Instant createdDate;
  private PaymentStatus paymentStatus;
  private String paymentRef;
}
