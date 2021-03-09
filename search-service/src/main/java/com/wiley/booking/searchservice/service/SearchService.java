package com.wiley.booking.searchservice.service;

import java.time.Instant;
import java.util.List;

import com.wiley.booking.property.Property;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
public interface SearchService {

  List<com.wiley.booking.searchservice.service.model.Property> findByLocation(
      final String location);

  com.wiley.booking.searchservice.service.model.Property findById(final String id);

  List<com.wiley.booking.searchservice.service.model.Property> findByDateRange(
      final Instant from, final Instant to);

  List<com.wiley.booking.searchservice.service.model.Property> findByDateRange(
      String location, Instant from, Instant to);

  Property save(Property property);

  List<String> getAllLocations();
}
