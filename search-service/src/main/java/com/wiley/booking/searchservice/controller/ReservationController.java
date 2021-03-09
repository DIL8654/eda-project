package com.wiley.booking.searchservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiley.booking.order.Order;
import com.wiley.booking.searchservice.reservation.ReservationService;

import lombok.RequiredArgsConstructor;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/reservation")
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping("/create")
  public ResponseEntity<Order> generateReservation(
      @RequestBody(required = true) final Order order) {

    return ResponseEntity.ok(reservationService.createReservation(order));
  }

}
