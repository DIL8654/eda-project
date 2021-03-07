package com.wiley.booking.paymentservice.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.wiley.booking.payment.Payment;
import com.wiley.booking.payment.PaymentRequest;
import com.wiley.booking.payment.PaymentStatus;
import com.wiley.booking.paymentservice.service.mapper.PaymentMapper;
import com.wiley.booking.paymentservice.util.PaymentRefGenerator;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class PaymentGatewayService {

  private final PaymentMapper mapper;

  private static final String INVALID_CVV = "000";

  Payment pay(final PaymentRequest paymentRequest) {
    Payment payment = mapper.map(paymentRequest);
    if (INVALID_CVV.equals(paymentRequest.getCvv())) {
      payment.setPaymentStatus(PaymentStatus.PAYMENT_FAILED);
      return payment;
    }
    payment.setPaymentStatus(PaymentStatus.PAYMENT_SUCCESS);
    return payment;
  }

}
