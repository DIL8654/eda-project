package com.wiley.booking.searchservice.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiley.booking.property.Address;
import com.wiley.booking.searchservice.controller.criteria.SearchCriteria;
import com.wiley.booking.searchservice.service.SearchService;
import com.wiley.booking.searchservice.service.model.Property;
import com.wiley.booking.user.UserType;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/search")
public class SearchController {

  private final SearchService searchService;

  @GetMapping("/byLocation/{location}")
  public ResponseEntity<List<Property>> findByLocation(
      @ApiParam(value = "location value ( city or country)", required = true)
          @PathVariable("location")
          final String location) {
    return ResponseEntity.ok(searchService.findByLocation(location));
  }

  @GetMapping("/byId/{id}")
  public ResponseEntity<Property> findById(
      @ApiParam(value = "location id", required = true) @PathVariable("id") final String id) {
    return ResponseEntity.ok(searchService.findById(id));
  }

  @PostMapping("/range")
  public ResponseEntity<List<Property>> findByDateRange(
      @ApiParam(value = "Properties search criteria", required = true) @RequestBody(required = true)
          final SearchCriteria searchCriteria) {
    if (searchCriteria.getLocation() != null && !searchCriteria.getLocation().isEmpty()) {
      boolean premium = UserType.PREMIUM.equals(searchCriteria.getUserType()) ? true : false;
      return ResponseEntity.ok(
          searchService.findByDateRange(
              searchCriteria.getLocation(),
              Instant.ofEpochMilli(searchCriteria.getFrom()),
              Instant.ofEpochMilli(searchCriteria.getTo()),
              premium));
    }
    return ResponseEntity.ok(
        searchService.findByDateRange(
            Instant.ofEpochMilli(searchCriteria.getFrom()),
            Instant.ofEpochMilli(searchCriteria.getTo())));
  }

  @PostMapping
  public void saveTestData() {
    com.wiley.booking.property.Property property1 = new com.wiley.booking.property.Property();
    property1.setName("Ananthaya Resort & spa");
    Address address = new Address();
    address.setStreet("1st street");
    address.setCity("Anuradhapura");
    address.setCountry("Sri lanka");
    address.setPostcode("100001");
    property1.setAddress(address);
    property1.setAvailable(true);
    property1.setDiscountRate(0.25);
    property1.setEmail("ananthaya@ananthaya.com");
    property1.setFromDate(Instant.now());
    property1.setToDate(Instant.now().plus(10, ChronoUnit.DAYS));
    property1.setHotlineMobile("0714123456");
    property1.setPrice(999.99);

    searchService.save(property1);
  }
}
