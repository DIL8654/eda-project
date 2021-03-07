package com.wiley.booking.bookingservice.service;

import com.wiley.booking.order.Booking;
import com.wiley.booking.payment.PaymentStatus;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
public interface BookingService {

  Booking createBooking(Booking booking);

  String updatePaymentRef(final String bookingRef, final String paymentRef, final
  PaymentStatus paymentStatus);

}
