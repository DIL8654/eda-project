package com.wiley.booking.searchservice.kafka.producer;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.wiley.booking.booking.Booking;
import com.wiley.booking.order.Order;

import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Log4j2
@Component
public class KafkaProducer {

  @Autowired private KafkaTemplate<String, Object> kafkaTemplate;

  @Transactional
  public void send(String topic, Order payload) {
    log.info("sending payload='{}' to topic='{}'", payload.toString(), topic);

    final ListenableFuture<SendResult<String, Object>> listenableFuture =
        kafkaTemplate.executeInTransaction(
            operations -> operations.send(topic, payload.getReservationRef(), payload));

    listenableFuture.addCallback(
        getCallback(
            () -> "Order event fired successfully (Order Id={}, data={})",
            () -> "Order event fired and failed (Order Id={}, data={})",
            payload.getReservationRef(),
            payload.toString()));
  }

  private ListenableFutureCallback<SendResult<String, Object>> getCallback(
      final Supplier<String> successMessage,
      final Supplier<String> failureMessage,
      final String reservationId,
      final String status) {
    return new ListenableFutureCallback<SendResult<String, Object>>() {
      @Override
      public void onSuccess(final SendResult<String, Object> result) {
        log.info(successMessage.get(), reservationId, status);
      }

      @Override
      public void onFailure(final Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
        log.error(failureMessage.get(), reservationId, status);
      }
    };
  }
}
