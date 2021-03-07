package com.wiley.booking.paymentservice.service;

import com.wiley.booking.payment.Payment;
import com.wiley.booking.payment.PaymentRequest;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
public interface PaymentService {

  Payment pay(final PaymentRequest paymentRequest);
}
