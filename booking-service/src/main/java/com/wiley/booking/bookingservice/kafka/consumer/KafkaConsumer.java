package com.wiley.booking.bookingservice.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.wiley.booking.bookingservice.service.BookingMapper;
import com.wiley.booking.bookingservice.service.BookingService;
import com.wiley.booking.order.Order;
import com.wiley.booking.payment.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

  private CountDownLatch latch = new CountDownLatch(1);

  private final BookingService bookingService;
  private final BookingMapper bookingMapper;

  @KafkaListener(topics = "${app.booking.createtopic}")
  public void receiveBooking(Order order) {
    log.info("received booking payload='{}'", order.toString());

    bookingService.createBooking(bookingMapper.getBooking(order));
    latch.countDown();
  }

  @KafkaListener(topics = "${app.payment.paymenttopic}")
  public void receivePayment(Payment payment) {
    log.info("received payment payload='{}'", payment.toString());
    bookingService.updatePaymentRef(
        payment.getBookingRef(), payment.getPaymentRef(), payment.getPaymentStatus());
    latch.countDown();
  }
}
