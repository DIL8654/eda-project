package com.wiley.booking.paymentservice.kafka.producer;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.wiley.booking.payment.Payment;

import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Log4j2
@Component
public class KafkaProducer {

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;

  @Transactional
  public void send(String topic, Payment payload) {
    log.info("sending payload='{}' to topic='{}'", payload.toString(), topic);

    final ListenableFuture<SendResult<String, Object>> listenableFuture =
        kafkaTemplate.executeInTransaction(
            operations -> operations.send(topic, payload.getPaymentRef(), payload));

    listenableFuture.addCallback(
        getCallback(
            () -> "Payment event fired successfully (Payment Id={}, data={})",
            () -> "Payment event fired and failed (Payment Id={}, data={})",
            payload.getPaymentRef(),
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
