package com.wiley.booking.searchservice.reservation;

import com.wiley.booking.order.Booking;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
public interface ReservationService {

  Booking createReservation(final Booking booking);

}
