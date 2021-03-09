package com.wiley.booking.offerservice.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.wiley.booking.offer.Offer;

/**
 * @author Dilanka
 * @create at 3/8/2021
 */
public interface OfferService {

  Optional<Offer> getById(final String id);

  Iterable<Offer> getAll();

  List<Offer> getByDateRangeAndPropertyId(
      final String propertyId, final Instant from, final Instant to);

  List<Offer> getByDateRange(final Instant from, final Instant to);

  List<Offer> getByDateRangeAndIds(
      final List<String> propertyIds, final Instant from, final Instant to);

  Offer save(final Offer offer);
}
