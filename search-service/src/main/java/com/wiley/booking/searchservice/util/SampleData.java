package com.wiley.booking.searchservice.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wiley.booking.offer.Offer;
import com.wiley.booking.property.Address;
import com.wiley.booking.searchservice.service.SearchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/10/2021
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class SampleData {

  @Autowired private final SearchService searchService;
  @Autowired private final RestTemplate restTemplate;

  public void insertSampleData() {
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
    offer.setFrom(saved.getFromDate());
    offer.setTo(saved.getToDate());
    offer.setDiscount(0.25);
    saveOffer(offer);

    com.wiley.booking.property.Property property2 = new com.wiley.booking.property.Property();
    property2.setName("Beruwala Beach hotel");
    Address address2 = new Address();
    address2.setStreet("1st street");
    address2.setCity("Beruwala");
    address2.setCountry("Sri lanka");
    address2.setPostcode("200232");
    property2.setAddress(address2);
    property2.setAvailable(true);
    property2.setEmail("bbh@amayahotels.com");
    property2.setFromDate(Instant.now());
    property2.setToDate(Instant.now().plus(10, ChronoUnit.DAYS));
    property2.setHotlineMobile("0714123456");
    property2.setPrice(55.99);

    final com.wiley.booking.property.Property saved2 = searchService.save(property2);
    Offer offer2 = new Offer();
    offer2.setPropertyId(saved2.getId());
    offer2.setFrom(saved2.getFromDate());
    offer2.setTo(saved2.getToDate());
    offer2.setDiscount(0.35);
    saveOffer(offer);

    com.wiley.booking.property.Property property3 = new com.wiley.booking.property.Property();
    property2.setName("Nuwara Eliya hotel");
    Address address3 = new Address();
    address3.setStreet("Hill state");
    address3.setCity("Nuwara Eliya");
    address3.setCountry("Sri lanka");
    address3.setPostcode("400232");
    property3.setAddress(address3);
    property3.setAvailable(true);
    property3.setEmail("neh@amayahotels.com");
    property3.setFromDate(Instant.now());
    property3.setToDate(Instant.now().plus(5, ChronoUnit.DAYS));
    property3.setHotlineMobile("0714123456");
    property3.setPrice(150.99);

    final com.wiley.booking.property.Property saved3 = searchService.save(property3);
    Offer offer3 = new Offer();
    offer3.setPropertyId(saved3.getId());
    offer3.setFrom(saved3.getFromDate());
    offer3.setTo(saved3.getToDate());
    offer3.setDiscount(0.15);
    saveOffer(offer3);

    com.wiley.booking.property.Property property4 = new com.wiley.booking.property.Property();
    property2.setName("Shangri La Hambanthota");
    Address address4 = new Address();
    address4.setStreet("beach road");
    address4.setCity("Hambanthota");
    address4.setCountry("Sri lanka");
    address4.setPostcode("4002456");
    property4.setAddress(address4);
    property4.setAvailable(true);
    property4.setEmail("slh@shangila.com");
    property4.setFromDate(Instant.now());
    property4.setToDate(Instant.now().plus(15, ChronoUnit.DAYS));
    property4.setHotlineMobile("0714123456");
    property4.setPrice(300.99);

    final com.wiley.booking.property.Property saved4 = searchService.save(property4);
    Offer offer4 = new Offer();
    offer4.setPropertyId(saved4.getId());
    offer4.setFrom(saved4.getFromDate());
    offer4.setTo(saved4.getToDate());
    offer4.setDiscount(0.55);
    saveOffer(offer4);

    com.wiley.booking.property.Property property5 = new com.wiley.booking.property.Property();
    property2.setName("Shangri La Hambanthota - 2");
    Address address5 = new Address();
    address5.setStreet("beach road");
    address5.setCity("Hambanthota");
    address5.setCountry("Sri lanka");
    address5.setPostcode("4002456");
    property5.setAddress(address5);
    property5.setAvailable(true);
    property5.setEmail("slh@shangila.com");
    property5.setFromDate(Instant.now());
    property5.setToDate(Instant.now().plus(15, ChronoUnit.DAYS));
    property5.setHotlineMobile("0714123456");
    property5.setPrice(300.99);

    final com.wiley.booking.property.Property saved5 = searchService.save(property5);
    Offer offer5 = new Offer();
    offer5.setPropertyId(saved5.getId());
    offer5.setFrom(saved5.getFromDate());
    offer5.setTo(saved5.getToDate());
    offer5.setDiscount(0.55);
    saveOffer(offer5);

    com.wiley.booking.property.Property property6 = new com.wiley.booking.property.Property();
    property1.setName("Seegiriya hotel");
    Address address6 = new Address();
    address6.setStreet("1st street");
    address6.setCity("Anuradhapura");
    address6.setCountry("Sri lanka");
    address6.setPostcode("100001");
    property6.setAddress(address6);
    property6.setAvailable(true);
    property6.setEmail("seegiriya@seegiriya.com");
    property6.setFromDate(Instant.now());
    property6.setToDate(Instant.now().plus(5, ChronoUnit.DAYS));
    property6.setHotlineMobile("0714123456");
    property6.setPrice(666.99);

    final com.wiley.booking.property.Property saved6 = searchService.save(property6);
    Offer offer6 = new Offer();
    offer6.setPropertyId(saved6.getId());
    offer6.setFrom(saved6.getFromDate());
    offer6.setTo(saved6.getToDate());
    offer6.setDiscount(0.35);
    saveOffer(offer6);

    com.wiley.booking.property.Property property7 = new com.wiley.booking.property.Property();
    property1.setName("Seegiriya hotel-2");
    Address address7 = new Address();
    address7.setStreet("1st street");
    address7.setCity("Anuradhapura");
    address7.setCountry("Sri lanka");
    address7.setPostcode("100022");
    property7.setAddress(address7);
    property7.setAvailable(true);
    property7.setEmail("seegiriya@seegiriya.com");
    property7.setFromDate(Instant.now());
    property7.setToDate(Instant.now().plus(5, ChronoUnit.DAYS));
    property7.setHotlineMobile("0714123456");
    property7.setPrice(453.99);

    final com.wiley.booking.property.Property saved7 = searchService.save(property7);
    Offer offer7 = new Offer();
    offer7.setPropertyId(saved7.getId());
    offer7.setFrom(saved7.getFromDate());
    offer7.setTo(saved7.getToDate());
    offer7.setDiscount(0.35);
    saveOffer(offer7);

    com.wiley.booking.property.Property property8 = new com.wiley.booking.property.Property();
    property1.setName("Seegiriya hotel-2");
    Address address8 = new Address();
    address8.setStreet("1st street");
    address8.setCity("Anuradhapura");
    address8.setCountry("Sri lanka");
    address8.setPostcode("100022");
    property8.setAddress(address8);
    property8.setAvailable(true);
    property8.setEmail("seegiriya@seegiriya.com");
    property8.setFromDate(Instant.now());
    property8.setToDate(Instant.now().plus(5, ChronoUnit.DAYS));
    property8.setHotlineMobile("0714123456");
    property8.setPrice(453.99);

    final com.wiley.booking.property.Property saved8 = searchService.save(property8);
    Offer offer8 = new Offer();
    offer8.setPropertyId(saved8.getId());
    offer8.setFrom(saved8.getFromDate());
    offer8.setTo(saved8.getToDate());
    offer8.setDiscount(0.35);
    saveOffer(offer8);

    com.wiley.booking.property.Property property9 = new com.wiley.booking.property.Property();
    property2.setName("Nuwara Eliya hotel-2");
    Address address9 = new Address();
    address9.setStreet("Hill state");
    address9.setCity("Nuwara Eliya");
    address9.setCountry("Sri lanka");
    address9.setPostcode("400232");
    property9.setAddress(address9);
    property9.setAvailable(true);
    property9.setEmail("neh@amayahotels.com");
    property9.setFromDate(Instant.now());
    property9.setToDate(Instant.now().plus(5, ChronoUnit.DAYS));
    property9.setHotlineMobile("0714123456");
    property9.setPrice(150.99);

    final com.wiley.booking.property.Property saved9 = searchService.save(property9);
    Offer offer9 = new Offer();
    offer9.setPropertyId(saved9.getId());
    offer9.setFrom(saved9.getFromDate());
    offer9.setTo(saved9.getToDate());
    offer9.setDiscount(0.15);
    saveOffer(offer9);
  }

  public void saveOffer(final Offer offer) {
    Offer savedOffer = restTemplate.postForObject("http://offer/offer/save", offer, Offer.class);
    log.info("offer test data saved: {}", offer.toString());
  }
}
