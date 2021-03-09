package com.wiley.booking.offerservice.controller;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiley.booking.offer.Offer;
import com.wiley.booking.offerservice.service.OfferService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/8/2021
 */
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/offer")
public class OfferController {

  private final OfferService offerService;

  @GetMapping()
  public ResponseEntity<Iterable<Offer>> getOffers()
  {
    return ResponseEntity.ok(offerService.getAll());
  }
  @PostMapping("/getoffers")
  public ResponseEntity<List<Offer>> getOffers(
      @RequestBody(required = true) final OfferRequest offerRequest) {
    if (offerRequest.getPropertyIds() != null && !offerRequest.getPropertyIds().isEmpty()) {
      return ResponseEntity.ok(
          offerService.getByDateRangeAndIds(
              offerRequest.getPropertyIds(),
              Instant.ofEpochMilli(offerRequest.getFrom()),
              Instant.ofEpochMilli(offerRequest.getTo())));
    }
    return ResponseEntity.ok(Collections.emptyList());
  }

  @PostMapping("/save")
  public ResponseEntity<Offer> saveOffer(@RequestBody(required = true) final Offer offer)
  {
    return ResponseEntity.ok(offerService.save(offer));
  }
}
