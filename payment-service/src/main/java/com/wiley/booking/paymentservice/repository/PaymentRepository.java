package com.wiley.booking.paymentservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.wiley.booking.payment.Payment;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
public interface PaymentRepository extends CrudRepository<Payment, String> {

}
