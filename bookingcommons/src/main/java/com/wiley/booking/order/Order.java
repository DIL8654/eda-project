package com.wiley.booking.order;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import com.wiley.booking.booking.BookingStatus;
import com.wiley.booking.user.User;

import lombok.Data;
import lombok.ToString;

/**
 * @author Dilanka
 * @create at 3/9/2021
 */
@ToString
@Data
@Document(collection = "ORDERS")
public class Order implements Serializable {
  private String id;
  private String reservationRef;
  private String propertyId;
  private BookingStatus bookingStatus;
  private String userId;
  private Instant createdAt;
  private Instant modifiedAt;
  private Instant fromDate;
  private Instant toDate;
}
