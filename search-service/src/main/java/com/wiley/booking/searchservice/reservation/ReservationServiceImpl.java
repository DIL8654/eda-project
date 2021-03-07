package com.wiley.booking.searchservice.reservation;

import org.springframework.stereotype.Service;

import com.wiley.booking.order.Booking;
import com.wiley.booking.order.BookingStatus;
import com.wiley.booking.searchservice.kafka.config.KafkaTopicConfig;
import com.wiley.booking.searchservice.kafka.producer.KafkaProducer;
import com.wiley.booking.searchservice.util.ReservationRefGenerator;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

  private final KafkaProducer kafkaProducer;
  private final KafkaTopicConfig kafkaTopicConfig;

  @Override
  public Booking createReservation(Booking booking) {
    booking.setReservationRef(ReservationRefGenerator.generateReservationRef());
    booking.setBookingStatus(BookingStatus.BOOKING_CREATED);

    // fire kafka event for reservation creation
    kafkaProducer.send(kafkaTopicConfig.getCreatebooking(), booking);

    return booking;
  }
}
