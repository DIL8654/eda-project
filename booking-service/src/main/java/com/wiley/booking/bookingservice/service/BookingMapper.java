package com.wiley.booking.bookingservice.service;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.wiley.booking.booking.Booking;
import com.wiley.booking.order.Order;
import com.wiley.booking.user.User;
import com.wiley.booking.user.UserType;

import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/9/2021
 */
@Log4j2
@Component
public class BookingMapper {


  public Booking getBooking(Order order)
  {
    Booking booking = new Booking();
    booking.setReservationRef(order.getReservationRef());
    booking.setPropertyId(order.getPropertyId());
    booking.setBookingStatus(order.getBookingStatus());
    booking.setPaymentRefId(null); // not needed in this stage

    User user = new User();
    user.setId(order.getUserId());

    // find user details from DB and set it here
    user.setEmail("user@user.com");
    user.setMobile("077712345");
    user.setUserType(UserType.PREMIUM);

    booking.setUser(user);
    booking.setCreatedAt(Instant.now());
    booking.setModifiedAt(Instant.now());
    booking.setFromDate(order.getFromDate());
    booking.setToDate(order.getToDate());
    return booking;
  }
}
