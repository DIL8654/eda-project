package com.wiley.booking.order;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.core.serializer.Deserializer;
import org.springframework.data.mongodb.core.mapping.Document;

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

  private String reservationRef;
  private String propertyId;
  private BookingStatus bookingStatus;
  private String paymentRefId;
  private String userId;
  private Instant createdAt;
  private Instant modifiedAt;

}
