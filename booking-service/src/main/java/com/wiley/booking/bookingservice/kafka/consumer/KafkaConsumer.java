package com.wiley.booking.bookingservice.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.wiley.booking.bookingservice.service.BookingService;
import com.wiley.booking.order.Booking;
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

  @KafkaListener(topics = "${app.booking.createtopic}")
  public void receiveBooking(Booking booking) {
    log.info("received booking payload='{}'", booking.toString());
    bookingService.createBooking(booking);
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
