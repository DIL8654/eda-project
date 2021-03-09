package com.wiley.booking.booking;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import com.wiley.booking.user.User;

import lombok.Data;
import lombok.ToString;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
@ToString
@Data
@Document(collection = "BOOKINGS")
public class Booking implements Serializable {

  private String id;
  private String reservationRef;
  private String propertyId;
  private BookingStatus bookingStatus;
  private String paymentRefId;
  private User user;
  private Instant createdAt;
  private Instant modifiedAt;
  private Instant fromDate;
  private Instant toDate;

}
