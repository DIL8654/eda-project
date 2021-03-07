package com.wiley.booking.payment;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

import lombok.Data;
import lombok.ToString;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */

@Data
@ToString
public class PaymentRequest implements Serializable {

  private String cardNumber;
  private String cvv;
  private double paymentAmount;
  private String bookingRef;
  private String userId;
}
