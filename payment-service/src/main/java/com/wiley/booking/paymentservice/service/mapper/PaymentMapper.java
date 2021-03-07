package com.wiley.booking.paymentservice.service.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.wiley.booking.payment.Payment;
import com.wiley.booking.payment.PaymentRequest;
import com.wiley.booking.paymentservice.util.PaymentRefGenerator;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */

@Component
public class PaymentMapper {

  public Payment map(final PaymentRequest paymentRequest) {
    Payment payment = new Payment();
    payment.setAmount(paymentRequest.getPaymentAmount());
    payment.setBookingRef(paymentRequest.getBookingRef());
    payment.setCreatedDate(Instant.now());
    payment.setLast4Digits(
        paymentRequest
            .getCardNumber()
            .substring(
                paymentRequest.getCardNumber().length() - 4));
    payment.setUserRef(paymentRequest.getUserId());
    payment.setBookingRef(paymentRequest.getBookingRef());
    payment.setPaymentRef(PaymentRefGenerator.generateReservationRef());
    return payment;
  }
}
