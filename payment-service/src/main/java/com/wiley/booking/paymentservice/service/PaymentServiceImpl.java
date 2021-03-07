package com.wiley.booking.paymentservice.service;

import java.time.Instant;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;
import com.wiley.booking.order.Booking;
import com.wiley.booking.payment.Payment;
import com.wiley.booking.payment.PaymentRequest;
import com.wiley.booking.payment.PaymentStatus;
import com.wiley.booking.paymentservice.kafka.config.KafkaTopicConfig;
import com.wiley.booking.paymentservice.kafka.producer.KafkaProducer;
import com.wiley.booking.paymentservice.repository.PaymentRepository;
import com.wiley.booking.paymentservice.service.mapper.PaymentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentGatewayService paymentGatewayService;
  private final KafkaProducer kafkaProducer;
  private final KafkaTopicConfig kafkaTopicConfig;

  @Override
  public Payment pay(PaymentRequest paymentRequest) {
    Payment payment = paymentRepository.save(paymentGatewayService.pay(paymentRequest));
    log.info("payment save. payment: {}", payment.toString());

    kafkaProducer.send(kafkaTopicConfig.getPaymenttopic(), payment);

    return payment;
  }
}
