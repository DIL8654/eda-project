package com.wiley.booking.searchservice.reservation;

import org.springframework.stereotype.Service;

import com.wiley.booking.booking.Booking;
import com.wiley.booking.booking.BookingStatus;
import com.wiley.booking.order.Order;
import com.wiley.booking.searchservice.kafka.config.KafkaTopicConfig;
import com.wiley.booking.searchservice.kafka.producer.KafkaProducer;
import com.wiley.booking.searchservice.repository.OrderRepository;
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
  private final OrderRepository orderRepository;

  @Override
  public Order createReservation(Order order) {
    order.setReservationRef(ReservationRefGenerator.generateReservationRef());
    order.setBookingStatus(BookingStatus.BOOKING_CREATED);

    Order savedOrder = orderRepository.save(order);
    // fire kafka event for reservation creation
    kafkaProducer.send(kafkaTopicConfig.getCreatebooking(), savedOrder);

    return order;
  }
}
