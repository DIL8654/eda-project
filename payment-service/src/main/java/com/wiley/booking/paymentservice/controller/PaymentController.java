package com.wiley.booking.paymentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiley.booking.payment.Payment;
import com.wiley.booking.payment.PaymentRequest;
import com.wiley.booking.paymentservice.service.PaymentService;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping("/pay")
  public ResponseEntity<Payment> pay(
      @ApiParam(value = "paymentRequest criteria", required = true) @RequestBody(required = true)
          final PaymentRequest paymentRequest) {

    return ResponseEntity.ok(paymentService.pay(paymentRequest));
  }
}
