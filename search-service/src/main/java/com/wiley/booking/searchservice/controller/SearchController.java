package com.wiley.booking.searchservice.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.wiley.booking.offer.Offer;
import com.wiley.booking.offer.OfferRequest;
import com.wiley.booking.property.Address;
import com.wiley.booking.searchservice.controller.criteria.SearchCriteria;
import com.wiley.booking.searchservice.service.AuthService;
import com.wiley.booking.searchservice.service.PropertyMapper;
import com.wiley.booking.searchservice.service.SearchService;
import com.wiley.booking.searchservice.service.ServiceFacade;
import com.wiley.booking.searchservice.service.model.Property;

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
  private final RestTemplate restTemplate;
  private final ServiceFacade serviceFacade;
  private final AuthService authService;
  private final PropertyMapper propertyMapper;

  @GetMapping("/allLocations")
  public ResponseEntity<List<String>> getAllLocations() {
    return ResponseEntity.ok(searchService.getAllLocations());
  }

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
      final HttpServletRequest request,
      @RequestBody(required = true) final SearchCriteria searchCriteria)
      throws ExecutionException, InterruptedException {

    if (searchCriteria.getLocation() != null && !searchCriteria.getLocation().isEmpty()) {
      boolean isPremium = authService.isPremiumUser(request);

      List<Property> propertiesWithoutDiscount =
          searchService.findByDateRange(
              searchCriteria.getLocation(),
              Instant.ofEpochMilli(searchCriteria.getFrom()),
              Instant.ofEpochMilli(searchCriteria.getTo()));

      if (isPremium) {
        if (propertiesWithoutDiscount != null && !propertiesWithoutDiscount.isEmpty()) {
          List<String> propertyIds = new ArrayList<>();
          List<Offer> offers = null;
          for (Property property : propertiesWithoutDiscount) {
            propertyIds.add(property.getId());
          }
          if (propertyIds != null && !propertyIds.isEmpty()) {
            OfferRequest offerRequest = new OfferRequest();
            offerRequest.setPropertyIds(propertyIds);
            offerRequest.setFrom(searchCriteria.getFrom());
            offerRequest.setTo(searchCriteria.getTo());
            offers = serviceFacade.getOffers(offerRequest);
          }
          return ResponseEntity.ok(
              propertyMapper.calculateDiscounts(propertiesWithoutDiscount, offers));
        }
      }

      return ResponseEntity.ok(propertiesWithoutDiscount);
    }
    return ResponseEntity.ok(
        searchService.findByDateRange(
            Instant.ofEpochMilli(searchCriteria.getFrom()),
            Instant.ofEpochMilli(searchCriteria.getTo())));
  }

  @PostMapping
  public void saveTestData(HttpServletRequest request) {

    com.wiley.booking.property.Property property1 = new com.wiley.booking.property.Property();
    property1.setName("Ananthaya Resort & spa");
    Address address = new Address();
    address.setStreet("1st street");
    address.setCity("Anuradhapura");
    address.setCountry("Sri lanka");
    address.setPostcode("100001");
    property1.setAddress(address);
    property1.setAvailable(true);
    property1.setEmail("ananthaya@ananthaya.com");
    property1.setFromDate(Instant.now());
    property1.setToDate(Instant.now().plus(10, ChronoUnit.DAYS));
    property1.setHotlineMobile("0714123456");
    property1.setPrice(999.99);

    final com.wiley.booking.property.Property saved = searchService.save(property1);
    Offer offer = new Offer();
    offer.setPropertyId(saved.getId());
    offer.setFrom(property1.getFromDate());
    offer.setTo(property1.getToDate());
    offer.setDiscount(0.25);
    saveOffer(offer);
  }

  public void saveOffer(final Offer offer) {
    Offer savedOffer = restTemplate.postForObject("http://offer/offer/save", offer, Offer.class);
    log.info("offer test data saved: {}", offer.toString());
  }
}
