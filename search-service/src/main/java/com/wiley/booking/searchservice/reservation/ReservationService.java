package com.wiley.booking.searchservice.reservation;

import com.wiley.booking.booking.Booking;
import com.wiley.booking.order.Order;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
public interface ReservationService {

  Order createReservation(final Order order);

}
