package com.wiley.booking.bookingservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.wiley.booking.booking.Booking;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
public interface BookingRepository extends CrudRepository<Booking, String> {

}
